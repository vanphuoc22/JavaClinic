/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.owen.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.owen.pojo.Appointment;
import com.owen.pojo.Prescription;
import com.owen.pojo.Role;
import com.owen.pojo.ScheduleDetail;
import com.owen.pojo.Shift;
import com.owen.pojo.User;
import com.owen.repository.RoleReponsitory;
import com.owen.repository.ScheduleRepository;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.owen.repository.UserRepository;
import com.owen.service.AppointmentService;
import com.owen.service.PrescriptionService;
import com.owen.service.ScheduleService;
import com.owen.service.UserService;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Trinh Bao Duy //
 */
@Service("userDetailsService")
public class UserServiceImpl implements UserService {

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleReponsitory roleRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private ScheduleService scheduleService;
    
    @Autowired
    private AppointmentService AppointmentService;
    
    @Autowired
    private PrescriptionService PrescriptionService;

    @Override
    public List<User> getUsers(Map<String, String> params) {
        return this.userRepo.getUsers(params);
    }

    @Override
    public Long countUser() {
        return this.userRepo.countUser();
    }

    @Override
    public boolean deleteUser(int id) {
        if(this.scheduleService.getScheduleDetailsByTaiKhoanfordelete(this.userRepo.getUserById(id))!= null){
            List<ScheduleDetail> ds = this.scheduleService.getScheduleDetailsByTaiKhoanfordelete(this.userRepo.getUserById(id));
            for(ScheduleDetail lich : ds){
                this.scheduleService.deleteScheduleDetail(lich.getId());
            }
        }
        if(this.userRepo.getUserById(id).getRoleId().getId() == 2){
           List<Appointment> ds = this.AppointmentService.getAppointmentsbyDoctorfordelete(this.userRepo.getUserById(id));
           for(Appointment lich : ds){
                this.AppointmentService.deleteAppo(lich.getId());
                this.PrescriptionService.deletePrescription(lich.getPrescriptionId().getId());
            }
        }
        if(this.userRepo.getUserById(id).getRoleId().getId() == 3){
           List<Appointment> ds = this.AppointmentService.getAppointmentsbyNursefordelete(this.userRepo.getUserById(id));
           for(Appointment lich : ds){
                this.AppointmentService.deleteAppo(lich.getId());
                this.PrescriptionService.deletePrescription(lich.getPrescriptionId().getId());
            }
        }
        if(this.userRepo.getUserById(id).getRoleId().getId() == 4){
           List<Appointment> ds = this.AppointmentService.getAppointmentsbySickPersonfordelete(this.userRepo.getUserById(id));
           for(Appointment lich : ds){
                this.AppointmentService.deleteAppo(lich.getId());
                this.PrescriptionService.deletePrescription(lich.getPrescriptionId().getId());
            }
        }
        return this.userRepo.deleteUser(id);
    }

    @Override
    public List<User> searchUsersByName(Map<String, String> name) {
        return this.userRepo.searchUsersByName(name);
    }

    @Override
    public boolean addOrUpdateUser(User u) {
        if (u.getId() == null) {
            String pass = u.getPassword();
            u.setPassword(this.passwordEncoder.encode(pass));
        }
        if (!u.getFile().isEmpty()) {
            try {
                Map r = this.cloudinary.uploader().upload(u.getFile().getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));
                String avatar = (String) r.get("secure_url");
                u.setAvatar(avatar);

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return this.userRepo.addOrUpdateUser(u);
    }

    @Override
    public boolean authUser(String username, String password) {
        User u = this.getUserByUsername(username);
        return this.passwordEncoder.matches(password, u.getPassword());
    }

    @Override
    public User getUserById(int id) {
        return this.userRepo.getUserById(id);
    }

   @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = this.userRepo.getUserByUsername(username);
        if (u == null) {
            throw new UsernameNotFoundException("Tài khoản không tồn tại!");
        }

        boolean canLogin = false;
        List<ScheduleDetail> chiTietThoiGianTrucList = this.scheduleService.getScheduleDetailsByTaiKhoan(u);
        if (chiTietThoiGianTrucList.isEmpty()) {
            canLogin = true;
        } else {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

            Date currentDate = new Date();
            Date currentTime = new Date();

            String formattedDate = formatter.format(currentDate);
            String currentTimeStr = timeFormat.format(currentTime);

            try {
                Date ngayHienTai = formatter.parse(formattedDate);
                Date gioHienTai = timeFormat.parse(currentTimeStr);

                for (ScheduleDetail chiTietThoiGianTruc : chiTietThoiGianTrucList) {

                    Shift thoiGianTruc = chiTietThoiGianTruc.getShiftId();

                    Date startTime = thoiGianTruc.getStart();
                    Date endTime = thoiGianTruc.getEnd();

                    Date ngayDkyTruc = chiTietThoiGianTruc.getDateSchedule();
                    
                    boolean check1 = ngayDkyTruc.equals(ngayHienTai);
                    boolean check2 = gioHienTai.after(startTime);
                    boolean check3 =  gioHienTai.before(endTime);
                        if (check1 == true && check2 == true && check3 == true) {
                        canLogin = true;
                        break;
                    }
                }
            } catch (ParseException ex) {
                Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, "Lỗi khi phân tích ngày tháng", ex);
            }
        }
        if (canLogin) {
            Set<GrantedAuthority> authorities = new HashSet<>();
            authorities.add(new SimpleGrantedAuthority(u.getRoleId().getName()));
            return new org.springframework.security.core.userdetails.User(
                    u.getUsername(), u.getPassword(), authorities);
        } else {
            throw new UsernameNotFoundException("Không thể đăng nhập vào lúc này!");
        }
    }

    @Override
    public User getUserByUsername(String username) {
        return this.userRepo.getUserByUsername(username);
    }

    @Override
    public List<User> getBacSi() {
        return this.userRepo.getBacSi();
    }

    @Override
    public User addUser(Map<String, String> params,
            MultipartFile avatar) {
        User u = new User();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate = null;

        try {
            parsedDate = dateFormat.parse(params.get("dod"));

        } catch (ParseException ex) {
            Logger.getLogger(UserServiceImpl.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        u.setUsername(params.get("username"));
        u.setEmaill(params.get("email"));
        u.setPhone(params.get("phone"));
        u.setName(params.get("name"));
        u.setAddress(params.get("address"));
        u.setPassword(this.passwordEncoder.encode(params.get("password")));
        u.setSex(params.get("sex"));
        u.setDod(parsedDate);
//        u.setRoleId(this.roleRepo.getRoleById(4));
        List<Role> listRole = this.roleRepo.getRoles();
        String role = params.get("role");
        for (Role r : listRole) {
            if (role.equals("ROLE_SICKPERSON")) {
                if (r.getId() == 4) {
                    u.setRoleId(r);
                }
            }
//
        }

        if (!avatar.isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(avatar.getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));
                u.setAvatar(res.get("secure_url").toString());
            } catch (IOException ex) {
                System.err.println("ERROR");
            }
        }

        this.userRepo.addUser(u);
        return u;
    }

    @Override
    public List<User> getBacSi(int id) {
        return this.userRepo.getBacSi(id);
    }

    @Override
    public User getUserByEmail(String mail) {
        return this.userRepo.getUserByEmail(mail);
    }

    @Override
    public boolean changePassword(User user, String newPassword) {
        return this.userRepo.changePassword(user, newPassword);
    }

    @Override
    public User registerUserGoogle(Map<String, String> params) {
        if(this.userRepo.getUsersByUsername(params.get("username"))== null){
            return this.userRepo.registerUserGoogle(params);
        }else{
            return this.getUserByUsername(params.get("username"));
        }
        
    }

}

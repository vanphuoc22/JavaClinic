/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.owen.controllers;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.owen.pojo.Appointment;
import com.owen.pojo.Bill;
import com.owen.pojo.Medicine;
import com.owen.pojo.Prescription;
import com.owen.pojo.PrescriptionItem;
import com.owen.pojo.ScheduleDetail;
import com.owen.pojo.ServiceItems;
import com.owen.pojo.User;
import com.owen.service.AppointmentService;
import com.owen.service.BillService;
import com.owen.service.MedicineService;
import com.owen.service.PrescriptionItemService;
import com.owen.service.PrescriptionService;
import com.owen.service.ScheduleService;
import com.owen.service.ServiceItemService;
import com.owen.service.ServiceService;
import com.owen.service.ShiftService;
import com.owen.service.UserService;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author hung
 */
@Controller
public class DoctorController {

    @Autowired
    private UserService userService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private MedicineService medicineService;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private ServiceItemService serviceItemService;

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private PrescriptionService prescriptionService;

    @Autowired
    private PrescriptionItemService prescriptionItemService;

    @Autowired
    private ShiftService shiftService;

    @Autowired
    private CustomDateEditor customDateEditor;

    @Autowired
    private BillService billService;
    @Autowired
    private Environment env;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, customDateEditor);
    }

    @GetMapping("/doctor/xemlichkham")
    public String xemlichkham() {
        return "xemlichkham";
    }

    @ModelAttribute("currentDateTime")
    public LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now();
    }

    @GetMapping("/doctor")
    public String doctorInfor(Model model, Authentication authentication) {
        model.addAttribute("doctor", new User());
        if (authentication != null) {
            UserDetails user = this.userService.loadUserByUsername(authentication.getName());
            User u = this.userService.getUserByUsername(user.getUsername());
            model.addAttribute("doctor", u);
//            model.addAttribute("lichkham", this.appointmentService.getAppointmentsbyDoctor(u));
//            model.addAttribute("lichkham", this.appointmentService.getAppointmentServiceByDoctor(u));
            model.addAttribute("lichkham", this.appointmentService.getAppointmentsbyDoctor(u));

        }
        return "doctor";
    }

    @GetMapping("/doctor/khambenh/{id}")
    public String khambenh(Model model, @PathVariable(value = "id") int id) {
        model.addAttribute("chitietdichvu", new ServiceItems());
        model.addAttribute("phieubenh", new Prescription());
        model.addAttribute("getmediciens", this.medicineService.getMediciness(null));
        model.addAttribute("appo", this.appointmentService.getAppointmentById(id));
        model.addAttribute("dichvu", this.serviceService.getServices(null));
        return "khambenh";
    }

    @PostMapping("/doctor/khambenh")
    public String Updatekhambenh(Model mode, @ModelAttribute(value = "phieubenh") @Valid Prescription p, @ModelAttribute(value = "chitietdichvu") @Valid ServiceItems chitietdichvu, @RequestParam(value = "appoID") int appoId,
            BindingResult rs) {
        if (!rs.hasErrors()) {
            if (this.serviceItemService.addOrUpdateServiceItem(chitietdichvu, appoId) == true) {
                if (this.prescriptionService.addOrUpdatePrescription(p, appoId) == true) {
                    return "redirect:/doctor/khambenh/kethuoc/" + appoId;
                }
            }
        }
        return "index";

    }

    @PostMapping("/doctor/khambenh/dichvu")
    public String Updatekhambenhtest(Model mode, @ModelAttribute(value = "chitietdichvu") @Valid ServiceItems chitietdichvu, @RequestParam(value = "appoID") int appoId,
            BindingResult rs) {
        if (!rs.hasErrors()) {
            if (this.serviceItemService.addOrUpdateServiceItem(chitietdichvu, appoId) == true) {
//                if (this.prescriptionService.addOrUpdatePrescription(p, appoId) == true) {
                return "redirect:/doctor/khambenh/" + appoId;
            }
//            }
        }
        return "index";

    }

    @GetMapping("/doctor/khambenh/kethuoc")
    public String kethuoc(Model model, @RequestParam Map<String, String> params
    ) {
        model.addAttribute("getmediciens", this.medicineService.getMediciness(params));
        model.addAttribute("phieuthuoc", new PrescriptionItem());
        return "kethuoc";
    }

    @GetMapping("/doctor/khambenh/kethuoc/{id}")
    public String kethuocid(Model model, Authentication authentication,
            @RequestParam Map<String, String> params, @PathVariable(value = "id") int id,
            @RequestParam(name = "msg", required = false) String msg
    ) {
        model.addAttribute("msg", msg);
        model.addAttribute("hoadonmoi", new Bill());
        model.addAttribute("getmediciens", this.medicineService.getMediciness(params));
        model.addAttribute("appo", this.appointmentService.getAppointmentById(id));
        model.addAttribute("phieuthuoc", new PrescriptionItem());
        Appointment a = this.appointmentService.getAppointmentById(id);
        model.addAttribute("dsthuoc", this.prescriptionItemService.getPrescriptionsbyIDPres(a.getPrescriptionId().getId()));
         if (authentication != null) {
            UserDetails user = this.userService.loadUserByUsername(authentication.getName());
            User u = this.userService.getUserByUsername(user.getUsername());
            model.addAttribute("doctor", u);
         }
        return "kethuoc";
    }

    @PostMapping("/doctor/khambenh/kethuoc")
    public String update(@ModelAttribute(value = "phieuthuoc")
            @Valid PrescriptionItem phieuthuoc, @RequestParam(value = "PreId") int id,
            BindingResult rs
    ) throws UnsupportedEncodingException {
        String msg = "";
        if (phieuthuoc.getQuantity() != null && phieuthuoc.getInstructions() != null) {
            if (!rs.hasErrors()) {
                if (this.prescriptionItemService.addOrUpdatePrescriptionItem(phieuthuoc, id) == true) {
                    Medicine medicine = this.medicineService.getMedicineById(phieuthuoc.getMedicineId().getId());
                    int soluongthuoc = medicine.getQuantity();
                    int soluongban = phieuthuoc.getQuantity();
                    int soluongupdate = soluongthuoc - soluongban;
                    medicine.setQuantity(soluongupdate);
                    this.medicineService.addOrUpdateMedicine(medicine); //
                    return "redirect:/doctor/khambenh/kethuoc/" + id;
                }
            }
            msg = "BindingResult lỗi rồi bạn ơi";
            return "redirect:/doctor/khambenh/kethuoc/" + id + "?msg=" + URLEncoder.encode(msg, "UTF-8");
        }else{
            msg = "Nhập thiếu dữ liệu khi kê thuốc cho bệnh nhân";
            return "redirect:/doctor/khambenh/kethuoc/" + id + "?msg=" + URLEncoder.encode(msg, "UTF-8");
        }
    }

    @PostMapping("/doctor/khambenh/kethuoc/taohoahon")
    public String taohoadon(@ModelAttribute(value = "hoadonmoi")
            @Valid Bill m,
            BindingResult rs
    ) {
        if (!rs.hasErrors()) {
            if (this.billService.addOrUpdateBill(m) == true) {
                return "redirect:/doctor";
            }
        }
        return "doctor";
    }

    @GetMapping("/doctor/lichsukham/{id}")
    public String lichsukham(Model model, @PathVariable(value = "id") int id, Authentication authentication
    ) {
        if (authentication != null) {
            UserDetails user = this.userService.loadUserByUsername(authentication.getName());
            User u = this.userService.getUserByUsername(user.getUsername());
            model.addAttribute("doctor", u);

        }
        model.addAttribute("benhnhan", this.userService.getUserById(id));
        model.addAttribute("lishsubenh", this.appointmentService.getAppointmentsbyUser(this.userService.getUserById(id), null));

        return "lichsukham";
    }

    @PostMapping("/doctor/lichsukham/{id}")
    public String lichsukhamxuli(Model model, @PathVariable(value = "id") int id, Authentication authentication, @RequestParam(value = "date") Date date
    ) {
        if (authentication != null) {
            UserDetails user = this.userService.loadUserByUsername(authentication.getName());
            User u = this.userService.getUserByUsername(user.getUsername());
            model.addAttribute("doctor", u);

        }
        model.addAttribute("benhnhan", this.userService.getUserById(id));
        model.addAttribute("lishsubenh", this.appointmentService.getAppointmentsbyUser(this.userService.getUserById(id), date));

        return "lichsukham";
    }

    @GetMapping("/doctor/khambenh/kethuoc/export/{id}")
    public void exportPDF(HttpServletResponse response, @PathVariable(value = "id") int id) {
        // Lấy thông tin và dữ liệu cần thiết từ dịch vụ và nguồn dữ liệu của bạn
        Appointment a = this.appointmentService.getAppointmentById(id);
        String tenBenhnhan = a.getSickpersonId().getName();
        String tenBacsi = a.getDoctorId().getName();
        int idPre = a.getPrescriptionId().getId();
        Prescription p = this.prescriptionService.getPrescriptionById(idPre);
        String chuanDoan = p.getSymptom();
        List<PrescriptionItem> thuoc = this.prescriptionItemService.getPrescriptionsbyIDPres(idPre);

        try {
            // Tạo một đối tượng Document
            Document document = new Document();

            // Tạo một đối tượng PdfWriter để ghi dữ liệu vào tài liệu PDF
            PdfWriter.getInstance(document, response.getOutputStream());

            // Thiết lập tên tệp PDF đầu ra
            response.setHeader("Content-Disposition", "attachment; filename=\"Donthuoc.pdf\"");

            // Mở tài liệu
            document.open();

            // Tạo và định dạng các phần tử trong tài liệu
            Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
            Font headerFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
            Font contentFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
//             BaseFont unicodeFont = BaseFont.createFont("path/to/unicode/font.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
//            Font unicodeContentFont = new Font(unicodeFont, 12, Font.NORMAL);

            // Tiêu đề
            Paragraph title = new Paragraph("PHONG MACH PISCEL", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            // Thông tin bệnh nhân
            Paragraph patientInfo = new Paragraph("Benh nhan: " + tenBenhnhan, headerFont);
            document.add(patientInfo);

            // Thông tin bác sĩ
            Paragraph doctorInfo = new Paragraph("Bac si: " + tenBacsi, headerFont);
            document.add(doctorInfo);

            // Chẩn đoán
            Paragraph diagnosis = new Paragraph("Chuan doan: " + chuanDoan, headerFont);
            document.add(diagnosis);

            // Danh sách thuốc
            Paragraph prescriptionHeading = new Paragraph("Danh sach thuoc:", headerFont);
            document.add(prescriptionHeading);

            for (PrescriptionItem item : thuoc) {
                String tenThuoc = item.getMedicineId().getName();
                String huongDan = item.getInstructions();
                Paragraph medication = new Paragraph("- " + tenThuoc + ": " + huongDan, contentFont);
                document.add(medication);
            }

            // Đóng tài liệu
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/doctor/dangkylam")
    public String dangkylam(Model model, Authentication authentication) {

        List<Date> dateList = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); // Đặt ngày là thứ Hai
        calendar.add(Calendar.WEEK_OF_YEAR, 1);
        dateList.add(calendar.getTime()); // Thêm ngày thứ Hai gần nhất vào danh sách
        for (int i = 0; i < 6; i++) { // Thêm các ngày từ thứ Ba đến Chủ nhật
            calendar.add(Calendar.DAY_OF_WEEK, 1);
            dateList.add(calendar.getTime());
        }
        model.addAttribute("dateList", dateList);
//        

//        List<ScheduleDetail> scheduleDetails = new ArrayList<ScheduleDetail>();
        model.addAttribute("lichlam", new ScheduleDetail());
        model.addAttribute("lich", this.shiftService.getShifts());
        if (authentication != null) {
            UserDetails user = this.userService.loadUserByUsername(authentication.getName());
            User u = this.userService.getUserByUsername(user.getUsername());
            model.addAttribute("doctor", u);
            model.addAttribute("lichhientaica1", this.scheduleService.getScheduleofUser(u, dateList, 1));
            model.addAttribute("lichhientaica2", this.scheduleService.getScheduleofUser(u, dateList, 2));
            model.addAttribute("lichhientaica3", this.scheduleService.getScheduleofUser(u, dateList, 3));

        }
        return "dangkylam";
    }

    @PostMapping("/doctor/dangkylam")
    public String update(@ModelAttribute(value = "lichlam") @Valid ScheduleDetail scheduleDetail,
            BindingResult rs) {
        if (!rs.hasErrors()) {
            if (this.scheduleService.addOrUpdateScheduleDetail(scheduleDetail) == true) {
                return "redirect:/doctor/dangkylam";
            }
        }
        return "dangkylam";
    }

    @GetMapping("/doctor/xemlichlam")
    public String xemlichlam(Model model, Authentication authentication) {
        List<Date> dateListnow = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); // Đặt ngày là thứ Hai của tuần hiện tại

        for (int i = 0; i < 7; i++) { // Thêm các ngày từ thứ Hai đến Chủ nhật
            dateListnow.add(calendar.getTime());
            calendar.add(Calendar.DAY_OF_WEEK, 1);
        }

        List<Date> dateList = new ArrayList<>();
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); // Đặt ngày là thứ Hai
        calendar2.add(Calendar.WEEK_OF_YEAR, 1);
        dateList.add(calendar2.getTime()); // Thêm ngày thứ Hai gần nhất vào danh sách
        for (int i = 0; i < 6; i++) { // Thêm các ngày từ thứ Ba đến Chủ nhật
            calendar2.add(Calendar.DAY_OF_WEEK, 1);
            dateList.add(calendar2.getTime());
        }
        model.addAttribute("dateList", dateList);

//        List<ScheduleDetail> scheduleDetails = new ArrayList<ScheduleDetail>();
        model.addAttribute("lichlam", new ScheduleDetail());
        model.addAttribute("lich", this.shiftService.getShifts());

        if (authentication != null) {
            UserDetails user = this.userService.loadUserByUsername(authentication.getName());
            User u = this.userService.getUserByUsername(user.getUsername());
            model.addAttribute("doctor", u);
            model.addAttribute("lichhientaica", this.scheduleService.getSchedulesofUser(u, dateList));
            model.addAttribute("lichhientai", this.scheduleService.getScheduleNowofUser(u, dateListnow));
        }

        return "xemlichlam";
    }

    @GetMapping("/doctor/xemlichlam/huy/{id}")
    public String huylichlam(Model model, Authentication authentication, @PathVariable(value = "id") int id) {

        List<Date> dateListnow = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); // Đặt ngày là thứ Hai của tuần hiện tại

        for (int i = 0; i < 7; i++) { // Thêm các ngày từ thứ Hai đến Chủ nhật
            dateListnow.add(calendar.getTime());
            calendar.add(Calendar.DAY_OF_WEEK, 1);
        }

        List<Date> dateList = new ArrayList<>();
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); // Đặt ngày là thứ Hai
        calendar2.add(Calendar.WEEK_OF_YEAR, 1);
        dateList.add(calendar2.getTime()); // Thêm ngày thứ Hai gần nhất vào danh sách
        for (int i = 0; i < 6; i++) { // Thêm các ngày từ thứ Ba đến Chủ nhật
            calendar2.add(Calendar.DAY_OF_WEEK, 1);
            dateList.add(calendar2.getTime());
        }
        model.addAttribute("dateList", dateList);

//        List<ScheduleDetail> scheduleDetails = new ArrayList<ScheduleDetail>();
        model.addAttribute("lichlam", new ScheduleDetail());
        model.addAttribute("lich", this.shiftService.getShifts());

        if (authentication != null) {
            UserDetails user = this.userService.loadUserByUsername(authentication.getName());
            User u = this.userService.getUserByUsername(user.getUsername());
            model.addAttribute("doctor", u);
            model.addAttribute("lichhientaica", this.scheduleService.getSchedulesofUser(u, dateList));
            model.addAttribute("lichhientai", this.scheduleService.getScheduleNowofUser(u, dateListnow));
        }
        if (this.scheduleService.deleteScheduleDetail(id) == true) {
            return "redirect:/doctor/xemlichlam";
        }

        return "doctor";
    }

}

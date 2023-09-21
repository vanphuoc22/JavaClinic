/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.owen.controllers;

import com.owen.pojo.Appointment;
import com.owen.pojo.User;
import com.owen.service.AppointmentService;
import com.owen.service.UserService;
import java.util.Date;
import java.util.Random;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Trinh Bao Duy
 */
@Controller
public class SickPersonController {

    @Autowired
    private CustomDateEditor customDateEditor;

    @Autowired
    private Environment env;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, customDateEditor);
    }
    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private UserService userService;

    @GetMapping("/user/booking")
    public String doctorInfor(Model model, Authentication authentication) {
        model.addAttribute("phieukhammoi", new Appointment());
        if (authentication != null) {
            UserDetails user = this.userService.loadUserByUsername(authentication.getName());
            User u = this.userService.getUserByUsername(user.getUsername());
            model.addAttribute("user", u);
        }
        return "booking";
    }

    @PostMapping("/user/booking")
    public String Update(Model model, Authentication authentication, @ModelAttribute(value = "phieukhammoi") @Valid Appointment a,
            BindingResult rs) {
        if (!rs.hasErrors()) {
            if (this.appointmentService.addOrUpdateAppointment(a) == true) {
                return "redirect:/home";
            } else {
                System.err.println(rs.getObjectName());
            }
        }
        return "booking";
    }

    @GetMapping("/otp")
    public String otp() {

        return "otp";
    }

    @PostMapping("/forgot-password")
    public String sendOTP(@RequestParam("email") String email, Model model, HttpSession session) throws MessagingException {
        // Tạo và gửi mã OTP đến địa chỉ email
        String otp = String.valueOf(100000 + new Random().nextInt(900000));
        // Lưu mã OTP trong session để sử dụng trong bước xác minh
        session.setAttribute("otp", otp);
        session.setAttribute("emaill", email);
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        System.err.println(email);
        helper.setTo(email);
        helper.setSubject("LỊCH HẸN PHÒNG MẠCH");
        String content = "<html><body>"
                + "<p>Mã OTP của bạn là: " + otp + "</p>"
                + "<p>Vui long không chia sẽ mã này với bất kì ai</p>"
                + "</body></html>";
        helper.setText(content, true);

        emailSender.send(message);
        return "xatminhotp";
    }

    @GetMapping("/otp/xatminh")
    public String xatminhotp() {

        return "xatminhotp";
    }

    @PostMapping("/verify-otp")
    public String verifyOTP(@RequestParam("otp") String otp, HttpSession session, Model model) {
        // Lấy mã OTP từ session
        String storedOTP = (String) session.getAttribute("otp");

        if (storedOTP != null && storedOTP.equals(otp)) {
            // Mã OTP hợp lệ, cho phép người dùng đặt lại mật khẩu
            model.addAttribute("message", "Mã OTP hợp lệ. Bạn có thể đặt lại mật khẩu của mình.");
            return "quenmatkhau";
        } else {
            // Mã OTP không hợp lệ, cung cấp thông báo lỗi
            model.addAttribute("error", "Mã OTP không hợp lệ. Vui lòng thử lại.");
            return "xatminhotp";
        }
    }

    @GetMapping("/quenmatkhau")
    public String quenmatkhau(HttpSession session, Model model) {
        String emailcandoi = (String) session.getAttribute("emaill");
        model.addAttribute("nguoidung", this.userService.getUserByEmail(emailcandoi));
        return "quenmatkhau";
    }

    @PostMapping("/quenmatkhau")
    public String xulimatkkhau(HttpSession session, Model model, @RequestParam("mk") String mk, @RequestParam("mka") String mka) {
        String emailcandoi = (String) session.getAttribute("emaill");
        User nguoidung = this.userService.getUserByEmail(emailcandoi);
        if (mk == mka) {
            if (this.userService.changePassword(nguoidung, mk) == true) {
                return "login";
            }
        }else {
            model.addAttribute("msg", "Nhập mật khẩu không giống nhau");
        }

        return "quenmatkhau";
    }
}

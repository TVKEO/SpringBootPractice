package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Admin;
import com.example.demo.service.AdminService;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/admin/signin")
    public String signin() {
        return "admin/signin"; 
    }

    @GetMapping("/admin/signup")
    public String showSignUpForm(Model model) {
        model.addAttribute("admin", new Admin());
        return "admin/signup"; 
    }

    @PostMapping("/admin/signup")
    public String registerAdmin(@ModelAttribute Admin admin, Model model) {
        try {
            adminService.registerNewAdmin(admin);

            model.addAttribute("message", "ユーザーが正常に登録されました。");

            return "redirect:/admin/signin";
        } catch (Exception e) {
            model.addAttribute("error", "登録に失敗しました。");
            return "admin/signup"; 
        }
    }
}
package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Admin;
import com.example.demo.repository.AdminRepository;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Admin registerNewAdmin(Admin admin) {
        // パスワードをエンコードする
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        // 管理者をデータベースに保存する
        return adminRepository.save(admin);
    }
}
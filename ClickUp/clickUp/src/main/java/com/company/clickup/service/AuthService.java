package com.company.clickup.service;

import com.company.clickup.dto.LoginDto;
import com.company.clickup.dto.RegisterDto;
import com.company.clickup.dto.Response;

public interface AuthService {
    Response registerUser(RegisterDto registerDto);
    Response login(LoginDto loginDto);
    Response verifyEmail(String email,String emailCode);
}

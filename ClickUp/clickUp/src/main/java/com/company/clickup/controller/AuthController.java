package com.company.clickup.controller;

import com.company.clickup.dto.LoginDto;
import com.company.clickup.dto.RegisterDto;
import com.company.clickup.dto.Response;
import com.company.clickup.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private AuthService authService;
@Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> addUser(@Valid @RequestBody RegisterDto registerDto){
        Response response=authService.registerUser(registerDto);
        return ResponseEntity.status(response.isSuccess()? HttpStatus.OK:HttpStatus.CONFLICT).body(response);
    }
    @PostMapping("/login")
    public ResponseEntity<Response> loginUser(@Valid @RequestBody LoginDto loginDto){
    Response response=authService.login(loginDto);
    return ResponseEntity.status(response.isSuccess()?HttpStatus.OK:HttpStatus.NOT_FOUND).body(response);
    }

    @GetMapping("/verifyEmail")
    public ResponseEntity<Response> verifyEmail(@RequestParam String email,@RequestParam String emailCode){
    Response response=authService.verifyEmail(email,emailCode);
    return ResponseEntity.status(response.isSuccess()?HttpStatus.OK:HttpStatus.NOT_FOUND).body(response);

    }
}

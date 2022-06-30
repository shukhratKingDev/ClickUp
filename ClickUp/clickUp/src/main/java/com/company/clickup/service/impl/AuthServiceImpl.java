package com.company.clickup.service.impl;

import com.company.clickup.dto.LoginDto;
import com.company.clickup.dto.RegisterDto;
import com.company.clickup.dto.Response;
import com.company.clickup.entity.User;
import com.company.clickup.entity.enums.SystemRoleName;
import com.company.clickup.repository.UserRepository;
import com.company.clickup.security.JwtProvider;
import com.company.clickup.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class AuthServiceImpl implements UserDetailsService, AuthService {
  private UserRepository userRepository;
  private PasswordEncoder passwordEncoder;
  private JavaMailSender javaMailSender;
  private AuthenticationManager authenticationManager;
  private JwtProvider jwtProvider;
  @Autowired
    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JavaMailSender javaMailSender, AuthenticationManager authenticationManager,  JwtProvider jwtProvider) {
        this.userRepository = userRepository;
      this.passwordEncoder = passwordEncoder;
      this.javaMailSender = javaMailSender;
      this.authenticationManager = authenticationManager;
      this.jwtProvider = jwtProvider;
  }

    public Response registerUser(RegisterDto registerDto) {
        if (userRepository.existsByEmail(registerDto.getEmail())) {
            return new Response("This user already exists",false);
        }
        User user=new User();
        user.setFullName(registerDto.getFullName());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setSystemRoleName(SystemRoleName.SYSTEM_ROLE_USER);
        int code=new Random().nextInt(9999999);
        user.setEmailCode(String.valueOf(code).substring(0,4));
        sendEmail(user.getEmail(),user.getEmailCode());
        userRepository.save(user);
        return new Response("User successfully saved",true);
    }

    public Response verifyEmail(String email,String emailCode){
        Optional<User> user = userRepository.findByEmailAndEmailCode(email, emailCode);
        if (!user.isPresent()) {
            return new Response("This user not found",false);
        }
        if (user.get().getEmailCode()==null) {
            return new Response("This user is already verified",false);
        }
        user.get().setEnabled(true);
        user.get().setEmailCode(null);
        userRepository.save(user.get());
        return new Response("Email successfully verified",true);
    }

    public Response login(LoginDto loginDto){
      try{
        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(),loginDto.getPassword()));
        User user=(User) authentication.getPrincipal();
        String jwt = jwtProvider.generateJwt(user.getUsername(), user.getSystemRoleName());
        return new Response("You successfully logged in",true,jwt);}
      catch (BadCredentialsException e){
          return new Response("Incorrect email or password",false);
      }
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("This user not found") );
    }

    public Boolean sendEmail(String sendingEmail,String emailCode){
        try{
            SimpleMailMessage message=new SimpleMailMessage();
            message.setFrom("shukhratDev1201@gmail.com");
            message.setTo(sendingEmail);
            message.setSubject("Verify email");
            message.setText("<h1>Verification email</h1><button><a http://localhost:8080/api/auth/verifyEmail?emailCode=" + emailCode + "&email=" + sendingEmail + "'>Verify Email</a></button>");
            javaMailSender.send(message);
            return true;
        }catch (Exception e){
            return false;
        }

    }
}

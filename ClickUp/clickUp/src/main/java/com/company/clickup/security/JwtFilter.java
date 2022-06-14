package com.company.clickup.security;

import com.company.clickup.service.AuthService;
import com.company.clickup.service.impl.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class JwtFilter extends OncePerRequestFilter {
    private JwtProvider jwtProvider;
    private AuthServiceImpl authServiceImpl;
@Autowired
    public JwtFilter(JwtProvider jwtProvider, AuthServiceImpl authServiceImp) {
        this.jwtProvider = jwtProvider;
        this.authServiceImpl = authServiceImp;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
String authorization=request.getHeader("Authorization");
        if (authorization.startsWith("Bearer")) {
            String email=jwtProvider.getEmailFromToken(authorization.substring(7));
            if (email!=null){
                UserDetails userDetails=authServiceImpl.loadUserByUsername(email);
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }


        filterChain.doFilter(request,response);
    }
}

package com.Satellite.config;

import com.Satellite.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.List;

public class JwtTokenValidator extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

//        if (request.getRequestURI().startsWith("/new")) {
//            // Skip JWT validation for this request
//            filterChain.doFilter(request, response);
//            return;
//        }
//        if (request.getRequestURI().startsWith("/auth/student/register")) {
//            // Skip JWT validation for this request
//            filterChain.doFilter(request, response);
//            return;
//        }
//        if(request.getRequestURI().startsWith("/auth/signin"))
//        {
//            filterChain.doFilter(request, response);
//            return;
//        }
        System.out.println("rfbvjh");
String jwt=request.getHeader(JwtConstant.JWT_HEADER);

if(jwt!=null) {
    jwt=jwt.substring(7);
    try {
        SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
        String username = String.valueOf(claims.get("username"));
        String authorities=String.valueOf(claims.get("authorities"));
        List<GrantedAuthority> auth= AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
        Authentication authentication=new UsernamePasswordAuthenticationToken(username,null ,auth);
        SecurityContextHolder.getContext().setAuthentication(authentication);
System.out.println("validated");
    } catch (Exception e) {
        throw new BadCredentialsException("invalid token......");
    }
}
filterChain.doFilter(request,response);
    }
}

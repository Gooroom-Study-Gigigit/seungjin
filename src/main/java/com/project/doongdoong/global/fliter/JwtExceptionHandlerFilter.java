package com.project.doongdoong.global.fliter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.doongdoong.global.common.ApiResponse;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
/*

//@Component
public class JwtExceptionHandlerFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        try {
            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException e) {
            //토큰의 유효기간 만료
            setErrorResponse(response, HttpStatus.UNAUTHORIZED, "만료된 토큰입니다.");
        } catch (JwtException | IllegalArgumentException e) {
            //유효하지 않은 토큰
            setErrorResponse(response, HttpStatus.UNAUTHORIZED, "유효하지 않은 토큰입니다.");
        }
    }
    private void setErrorResponse(
            HttpServletResponse response,
            HttpStatus status,
            String message
    ){
        ObjectMapper objectMapper = new ObjectMapper();
        response.setStatus(status.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        ApiResponse<Object> result = ApiResponse.of(status, message, null);
        try{
            response.getWriter().write(objectMapper.writeValueAsString(result));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}*/

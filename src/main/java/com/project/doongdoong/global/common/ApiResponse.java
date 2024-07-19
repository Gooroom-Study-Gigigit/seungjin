package com.project.doongdoong.global.common;

import lombok.*;
import org.springframework.http.HttpStatus;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApiResponse<T>  {
    private int code;
    private HttpStatus state;
    private String message;
    private T data;

    public ApiResponse(HttpStatus status, String message, T data) {
        this.code = status.value();
        this.state = status;
        this.message = message;
        this.data = data;
    }

    public static <T> ApiResponse<T> of(HttpStatus status, String message, T data){
        return new ApiResponse<>(status,message,data);
    }

}

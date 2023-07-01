package xyz.andornot.student.register;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse {
    private Integer code;

    public static ApiResponse empty() {
        return new ApiResponse(0);
    }

    public static ApiResponse bad(int code) {
        return new ApiResponse(code);
    }
}

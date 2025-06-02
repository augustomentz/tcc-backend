package tcc.api_cart.model;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ApiResponse<T> {
    private boolean success;
    private T data;
    private final LocalDateTime timestamp;
    private int status;

    private ApiResponse() {
        this.timestamp = LocalDateTime.now();
    }

    public static <T> ApiResponse<T> success(T data) {
        ApiResponse<T> response = new ApiResponse<>();
        response.success = true;
        response.data = data;
        response.status = 200;
        return response;
    }

    public static <T> ApiResponse<T> error(int status) {
        ApiResponse<T> response = new ApiResponse<>();
        response.success = false;
        response.status = status;
        return response;
    }

    public static <T> ApiResponse<T> notFound() {
        return error(404);
    }

    public static <T> ApiResponse<T> badRequest() {
        return error(400);
    }

    public static <T> ApiResponse<T> serverError() {
        return error(500);
    }

}
package dev.rupom.learning.springboot.webapi.advices;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;


@Data
@JsonPropertyOrder({"status", "message","reason"})
@Builder
public class ApiError {
    private HttpStatus status;
    private String message;
    private List<String> reason;
}

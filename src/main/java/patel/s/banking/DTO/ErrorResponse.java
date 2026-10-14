package patel.s.banking.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    LocalDateTime timestamp;
    int status;
    String error;
    String message;
    public ErrorResponse(int status,String error,String message){
        this.timestamp=LocalDateTime.now();
        this.status=status;
        this.error=error;
        this.message=message;
    }
}

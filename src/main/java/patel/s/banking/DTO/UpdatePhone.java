package patel.s.banking.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePhone {
    @NotBlank(message = "Phone number is required")
   @Pattern(
           message = "Invalid Phone number",
           regexp = "^[6-9]\\d{9}$"
   )
    private String phone;
}

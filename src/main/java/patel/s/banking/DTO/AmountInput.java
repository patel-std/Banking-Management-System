package patel.s.banking.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AmountInput {
    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be greater than 0")
    private double amount;
}

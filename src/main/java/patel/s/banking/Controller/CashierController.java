package patel.s.banking.Controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import patel.s.banking.DTO.AmountInput;
import patel.s.banking.Entity.TransactionEntity;
import patel.s.banking.Repository.TransactionRepository;
import patel.s.banking.Services.CashierService;

import java.util.List;

@RestController
@Validated
@RequestMapping("/cashier")
public class CashierController {
    private final CashierService cashierService;
    CashierController(CashierService cashierService){
    this.cashierService=cashierService;
    }

    @PostMapping("/debit/{account}")
    public ResponseEntity<TransactionEntity> amtdebit(@PathVariable @Pattern(
            message = "Invalid Account Number",
            regexp = "^[1-9]\\d{0,10}$"
    ) String account, TransactionEntity transactionEntity, @Valid @RequestBody AmountInput amountInput){
        return cashierService.amtdebit(account,transactionEntity,amountInput);
    }

    @PostMapping("/credit/{account}")
    public ResponseEntity<TransactionEntity> amtcredit(@PathVariable @Pattern(
            message = "Invalid Account Number",
            regexp = "^[1-9]\\d{0,10}$"
    ) String account, TransactionEntity transactionEntity, @Valid@RequestBody AmountInput amountInput){
        return cashierService.amtcredit(account,transactionEntity,amountInput);
    }

    @GetMapping("/check_balance/{account}")
    public ResponseEntity<Double> getBalance(@PathVariable @Pattern( message = "Invalid Account Number",
            regexp = "^[1-9]\\d{0,10}$") String account){
        return cashierService.getBalance(account);
    }
    @GetMapping("/transaction/{account}")
    public ResponseEntity<List<TransactionEntity>> transaction(@PathVariable @Pattern(
            message = "Invalid Account Number",
            regexp = "^[1-9]\\d{0,10}$"
    )String account)
    {
      return cashierService.transaction(account);
    }
}

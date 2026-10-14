package patel.s.banking.Controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import patel.s.banking.DTO.UpdateEmail;
import patel.s.banking.DTO.UpdatePhone;
import patel.s.banking.Entity.Account_Open;
import patel.s.banking.Entity.AmountEntity;
import patel.s.banking.Services.ClerkService;

@RestController
@Validated
@RequestMapping("/clerk")
public class ClerkController {
    private final ClerkService clerkService;
    ClerkController(ClerkService clerkService){
        this.clerkService=clerkService;
    }
    @PostMapping("/open_acc")
    public ResponseEntity<Account_Open> open_acc(@Valid @RequestBody Account_Open holder, AmountEntity amt){
        return  clerkService.addaccount(holder,amt);

    }

    @PutMapping("/email/{account}")
    public ResponseEntity<Account_Open> updateEmail(@PathVariable @Positive(message = "Account should be positive") String account, @Valid@RequestBody UpdateEmail updateEmail){
        return clerkService.updateEmail(account,updateEmail);
    }

    @PutMapping("/phone/{account}")
    public ResponseEntity<Account_Open> updatePhone(@PathVariable @Positive(message = "Account should be positive") String account, @Valid @RequestBody UpdatePhone updatePhone){
        return clerkService.updatePhone(account,updatePhone);
    }
}

package patel.s.banking.Controller;

import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import patel.s.banking.Entity.DeleteEntity;
import patel.s.banking.Entity.TransactionEntity;
import patel.s.banking.Services.ManagerService;

import java.util.List;

@RestController
@RequestMapping("/manager")
public class ManagerController {
   private final ManagerService managerService;
    public ManagerController(ManagerService managerService){
        this.managerService=managerService;
    }
    @DeleteMapping("/delete/{account}")
    public String deleteHolder(@PathVariable@Positive(message = "Enter valid Account Number")String account,DeleteEntity deleteEntity){
        managerService.deleteHolder(account,deleteEntity);
        return "";
    }
    @GetMapping("/all_transaction")
    public ResponseEntity<List<TransactionEntity>> allTrans(){
        return managerService.allTrans();
    }
}

package patel.s.banking.Services;

import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import patel.s.banking.DTO.UpdateEmail;
import patel.s.banking.DTO.UpdatePhone;
import patel.s.banking.Entity.Account_Open;
import patel.s.banking.Entity.AmountEntity;
import patel.s.banking.Repository.AmountRespository;
import patel.s.banking.Repository.BankHoldersRepository;

import java.util.Optional;

@Service
public class ClerkService {

    private final BankHoldersRepository holdersrepo;
    private final AmountRespository amtrepo;
    private final EmailService emailService;
    public ClerkService(BankHoldersRepository holdersrepo,AmountRespository amtrepo,EmailService emailService){
        this.amtrepo=amtrepo;
        this.holdersrepo=holdersrepo;
        this.emailService=emailService;
    }

    @Transactional
    public ResponseEntity<Account_Open> addaccount(Account_Open holder, AmountEntity amt){
        Account_Open ex=holdersrepo.findByAdhar(holder.getAdhar());
        Account_Open ex2=holdersrepo.findByPan(holder.getPan());
        if(ex!=null){
            throw new RuntimeException("Adhar Number Already Exist");
        }
        if(ex2!=null){
            throw new RuntimeException("Pan Number Already Exist");
        }
        Account_Open saved=holdersrepo.save(holder);
        emailService.sendEmail(saved);
        amt.setAccount(saved.getAccount());
        amt.setName(saved.getName());
        amtrepo.save(amt);
        return  new ResponseEntity<>(saved,HttpStatus.CREATED);
    }
    public ResponseEntity<Account_Open> updateEmail(String account, UpdateEmail updateEmail){
        Optional<Account_Open> ex=holdersrepo.findById(Long.parseLong(account));
        if(ex.isEmpty()){
            throw new RuntimeException("Account Does Not Exist");
        }
        if(ex.get().getEmail()==updateEmail.getEmail()){
            throw new RuntimeException("Email already updated");
        }
        ex.get().setEmail(updateEmail.getEmail());
        Account_Open saved=holdersrepo.save(ex.get());
        return new ResponseEntity<>(saved,HttpStatus.ACCEPTED);
    }

    public ResponseEntity<Account_Open> updatePhone(String account, UpdatePhone updatePhone){
        Optional<Account_Open> ex=holdersrepo.findById(Long.parseLong(account));
        if(ex.isEmpty()){
            throw new RuntimeException("Account Does Not Exist");
        }
        if(ex.get().getPhone()==updatePhone.getPhone()){
            throw new RuntimeException("Phone number already updated");
        }
        ex.get().setPhone(updatePhone.getPhone());
        Account_Open saved=holdersrepo.save(ex.get());
        return new ResponseEntity<>(saved,HttpStatus.ACCEPTED);
    }
}

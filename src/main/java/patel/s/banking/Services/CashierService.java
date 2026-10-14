package patel.s.banking.Services;

import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import patel.s.banking.DTO.AmountInput;
import patel.s.banking.Entity.Account_Open;
import patel.s.banking.Entity.AmountEntity;
import patel.s.banking.Entity.TransactionEntity;
import patel.s.banking.Repository.AmountRespository;
import patel.s.banking.Repository.BankHoldersRepository;
import patel.s.banking.Repository.TransactionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CashierService {
    private final TransactionRepository transactionRepository;
    private final AmountRespository amountRespository;
    private final EmailService emailService;
    private final BankHoldersRepository bankHoldersRepository;
    public CashierService(TransactionRepository transactionRepository,AmountRespository amountRespository,EmailService emailService,BankHoldersRepository bankHoldersRepository){
        this.transactionRepository=transactionRepository;
        this.amountRespository=amountRespository;
        this.emailService=emailService;
        this.bankHoldersRepository=bankHoldersRepository;
    }
    @Transactional
    public ResponseEntity<TransactionEntity> amtdebit(String account, TransactionEntity transactionEntity, AmountInput amountInput){
        Optional<AmountEntity> existing=amountRespository.findById(Long.parseLong(account));
        if(existing.isEmpty()){
           throw new RuntimeException("Account does not exist");
        }
        transactionEntity.setAccount(existing.get().getAccount());
        if(amountInput.getAmount()<=0 || existing.get().getBalance()<amountInput.getAmount()){
            throw new IllegalArgumentException("Enter Valid Amount");
        }
        transactionEntity.setBalance(existing.get().getBalance()-amountInput.getAmount());
        transactionEntity.setDebit(amountInput.getAmount());
        TransactionEntity saved=transactionRepository.save(transactionEntity);
        existing.get().setBalance(existing.get().getBalance()-amountInput.getAmount());
        amountRespository.save(existing.get());
        Optional<Account_Open> ac=bankHoldersRepository.findById(Long.parseLong(account));
        if (ac.isEmpty()) {
            throw new RuntimeException("Account holder does not exist");
        }
        emailService.sentDebitMail(saved,ac.get());
        return new ResponseEntity<>(saved,HttpStatus.ACCEPTED);
    }
    @Transactional
    public ResponseEntity<TransactionEntity> amtcredit(String account, TransactionEntity transactionEntity, AmountInput amountInput){
        Optional<AmountEntity> existing=amountRespository.findById(Long.parseLong(account));
        if(existing.isEmpty()){
            throw new RuntimeException("Account does not exist");
        }
        if(amountInput.getAmount()<=0){
            throw new IllegalArgumentException("Enter Valid Amount");
        }
        transactionEntity.setAccount(existing.get().getAccount());
        transactionEntity.setBalance(existing.get().getBalance()+amountInput.getAmount());
        transactionEntity.setCredit(amountInput.getAmount());
        TransactionEntity saved=transactionRepository.save(transactionEntity);
        existing.get().setBalance(existing.get().getBalance()+amountInput.getAmount());
        amountRespository.save(existing.get());
        Optional<Account_Open> ac=bankHoldersRepository.findById(Long.parseLong(account));
        if (ac.isEmpty()) {
            throw new RuntimeException("Account holder does not exist");
        }
        emailService.sentCreditMail(saved,ac.get());
        return new ResponseEntity<>(saved,HttpStatus.ACCEPTED);
    }

    public ResponseEntity<Double> getBalance(String account) {
        Optional<AmountEntity> ex=amountRespository.findById(Long.parseLong(account));
        if(ex.isEmpty()){
            throw new RuntimeException("Account does not exist");
        }
        double bal=ex.get().getBalance();
        return new ResponseEntity<>(bal,HttpStatus.OK);
    }
    public ResponseEntity<List<TransactionEntity>> transaction(String account){
        List<TransactionEntity> trans=transactionRepository.findAllByAccount(Long.parseLong(account));
        if(trans.isEmpty()){
            throw new RuntimeException("Transaction Not Found");
        }
        return new ResponseEntity<>(trans,HttpStatus.OK);
    }
}

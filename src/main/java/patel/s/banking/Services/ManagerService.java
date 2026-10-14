package patel.s.banking.Services;

import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import patel.s.banking.Entity.*;
import patel.s.banking.Repository.*;

import java.util.List;
import java.util.Optional;

@Service
public class ManagerService {
    private final AmountRespository amountRespository;
    private final BankHoldersRepository bankHoldersRepository;
    private final DeleteHoldersRepository deleteHoldersRepository;
    private final EmailService emailService;
    private final TransactionRepository transactionRepository;
    private final CredentialRepo credentialRepo;
    ManagerService(CredentialRepo credentialRepo,AmountRespository amountRespository,BankHoldersRepository bankHoldersRepository,DeleteHoldersRepository deleteHoldersRepository,EmailService emailService,TransactionRepository transactionRepository){
        this.amountRespository=amountRespository;
        this.bankHoldersRepository=bankHoldersRepository;
        this.deleteHoldersRepository=deleteHoldersRepository;
        this.emailService=emailService;
        this.transactionRepository=transactionRepository;
        this.credentialRepo=credentialRepo;
    }

    @Transactional
    public ResponseEntity<String> deleteHolder(String account, DeleteEntity deleteEntity){
        Optional<Account_Open> ex=bankHoldersRepository.findById(Long.parseLong(account));
        if(ex.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        deleteEntity.setAccount(ex.get().getAccount());
        deleteEntity.setName(ex.get().getName());
        deleteEntity.setFatherName(ex.get().getFatherName());
        deleteEntity.setMotherName(ex.get().getMotherName());
        deleteEntity.setType(ex.get().getType());
        deleteEntity.setPhone(ex.get().getPhone());
        deleteEntity.setAdhar(ex.get().getAdhar());
        deleteEntity.setPan(ex.get().getPan());
        deleteEntity.setEmail(ex.get().getEmail());
        deleteEntity.setDate(ex.get().getDate());
        deleteEntity.setDob(ex.get().getDob());
        deleteHoldersRepository.save(deleteEntity);

        Optional<AmountEntity> ex2=amountRespository.findById(Long.parseLong(account));
        if(ex2.isPresent()){
            amountRespository.deleteById(Long.parseLong(account));
        }
        bankHoldersRepository.deleteById(Long.parseLong(account));
        emailService.sentDeletionMail(ex.get());
        return new ResponseEntity<>("Successfully Deleted",HttpStatus.OK);
    }
    public ResponseEntity<List<TransactionEntity>> allTrans(){
        List<TransactionEntity> transaction=transactionRepository.findAll();
        if(transaction.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(transaction,HttpStatus.FOUND);
    }
}

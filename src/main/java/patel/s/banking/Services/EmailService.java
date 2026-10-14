package patel.s.banking.Services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import patel.s.banking.Entity.Account_Open;
import patel.s.banking.Entity.TransactionEntity;

import java.time.LocalDateTime;
@Service
public class EmailService {
    private final JavaMailSender javaMailSender;
    public EmailService(JavaMailSender javaMailSender){
     this.javaMailSender=javaMailSender;
    }

    @Value("${spring.mail.username}")
    private String fromEmail;
    public void sendEmail(Account_Open saved){
         SimpleMailMessage mail=new SimpleMailMessage();
         mail.setFrom(fromEmail);
         mail.setTo(saved.getEmail());
         mail.setSubject("Bank Of Patel's");
         mail.setText("Dear \n"+saved.getName()+"\n\nHolder's Name : "+saved.getName()+"\nAccount : "+saved.getAccount()+"\nAccount Type : "+saved.getType()+"\nFather's Name : "+saved.getFatherName()+"\nMother's Name : "+saved.getMotherName()+"\nAdhar Number : "+saved.getAdhar()+"\nPan Number : "+saved.getPan()+"\nDate & Time : "+LocalDateTime.now()+"\nThank You !");
         javaMailSender.send(mail);
    }
    public void sentDebitMail(TransactionEntity saved,Account_Open ac){
        SimpleMailMessage message=new SimpleMailMessage();
        message.setTo(ac.getEmail());
        message.setFrom(fromEmail);
        message.setSubject("Amount Debit");
        message.setText("Account : "+saved.getAccount()+"\nDebit Amount : "+saved.getDebit()+"\nAvl Balance : "+saved.getBalance()+"\nRef.No : "+saved.getTrn_no()+"\n"+LocalDateTime.now());
        javaMailSender.send(message);
    }
    public void sentCreditMail(TransactionEntity saved,Account_Open ac){
        SimpleMailMessage message=new SimpleMailMessage();
        message.setTo(ac.getEmail());
        message.setFrom(fromEmail);
        message.setSubject("Amount Credit");
        message.setText("Account : "+saved.getAccount()+"\nCredit Amount : "+saved.getCredit()+"\nAvl Balance : "+saved.getBalance()+"\n"+LocalDateTime.now());
        javaMailSender.send(message);
    }
    public void sentDeletionMail(Account_Open saved){
        SimpleMailMessage message=new SimpleMailMessage();
        message.setTo(saved.getEmail());
        message.setFrom(fromEmail);
        message.setSubject("Account Deletion");
        message.setText("Dear \n"+saved.getName()+"\nAccount : "+saved.getAccount()+"\nHolder's Name : "+saved.getName()+"\nYour Account has been deleted !"+"\n"+LocalDateTime.now());
        javaMailSender.send(message);
    }
}

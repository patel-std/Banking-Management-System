package patel.s.banking.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name="transactions")
@Getter
@Setter
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long trn_no;

    @NotNull(message = "Account number is required")
    @Column(name="account")
    private Long account;

    @Column(name="debit")
    private double debit=0.0;

    @Column(name="credit")
    private double credit=0.0;

    @Column(name="avl_balance")
    private double balance=0.0;

    @CreationTimestamp
    @Column(name="created_at")
    private LocalDateTime created_at;
}

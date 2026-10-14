package patel.s.banking.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name="balance_transactions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
    public class AmountEntity {
        @Id
        @Column(name="account_no",unique = true)
        @NotNull
        private Long account;

        @Column(name="holders_name")
        @NotNull
        private String name;

        @Column(name="avl_bal")
        private double balance=0.0;
    }


package patel.s.banking.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="deleted_holders")
public class DeleteEntity {

    @Id
    @Column(name="account_no")
    private Long account;

    @Column(name="holder_name")
    private String name;

    @Column(name="father_name")
    private String fatherName;

    @Column(name="mother_name")
    private String motherName;

    @Column(name="dob")
    private LocalDate dob;

    @Column(name="adhar_card",unique = true)
    private String adhar;

    @Column(name="pan_card",unique=true)
    private String pan;

    @Column(name = "account_type")
    private String type;

    @Column(name="mobile_no")
    private String phone;

    @Column(name="email_id")
    private String email;

    @Column(name="created_at",updatable = false)
    LocalDateTime date;

    @CreationTimestamp
    @Column(name="deleted_at",updatable = false)
    LocalDateTime dated;
}

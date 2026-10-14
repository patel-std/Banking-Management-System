package patel.s.banking.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

@Table (name="account_holders")
@Getter 
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Account_Open {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long account;

    @NotBlank(message ="Full Name Name is Required")
    @Column(name="holder_name")
    @Pattern(
            message = "Enter Valid Name",
            regexp = "^[a-zA-Z ]{1,60}$"
    )
    private String name;

    @Column(name="father_name")
    @NotBlank(message ="Father's Name is Required")
    @Pattern(
            message = "Enter Valid Father Name",
            regexp = "^[a-zA-Z ]{1,60}$"
    )
    private String fatherName;

    @Column(name="mother_name")
    @NotBlank(message ="Mother's Name is Required")
    @Pattern(
            message = "Enter Valid Mother Name",
            regexp = "^[a-zA-Z ]{1,60}$"
    )
    private String motherName;

    //2007-12-03 (Format)
    @Column(name="dob")
    @NotNull(message = "Date of Birth is Required")
    private LocalDate dob;

    @Column(name="adhar_card",unique = true)
    @NotBlank(message ="Adhar number is Required")
    @Pattern(
            message = "Enter Valid Adhar Number",
            regexp = "^\\d{12}$"
    )
    private String adhar;

    @Column(name="pan_card",unique=true)
    @Pattern(
            message = "Enter Valid Pan Number",
            regexp = "^[a-zA-Z]{5}\\d{4}[a-zA-Z]$"
    )
    private String pan;

    @Column(name = "account_type")
    @NotBlank(message = "Type is required")
    @Pattern(
            message = "Enter valid type",
            regexp = "^[a-zA-Z]{5,10}$"
    )
    private String type;

    @Column(name="mobile_no")
    @NotBlank(message = "Mobile number is required")
    @Pattern(
            message = "Enter Valid Phone Number",
            regexp = "^[6-9]\\d{9}$"
    )
    private String phone;

    @Column(name="email_id")
    @NotBlank(message = "Email is required")
    @Pattern(
            message = "Enter Valid email",
            regexp = "^[a-zA-Z0-9.+_-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"
    )
    private String email;

    @CreationTimestamp
    @Column(name="created_at",updatable = false)
    LocalDateTime date;
    
}

package patel.s.banking.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name="admin_details")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AdminEntity{
    @Id
    @Column(name="username",unique = true)
    String username;

    @Column(name="password")
    String password;

    @Column(name="role")
    String role;
}

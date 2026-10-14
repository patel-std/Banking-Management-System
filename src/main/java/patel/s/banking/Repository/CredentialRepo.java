package patel.s.banking.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import patel.s.banking.Entity.Credential;

public interface CredentialRepo extends JpaRepository<Credential,String> {
     Credential findByUsername(String username);
}

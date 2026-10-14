package patel.s.banking.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import patel.s.banking.Entity.Account_Open;

public interface
BankHoldersRepository extends JpaRepository<Account_Open,Long> {
    Account_Open findByAdhar(String adhar);
    Account_Open findByPan(String pan);
}

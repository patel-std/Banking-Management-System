package patel.s.banking.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import patel.s.banking.Entity.TransactionEntity;

import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionEntity,Long> {
    List<TransactionEntity> findAllByAccount(Long account);
}

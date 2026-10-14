package patel.s.banking.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import patel.s.banking.Entity.AmountEntity;

@Repository
public interface AmountRespository extends JpaRepository<AmountEntity,Long> {
}

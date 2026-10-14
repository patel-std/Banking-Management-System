package patel.s.banking.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import patel.s.banking.Entity.DeleteEntity;

@Repository
public interface DeleteHoldersRepository extends JpaRepository<DeleteEntity,Long> {
}

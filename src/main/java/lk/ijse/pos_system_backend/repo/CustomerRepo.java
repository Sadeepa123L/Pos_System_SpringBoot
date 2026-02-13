package lk.ijse.pos_system_backend.repo;

import lk.ijse.pos_system_backend.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {
}

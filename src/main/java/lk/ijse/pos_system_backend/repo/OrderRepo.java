package lk.ijse.pos_system_backend.repo;

import lk.ijse.pos_system_backend.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Orders, Long> {
}

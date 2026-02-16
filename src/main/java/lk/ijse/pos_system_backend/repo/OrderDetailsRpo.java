package lk.ijse.pos_system_backend.repo;

import lk.ijse.pos_system_backend.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailsRpo extends JpaRepository<OrderDetails, Long> {
}

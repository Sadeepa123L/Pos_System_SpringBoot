package lk.ijse.pos_system_backend.repo;

import lk.ijse.pos_system_backend.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepo extends JpaRepository<Item, Long> {
}

package lk.ijse.pos_system_backend.service.custom;

import lk.ijse.pos_system_backend.dto.OrderDTO;

public interface OrderService {
    void placeOrder(OrderDTO orderDTO);

}

package lk.ijse.pos_system_backend.service.custom;

import lk.ijse.pos_system_backend.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    void placeOrder(OrderDTO orderDTO);
    List<OrderDTO> getAllOrders();

}

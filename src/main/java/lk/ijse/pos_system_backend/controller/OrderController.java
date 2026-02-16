package lk.ijse.pos_system_backend.controller;


import lk.ijse.pos_system_backend.dto.OrderDTO;
import lk.ijse.pos_system_backend.service.custom.OrderService;
import lk.ijse.pos_system_backend.service.impl.OrderServiceImpl;
import lk.ijse.pos_system_backend.util.APIResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/placeOrder")
    public ResponseEntity<APIResponse<String>> placeOrder(@RequestBody OrderDTO orderDTO) {
        orderService.placeOrder(orderDTO);

        return new ResponseEntity<>(new APIResponse<>(
                201, "Order Placed Successfully", null
        ), HttpStatus.CREATED);
    }
}

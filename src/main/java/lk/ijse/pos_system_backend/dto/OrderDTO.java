package lk.ijse.pos_system_backend.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDTO {
    private Long orderId;
    private LocalDate orderDate;
    private Long customerId;

    private List<OrderDetailsDTO> orderDetails;

}

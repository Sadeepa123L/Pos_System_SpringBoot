package lk.ijse.pos_system_backend.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import jakarta.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetailsDTO {
    private Long itemId;

    @Min(value = 1, message = "Quantity must be at least 1")
    private int qty;

    @Positive(message = "Unit price must be positive")
    private double unitPrice;
}

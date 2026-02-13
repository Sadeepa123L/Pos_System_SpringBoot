package lk.ijse.pos_system_backend.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemDTO {
    private Long id;
    @NotBlank(message = "Item name cannot be empty")
    private String name;
    @Min(value = 0, message = "Price cannot be negative")
    private Double price;
    @Min(value = 0, message = "Qty cannot be negative")
    private int qtyOnHand;
}

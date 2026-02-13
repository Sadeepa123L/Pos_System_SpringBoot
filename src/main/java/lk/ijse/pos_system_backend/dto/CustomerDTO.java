package lk.ijse.pos_system_backend.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDTO {

    private Long id;

    @NotBlank(message = "Customer name cannot be empty")
    private String name;

    @NotBlank(message = "Address is required")
    private String address;
}

package lk.ijse.pos_system_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;
    private int qtyOnHand;


    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<OrderDetails> orderDetails;


}

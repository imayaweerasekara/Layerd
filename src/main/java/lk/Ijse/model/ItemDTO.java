package lk.Ijse.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemDTO {
    String code;
    String name;
    String description;
    String category;
    double price;
    int QtyOnHand;
}

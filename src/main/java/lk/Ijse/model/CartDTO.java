package lk.Ijse.model;
import com.jfoenix.controls.JFXButton;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartDTO {
     String code;
     String description;
     double unitPrice;
     int qty;
     double total;
     JFXButton btnRemove;
}

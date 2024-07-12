package lk.Ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class IngredientDTO {
    String Ingredient_id;
    String Ingredient_name;
    String category;
    int qty_avalible;
    double unit_price;
    String supplier_id;
}

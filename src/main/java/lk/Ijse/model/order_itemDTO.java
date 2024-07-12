package lk.Ijse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class order_itemDTO {

    private String ItemID;
    private String orderid;
    private int Qty;
    private double unitprice;
    private double totalprice;


}

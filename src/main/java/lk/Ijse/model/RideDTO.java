package lk.Ijse.model;
import lombok.*;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RideDTO {
    private String ride_id;
    private String time;
    private Date date;
    private String status;
    private String description;
    private String customer_id;

}

package lk.Ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

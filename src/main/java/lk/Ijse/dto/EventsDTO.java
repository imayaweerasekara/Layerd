package lk.Ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class EventsDTO {
    String event_id;
    String name;
    String description;
    Date date;
    String start_time;
    String end_time;
}

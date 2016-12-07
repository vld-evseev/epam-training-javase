package training.tasks.unit8.task2.data.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TrackDto {

    private int id;
    private int position;
    private String name;
    private int albumId;

}

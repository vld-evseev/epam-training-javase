package training.tasks.unit8.task2.data.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AlbumDto {

    private int id;
    private String name;
    private ArtistDto artist;

}

package training.tasks.unit4.task4;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Actor implements Serializable {

    private String name;
    private String gender;

}

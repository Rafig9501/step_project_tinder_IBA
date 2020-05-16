package entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@AllArgsConstructor
@Data
public class Unlike implements Serializable {

    private String id;
    private String from;
    private String to;
}

package entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Like implements Serializable {

    private String id;
    private User from;
    private User to;
}

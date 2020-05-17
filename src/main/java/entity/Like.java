package entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Like implements Serializable {

    private String id;
    private String fromUser;
    private String toUser;
    private Boolean isLiked;
}

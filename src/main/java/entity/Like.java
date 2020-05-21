package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@AllArgsConstructor
@ToString
public class Like implements Serializable {

    private String id;
    private String fromUser;
    private String toUser;
    private Boolean isLiked;
}

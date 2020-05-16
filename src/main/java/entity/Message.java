package entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@AllArgsConstructor
@Data
public class Message implements Serializable {

    private String id;
    private String fromId;
    private String toId;
    private String contentId;
    private String content;
}

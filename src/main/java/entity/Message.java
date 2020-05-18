package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.ZonedDateTime;

@AllArgsConstructor
@Data
@ToString
public class Message implements Serializable {

    private String id;
    private String fromId;
    private String toId;
    private String content;
    private ZonedDateTime messageDateTime;
}

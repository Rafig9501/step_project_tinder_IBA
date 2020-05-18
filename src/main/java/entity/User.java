package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@ToString
public class User implements Serializable {

    private String id;
    private String email;
    private String password;
    private String name;
    private String surname;
    private String photoUrl;
    private ZonedDateTime lastLogin;
}
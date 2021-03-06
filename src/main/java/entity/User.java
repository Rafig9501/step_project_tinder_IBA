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

    public User(String name, String surname, String email, String photoUrl, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.photoUrl = photoUrl;
        this.password = password;
    }
}
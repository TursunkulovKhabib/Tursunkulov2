package org.tursunkulov.authorization.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Getter
@Setter
public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
}
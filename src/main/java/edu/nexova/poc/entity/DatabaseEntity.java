package edu.nexova.poc.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DatabaseEntity {

    private Long id;
    private String databaseName;
    private String username;
    private String host;
    private Integer port;
    private String password;
}

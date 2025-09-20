package edu.nexova.poc.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DatabaseDTO {

    private Long id;

    @NotBlank(message = " Database name is mandatory")
    private String databaseName;

    @NotBlank(message = " Username is mandatory")
    private String username;

    @NotBlank(message = " Host is mandatory")
    private String host;

    @NotNull(message = " Port is mandatory")
    @Positive(message = " Port must be a positive number")
    private Integer port;

    @NotBlank(message = " Password is mandatory")
    private String password;
}
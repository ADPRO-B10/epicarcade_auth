package adpro.b10.epicarcade_auth.dto;

import lombok.Data;

@Data
public class LoginDto {
    private String email;
    private String password;
}
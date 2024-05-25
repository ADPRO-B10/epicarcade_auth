package adpro.b10.epicarcade_auth.dto;

import lombok.Data;

@Data
public class RegisterDto {
    private String username;
    private String email;
    private String password;
    private String role;
    private String profilePictureUrl;
}
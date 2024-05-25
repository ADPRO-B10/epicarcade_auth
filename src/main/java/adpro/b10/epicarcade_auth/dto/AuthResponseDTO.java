package adpro.b10.epicarcade_auth.dto;

import lombok.Data;

@Data
public class AuthResponseDTO {
    private Integer userID;
    private String username;
    private String email;
    private String role;
    private String profilePictureUrl;
    private String accessToken;
    private String tokenType = "Bearer ";

    public AuthResponseDTO(Integer userID, String username, String email, String role,
                           String profilePictureUrl, String accessToken)
    {
        this.userID = userID;
        this.username = username;
        this.email = email;
        this.role = role;
        this.profilePictureUrl = profilePictureUrl;
        this.accessToken = accessToken;
    }
}

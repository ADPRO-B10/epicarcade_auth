package adpro.b10.epicarcade_auth.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer id;

    @Column(nullable = false)
    @Getter
    @Setter
    private String username;

    @Column(unique = true, length = 255, nullable = false)
    @Getter
    @Setter
    private String email;

    @Column(nullable = false)
    @Setter
    private String password;

    @Column(nullable = false)
    @Getter
    @Setter
    private String role;
}

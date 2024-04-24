package adpro.b10.epicarcade_auth.model;
import java.util.ArrayList;

import enums.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    UserEntity BUYER1 = new UserEntity();
    UserEntity SELLER1 = new UserEntity();
    UserEntity ADMIN1 = new UserEntity();
    private ArrayList<UserEntity> userEntities;

    @BeforeEach
    void setUp() {

        //USER1
        BUYER1.setUsername("wongirengganteng635");
        BUYER1.setEmail("wongirengganteng635@dummymail.com");
        //        TODO: SET PASSWORD TO ENCRYPTION
        BUYER1.setPassword("AXASW123ASXASEA");
        BUYER1.setRole(UserRole.BUYER.getValue());

        //USER2
        SELLER1.setUsername("skibiditoilet");
        SELLER1.setEmail("skibiditoilet@dummymail.com");
        //        TODO: SET PASSWORD TO ENCRYPTION
        SELLER1.setPassword("ZZXX123OWKX");
        SELLER1.setRole(UserRole.SELLER.getValue());

        //USER3
        ADMIN1.setUsername("admin");
        //USER3.setEmail("admin@dummymail.com");
//        ADMIN1.setEmail("ASFSDFGSG213");
        ADMIN1.setRole(UserRole.ADMIN.getValue());
    }

    @Test
    void testCreateBuyer() {
        assertEquals("wongirengganteng635", BUYER1.getUsername());
        assertEquals("wongirengganteng635@dummymail.com", BUYER1.getEmail());
//        assertEquals("AXASW123ASXASEA", BUYER1.getPassword();
        assertEquals(UserRole.BUYER.getValue(), BUYER1.getRole());
    }

    @Test
    void testCreateSeller() {
        assertEquals("skibiditoilet", SELLER1.getUsername());
        assertEquals("wongirengganteng635@dummymail.com", SELLER1.getEmail());
//        assertEquals("AXASW123ASXASEA", BUYER1.getPassword();
        assertEquals(UserRole.SELLER.getValue(), SELLER1.getRole());
    }

    @Test
    void testCreateAdmin() {
        assertEquals("admin", ADMIN1.getUsername());
        assertEquals(UserRole.ADMIN.getValue(), ADMIN1.getRole());
    }
}

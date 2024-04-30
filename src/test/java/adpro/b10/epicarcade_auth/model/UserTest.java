package adpro.b10.epicarcade_auth.model;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import enums.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    UserEntity BUYER1 = new UserEntity();
    UserEntity SELLER1 = new UserEntity();
    UserEntity ADMIN1 = new UserEntity();
    private ArrayList<UserEntity> userEntities;

//    @BeforeEach
//    void setUp() {
//        //USER1
//        BUYER1.setUsername("wongirengganteng635");
//        //        TODO: SET PASSWORD TO ENCRYPTION
//        BUYER1.setPassword("AXASW123ASXASEA");
//        BUYER1.getRoles().add(new Role(1, UserRole.BUYER.getValue()));
//
//        //USER2
//        SELLER1.setUsername("skibiditoilet");
//        //        TODO: SET PASSWORD TO ENCRYPTION
//        SELLER1.setPassword("ZZXX123OWKX");
//        SELLER1.getRoles().add(new Role(2, UserRole.SELLER.getValue()));
//
//        //USER3
//        ADMIN1.setUsername("admin");
////        ADMIN1.setEmail("ASFSDFGSG213");
//        ADMIN1.getRoles().add(new Role(0, UserRole.ADMIN.getValue()));
//    }
//
//    @Test
//    void testCreateBuyer() {
//        assertEquals("wongirengganteng635", BUYER1.getUsername());
////        assertEquals("AXASW123ASXASEA", BUYER1.getPassword();
//        assertEquals(UserRole.BUYER.getValue(), BUYER1.getRoles().getFirst().getName());
//    }
//
//    @Test
//    void testCreateSeller() {
//        assertEquals("skibiditoilet", SELLER1.getUsername());
////        assertEquals("AXASW123ASXASEA", BUYER1.getPassword();
//        assertEquals(UserRole.SELLER.getValue(), SELLER1.getRoles().getFirst().getName());
//    }
//
//    @Test
//    void testCreateAdmin() {
//        assertEquals("admin", ADMIN1.getUsername());
//        assertEquals(UserRole.ADMIN.getValue(), ADMIN1.getRoles().getFirst().getName());
//    }
}

//package com.BikeStoreApi.BikeStoreApi;
//
//
//import com.BikeStoreApi.BikeStoreApi.entities.Role;
//import com.BikeStoreApi.BikeStoreApi.repositories.RoleRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.annotation.Rollback;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@Rollback(false)
//public class RoleTest {
//    @Autowired
//    private RoleRepository repo;
//
//    @Test
//    public void testCreateRoles() {
//        Role admin = new Role("ROLE_ADMIN");
//        Role editor = new Role("ROLE_MANAGER");
//        Role customer = new Role("ROLE_USER");
//
//        repo.saveAll(List.of(admin, editor, customer));
//
//        long count = repo.count();
//        assertEquals(3, count);
//    }
//
//}
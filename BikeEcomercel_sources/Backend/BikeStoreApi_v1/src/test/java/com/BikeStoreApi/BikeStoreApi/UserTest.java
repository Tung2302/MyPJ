//package com.BikeStoreApi.BikeStoreApi;
//
//import com.BikeStoreApi.BikeStoreApi.entities.Role;
//import com.BikeStoreApi.BikeStoreApi.entities.User;
//import com.BikeStoreApi.BikeStoreApi.repositories.RoleRepository;
//import com.BikeStoreApi.BikeStoreApi.repositories.UserRepository;
//import com.BikeStoreApi.BikeStoreApi.services.UserService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.annotation.Rollback;
//
//import java.util.HashSet;
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@Rollback(false)
//public class UserTest {
//    @Autowired
//    private UserRepository repo;
//
//    @Test
//    public void testAssignRoleToUser() {
//        User user1 = repo.save(new User(null,"buiductung2002@gmail.com","amid2002","0377871889","Amid","Tung",false,new HashSet<>()));
//        Integer userId = 1;
//        Integer roleId = 3;
//        User user = repo.findById(userId).get();
//        user.addRole(new Role(roleId));
//
//        User updatedUser = repo.save(user);
//
//
//    }
//}

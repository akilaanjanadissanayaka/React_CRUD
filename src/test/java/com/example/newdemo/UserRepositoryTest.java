package com.example.newdemo;

import com.example.newdemo.user.User;
import com.example.newdemo.user.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {
    @Autowired private UserRepository repo;
    @Test

    public void testAddNew(){
        User user=new User();
        user.setEmail("Anjana@gmail.com");
        user.setFirstName("Akilaa");
        user.setLastName("Disaa");
        user.setPassword("12345");

        User savedUser=repo.save(user);
        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
    }
    @Test
    public void testListAll(){
        Iterable<User> users = repo.findAll();
        Assertions.assertThat(users).hasSizeGreaterThan(0);
        for(User user:users){
            System.out.println(user);
        }
    }
    @Test
    public void testUpdate(){
        Integer id=1;
        Optional<User> optionalUser=repo.findById(id);
        User user=optionalUser.get();
        user.setPassword("3333");
        repo.save((user));
        User updatedUser=repo.findById(id).get();
        Assertions.assertThat(updatedUser.getPassword()).isEqualTo("3333");

    }
    @Test
    public void testGet(){
        Integer id=2;
        Optional<User> optionalUser=repo.findById(id);

        Assertions.assertThat(optionalUser).isPresent();
        System.out.println(optionalUser.get());
    }

    @Test
    public void testDelete(){
        Integer userId=2;
        repo.deleteById(userId);

        Optional<User> optionalUser=repo.findById(userId);
        Assertions.assertThat(optionalUser).isNotPresent();
    }
}

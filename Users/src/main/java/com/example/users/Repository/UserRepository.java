package com.example.users.Repository;

import com.example.users.Model.UserManagement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserManagement,Integer> {

    UserManagement findByEmail(String email);

    List<UserManagement> findByRole(String role);

    @Query("select u from UserManagement u where u.age >= ?1")
    List<UserManagement> findByAge(Integer age);

    @Query("select u from UserManagement u where u.username = ?1 and u.password = ?2")
    UserManagement findByUsernameAndPassword(String userName, String password);

    UserManagement findByUsername(String userName);
}

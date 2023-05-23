package com.example.users.Repository;

import com.example.users.Model.UserManagement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<UserManagement,Integer> {

    UserManagement get(String email);

    UserManagement getAllUsers(String role);

    @Query("select u from UserManagement u where u.AGE >= ?1")
    UserManagement getAllUsersAge(int age);

    @Query("select u.AGE from UserManagement u where u.AGE >= ?1")
    UserManagement check(String userName, String password)
}

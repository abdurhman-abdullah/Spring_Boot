package com.example.usersmanagement.Repository;

import com.example.usersmanagement.Model.users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<users,Integer> {
}

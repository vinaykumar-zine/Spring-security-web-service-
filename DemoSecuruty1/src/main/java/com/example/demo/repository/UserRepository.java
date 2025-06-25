package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.MyUser;


public interface UserRepository extends JpaRepository<MyUser, Long>{
	Optional<MyUser> findByName(String name);
}

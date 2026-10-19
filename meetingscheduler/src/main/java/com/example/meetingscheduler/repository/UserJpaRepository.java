package com.example.meetingscheduler.repository;

import com.example.meetingscheduler.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserEntity, String> {
}

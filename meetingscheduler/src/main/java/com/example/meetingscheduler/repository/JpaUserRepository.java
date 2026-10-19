package com.example.meetingscheduler.repository;


import com.example.meetingscheduler.entities.UserEntity;
import com.example.meetingscheduler.enums.NotificationType;
import com.example.meetingscheduler.model.User;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaUserRepository implements IUserRepository{

    private final UserJpaRepository repository;
    private final PersistenceMapper mapper;

    public JpaUserRepository(UserJpaRepository repository, PersistenceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public void addUser(User user) {
        if (repository.existsById(user.getUserId())) {
            throw new DuplicateKeyException("User ID already exists");
        }

        repository.saveAndFlush(mapper.toEntity(user));
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return repository.findAll(Sort.by("userId")).stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> getUserById(String userId) {
        return repository.findById(userId).map(mapper::toDomain);
    }

    @Override
    @Transactional
    public boolean updateUserPreference(
            String userId, NotificationType notificationType) {

        Optional<UserEntity> result = repository.findById(userId);
        if (result.isEmpty()) {
            return false;
        }

        result.get().setNotificationType(notificationType);
        return true;
    }
}

package com.example.meetingscheduler.repository;

import com.example.meetingscheduler.enums.NotificationType;
import com.example.meetingscheduler.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public class JdbcUserRepository implements IUserRepository{

    private final JdbcTemplate jdbcTemplate;

    private static final RowMapper<User> USER_ROW_MAPPER = (rs, rowNum) -> {
        User user = new User();
        user.setUserId(rs.getString("user_id"));
        user.setUserName(rs.getString("user_name"));
        user.setEmail(rs.getString("email"));
        user.setPhoneNumber(rs.getString("phone_number"));
        user.setNotificationType(NotificationType.valueOf(rs.getString("notification_type")));
        return user;
    };

    public JdbcUserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addUser(User user) {

        jdbcTemplate.update("""
                INSERT INTO scheduler.app_user(user_id, user_name, email, phone_number,
                notification_type) VALUES(?,?,?,?,?)
                """,
                user.getUserId(),
                user.getUserName(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getNotificationType().name());


    }

    @Override
    public List<User> getAllUsers() {
        return jdbcTemplate.query(
                """
    SELECT user_id, user_name, email, phone_number, notification_type
    FROM scheduler.app_user ORDER BY user_id""", USER_ROW_MAPPER);
    }

    @Override
    public Optional<User> getUserById(String userId) {
        return jdbcTemplate.query(
                """
    SELECT user_id, user_name, email, phone_number, notification_type
    FROM scheduler.app_user WHERE user_id = ?""", USER_ROW_MAPPER, userId).stream().findFirst();
    }

    @Override
    public boolean updateUserPreference(String userId, NotificationType notificationType) {
        return jdbcTemplate.update("""
                UPDATE scheduler.app_user SET notification_type = ?
                WHERE user_id = ?
         
                """, notificationType.name(), userId) == 1;
    }
}

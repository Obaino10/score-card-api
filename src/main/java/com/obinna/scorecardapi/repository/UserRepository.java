package com.obinna.scorecardapi.repository;

import com.obinna.scorecardapi.enums.Role;
import com.obinna.scorecardapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
     Optional<User> findByEmail(String email);
     Optional<User> findFirstByRole(Role role);
     Optional<User> findById(Long id);
     Optional<User> findUserByIdAndRole(Long id, Role role);

}

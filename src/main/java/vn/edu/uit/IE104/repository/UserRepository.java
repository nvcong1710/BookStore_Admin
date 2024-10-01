package vn.edu.uit.IE104.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.uit.IE104.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    boolean existsByEmail(String email);

    void deleteByEmail(String email);
}

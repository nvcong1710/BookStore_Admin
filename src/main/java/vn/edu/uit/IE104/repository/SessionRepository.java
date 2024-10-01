package vn.edu.uit.IE104.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.uit.IE104.entity.Session;

public interface SessionRepository extends JpaRepository<Session, Long> {
}

package vn.edu.uit.IE104.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.uit.IE104.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}

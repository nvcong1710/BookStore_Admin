package vn.edu.uit.IE104.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.uit.IE104.entity.Exam;

public interface ExamRepository extends JpaRepository<Exam, Long> {
}

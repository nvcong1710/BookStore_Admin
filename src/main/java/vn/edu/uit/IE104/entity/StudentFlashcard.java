package vn.edu.uit.IE104.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "student_flashcards")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentFlashcard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private User student;

    @ManyToOne
    @JoinColumn(name = "flashcard_id", nullable = false)
    private Flashcard flashcard;

    @Column(nullable = false)
    private boolean isLearned; // Trạng thái đã học hoặc chưa học

    @Column(name = "learned_at")
    private LocalDateTime learnedAt; // Thời gian học flashcard, nếu có
}

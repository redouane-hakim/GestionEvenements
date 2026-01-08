package ma.xproce.gestionevenements.dao.repository;

import ma.xproce.gestionevenements.dao.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByEvenement_EidOrderByDateQuestionDesc(Long eid);
}

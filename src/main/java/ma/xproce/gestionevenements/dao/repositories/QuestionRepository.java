package ma.xproce.gestionevenements.dao.repositories;

import ma.xproce.gestionevenements.dao.entities.Question;
import ma.xproce.gestionevenements.dao.entities.Evenement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    List<Question> findByEvenement(Evenement evenement);
}

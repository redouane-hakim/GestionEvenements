package ma.xproce.gestionevenements.service;

import ma.xproce.gestionevenements.dao.entities.Evenement;
import ma.xproce.gestionevenements.dao.entities.Question;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface QuestionService {
    Question save(Question question);
    Question update(Question question);
    void deleteById(int id);
    Optional<Question> findById(int id);
    List<Question> findByEvenement(Evenement evenement);
}

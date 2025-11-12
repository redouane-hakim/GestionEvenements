package ma.xproce.gestionevenements.service.manager;

import ma.xproce.gestionevenements.dao.entities.Evenement;
import ma.xproce.gestionevenements.dao.entities.Question;
import ma.xproce.gestionevenements.dao.repositories.QuestionRepository;
import ma.xproce.gestionevenements.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class QuestionManager implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public Question save(Question q) {
        return questionRepository.save(q);
    }

    @Override
    public Question update(Question q) {
        return questionRepository.save(q);
    }

    @Override
    public void deleteById(int id) {
        questionRepository.deleteById(id);
    }

    @Override
    public Optional<Question> findById(int id) {
        return questionRepository.findById(id);
    }

    @Override
    public List<Question> findByEvenement(Evenement ev) {
        return questionRepository.findByEvenement(ev);
    }
}

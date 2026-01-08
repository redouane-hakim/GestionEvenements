package ma.xproce.gestionevenements.service.impl;

import lombok.RequiredArgsConstructor;
import ma.xproce.gestionevenements.dao.entities.Question;
import ma.xproce.gestionevenements.dao.entities.Utilisateur;
import ma.xproce.gestionevenements.dao.repository.QuestionRepository;
import ma.xproce.gestionevenements.service.QuestionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;

    @Override
    public Question save(Question q) {
        return questionRepository.save(q);
    }

    @Override
    public List<Question> findByEvenement(Long eid) {
        return questionRepository.findByEvenement_EidOrderByDateQuestionDesc(eid);
    }

    @Override
    public Optional<Question> findById(Long  id) {
        return questionRepository.findById(id);
    }

    @Override
    @Transactional
    public Question repondre(Long qid, String reponse, Utilisateur repondeur) {
        Question q = questionRepository.findById(qid).orElseThrow();
        q.setReponse(reponse);
        q.setDateReponse(LocalDateTime.now());
        q.setRepondeur(repondeur);
        return q;
    }
}

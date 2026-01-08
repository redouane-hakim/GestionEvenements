package ma.xproce.gestionevenements.service;

import ma.xproce.gestionevenements.dao.entities.Question;
import ma.xproce.gestionevenements.dao.entities.Utilisateur;

import java.util.List;
import java.util.Optional;

public interface QuestionService {
    Question save(Question q);
    List<Question> findByEvenement(Long eid);
    Optional<Question> findById(Long id);
    Question repondre(Long qid, String reponse, Utilisateur repondeur);
}

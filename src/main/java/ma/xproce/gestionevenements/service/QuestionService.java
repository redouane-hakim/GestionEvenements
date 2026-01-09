package ma.xproce.gestionevenements.service;

import ma.xproce.gestionevenements.dto.QuestionDto;
import ma.xproce.gestionevenements.dto.UtilisateurDto;

import java.util.List;
import java.util.Optional;

public interface QuestionService {
    QuestionDto save(QuestionDto q);
    List<QuestionDto> findByEvenement(Long eid);
    Optional<QuestionDto> findById(Long id);
    QuestionDto repondre(Long qid, String reponse, UtilisateurDto repondeur);
}

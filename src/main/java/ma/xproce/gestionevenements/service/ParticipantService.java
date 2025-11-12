package ma.xproce.gestionevenements.service;

import ma.xproce.gestionevenements.dao.entities.Evenement;
import ma.xproce.gestionevenements.dao.entities.Participant;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ParticipantService {
    Participant save(Participant participant);
    void deleteById(int id);
    Optional<Participant> findById(int id);
    List<Participant> findByEvenement(Evenement evenement);
}

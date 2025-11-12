package ma.xproce.gestionevenements.dao.repositories;

import ma.xproce.gestionevenements.dao.entities.Participant;
import ma.xproce.gestionevenements.dao.entities.Evenement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParticipantRepository extends JpaRepository<Participant, Integer> {
    List<Participant> findByEvenement(Evenement evenement);
    List<Participant> findByEmailIgnoreCase(String email);
}

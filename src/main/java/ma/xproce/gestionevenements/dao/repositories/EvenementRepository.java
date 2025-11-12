package ma.xproce.gestionevenements.dao.repositories;

import ma.xproce.gestionevenements.dao.entities.Evenement;
import ma.xproce.gestionevenements.dao.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EvenementRepository extends JpaRepository<Evenement, Integer> {
    List<Evenement> findByOrganisateur(Utilisateur organisateur);
    List<Evenement> findByDateFinBefore(LocalDateTime limit);
    List<Evenement> findByTitreContainingIgnoreCase(String mot);
}

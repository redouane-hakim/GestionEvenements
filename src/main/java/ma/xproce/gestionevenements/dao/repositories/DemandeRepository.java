package ma.xproce.gestionevenements.dao.repositories;

import ma.xproce.gestionevenements.dao.entities.Demande;
import ma.xproce.gestionevenements.dao.entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface DemandeRepository extends JpaRepository<Demande, Integer> {
    List<Demande> findByStatus(Status status);
    List<Demande> findByDateDemandeBetween(LocalDateTime from, LocalDateTime to);
    List<Demande> findByEmailOrganisateurIgnoreCase(String email);
}

package ma.xproce.gestionevenements.dao.repository;

import ma.xproce.gestionevenements.dao.entities.Demande;
import ma.xproce.gestionevenements.dao.entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DemandeRepository extends JpaRepository<Demande, Long> {
    List<Demande> findByStatusOrderByDateDemandeDesc(Status status);
    List<Demande> findAllByOrderByDateDemandeDesc();
    List<Demande> findByEmailOrganisateurOrderByDateDemandeDesc(String emailOrganisateur);
}

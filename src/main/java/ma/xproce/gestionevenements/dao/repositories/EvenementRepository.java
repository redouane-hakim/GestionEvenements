package ma.xproce.gestionevenements.dao.repositories;

import ma.xproce.gestionevenements.dao.entities.Evenement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EvenementRepository extends JpaRepository<Evenement, Long> {
    List<Evenement> findTop5ByOrderByDateDebutDesc();
    List<Evenement> findAllByOrderByDateDebutDesc();
    List<Evenement> findByOrganisateur_UidOrderByDateDebutDesc(Long uid);
}

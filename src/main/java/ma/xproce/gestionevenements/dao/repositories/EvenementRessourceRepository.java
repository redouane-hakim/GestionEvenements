package ma.xproce.gestionevenements.dao.repositories;


import lombok.NonNull;
import ma.xproce.gestionevenements.dao.entities.Evenement;
import ma.xproce.gestionevenements.dao.entities.EvenementRessource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EvenementRessourceRepository extends JpaRepository<EvenementRessource, Integer> {
    List<EvenementRessource> findByEvenement(Evenement evenement);
    @Override
    @NonNull
    List<EvenementRessource> findAll();

}

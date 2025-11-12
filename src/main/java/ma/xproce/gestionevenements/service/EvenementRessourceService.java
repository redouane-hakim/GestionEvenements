package ma.xproce.gestionevenements.service;

import ma.xproce.gestionevenements.dao.entities.Evenement;
import ma.xproce.gestionevenements.dao.entities.EvenementRessource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface EvenementRessourceService {
    EvenementRessource save(EvenementRessource evenementRessource);
    void deleteById(int id);
    Optional<EvenementRessource> findById(int id);
    List<EvenementRessource> findByEvenement(Evenement evenement);
}

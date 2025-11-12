package ma.xproce.gestionevenements.service;

import ma.xproce.gestionevenements.dao.entities.Categorie;
import ma.xproce.gestionevenements.dao.entities.Ressource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface RessourceService {
    Ressource save(Ressource ressource);
    Ressource update(Ressource ressource);
    void deleteById(int id);
    Optional<Ressource> findById(int id);
    List<Ressource> findAll();
    List<Ressource> findByCategorie(Categorie categorie);
    List<Ressource> searchByNom(String mot);
}

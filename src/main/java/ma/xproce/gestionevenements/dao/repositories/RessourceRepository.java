package ma.xproce.gestionevenements.dao.repositories;

import ma.xproce.gestionevenements.dao.entities.Ressource;
import ma.xproce.gestionevenements.dao.entities.Category;
import ma.xproce.gestionevenements.dao.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RessourceRepository extends JpaRepository<Ressource, Integer> {
    List<Ressource> findByCategorie(Category categorie);
    List<Ressource> findBy(Utilisateur createur);
    List<Ressource> findByNomContainingIgnoreCase(String mot);
}

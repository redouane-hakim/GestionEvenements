package ma.xproce.gestionevenements.dao.repositories;

import ma.xproce.gestionevenements.dao.entities.Categorie;
import ma.xproce.gestionevenements.dao.entities.Ressource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RessourceRepository extends JpaRepository<Ressource, Integer> {
    List<Ressource> findByCategorie(Categorie categorie);
    List<Ressource> findByNomContainingIgnoreCase(String mot);
}

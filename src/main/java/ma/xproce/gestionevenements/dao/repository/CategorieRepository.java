package ma.xproce.gestionevenements.dao.repository;

import ma.xproce.gestionevenements.dao.entities.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {
}
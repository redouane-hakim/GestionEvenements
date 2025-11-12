package ma.xproce.gestionevenements.service;

import ma.xproce.gestionevenements.dao.entities.Categorie;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CategorieService {
    Categorie save(Categorie categorie);
    Categorie update(Categorie categorie);
    void deleteById(int id);
    Optional<Categorie> findById(int id);
    List<Categorie> findAll();
}

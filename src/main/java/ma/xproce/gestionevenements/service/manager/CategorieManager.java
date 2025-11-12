package ma.xproce.gestionevenements.service.manager;

import ma.xproce.gestionevenements.dao.entities.Categorie;
import ma.xproce.gestionevenements.dao.repositories.CategorieRepository;
import ma.xproce.gestionevenements.service.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class CategorieManager implements CategorieService {

    @Autowired
    private CategorieRepository categorieRepository;

    @Override
    public Categorie save(Categorie c) {
        return categorieRepository.save(c);
    }

    @Override
    public Categorie update(Categorie c) {
        return categorieRepository.save(c);
    }

    @Override
    public void deleteById(int id) {
        categorieRepository.deleteById(id);
    }

    @Override
    public Optional<Categorie> findById(int id) {
        return categorieRepository.findById(id);
    }

    @Override
    public List<Categorie> findAll() {
        return categorieRepository.findAll();
    }
}

package ma.xproce.gestionevenements.service.manager;

import ma.xproce.gestionevenements.dao.entities.Categorie;
import ma.xproce.gestionevenements.dao.entities.Ressource;
import ma.xproce.gestionevenements.dao.repositories.RessourceRepository;
import ma.xproce.gestionevenements.service.RessourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class RessourceManager implements RessourceService {

    @Autowired
    private RessourceRepository ressourceRepository;

    @Override
    public Ressource save(Ressource r) {
        return ressourceRepository.save(r);
    }

    @Override
    public Ressource update(Ressource r) {
        return ressourceRepository.save(r);
    }

    @Override
    public void deleteById(int id) {
        ressourceRepository.deleteById(id);
    }

    @Override
    public Optional<Ressource> findById(int id) {
        return ressourceRepository.findById(id);
    }

    @Override
    public List<Ressource> findAll() {
        return ressourceRepository.findAll();
    }

    @Override
    public List<Ressource> findByCategorie(Categorie c) {
        return ressourceRepository.findByCategorie(c);
    }

    @Override
    public List<Ressource> searchByNom(String mot) {
        return ressourceRepository.findByNomContainingIgnoreCase(mot);
    }
}

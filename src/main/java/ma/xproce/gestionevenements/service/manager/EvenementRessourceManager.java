package ma.xproce.gestionevenements.service.manager;

import ma.xproce.gestionevenements.dao.entities.Evenement;
import ma.xproce.gestionevenements.dao.entities.EvenementRessource;
import ma.xproce.gestionevenements.dao.repositories.EvenementRessourceRepository;
import ma.xproce.gestionevenements.service.EvenementRessourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class EvenementRessourceManager implements EvenementRessourceService {

    @Autowired
    private EvenementRessourceRepository evenementRessourceRepository;

    @Override
    public EvenementRessource save(EvenementRessource er) {
        return evenementRessourceRepository.save(er);
    }

    @Override
    public void deleteById(int id) {
        evenementRessourceRepository.deleteById(id);
    }

    @Override
    public Optional<EvenementRessource> findById(int id) {
        return evenementRessourceRepository.findById(id);
    }

    @Override
    public List<EvenementRessource> findByEvenement(Evenement ev) {
        return evenementRessourceRepository.findByEvenement(ev);
    }
}

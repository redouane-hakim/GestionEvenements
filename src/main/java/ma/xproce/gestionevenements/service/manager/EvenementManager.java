package ma.xproce.gestionevenements.service.manager;

import ma.xproce.gestionevenements.dao.entities.Evenement;
import ma.xproce.gestionevenements.dao.entities.Utilisateur;
import ma.xproce.gestionevenements.dao.repositories.EvenementRepository;
import ma.xproce.gestionevenements.service.EvenementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class EvenementManager implements EvenementService {

    @Autowired
    private EvenementRepository evenementRepository;

    @Override
    public Evenement save(Evenement evenement) {
        return evenementRepository.save(evenement);
    }

    @Override
    public Evenement update(Evenement evenement) {
        return evenementRepository.save(evenement);
    }

    @Override
    public void deleteById(int id) {
        evenementRepository.deleteById(id);
    }

    @Override
    public Optional<Evenement> findById(int id) {
        return evenementRepository.findById(id);
    }

    @Override
    public List<Evenement> findAll() {
        return evenementRepository.findAll();
    }

    @Override
    public List<Evenement> findByOrganisateur(Utilisateur organisateur) {
        return evenementRepository.findByOrganisateur(organisateur);
    }
}

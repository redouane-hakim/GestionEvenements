package ma.xproce.gestionevenements.service.impl;

import lombok.RequiredArgsConstructor;
import ma.xproce.gestionevenements.dao.entities.Demande;
import ma.xproce.gestionevenements.dao.entities.Evenement;
import ma.xproce.gestionevenements.dao.entities.Utilisateur;
import ma.xproce.gestionevenements.dao.repository.EvenementRepository;
import ma.xproce.gestionevenements.service.EvenementService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EvenementServiceImpl implements EvenementService {
    private final EvenementRepository evenementRepository;

    @Override
    public List<Evenement> findAll() {
        return evenementRepository.findAllByOrderByDateDebutDesc();
    }

    @Override
    public List<Evenement> findLatest5() {
        return evenementRepository.findTop5ByOrderByDateDebutDesc();
    }

    @Override
    public Optional<Evenement> findById(Long id) {
        return evenementRepository.findById(id);
    }

    @Override
    public Evenement save(Evenement e) {
        return evenementRepository.save(e);
    }

    @Override
    @Transactional
    public Evenement creerDepuisDemandeAcceptee(Demande d, Utilisateur organisateur) {
        Evenement e = new Evenement();
        e.setTitre(d.getIntitule());
        e.setDescription(d.getDescription());
        e.setOrganisateur(organisateur);
        e.setDemande(d);
        // le reste sera complété par l'organisateur après acceptation
        return evenementRepository.save(e);
    }

    @Override
    public List<Evenement> findByOrganisateur(Long uid) {
        return evenementRepository.findByOrganisateur_UidOrderByDateDebutDesc(uid);
    }
}

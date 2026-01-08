package ma.xproce.gestionevenements.service;

import ma.xproce.gestionevenements.dao.entities.Demande;
import ma.xproce.gestionevenements.dao.entities.Evenement;
import ma.xproce.gestionevenements.dao.entities.Utilisateur;

import java.util.List;
import java.util.Optional;

public interface EvenementService {
    List<Evenement> findAll();
    List<Evenement> findLatest5();
    Optional<Evenement> findById(Long id);
    Evenement save(Evenement e);
    Evenement creerDepuisDemandeAcceptee(Demande d, Utilisateur organisateur);
    List<Evenement> findByOrganisateur(Long uid);
}

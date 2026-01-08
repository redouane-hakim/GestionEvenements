package ma.xproce.gestionevenements.service;

import ma.xproce.gestionevenements.dao.entities.Demande;
import ma.xproce.gestionevenements.dao.entities.Utilisateur;

import java.util.List;
import java.util.Optional;

public interface DemandeService {
    Demande creer(Demande demande);
    List<Demande> findAll();
    List<Demande> findByOrganisateurEmail(String email);
    Optional<Demande> findById(Long id);
    Demande accepter(Long id, Utilisateur validateur);
    Demande refuser(Long id, Utilisateur validateur);
}

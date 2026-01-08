package ma.xproce.gestionevenements.service;

import ma.xproce.gestionevenements.dao.entities.Utilisateur;

import java.util.List;
import java.util.Optional;

public interface UtilisateurService {
    Optional<Utilisateur> findByEmail(String email);
    Utilisateur save(Utilisateur u);
    List<Utilisateur> findAll();
    Optional<Utilisateur> findById(Long id);
}

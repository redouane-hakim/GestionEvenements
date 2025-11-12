package ma.xproce.gestionevenements.service;

import ma.xproce.gestionevenements.dao.entities.Utilisateur;
import ma.xproce.gestionevenements.dao.entities.Role;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UtilisateurService {
    Utilisateur save(Utilisateur utilisateur);
    Utilisateur update(Utilisateur utilisateur);
    void deleteById(int id);
    Optional<Utilisateur> findById(int id);
    Optional<Utilisateur> findByEmail(String email);
    List<Utilisateur> findByRole(Role role);
    List<Utilisateur> findAll();
}

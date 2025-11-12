package ma.xproce.gestionevenements.dao.repositories;

import ma.xproce.gestionevenements.dao.entities.Utilisateur;
import ma.xproce.gestionevenements.dao.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
    Optional<Utilisateur> findByEmail(String email);
    boolean existsByEmail(String email);
    List<Utilisateur> findByRole(Role role);
}

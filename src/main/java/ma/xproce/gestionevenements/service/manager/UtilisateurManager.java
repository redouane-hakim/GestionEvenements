package ma.xproce.gestionevenements.service.manager;

import ma.xproce.gestionevenements.dao.entities.Utilisateur;
import ma.xproce.gestionevenements.dao.entities.Role;
import ma.xproce.gestionevenements.dao.repositories.UtilisateurRepository;
import ma.xproce.gestionevenements.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class UtilisateurManager implements UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Override
    public Utilisateur save(Utilisateur u) {
        return utilisateurRepository.save(u);
    }

    @Override
    public Utilisateur update(Utilisateur u) {
        return utilisateurRepository.save(u);
    }

    @Override
    public void deleteById(int id) {
        utilisateurRepository.deleteById(id);
    }

    @Override
    public Optional<Utilisateur> findById(int id) {
        return utilisateurRepository.findById(id);
    }

    @Override
    public Optional<Utilisateur> findByEmail(String e) {
        return utilisateurRepository.findByEmail(e);
    }

    @Override
    public List<Utilisateur> findByRole(Role role) {
        return utilisateurRepository.findByRole(role);
    }

    @Override
    public List<Utilisateur> findAll() {
        return utilisateurRepository.findAll();
    }
}

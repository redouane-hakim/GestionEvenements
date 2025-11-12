package ma.xproce.gestionevenements.service;

import ma.xproce.gestionevenements.dao.entities.Evenement;
import ma.xproce.gestionevenements.dao.entities.Utilisateur;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface EvenementService {
    Evenement save(Evenement evenement);
    Evenement update(Evenement evenement);
    void deleteById(int id);
    Optional<Evenement> findById(int id);
    List<Evenement> findAll();
    List<Evenement> findByOrganisateur(Utilisateur organisateur);
}
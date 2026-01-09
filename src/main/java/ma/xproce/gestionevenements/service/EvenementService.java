package ma.xproce.gestionevenements.service;

import ma.xproce.gestionevenements.dto.DemandeDto;
import ma.xproce.gestionevenements.dto.EvenementDto;
import ma.xproce.gestionevenements.dto.UtilisateurDto;

import java.util.List;
import java.util.Optional;

public interface EvenementService {
    List<EvenementDto> findAll();
    List<EvenementDto> findLatest5();
    Optional<EvenementDto> findById(Long id);
    EvenementDto save(EvenementDto e);
    EvenementDto creerDepuisDemandeAcceptee(DemandeDto d, UtilisateurDto organisateur);
    List<EvenementDto> findByOrganisateur(Long uid);
}

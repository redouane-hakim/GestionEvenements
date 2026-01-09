package ma.xproce.gestionevenements.service;

import ma.xproce.gestionevenements.dto.DemandeDto;
import ma.xproce.gestionevenements.dto.UtilisateurDto;

import java.util.List;
import java.util.Optional;

public interface DemandeService {
    DemandeDto creer(DemandeDto demande);
    List<DemandeDto> findAll();
    List<DemandeDto> findByOrganisateurEmail(String email);
    Optional<DemandeDto> findById(Long id);
    DemandeDto accepter(Long id, UtilisateurDto validateur);
    DemandeDto refuser(Long id, UtilisateurDto validateur);
}

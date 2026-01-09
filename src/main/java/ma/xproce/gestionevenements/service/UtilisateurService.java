package ma.xproce.gestionevenements.service;

import ma.xproce.gestionevenements.dto.UtilisateurDto;

import java.util.List;
import java.util.Optional;

public interface UtilisateurService {
    Optional<UtilisateurDto> findByEmail(String email);
    UtilisateurDto save(UtilisateurDto u);
    List<UtilisateurDto> findAll();
    Optional<UtilisateurDto> findById(Long id);
}

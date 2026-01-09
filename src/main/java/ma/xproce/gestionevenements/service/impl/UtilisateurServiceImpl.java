package ma.xproce.gestionevenements.service.impl;

import lombok.RequiredArgsConstructor;
import ma.xproce.gestionevenements.dao.entities.Utilisateur;
import ma.xproce.gestionevenements.dao.repository.UtilisateurRepository;
import ma.xproce.gestionevenements.dto.UtilisateurDto;
import ma.xproce.gestionevenements.service.UtilisateurService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UtilisateurServiceImpl implements UtilisateurService {
    private final UtilisateurRepository utilisateurRepository;

    // 🔄 Conversion Entity -> DTO
    private UtilisateurDto toDto(Utilisateur u) {
        if (u == null) return null;
        return new UtilisateurDto(
                u.getUid(),
                u.getNom(),
                u.getPrenom(),
                u.getEmail(),
                u.getTelephone(),
                u.getRole(),
                u.getSignatureImageUrl(),
                u.getDateExpirationRole()
        );
    }

    // 🔄 Conversion DTO -> Entity
    private Utilisateur toEntity(UtilisateurDto dto) {
        if (dto == null) return null;
        Utilisateur u = new Utilisateur();
        u.setUid(dto.getUid());
        u.setNom(dto.getNom());
        u.setPrenom(dto.getPrenom());
        u.setEmail(dto.getEmail());
        u.setTelephone(dto.getTelephone());
        u.setRole(dto.getRole());
        u.setSignatureImageUrl(dto.getSignatureImageUrl());
        u.setDateExpirationRole(dto.getDateExpirationRole());
        return u;
    }

    @Override
    public Optional<UtilisateurDto> findByEmail(String email) {
        return utilisateurRepository.findByEmail(email).map(this::toDto);
    }

    @Override
    public UtilisateurDto save(UtilisateurDto dto) {
        Utilisateur saved = utilisateurRepository.save(toEntity(dto));
        return toDto(saved);
    }

    @Override
    public List<UtilisateurDto> findAll() {
        return utilisateurRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UtilisateurDto> findById(Long id) {
        return utilisateurRepository.findById(id).map(this::toDto);
    }
}

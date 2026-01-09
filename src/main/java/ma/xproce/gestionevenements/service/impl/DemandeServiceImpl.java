package ma.xproce.gestionevenements.service.impl;

import lombok.RequiredArgsConstructor;
import ma.xproce.gestionevenements.dao.entities.Demande;
import ma.xproce.gestionevenements.dao.entities.Status;
import ma.xproce.gestionevenements.dao.entities.Utilisateur;
import ma.xproce.gestionevenements.dao.repository.DemandeRepository;
import ma.xproce.gestionevenements.dto.DemandeDto;
import ma.xproce.gestionevenements.dto.UtilisateurDto;
import ma.xproce.gestionevenements.service.DemandeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DemandeServiceImpl implements DemandeService {
    private final DemandeRepository demandeRepository;

    // 🔄 Conversion Entity -> DTO
    private DemandeDto toDto(Demande d) {
        if (d == null) return null;
        return new DemandeDto(
                d.getDid(),
                d.getNomOrganisateur(),
                d.getPrenomOrganisateur(),
                d.getEmailOrganisateur(),
                d.getTelephoneOrganisateur(),
                d.getNomOrganisation(),
                d.getTypeEvenement(),
                d.getIntitule(),
                d.getDescription(),
                d.getSignatureOrganisateurImageUrl(),
                d.getDateDemande(),
                d.getStatus(),
                d.getDateValidation(),
                d.getValidateur() != null ? d.getValidateur().getUid() : null
        );
    }

    // 🔄 Conversion DTO -> Entity
    private Demande toEntity(DemandeDto dto) {
        if (dto == null) return null;
        Demande d = new Demande();
        d.setDid(dto.getDid());
        d.setNomOrganisateur(dto.getNomOrganisateur());
        d.setPrenomOrganisateur(dto.getPrenomOrganisateur());
        d.setEmailOrganisateur(dto.getEmailOrganisateur());
        d.setTelephoneOrganisateur(dto.getTelephoneOrganisateur());
        d.setNomOrganisation(dto.getNomOrganisation());
        d.setTypeEvenement(dto.getTypeEvenement());
        d.setIntitule(dto.getIntitule());
        d.setDescription(dto.getDescription());
        d.setSignatureOrganisateurImageUrl(dto.getSignatureOrganisateurImageUrl());
        d.setDateDemande(dto.getDateDemande());
        d.setStatus(dto.getStatus());
        d.setDateValidation(dto.getDateValidation());
        return d;
    }

    @Override
    public DemandeDto creer(DemandeDto dto) {
        Demande d = toEntity(dto);
        d.setStatus(Status.EN_ATTENTE);
        if (d.getDateDemande() == null) d.setDateDemande(LocalDateTime.now());
        return toDto(demandeRepository.save(d));
    }

    @Override
    public List<DemandeDto> findAll() {
        return demandeRepository.findAllByOrderByDateDemandeDesc()
                .stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public List<DemandeDto> findByOrganisateurEmail(String email) {
        return demandeRepository.findByEmailOrganisateurOrderByDateDemandeDesc(email)
                .stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public Optional<DemandeDto> findById(Long id) {
        return demandeRepository.findById(id).map(this::toDto);
    }

    @Override
    @Transactional
    public DemandeDto accepter(Long id, UtilisateurDto validateurDto) {
        Demande d = demandeRepository.findById(id).orElseThrow();
        d.setStatus(Status.ACCEPTEE);

        Utilisateur validateur = new Utilisateur();
        validateur.setUid(validateurDto.getUid());
        validateur.setNom(validateurDto.getNom());
        validateur.setPrenom(validateurDto.getPrenom());
        validateur.setEmail(validateurDto.getEmail());

        d.setValidateur(validateur);
        d.setDateValidation(LocalDateTime.now());
        return toDto(d);
    }

    @Override
    @Transactional
    public DemandeDto refuser(Long id, UtilisateurDto validateurDto) {
        Demande d = demandeRepository.findById(id).orElseThrow();
        d.setStatus(Status.REFUSEE);

        Utilisateur validateur = new Utilisateur();
        validateur.setUid(validateurDto.getUid());
        validateur.setNom(validateurDto.getNom());
        validateur.setPrenom(validateurDto.getPrenom());
        validateur.setEmail(validateurDto.getEmail());

        d.setValidateur(validateur);
        d.setDateValidation(LocalDateTime.now());
        return toDto(d);
    }
}

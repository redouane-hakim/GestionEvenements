package ma.xproce.gestionevenements.service.impl;

import lombok.RequiredArgsConstructor;
import ma.xproce.gestionevenements.dao.entities.Demande;
import ma.xproce.gestionevenements.dao.entities.Evenement;
import ma.xproce.gestionevenements.dao.entities.Utilisateur;
import ma.xproce.gestionevenements.dao.repository.EvenementRepository;
import ma.xproce.gestionevenements.dto.DemandeDto;
import ma.xproce.gestionevenements.dto.EvenementDto;
import ma.xproce.gestionevenements.dto.UtilisateurDto;
import ma.xproce.gestionevenements.service.EvenementService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EvenementServiceImpl implements EvenementService {
    private final EvenementRepository evenementRepository;

    // 🔄 Conversion Entity -> DTO
    private EvenementDto toDto(Evenement e) {
        if (e == null) return null;
        return new EvenementDto(
                e.getEid(),
                e.getTitre(),
                e.getDescription(),
                e.getLieu(),
                e.getDateDebut(),
                e.getDateFin(),
                e.getCategorie(), // ✅ String directement
                e.getAfficheUrl(),
                e.getOrganisateur() != null ? e.getOrganisateur().getUid() : null,
                e.getDemande() != null ? e.getDemande().getDid() : null
        );
    }

    // 🔄 Conversion DTO -> Entity
    private Evenement toEntity(EvenementDto dto) {
        if (dto == null) return null;
        Evenement e = new Evenement();
        e.setEid(dto.getEid());
        e.setTitre(dto.getTitre());
        e.setDescription(dto.getDescription());
        e.setLieu(dto.getLieu());
        e.setDateDebut(dto.getDateDebut());
        e.setDateFin(dto.getDateFin());
        e.setCategorie(dto.getCategorie()); // ✅ String
        e.setAfficheUrl(dto.getAfficheUrl());

        if (dto.getOrganisateurUid() != null) {
            Utilisateur u = new Utilisateur();
            u.setUid(dto.getOrganisateurUid());
            e.setOrganisateur(u);
        }

        if (dto.getDemandeDid() != null) {
            Demande d = new Demande();
            d.setDid(dto.getDemandeDid());
            e.setDemande(d);
        }

        return e;
    }

    @Override
    public List<EvenementDto> findAll() {
        return evenementRepository.findAllByOrderByDateDebutDesc()
                .stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public List<EvenementDto> findLatest5() {
        return evenementRepository.findTop5ByOrderByDateDebutDesc()
                .stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public Optional<EvenementDto> findById(Long id) {
        return evenementRepository.findById(id).map(this::toDto);
    }

    @Override
    public EvenementDto save(EvenementDto dto) {
        Evenement saved = evenementRepository.save(toEntity(dto));
        return toDto(saved);
    }

    @Override
    @Transactional
    public EvenementDto creerDepuisDemandeAcceptee(DemandeDto dDto, UtilisateurDto organisateurDto) {
        Demande d = new Demande();
        d.setDid(dDto.getDid());
        d.setIntitule(dDto.getIntitule());
        d.setDescription(dDto.getDescription());

        Utilisateur organisateur = new Utilisateur();
        organisateur.setUid(organisateurDto.getUid());
        organisateur.setNom(organisateurDto.getNom());
        organisateur.setPrenom(organisateurDto.getPrenom());
        organisateur.setEmail(organisateurDto.getEmail());

        Evenement e = new Evenement();
        e.setTitre(d.getIntitule());
        e.setDescription(d.getDescription());
        e.setOrganisateur(organisateur);
        e.setDemande(d);

        return toDto(evenementRepository.save(e));
    }

    @Override
    public List<EvenementDto> findByOrganisateur(Long uid) {
        return evenementRepository.findByOrganisateur_UidOrderByDateDebutDesc(uid)
                .stream().map(this::toDto).collect(Collectors.toList());
    }
}

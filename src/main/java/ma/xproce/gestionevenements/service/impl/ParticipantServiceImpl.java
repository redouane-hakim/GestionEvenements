package ma.xproce.gestionevenements.service.impl;

import lombok.RequiredArgsConstructor;
import ma.xproce.gestionevenements.dao.entities.Participant;
import ma.xproce.gestionevenements.dao.repository.ParticipantRepository;
import ma.xproce.gestionevenements.dto.ParticipantDto;
import ma.xproce.gestionevenements.service.ParticipantService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ParticipantServiceImpl implements ParticipantService {
    private final ParticipantRepository participantRepository;

    // 🔄 Conversion Entity -> DTO
    private ParticipantDto toDto(Participant p) {
        if (p == null) return null;
        return new ParticipantDto(
                p.getPid(),
                p.getNom(),
                p.getPrenom(),
                p.getEmail(),
                p.getTelephone(),
                p.getMessage(),
                p.getEvenement() != null ? p.getEvenement().getEid() : null
        );
    }

    // 🔄 Conversion DTO -> Entity
    private Participant toEntity(ParticipantDto dto) {
        if (dto == null) return null;
        Participant p = new Participant();
        p.setPid(dto.getPid());
        p.setNom(dto.getNom());
        p.setPrenom(dto.getPrenom());
        p.setEmail(dto.getEmail());
        p.setTelephone(dto.getTelephone());
        p.setMessage(dto.getMessage());
        // ⚠️ Ici, il faudra injecter l’Evenement via son repository si nécessaire
        return p;
    }

    @Override
    public ParticipantDto save(ParticipantDto dto) {
        Participant saved = participantRepository.save(toEntity(dto));
        return toDto(saved);
    }

    @Override
    public List<ParticipantDto> findByEvenement(Long eid) {
        return participantRepository.findByEvenement_EidOrderByDateSoumissionDesc(eid)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}

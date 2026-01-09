package ma.xproce.gestionevenements.service;

import ma.xproce.gestionevenements.dto.ParticipantDto;

import java.util.List;

public interface ParticipantService {
    ParticipantDto save(ParticipantDto p);
    List<ParticipantDto> findByEvenement(Long eid);
}

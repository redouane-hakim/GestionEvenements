package ma.xproce.gestionevenements.service;

import ma.xproce.gestionevenements.dao.entities.Participant;

import java.util.List;

public interface ParticipantService {
    Participant save(Participant p);
    List<Participant> findByEvenement(Long eid);
}

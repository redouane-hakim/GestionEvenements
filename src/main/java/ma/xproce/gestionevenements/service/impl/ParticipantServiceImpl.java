package ma.xproce.gestionevenements.service.impl;

import lombok.RequiredArgsConstructor;
import ma.xproce.gestionevenements.dao.entities.Participant;
import ma.xproce.gestionevenements.dao.repositories.ParticipantRepository;
import ma.xproce.gestionevenements.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParticipantServiceImpl implements ParticipantService {
    @Autowired
    ParticipantRepository participantRepository;

    @Override
    public Participant save(Participant p) {
        return participantRepository.save(p);
    }

    @Override
    public List<Participant> findByEvenement(Long eid) {
        return participantRepository.findByEvenement_EidOrderByDateSoumissionDesc(eid);
    }
}

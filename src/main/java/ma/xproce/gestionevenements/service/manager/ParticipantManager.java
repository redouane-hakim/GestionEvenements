package ma.xproce.gestionevenements.service.manager;

import ma.xproce.gestionevenements.dao.entities.Evenement;
import ma.xproce.gestionevenements.dao.entities.Participant;
import ma.xproce.gestionevenements.dao.repositories.ParticipantRepository;
import ma.xproce.gestionevenements.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class ParticipantManager implements ParticipantService {

    @Autowired
    private ParticipantRepository participantRepository;

    @Override
    public Participant save(Participant p) {
        return participantRepository.save(p);
    }

    @Override
    public void deleteById(int id) {
        participantRepository.deleteById(id);
    }

    @Override
    public Optional<Participant> findById(int id) {
        return participantRepository.findById(id);
    }

    @Override
    public List<Participant> findByEvenement(Evenement ev) {
        return participantRepository.findByEvenement(ev);
    }
}

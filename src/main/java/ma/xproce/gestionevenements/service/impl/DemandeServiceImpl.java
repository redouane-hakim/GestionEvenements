package ma.xproce.gestionevenements.service.impl;

import lombok.RequiredArgsConstructor;
import ma.xproce.gestionevenements.dao.entities.Demande;
import ma.xproce.gestionevenements.dao.entities.Status;
import ma.xproce.gestionevenements.dao.entities.Utilisateur;
import ma.xproce.gestionevenements.dao.repositories.DemandeRepository;
import ma.xproce.gestionevenements.service.DemandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DemandeServiceImpl implements DemandeService {

    @Autowired
    DemandeRepository demandeRepository;

    @Override
    public Demande creer(Demande demande) {
        demande.setStatus(Status.EN_ATTENTE);
        if (demande.getDateDemande() == null) demande.setDateDemande(LocalDateTime.now());
        return demandeRepository.save(demande);
    }

    @Override
    public List<Demande> findAll() {
        return demandeRepository.findAllByOrderByDateDemandeDesc();
    }

    @Override
    public List<Demande> findByOrganisateurEmail(String email) {
        return demandeRepository.findByEmailOrganisateurOrderByDateDemandeDesc(email);
    }

    @Override
    public Optional<Demande> findById(Long id) {
        return demandeRepository.findById(id);
    }

    @Override
    @Transactional
    public Demande accepter(Long id, Utilisateur validateur) {
        Demande d = demandeRepository.findById(id).orElseThrow();
        d.setStatus(Status.ACCEPTEE);
        d.setValidateur(validateur);
        d.setDateValidation(LocalDateTime.now());
        return d;
    }

    @Override
    @Transactional
    public Demande refuser(Long id, Utilisateur validateur) {
        Demande d = demandeRepository.findById(id).orElseThrow();
        d.setStatus(Status.REFUSEE);
        d.setValidateur(validateur);
        d.setDateValidation(LocalDateTime.now());
        return d;
    }





}

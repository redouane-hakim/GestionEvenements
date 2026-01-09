package ma.xproce.gestionevenements.service.impl;

import lombok.RequiredArgsConstructor;
import ma.xproce.gestionevenements.dao.entities.Question;
import ma.xproce.gestionevenements.dao.entities.Utilisateur;
import ma.xproce.gestionevenements.dao.repository.QuestionRepository;
import ma.xproce.gestionevenements.dto.QuestionDto;
import ma.xproce.gestionevenements.dto.UtilisateurDto;
import ma.xproce.gestionevenements.service.QuestionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;

    // 🔄 Conversion Entity -> DTO
    private QuestionDto toDto(Question q) {
        if (q == null) return null;
        return new QuestionDto(
                q.getQid(),
                q.getAuteurNom(),
                q.getContenu(),
                q.getDateQuestion(),
                q.getReponse(),
                q.getDateReponse(),
                q.getEvenement() != null ? q.getEvenement().getEid() : null,
                q.getRepondeur() != null ? q.getRepondeur().getUid() : null
        );
    }

    // 🔄 Conversion DTO -> Entity
    private Question toEntity(QuestionDto dto) {
        if (dto == null) return null;
        Question q = new Question();
        q.setQid(dto.getQid());
        q.setAuteurNom(dto.getAuteurNom());
        q.setContenu(dto.getContenu());
        q.setDateQuestion(dto.getDateQuestion());
        q.setReponse(dto.getReponse());
        q.setDateReponse(dto.getDateReponse());
        // ⚠️ Ici, il faudra injecter l’Evenement et le Repondeur via leurs repositories si nécessaire
        return q;
    }

    @Override
    public QuestionDto save(QuestionDto dto) {
        Question saved = questionRepository.save(toEntity(dto));
        return toDto(saved);
    }

    @Override
    public List<QuestionDto> findByEvenement(Long eid) {
        return questionRepository.findByEvenement_EidOrderByDateQuestionDesc(eid)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<QuestionDto> findById(Long id) {
        return questionRepository.findById(id).map(this::toDto);
    }

    @Override
    @Transactional
    public QuestionDto repondre(Long qid, String reponse, UtilisateurDto repondeurDto) {
        Question q = questionRepository.findById(qid).orElseThrow();
        q.setReponse(reponse);
        q.setDateReponse(LocalDateTime.now());

        // Conversion du DTO vers l'entité Utilisateur
        Utilisateur repondeur = new Utilisateur();
        repondeur.setUid(repondeurDto.getUid());
        repondeur.setNom(repondeurDto.getNom());
        repondeur.setPrenom(repondeurDto.getPrenom());
        repondeur.setEmail(repondeurDto.getEmail());
        repondeur.setTelephone(repondeurDto.getTelephone());
        repondeur.setRole(repondeurDto.getRole());
        repondeur.setSignatureImageUrl(repondeurDto.getSignatureImageUrl());
        repondeur.setDateExpirationRole(repondeurDto.getDateExpirationRole());

        q.setRepondeur(repondeur);

        return toDto(q);
    }
}

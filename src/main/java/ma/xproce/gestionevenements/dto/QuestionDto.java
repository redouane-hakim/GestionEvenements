package ma.xproce.gestionevenements.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link ma.xproce.gestionevenements.dao.entities.Question}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDto implements Serializable {
    Long qid;
    String auteurNom;
    String contenu;
    LocalDateTime dateQuestion;
    String reponse;
    LocalDateTime dateReponse;
    Long evenementEid;
    Long repondeurUid;
}
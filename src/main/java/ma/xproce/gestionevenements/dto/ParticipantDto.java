package ma.xproce.gestionevenements.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link ma.xproce.gestionevenements.dao.entities.Participant}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantDto implements Serializable {
    Long pid;
    @NotBlank
    String nom;
    @NotBlank
    String prenom;
    @Email
    @NotBlank
    String email;
    String telephone;
    String message;
    Long evenementId;
}
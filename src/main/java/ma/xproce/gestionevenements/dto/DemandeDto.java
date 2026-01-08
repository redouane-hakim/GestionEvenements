package ma.xproce.gestionevenements.dto;

import lombok.*;
import ma.xproce.gestionevenements.dao.entities.Status;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link ma.xproce.gestionevenements.dao.entities.Demande}
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DemandeDto implements Serializable {
    Long did;
    String nomOrganisateur;
    String prenomOrganisateur;
    String emailOrganisateur;
    String telephoneOrganisateur;
    String nomOrganisation;
    String typeEvenement;
    String intitule;
    String description;
    String signatureOrganisateurImageUrl;
    LocalDateTime dateDemande;
    Status status;
    LocalDateTime dateValidation;
    Long validateurUid;
}
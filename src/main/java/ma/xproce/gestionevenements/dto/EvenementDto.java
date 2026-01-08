package ma.xproce.gestionevenements.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link ma.xproce.gestionevenements.dao.entities.Evenement}
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EvenementDto implements Serializable {
    Long eid;
    String titre;
    String description;
    String lieu;
    LocalDateTime dateDebut;
    LocalDateTime dateFin;
    String categorie;
    String afficheUrl;
    Long organisateurUid;
    Long demandeDid;
}
package ma.xproce.gestionevenements.dto;

import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link ma.xproce.gestionevenements.dao.entities.EvenementRessource}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EvenementRessourceDto implements Serializable {
    Long eRid;
    Double quantite;
    String unite;
    String notes;
    String nomLibre;
    Long evenementEid;
    Long ressourceCatalogueRid;
}
package ma.xproce.gestionevenements.dto;

import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link ma.xproce.gestionevenements.dao.entities.Ressource}
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RessourceDto implements Serializable {
    Long rid;
    String nom;
    boolean disponible;
    Long categorieCid;
}
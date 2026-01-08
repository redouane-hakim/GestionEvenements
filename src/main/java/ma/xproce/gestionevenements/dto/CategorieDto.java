package ma.xproce.gestionevenements.dto;

import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link ma.xproce.gestionevenements.dao.entities.Categorie}
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategorieDto implements Serializable {
    Long cid;
    String nom;
    String description;
}
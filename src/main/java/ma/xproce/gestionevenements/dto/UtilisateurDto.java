package ma.xproce.gestionevenements.dto;

import lombok.*;
import ma.xproce.gestionevenements.dao.entities.Role;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link ma.xproce.gestionevenements.dao.entities.Utilisateur}
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UtilisateurDto implements Serializable {
    Long uid;
    String nom;
    String prenom;
    String email;
    String telephone;
    Role role;
    String signatureImageUrl;
    LocalDateTime dateExpirationRole;
}
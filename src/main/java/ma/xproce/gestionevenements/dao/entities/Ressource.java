package ma.xproce.gestionevenements.dao.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Ressource {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rid;

    private String nom;
    private boolean disponible = true;

    @ManyToOne
    private Categorie categorie;
}

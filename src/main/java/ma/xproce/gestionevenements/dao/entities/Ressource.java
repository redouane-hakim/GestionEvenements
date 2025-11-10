package ma.xproce.gestionevenements.dao.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Ressource {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rid;
    private String nom;
    private boolean disponible;
    @ManyToOne
    private Category categorie;

}

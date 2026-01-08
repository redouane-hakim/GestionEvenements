package ma.xproce.gestionevenements.dao.entities;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class EvenementRessource {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eRid;

    private Double quantite;
    private String unite;
    private String notes;
    private String nomLibre;

    @ManyToOne
    private Evenement evenement;

    @ManyToOne
    private Ressource ressourceCatalogue;
}

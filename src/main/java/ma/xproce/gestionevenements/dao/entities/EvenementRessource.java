package ma.xproce.gestionevenements.dao.entities;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class EvenementRessource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int eRid;

    private Double quantite;
    private String unite;
    private String notes;
    private String nomLibre;

    @ManyToOne
    private Evenement evenement;

    @ManyToOne
    private Ressource ressourceCatalogue;
}

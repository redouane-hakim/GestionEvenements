package ma.xproce.gestionevenements.dao.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Evenement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int eid;

    private String titre;
    @Lob
    private String description;
    private String lieu;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private String categorie;
    @Column(unique = true)
    private String afficheUrl;

    @ManyToOne
    private Utilisateur organisateur;

    @OneToOne
    private Demande demande;

    @OneToMany(mappedBy = "evenement")
    private List<Question> questions;

    @OneToMany(mappedBy = "evenement")
    private List<Participant> participants;

    @OneToMany(mappedBy = "evenement")
    private List<EvenementRessource> affectations;
}

package ma.xproce.gestionevenements.dao.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int qid;

    private String auteurNom;

    @Lob
    private String contenu;

    private LocalDateTime dateQuestion = LocalDateTime.now();
    @Lob
    private String reponse;
    private LocalDateTime dateReponse;

    @ManyToOne
    private Evenement evenement;

    @ManyToOne
    private Utilisateur repondeur;
}
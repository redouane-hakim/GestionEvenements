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
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pid;

    private String nom;
    private String prenom;
    private String email;
    private String telephone;

    @Lob
    private String message;

    private LocalDateTime dateSoumission = LocalDateTime.now();

    @ManyToOne
    private Evenement evenement;
}

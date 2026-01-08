package ma.xproce.gestionevenements.dao.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Demande {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long did;

    private String nomOrganisateur;
    private String prenomOrganisateur;
    private String emailOrganisateur;
    private String telephoneOrganisateur;

    private String nomOrganisation;
    private String typeEvenement;
    private String intitule;

    @Lob
    private String description;

    private String signatureOrganisateurImageUrl;

    private LocalDateTime dateDemande = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private Status status = Status.EN_ATTENTE;

    private LocalDateTime dateValidation;

    @ManyToOne
    private Utilisateur validateur;
}

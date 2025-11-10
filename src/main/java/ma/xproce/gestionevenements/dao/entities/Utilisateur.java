package ma.xproce.gestionevenements.dao.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Utilisateur {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uid;
    private String nom;
    private String prenom;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String telephone;
    private String motDePasse;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(unique = true)
    private String signatureImageUrl;
    private LocalDateTime dateExpirationRole;
}

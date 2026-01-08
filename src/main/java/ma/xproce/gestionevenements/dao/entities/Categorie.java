package ma.xproce.gestionevenements.dao.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Categorie {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cid;

    private String nom;
    @Lob
    private String description;

    @OneToMany(mappedBy = "categorie", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    @Builder.Default
    private List<Ressource> ressources = new ArrayList<>();
}

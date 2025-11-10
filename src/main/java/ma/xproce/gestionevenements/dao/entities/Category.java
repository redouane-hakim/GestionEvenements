package ma.xproce.gestionevenements.dao.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Category {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cid;
    private String name;
    private String description;
    @OneToMany(mappedBy = "categorie",cascade= CascadeType.ALL)
    private List<Ressource> ressources;
}

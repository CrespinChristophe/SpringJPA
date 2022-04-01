package technifutur.crespin.demo_jpa.models.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter // on peut spécifie un getter/setter juste pour un attribut en le mettant au-dessus et pas en général
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Section {

        @Id
        @Column(name = "section_id", columnDefinition = "INT(11)")
        private long id;
        @Column(name = "section_name")
        private String name;
        @Column(columnDefinition = "INT(11)")
        private Long delegateId; //int n'accepte pas NULL mais la classe wrapper Integer, oui
                                    // le camelcase sera généré auto en snake case

        @OneToMany(mappedBy = "section")
        private List<Professeur> professeurs = new ArrayList<>();
}


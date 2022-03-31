package technifutur.crespin.demo_jpa.models.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "course")
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Cours {

    @Id
    @Column(name = "course_id", length = 8 /*columnDefinition = "VARCHAR(8)"*/)//vu que c'est un String il s'attend auto à
    private String id;                                                         // un VARCHAR, donc juste mettre length
    @Column(name = "course_name", length = 200/*columnDefinition = "VARCHAR(200)"*/)
    private String name;
    @Column(name = "course_ects", columnDefinition = "DECIMAL(3,1)")
    private float ects;
    @Column(columnDefinition = "INT(11)")
    private Long professorId;

}

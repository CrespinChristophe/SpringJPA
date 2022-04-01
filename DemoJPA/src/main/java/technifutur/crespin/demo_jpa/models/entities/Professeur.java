package technifutur.crespin.demo_jpa.models.entities;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "professor")
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Professeur {

    @Id
    @Column(name = "professor_id", columnDefinition = "INT(11)")
    private long id;
    @Column(name = "professor_name", length = 30)
    private String name;
    @Column(name = "professor_surname", length = 30)
    private String surname;
    @Column(columnDefinition = "INT(11)")
    private long sectionId;
    @Column(name = "professor_office", columnDefinition = "INT(11)")
    private long office;
    @Column(name = "professor_email", length = 30)
    private String email;
    @Column(name = "professor_hire_date")
    private LocalDateTime hireDate;
    @Column(name = "professor_wage", columnDefinition = "INT(11)")
    private long wage;

    @OneToMany(mappedBy = "professeur")
    private List<Cours> course = new ArrayList<>();

    @ManyToOne
    private Section section;

}

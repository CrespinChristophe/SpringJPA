package technifutur.crespin.demo_jpa.data;

import org.springframework.stereotype.Repository;
import technifutur.crespin.demo_jpa.exceptions.ElementAlreadyExistsException;
import technifutur.crespin.demo_jpa.models.entities.Cours;
import technifutur.crespin.demo_jpa.models.entities.Professeur;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository

public class ProfesseurRepository {

    private final EntityManager manager;

    public ProfesseurRepository(EntityManager manager){
        this.manager = manager;
    }

    public Professeur getOne(Long id){
        return manager.find(Professeur.class, id);
    }

    public List<Professeur> getAll(){
        TypedQuery<Professeur> query = manager.createQuery("SELECT p FROM Professeur p", Professeur.class);//JPQL est sensible à la casse
        return query.getResultList();
    }

    @Transactional //pour dire que c'est Spring qui gère ça...dit qu'au début de la méthode on ouvre une transaction et à la fin
    //on la ferme (plus de commit, rollback, etc)
    public void insert(Professeur toInsert) throws IllegalArgumentException{

        if(getOne(toInsert.getId()) != null)
            throw new ElementAlreadyExistsException();

        manager.persist(toInsert);
    }

    @Transactional
    public Professeur delete(Long id){

        Professeur toDelete = getOne(id);

        if(toDelete != null)
            manager.remove(toDelete);
        return toDelete;
    }

  /*  //@Transactional
    public Professeur update(Professeur toUpdate) {
        Cours c = getOne(toUpdate.getId());

        if (c != null) {
            c.setName(toUpdate.getName());
            c.setEcts(toUpdate.getEcts());
            c.setProfessorId(toUpdate.getProfessorId());
        }
        return c;
    }*/
}

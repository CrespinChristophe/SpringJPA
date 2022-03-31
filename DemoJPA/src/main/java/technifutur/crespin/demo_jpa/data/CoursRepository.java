package technifutur.crespin.demo_jpa.data;

import org.springframework.stereotype.Repository;
import technifutur.crespin.demo_jpa.exceptions.ElementAlreadyExistsException;
import technifutur.crespin.demo_jpa.models.entities.Cours;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository

public class CoursRepository {

    private final EntityManager manager;

    public CoursRepository(EntityManager manager){
        this.manager = manager;
    }

    public Cours getOne(String id){
        return manager.find(Cours.class, id);
    }

    public List<Cours> getAll(){
        TypedQuery<Cours> query = manager.createQuery("SELECT c FROM Cours c", Cours.class);//JPQL est sensible à la casse
        return query.getResultList();
    }

    @Transactional //pour dire que c'est Spring qui gère ça...dit qu'au début de la méthode on ouvre une transaction et à la fin
                    //on la ferme (plus de commit, rollback, etc)
    public void insert(Cours toInsert) throws IllegalArgumentException{

        if(getOne(toInsert.getId()) != null)
            throw new ElementAlreadyExistsException();

        manager.persist(toInsert);
    }

    @Transactional
    public Cours delete(String id){

        Cours toDelete = getOne(id);

        if(toDelete != null)
            manager.remove(toDelete);
        return toDelete;
    }

    //@Transactional
    public Cours update(Cours toUpdate) {
        Cours c = getOne(toUpdate.getId());

        if (c != null) {
            c.setName(toUpdate.getName());
            c.setEcts(toUpdate.getEcts());
            c.setProfessorId(toUpdate.getProfessorId());
    }
        return c;
    }
}

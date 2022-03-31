package technifutur.crespin.demo_jpa.data;

import org.springframework.stereotype.Repository;
import technifutur.crespin.demo_jpa.models.entities.Section;

import javax.persistence.*;
import java.util.List;

@Repository
public class SectionRepository {

    //private final Scanner sc;

    private final EntityManagerFactory managerFactory;


    public SectionRepository(EntityManagerFactory managerFactory /*Scanner sc*/) {
        //this.sc = sc;
        this.managerFactory = managerFactory;
    }

   /* public SectionRepository(EntityManagerFactory managerFactory) {
        this.managerFactory = managerFactory;
    }*/

    public Section getOne(long id) {
        EntityManager manager = managerFactory.createEntityManager();
        Section section = manager.find(Section.class, id);//.class permet de récupérer un objet d'une classe
        manager.close();
        return section;

    }

    public List<Section> getAll(){
        EntityManager manager = managerFactory.createEntityManager();

        TypedQuery<Section> query = manager.createQuery("SELECT s FROM Section s", Section.class);//JPQL, pas SQL
        List<Section> list = query.getResultList();

        manager.close();

        return list;
    }

    public boolean insert(Section toInsert) {
        EntityManager manager = managerFactory.createEntityManager();//on crée notre entitymanager à chq méthode
        manager.getTransaction().begin();//commence la transaction   //puis on le ferme à la fin de chq méthode
        try {
            manager.persist(toInsert);
            manager.getTransaction().commit();//valide la transaction
            return true;

        } catch (EntityExistsException ex) {
            manager.getTransaction().rollback();//repasse dans l'état précédent de la DB
            return false;
        } catch (RollbackException ex) {
            return false;
        } finally {
            manager.close();
        }

    }

    public Section delete(long id) {
        EntityManager manager = managerFactory.createEntityManager();

        manager.getTransaction().begin();


        try {
            Section toDelete = manager.find(Section.class, id);
            manager.remove(toDelete);
            manager.getTransaction().commit();
            return toDelete;
        } catch (Exception ex) {
            return null;
        } finally {
            manager.close();
        }
    }

    public Section update(long id, Section newValues) {

        EntityManager manager = managerFactory.createEntityManager();
        manager.getTransaction().begin();//commence la transaction

        Section toUpdate = manager.find(Section.class, id);//si l'id que j'ai donné ne rend lieu à aucune section alors renvoit null

        //toUpdate.setId(newValues.getId());

        if(toUpdate != null) {
            toUpdate.setName(newValues.getName());
            toUpdate.setDelegateId(newValues.getDelegateId());
        }
        else
            manager.getTransaction().rollback();

        manager.getTransaction().commit();
        manager.close();

        return toUpdate;//renvoit l'objet détaché
    }
}

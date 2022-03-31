package technifutur.crespin.demo_jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import technifutur.crespin.demo_jpa.data.CoursRepository;
//import technifutur.crespin.demo_jpa.data.SectionDAO;
import technifutur.crespin.demo_jpa.data.ProfesseurRepository;
import technifutur.crespin.demo_jpa.data.SectionRepository;
import technifutur.crespin.demo_jpa.models.entities.Cours;
import technifutur.crespin.demo_jpa.models.entities.Section;

import java.util.List;

@SpringBootApplication
public class DemoJpaApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(DemoJpaApplication.class, args);//methode qui lance le container

		SectionRepository dao = context.getBean(SectionRepository.class);

		Section section = dao.getOne(1010);
		System.out.println(section);

		Section toAdd = new Section(10000, "ma section", 1L);
		if(dao.insert(toAdd)) {
			System.out.println("il a été ajouté");
		}
		else{
			System.out.println("il n'a pas été ajouté");
		}

		Section toUpdate = dao.getOne(10000);

		toUpdate.setName("au final tu ne seras pas deleted");
		toUpdate.setDelegateId(1L);

		dao.update(10000, toUpdate);

		List<Section> list = dao.getAll();

		list.forEach(System.out::println);

		CoursRepository repo = context.getBean(CoursRepository.class);
		// get one
		Cours c = repo.getOne("EG1010");
		System.out.println(c);
		// get all
		List<Cours> List = repo.getAll();
		list.forEach(System.out::println);

		//insert
		Cours c1= new Cours("GG0000", "toDelete", 0, 1L);
		repo.insert(c1);

		Cours c2 = repo.delete("GG0000");
		System.out.println(c2);

		//update

		c1.setName("nom modifié");
		Cours coursModifie = repo.update(c1);
		System.out.println(coursModifie);

		//Professeur

		ProfesseurRepository profRepo = context.getBean(ProfesseurRepository.class);




	}

}

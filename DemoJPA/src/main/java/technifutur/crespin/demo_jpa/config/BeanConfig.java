package technifutur.crespin.demo_jpa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import technifutur.crespin.demo_jpa.Boite;
import technifutur.crespin.demo_jpa.data.SectionDAO;

import java.util.Scanner;
import java.util.function.Predicate;

@Configuration

public class BeanConfig {

    @Bean
    public Scanner sc(){

        return new Scanner(System.in);

    }

    //Dans le cas où on souhaite un bean d'une classe générique

   // @Bean

    public Boite<String> boite(){
        return new Boite<>();
    }

    public Predicate<Integer> isEven(){
        return null;
    }

}

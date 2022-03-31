package technifutur.crespin.demo_jpa.exceptions;

public class ElementAlreadyExistsException extends RuntimeException{

    public ElementAlreadyExistsException(){
        super("l'élément existe déjà");
    }

}

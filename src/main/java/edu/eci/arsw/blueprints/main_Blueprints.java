package edu.eci.arsw.blueprints;


import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.services.BlueprintsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class main_Blueprints implements CommandLineRunner {

    @Autowired
    BlueprintsServices services;

    public static void main(String[] args) {
        SpringApplication.run(main_Blueprints.class,args);
    }

    public void prettyPrint(String author, String author1){
        System.out.println("Añadiendo planos al autor "+author+"....");
        System.out.println("Planos añadido con exito");
        System.out.println("");
        System.out.println("Añadiendo planos al autor "+author1+"....");
        System.out.println("Planos añadido con exito");
        System.out.println("");
    }

    @Override
    public void run(String... args) throws Exception{
        int planos = 10;
        String author = "Diego";
        String author1 = "Cristian";
        prettyPrint(author,author1);
        for(int i = 0;i<planos;i++){
            services.addNewBlueprint(new Blueprint(author,"plano"+(i*2)));
            services.addNewBlueprint(new Blueprint(author1,"plano"+(i*2)+1));
        }
        System.out.println("--------PRUEBA DE REGISTRO DE PLANOS--------");
        System.out.println(services.getAllBlueprints());
        System.out.println("--------PRUEBA DE REGISTRO DE CONSULTA DE PLANOS PARA AUTOR CRISTIAN--------");
        System.out.println(services.getBlueprintsByAuthor("Cristian"));

    }






}

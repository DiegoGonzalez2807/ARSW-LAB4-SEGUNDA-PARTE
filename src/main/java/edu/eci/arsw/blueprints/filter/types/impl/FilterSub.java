package edu.eci.arsw.blueprints.filter.types.impl;

import edu.eci.arsw.blueprints.filter.types.filterType;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;

import java.util.Set;

public class FilterSub implements filterType {
    /**
     * Funcion sobreescrita para que filtre los puntos de un blueprint por
     * submuestreo. Eso significa que se revisa la lista e intercalado
     * @param bp
     * @throws BlueprintNotFoundException
     */
    @Override
    public void filterBlueprint(Blueprint bp) throws BlueprintNotFoundException {
        for(int i = 0; i<=bp.getPoints().size();i++){
            if(i%2 == 0){
                bp.getPoints().remove(i);
            }
        }
    }

    @Override
    public void filterBlueprints(Set<Blueprint> blueprints) throws BlueprintPersistenceException, BlueprintNotFoundException {
        for(Blueprint print: blueprints){
            filterBlueprint(print);
        }
    }

    @Override
    public void filterPrintsByAuthor(String author,Set<Blueprint> blueprints) throws BlueprintNotFoundException {
        for(Blueprint print: blueprints){
            if(print.getAuthor().equals(author)){
                filterBlueprint(print);
            }
        }
    }
}

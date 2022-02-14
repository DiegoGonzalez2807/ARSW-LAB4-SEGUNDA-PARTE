package edu.eci.arsw.blueprints.filter.types.impl;

import edu.eci.arsw.blueprints.filter.types.filterType;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;

public class FilterSub implements filterType {
    @Override
    public void filterBlueprint(Blueprint bp) throws BlueprintNotFoundException {
        
    }

    @Override
    public void filterBlueprints() throws BlueprintPersistenceException, BlueprintNotFoundException {

    }

    @Override
    public void filterPrintsByAuthor(String author) throws BlueprintNotFoundException {

    }
}

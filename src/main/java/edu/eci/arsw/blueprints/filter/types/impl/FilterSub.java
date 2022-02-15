package edu.eci.arsw.blueprints.filter.types.impl;

import edu.eci.arsw.blueprints.filter.types.filterType;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;

import java.util.Set;

public class FilterSub implements filterType {
    @Override
    public void filterBlueprint(Blueprint bp) throws BlueprintNotFoundException {

    }

    @Override
    public void filterBlueprints(Set<Blueprint> blueprints) throws BlueprintPersistenceException, BlueprintNotFoundException {

    }

    @Override
    public void filterPrintsByAuthor(String author,Set<Blueprint> blueprints) throws BlueprintNotFoundException {

    }
}

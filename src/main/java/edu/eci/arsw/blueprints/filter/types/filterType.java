package edu.eci.arsw.blueprints.filter.types;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;

public interface filterType {
    public void filterBlueprint(Blueprint bp) throws BlueprintNotFoundException;
    public void filterBlueprints() throws BlueprintPersistenceException, BlueprintNotFoundException;
    public void filterPrintsByAuthor(String author) throws BlueprintNotFoundException;
}

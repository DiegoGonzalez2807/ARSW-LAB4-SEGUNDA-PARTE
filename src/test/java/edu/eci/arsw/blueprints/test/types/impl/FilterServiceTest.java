package edu.eci.arsw.blueprints.test.types.impl;

import edu.eci.arsw.blueprints.filter.services.FilterService;
import edu.eci.arsw.blueprints.filter.types.impl.filterRedundancy;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.impl.InMemoryBlueprintPersistence;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import org.junit.Test;
import org.junit.Assert;
public class FilterServiceTest {


    @Test
    public void DeberiaFiltrarPorRedundanciaBlueprint() throws BlueprintNotFoundException, BlueprintPersistenceException{
        InMemoryBlueprintPersistence ibpp=new InMemoryBlueprintPersistence();
        filterRedundancy fr = new filterRedundancy();
        Point[] pts0=new Point[]{new Point(40, 40),new Point(40,40), new Point(40,40),new Point(15, 15)};
        Blueprint bp0=new Blueprint("mack", "mypaint",pts0);

        ibpp.saveBlueprint(bp0);

        fr.filterBlueprint(bp0);

        Assert.assertEquals(2,bp0.getPoints().size());


    }
}

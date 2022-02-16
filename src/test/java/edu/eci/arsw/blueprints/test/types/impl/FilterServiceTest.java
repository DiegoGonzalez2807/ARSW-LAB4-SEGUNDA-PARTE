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

import java.util.HashSet;
import java.util.Set;

public class FilterServiceTest {


    @Test
    public void DeberiaFiltrarPorRedundanciaBlueprint() throws BlueprintNotFoundException, BlueprintPersistenceException{
        InMemoryBlueprintPersistence ibpp=new InMemoryBlueprintPersistence();
        filterRedundancy fr = new filterRedundancy();
        Point[] pts0=new Point[]{new Point(40, 40),new Point(15,15),new Point(40,40), new Point(40,40),new Point(15, 15),new Point(15,15)};
        Point[] pts1 = new Point[]{new Point(15,14)};
        Blueprint bp0=new Blueprint("mack", "mypaint",pts0);
        Blueprint bp1 = new Blueprint("prueba","2",pts1);
        Blueprint bp2 = new Blueprint("prueba","3",new Point[]{});
        ibpp.saveBlueprint(bp0);
        fr.filterBlueprint(bp0);
        Assert.assertEquals(2,bp0.getPoints().size());
        ibpp.saveBlueprint(bp1);
        fr.filterBlueprint(bp1);
        Assert.assertEquals(1,bp1.getPoints().size());
        ibpp.saveBlueprint(bp2);
        fr.filterBlueprint(bp2);
        Assert.assertEquals(0,bp2.getPoints().size());
    }

    @Test
    public void DeberiaFiltrarPorRedundanciaBluePrints(){
        filterRedundancy fr = new filterRedundancy();
        Point[] pts0=new Point[]{new Point(40, 40),new Point(15,15),new Point(40,40), new Point(40,40),new Point(15, 15),new Point(15,15)};
        Point[] pts1 = new Point[]{new Point(15,14)};
        Blueprint bp0=new Blueprint("mack", "mypaint",pts0);
        Blueprint bp1 = new Blueprint("prueba","2",pts1);
        Blueprint bp2 = new Blueprint("prueba","3",new Point[]{});
        Set<Blueprint> sbp = new HashSet<Blueprint>();
        sbp.add(bp0);sbp.add(bp1);sbp.add(bp2);
        try {
            fr.filterBlueprints(sbp);
        } catch (BlueprintPersistenceException e) {
            e.printStackTrace();
        } catch (BlueprintNotFoundException e) {
            e.printStackTrace();
        }
        Blueprint[] sbpA = (Blueprint[])sbp.toArray();
        Assert.assertEquals(2,sbpA[0]);
        Assert.assertEquals(1,sbpA[1]);
        Assert.assertEquals(0,sbpA[1]);

    }

    @Test
    public void DeberiaFiltrarPorRedundanciaBluePrintByAuthor(){
        filterRedundancy fr = new filterRedundancy();
        Point[] pts0=new Point[]{new Point(40, 40),new Point(15,15),new Point(40,40), new Point(40,40),new Point(15, 15),new Point(15,15)};
        Point[] pts1 = new Point[]{new Point(15,14)};
        Blueprint bp0=new Blueprint("mack", "mypaint",pts0);
        Blueprint bp1 = new Blueprint("prueba","2",pts1);
        Blueprint bp2 = new Blueprint("prueba","3",new Point[]{});
        Set<Blueprint> sbp = new HashSet<Blueprint>();
        sbp.add(bp0);sbp.add(bp1);sbp.add(bp2);
        try {
            fr.filterPrintsByAuthor("prueba",sbp);
        } catch (BlueprintNotFoundException e) {
            e.printStackTrace();
        }
        Blueprint[] sbpA = (Blueprint[])sbp.toArray();
        Assert.assertEquals(6,sbpA[0]);
        Assert.assertEquals(1,sbpA[1]);
        Assert.assertEquals(0,sbpA[1]);
    }
}

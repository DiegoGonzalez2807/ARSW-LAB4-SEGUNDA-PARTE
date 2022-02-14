package edu.eci.arsw.blueprints.filter.services;

import edu.eci.arsw.blueprints.filter.types.filterType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class FilterService {
    @Autowired
    @Qualifier("Redundancy")
    filterType filter;

    public void filterBlueprint(){

    }

    public void filterBlueprints(){

    }
}

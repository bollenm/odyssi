package lu.arhs.odyssi.sparqlEngine;

import org.junit.Test;

import lu.arhs.odyssi.core.SparqlQuery;
import lu.arhs.odyssi.sparqlEngine.impl.SparqlEngineImpl;


import static org.junit.Assert.*;

/**
 * @author bollenma
 */
public class SparqlEngineTest {

    @Test
    public void testProcess() throws Exception {

        SparqlEngine sparqlEngine = new SparqlEngineImpl();

        SparqlQuery sparqlQuery = new SparqlQuery();
        sparqlQuery.setWhere("where {?dataset_id ?p ?o}");

        sparqlEngine.process(sparqlQuery);

    }
}
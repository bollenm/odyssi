package lu.arhs.odyssi.sparqlengine;

import org.junit.Test;

import lu.arhs.odyssi.core.SparqlQuery;
import lu.arhs.odyssi.sparqlengine.impl.SparqlEngineImpl;

/**
 * @author bollenma
 */
public class SparqlEngineTest {

    @Test
    public void testProcess() throws Exception {

        SparqlEngine sparqlEngine = new SparqlEngineImpl();

        SparqlQuery sparqlQuery = new SparqlQuery();
        sparqlQuery.setSelect("*");
        sparqlQuery.setWhere("?dataset_uri ?p \"garbage\". " +
                "?dataset_uri skos:altLabel ?altlabel");

        sparqlEngine.process(sparqlQuery);

    }
}
package lu.arhs.odyssi.sparqlEngine;

import java.util.Collection;

import lu.arhs.odyssi.core.SparqlQuery;

/**
 * @author bollenma
 */
public interface SparqlEngine {

    Collection<String> process(SparqlQuery sparqlQuery);


}

package lu.arhs.odyssi.sparqlengine;

import java.util.Collection;

import lu.arhs.odyssi.core.SparqlQuery;

/**
 * @author bollenma
 */
public interface SparqlEngine {

    Collection<String> process(SparqlQuery sparqlQuery);


}

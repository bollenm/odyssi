package lu.arhs.odyssi.sparqlengine.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;

import lu.arhs.odyssi.core.SparqlQuery;
import lu.arhs.odyssi.sparqlengine.SparqlEngine;

/**
 * @author bollenma
 */
public class SparqlEngineImpl implements SparqlEngine {

    private String inputFileName = "src/main/resources/goc-example.ttl";

    @Override
    public Collection<String> process(final SparqlQuery sparqlQuery) {

        final Model model = ModelFactory.createDefaultModel();

        // read the RDF/XML file
        model.read(inputFileName);

        final String select = sparqlQuery.getSelect();

        final String queryString = sparqlQuery.constructQuery();
        final Query query = QueryFactory.create(queryString);

        final Collection<String> ret = new ArrayList<>();

        try (QueryExecution qexec = QueryExecutionFactory.create(query, model)) {
            final ResultSet results = qexec.execSelect();
            for (; results.hasNext(); ) {
                QuerySolution querySolution = results.nextSolution();

                final Resource datasetUri = querySolution.getResource(select);

                if (datasetUri != null) {
                    ret.add(datasetUri.getURI());
                }

            }
        }

        return ret;
    }


}

package lu.arhs.odyssi.sparqlengine.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.jena.graph.impl.LiteralLabel;
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

import lu.arhs.odyssi.core.Dataset;
import lu.arhs.odyssi.core.SparqlQuery;
import lu.arhs.odyssi.sparqlengine.SparqlEngine;

/**
 * @author bollenma
 */
public class SparqlEngineImpl implements SparqlEngine {

    private String inputFileName = "src/main/resources/goc-example.ttl";
    private Model model;

    public void prepareModel() {
        model = ModelFactory.createDefaultModel();
        model.read(inputFileName);
    }

    @Override
    public Collection<String> process(final SparqlQuery sparqlQuery) {

        if (model == null) {
            this.prepareModel();
        }

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

    public Collection<Dataset> findDataset(Collection<String> uris) {

        if (model == null) {
            this.prepareModel();
        }

        final Collection<Dataset> ret = new ArrayList<>();

        final String sparql = "prefix dct: <http://purl.org/dc/terms/> select ?title ?description where { <%s> dct:title ?title. <%s> dct:description ?description.}";

        for (final String uri : uris) {
            final String formattedSparql = String.format(sparql, uri, uri);
            final Query query = QueryFactory.create(formattedSparql);

            try (QueryExecution qexec = QueryExecutionFactory.create(query, model)) {
                final ResultSet results = qexec.execSelect();
                for (; results.hasNext(); ) {
                    QuerySolution querySolution = results.nextSolution();

                    final Literal title = querySolution.getLiteral("title");
                    final Literal description = querySolution.getLiteral("description");

                    Dataset dataset = new Dataset();
                    dataset.setUri(uri);
                    if (title != null) {
                        dataset.setTitle(title.getString());
                    }
                    if (description != null) {
                        dataset.setDescription(description.getString());
                    }

                    ret.add(dataset);
                }
            }

        }

        return ret;
    }

}

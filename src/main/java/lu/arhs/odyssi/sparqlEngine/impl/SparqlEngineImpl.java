package lu.arhs.odyssi.sparqlEngine.impl;

import java.io.InputStream;
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
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.util.FileManager;

import lu.arhs.odyssi.core.SparqlQuery;
import lu.arhs.odyssi.sparqlEngine.SparqlEngine;

/**
 * @author bollenma
 */
public class SparqlEngineImpl implements SparqlEngine {

    private String inputFileName = "src/main/resources/GoC_example.ttl";

    @Override
    public Collection<String> process(final SparqlQuery sparqlQuery) {

        final Model model = ModelFactory.createDefaultModel();

        // read the RDF/XML file
        model.read(inputFileName);

        final String queryString = sparqlQuery.constructQuery();
        final Query query = QueryFactory.create(queryString);

        final Collection<String> ret = new ArrayList<>();

        try (QueryExecution qexec = QueryExecutionFactory.create(query, model)) {
            ResultSet results = qexec.execSelect();
            for (; results.hasNext(); ) {
                QuerySolution soln = results.nextSolution();

                //                RDFNode x = soln.get("varName") ;       // Get a result variable by name.
                //                Resource r = soln.getResource("VarR") ; // Get a result variable - must be a resource
                Literal l = soln.getLiteral("dataset_id");   // Get a result variable - must be a literal

                ret.add(l.getString());

            }
        }

        return ret;
    }
}

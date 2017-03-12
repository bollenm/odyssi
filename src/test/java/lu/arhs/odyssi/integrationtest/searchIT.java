package lu.arhs.odyssi.integrationtest;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

import lu.arhs.odyssi.core.ContextEnum;
import lu.arhs.odyssi.core.ContextualKeyword;
import lu.arhs.odyssi.core.Dataset;
import lu.arhs.odyssi.core.SemanticQuery;
import lu.arhs.odyssi.core.SparqlQuery;
import lu.arhs.odyssi.keywords2sparql.Keywords2Sparql;
import lu.arhs.odyssi.keywords2sparql.impl.Keywords2SparqlImpl;
import lu.arhs.odyssi.sparqlengine.SparqlEngine;
import lu.arhs.odyssi.sparqlengine.impl.SparqlEngineImpl;

/**
 * @author bollenma
 */
public class searchIT {

    @Test
    public void process() throws Exception {

        final Keywords2Sparql keywords2Sparql = new Keywords2SparqlImpl();
        final SparqlEngine sparqlEngine = new SparqlEngineImpl();

        final ContextualKeyword subject = new ContextualKeyword();
        subject.setContext(ContextEnum.SUBJECT);
        subject.setKeyword("waste");

        final ContextualKeyword location = new ContextualKeyword();
        location.setContext(ContextEnum.SUBJECT);
        location.setKeyword("capellen");

        final Collection<ContextualKeyword> contextualKeywords = new ArrayList<>();
        contextualKeywords.add(subject);
        contextualKeywords.add(location);

        final SemanticQuery semanticQuery = new SemanticQuery();
        semanticQuery.setContextualKeywords(contextualKeywords);

        final SparqlQuery sparqlQuery = keywords2Sparql.process(semanticQuery);

        final Collection<String> uris = sparqlEngine.process(sparqlQuery);

        final Collection<Dataset> datasets = sparqlEngine.findDataset(uris);
    }
}

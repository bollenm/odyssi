package controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lu.arhs.odyssi.core.Dataset;
import lu.arhs.odyssi.core.SemanticQuery;
import lu.arhs.odyssi.core.SparqlQuery;
import lu.arhs.odyssi.keywords2sparql.Keywords2Sparql;
import lu.arhs.odyssi.sparqlengine.SparqlEngine;
import lu.arhs.odyssi.text2keywords.Text2Keywords;

/**
 * Created by djaghlyo on 12.03.17.
 */
@RestController
public class QueryController {

    @Autowired
    Text2Keywords text2Keywords;
    @Autowired
    Keywords2Sparql keywords2Sparql;
    @Autowired
    SparqlEngine sparqlEngine;

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public Collection<Dataset> queryMethod(@RequestParam("nlp") String nlp) {

        final SemanticQuery semanticQuery = text2Keywords.process(nlp);
        final SparqlQuery sparqlQuery = keywords2Sparql.process(semanticQuery);
        final Collection<String> uris = sparqlEngine.process(sparqlQuery);
        final Collection<Dataset> datasets = sparqlEngine.findDataset(uris);

        return datasets;

        // call the Text 2 Keywords service
    }
}

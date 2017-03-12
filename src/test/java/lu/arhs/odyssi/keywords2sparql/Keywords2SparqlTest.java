package lu.arhs.odyssi.keywords2sparql;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;

import lu.arhs.odyssi.core.ContextEnum;
import lu.arhs.odyssi.core.ContextualKeyword;
import lu.arhs.odyssi.core.SemanticQuery;
import lu.arhs.odyssi.core.SparqlQuery;
import lu.arhs.odyssi.keywords2sparql.impl.Keywords2SparqlImpl;

/**
 * @author bollenma
 */
public class Keywords2SparqlTest {
    @Test
    public void process() throws Exception {

        final Keywords2Sparql keywords2Sparql = new Keywords2SparqlImpl();

        final ContextualKeyword subject = new ContextualKeyword();
        subject.setContext(ContextEnum.SUBJECT);
        subject.setKeyword("garbage");

        final ContextualKeyword location = new ContextualKeyword();
        location.setContext(ContextEnum.SUBJECT);
        location.setKeyword("capellen");

        final Collection<ContextualKeyword> contextualKeywords = new ArrayList<>();
        contextualKeywords.add(subject);
        contextualKeywords.add(location);

        final SemanticQuery semanticQuery = new SemanticQuery();
        semanticQuery.setContextualKeywords(contextualKeywords);

        final SparqlQuery res = keywords2Sparql.process(semanticQuery);

        final SparqlQuery expected = new SparqlQuery();
        expected.setSelect("?dataset_uri");
        expected.setWhere("");
        Assert.assertEquals(expected, res);
    }

}
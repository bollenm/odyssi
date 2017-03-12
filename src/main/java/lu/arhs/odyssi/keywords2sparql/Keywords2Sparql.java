package lu.arhs.odyssi.keywords2sparql;

import org.springframework.stereotype.Service;

import lu.arhs.odyssi.core.SemanticQuery;
import lu.arhs.odyssi.core.SparqlQuery;

@Service
public interface Keywords2Sparql {

	SparqlQuery process(SemanticQuery semanticQuery);



}

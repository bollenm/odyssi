package lu.arhs.odyssi.keywords2sparql;

import java.util.Collection;

import lu.arhs.odyssi.core.SemanticQuery;
import lu.arhs.odyssi.core.SparqlQuery;

public interface Keywords2sparql {

	public Collection<SparqlQuery> process(SemanticQuery input);
}

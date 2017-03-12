package lu.arhs.odyssi.text2keywords.openNLPimpl;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import lu.arhs.odyssi.core.ContextualKeyword;
import lu.arhs.odyssi.core.SemanticQuery;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Text2KeywordsOpenNLPImplTest {
	private static final Logger LOG = LoggerFactory.getLogger(Text2KeywordsOpenNLPImplTest.class);
	
	private static final String EXAMPLE_SEARCH_EN = "Find the garbage collection near Bertrange not opened tomorrow.";
	private static final String EXAMPLE_SEARCH_FR = "Trouve la collecte de déchets à Paris";
	private static final String EXAMPLE_SEARCH_FR_SIMPLE = "Je m'appelle Baptiste";

	@Test
	public void testProcess() throws IOException {
		Text2KeywordsOpenNLPImpl text2word = new Text2KeywordsOpenNLPImpl();
		SemanticQuery semantics = text2word.process(EXAMPLE_SEARCH_EN);
		for(ContextualKeyword kw:semantics.getContextualKeywords()) {
			LOG.debug(kw.getContext() + " : " + kw.getKeyword());
		}
	}

}

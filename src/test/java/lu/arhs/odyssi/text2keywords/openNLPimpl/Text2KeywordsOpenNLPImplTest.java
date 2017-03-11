package lu.arhs.odyssi.text2keywords.openNLPimpl;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

public class Text2KeywordsOpenNLPImplTest {
	
	private static final String EXAMPLE_SEARCH_EN = "Find the garbage collection near Bertrange not opened tomorrow.";
	private static final String EXAMPLE_SEARCH_FR = "Trouve la collecte de déchets à Paris";
	private static final String EXAMPLE_SEARCH_FR_SIMPLE = "Je m'appelle Baptiste";

	@Test
	public void testProcess() throws IOException {
		Text2KeywordsOpenNLPImpl text2word = new Text2KeywordsOpenNLPImpl();
		text2word.process(EXAMPLE_SEARCH_EN);
	}

}

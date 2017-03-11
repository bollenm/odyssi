package lu.arhs.odyssi.text2keywords.openNLPimpl;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

public class Text2KeywordsOpenNLPImplTest {
	
	private static final String EXAMPLE_SEARCH = "Find the garbage collection near Bertrange.";

	@Test
	public void testProcess() throws IOException {
		InputStream is = new FileInputStream("en-sent.bin");
		
	}

}

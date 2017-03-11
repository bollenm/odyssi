package lu.arhs.odyssi.text2keywords.openNLPimpl;

import java.util.List;
import java.util.Properties;

import lu.arhs.odyssi.core.SemanticQuery;
import lu.arhs.odyssi.text2keywords.Text2Keywords;

import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;

public class Text2KeywordsOpenNLPImpl implements Text2Keywords {

	@Override
	public SemanticQuery process(String input) {
		// creates a StanfordCoreNLP object, with POS tagging, lemmatization, NER, parsing, and coreference resolution 
	    Properties props = new Properties();
	    props.put("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");
	    
	    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        // create an empty Annotation just with the given text
        Annotation document = new Annotation(input);

        // run all Annotators on this text
        pipeline.annotate(document);
//        SentencesAnnotation ann;
        
//        List<CoreMap> sentences = document.get(SentencesAnnotation.class);

//        for(CoreMap sentence: sentences) {
          // traversing the words in the current sentence
          // a CoreLabel is a CoreMap with additional token-specific methods
//          for (CoreLabel token: sentence.get(TokensAnnotation.class)) {
//            // this is the text of the token
//            String word = token.get(TextAnnotation.class);
//            // this is the POS tag of the token
//            String pos = token.get(PartOfSpeechAnnotation.class);
//            // this is the NER label of the token
//            String ne = token.get(NamedEntityTagAnnotation.class);
//          }

          // this is the parse tree of the current sentence
//          Tree tree = sentence.get(TreeAnnotation.class);
//
//          // this is the Stanford dependency graph of the current sentence
//          SemanticGraph dependencies = sentence.get(CollapsedCCProcessedDependenciesAnnotation.class);
//        }

        // This is the coreference link graph
        // Each chain stores a set of mentions that link to each other,
        // along with a method for getting the most representative mention
        // Both sentence and token offsets start at 1!
//        Map<Integer, CorefChain> graph = 
//          document.get(CorefChainAnnotation.class);
        
	    return null;
	}

}

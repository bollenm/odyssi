package lu.arhs.odyssi.text2keywords.openNLPimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import lu.arhs.odyssi.core.ContextEnum;
import lu.arhs.odyssi.core.ContextualKeyword;
import lu.arhs.odyssi.core.SemanticQuery;
import lu.arhs.odyssi.text2keywords.Text2Keywords;

import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Text2KeywordsOpenNLPImpl implements Text2Keywords {

    private static final Logger LOG = LoggerFactory.getLogger(Text2KeywordsOpenNLPImpl.class);

    private static StanfordCoreNLP pipeline = null;
    private static StanfordCoreNLP getPipeline() {
        if (pipeline == null) {
            // creates a StanfordCoreNLP object, with POS tagging, lemmatization, NER, parsing, and coreference resolution
            Properties props = new Properties();
            props.put("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");

            // French management
//        props.put("tokenize.language", "fr");
//        props.put("pos.model", "edu/stanford/nlp/models/pos-tagger/french/french.tagger");
//        props.put("parse.model", "edu/stanford/nlp/models/lexparser/frenchFactored.ser.gz");
//        props.put("depparse.model", "edu/stanford/nlp/models/parser/nndep/UD_French.gz");

            pipeline = new StanfordCoreNLP(props);
        }
        return pipeline;
    }


    @Override
    public SemanticQuery process(String input) {
        LOG.debug("Processing " + input);

        List<ContextualKeyword> keyWords = new ArrayList<>();

        // create an empty Annotation just with the given text
        Annotation document = new Annotation(input);

        // run all Annotators on this text
        getPipeline().annotate(document);

        List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);

        for (CoreMap sentence : sentences) {
            // traversing the words in the current sentence
            // a CoreLabel is a CoreMap with additional token-specific methods
            for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                // this is the text of the token
                String word = token.get(CoreAnnotations.TextAnnotation.class);
                // this is the POS tag of the token
                String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
                // this is the NER label of the token
                String ne = token.get(CoreAnnotations.NamedEntityTagAnnotation.class);
                LOG.debug("Sentence: [{}], token: [{}], word= [{}], pos= [{}], ne= [{}]", sentence, token, word, pos, ne);

                ContextualKeyword kw = processToken(word, pos, ne);
                if (kw != null) {
                    keyWords.add(kw);
                }
            }

            // this is the parse tree of the current sentence
            // This will be needed if we add adjectives management
//            Tree tree = sentence.get(TreeCoreAnnotations.TreeAnnotation.class);
//            displayTree(tree, 0);
//            for (Tree leaf : tree.getLeaves()) {
//                LOG.debug(leaf.toString());
//            }
        }

        SemanticQuery query = new SemanticQuery();
        query.setContextualKeywords(keyWords);
        return query;
    }

    private ContextualKeyword processToken(String word, String pos, String ne) {
        Validate.notNull(word);
        Validate.notNull(pos);
        Validate.notNull(ne);
        Validate.notEmpty(word);
        Validate.notEmpty(pos);
        Validate.notEmpty(ne);
        // POS : we will consider only NN*
        if (pos.startsWith("NN")) {
            ContextualKeyword ckw = new ContextualKeyword();
            ckw.setKeyword(word);
            if ("LOCATION".equals(ne)) {
                ckw.setContext(ContextEnum.LOCATION);
            } else if ("DATE".equals(ne)) {
                ckw.setContext(ContextEnum.TEMPORAL);
            } else {
                ckw.setContext(ContextEnum.SUBJECT);
            }
            return ckw;
        }
        return null;
    }

    private void displayTree(Tree tree, int indent) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= indent; i++) {
            sb.append("  ");
        }
        LOG.debug(sb.toString() + tree);
        if (indent > 10) {
            LOG.debug("MAX DEPTH");
        } else {
//            if (tree.getChildrenAsList().isEmpty()) {
//                LOG.debug(sb.toString() + "- LEAVES");
//                for (Tree leaf : tree.getLeaves()) {
//                    LOG.debug(sb.toString() + leaf);
//                }
//            } else {
//                LOG.debug(sb.toString() + "- CHILDREN");
                for (Tree child : tree.getChildrenAsList()) {
                    displayTree(child, indent + 1);
                }
//            }
        }
    }

}

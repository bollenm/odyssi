package controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by djaghlyo on 12.03.17.
 */
@RestController
public class QueryController {

    @RequestMapping(value="/query", method= RequestMethod.GET)
    public String queryMethod(@RequestParam("nlp") String nlp) {
        return "Your Query "+nlp;
        //System.out.println("Query= " + nlp);

        // call the Text 2 Keywords service
    }
}

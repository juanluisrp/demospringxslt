package es.juanluisrp.demospringxslt.controllers;

import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.output.DOMOutputter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.jdom.Element;

import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

/**
 * Created by juanl on 08/09/2016.
 */
@Controller
public class XsltController {

    @RequestMapping("/")
    public ModelAndView viewXslt(HttpServletRequest request) {
        // builds absolute path of the XML file
        String xmlFile = "resources/citizens.xml";
        String contextPath = request.getServletContext().getRealPath("");
        String xmlFilePath = contextPath + File.separator + xmlFile;

        Source source = new StreamSource(new File(xmlFilePath));

        // adds the XML source file to the model so the XsltView can detect
        ModelAndView model = new ModelAndView("xstlView");
        model.addObject("xmlSource", source);

        return model;
    }

    @RequestMapping("/element")
    public ModelAndView useGeneratedElement(HttpServletRequest request) throws JDOMException {

        Element citizens = new Element("citizens");
        for (int i = 1; i <= 10; i++) {
            Element citizen = new Element("citizen");
            citizen.addContent(new Element("ssn").setText("ssn number " + i));
            citizen.addContent(new Element("firstname").setText("first name number " + i));
            citizen.addContent(new Element("lastname").setText("last name number " + i));
            citizen.addContent(new Element("role").setText("role number " + i));
            citizen.addContent(new Element("salary").setText("salary number " + i));
            citizens.addContent(citizen);
        }

        Document jdoc = new Document(citizens);

        // Transform Document from JDOM to org.w3c.dom
        DOMOutputter outputter = new DOMOutputter();
        org.w3c.dom.Document doc = null;
        try {
            doc = outputter.output(jdoc);
        } catch (JDOMException e) {
            e.printStackTrace();
        }

        // adds the XML source file to the model so the XsltView can detect
        ModelAndView model = new ModelAndView("xstlView");
        model.addObject("xmlSource", doc);

        return model;
    }


}

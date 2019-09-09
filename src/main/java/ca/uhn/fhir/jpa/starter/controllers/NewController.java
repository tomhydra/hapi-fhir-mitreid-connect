package ca.uhn.fhir.jpa.starter.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NewController  {
    static final Logger ourLog = LoggerFactory.getLogger("NEW CONTROLLER");

    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public ModelAndView auth(ModelMap model) {

        model.addAttribute("message", "This is a test Auth page v");
        return new ModelAndView("redirect:" + "http://localhost:8081/openid-connect-server-webapp/");

    }



}
package ca.uhn.fhir.jpa.starter.controllers;

import ca.uhn.fhir.to.model.HomeRequest;
import org.apache.commons.lang3.StringUtils;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class NewController{
    static final Logger ourLog = LoggerFactory.getLogger("NEW CONTROLLER");

    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public String auth(ModelMap model) {

        model.addAttribute("message", "This is a test Auth page v");
        return "auth";

    }


}
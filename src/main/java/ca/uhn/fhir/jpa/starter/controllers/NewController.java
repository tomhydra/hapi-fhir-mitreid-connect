package ca.uhn.fhir.jpa.starter.controllers;


import ca.uhn.fhir.jpa.starter.FhirTesterConfig;
import org.mitre.openid.connect.client.OIDCAuthenticationFilter;
import org.mitre.openid.connect.client.SubjectIssuerGrantedAuthority;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.Set;

@Controller
@ImportResource("classpath:servlet-context.xml")
public class NewController  {
    static final Logger ourLog = LoggerFactory.getLogger("NEW CONTROLLER");

    // filter reference so we can get class names and things like that.
    @Autowired
    private OIDCAuthenticationFilter filter;

    @Resource(name = "namedAdmins")
    private Set<SubjectIssuerGrantedAuthority> admins;


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(ModelMap model) {

        return "login";

    }


    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public ModelAndView auth(ModelMap model) {

        return new ModelAndView("redirect:" + "http://localhost:8080/hapi-fhir-jpaserver/openid_connect_login?identifier=http%3A%2F%2Flocalhost%3A8081%2Fopenid-connect-server-webapp%2F");

    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String test(Model model, Principal p) {
        model.addAttribute("admins", admins);
        return "admin";

    }



}
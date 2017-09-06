package me.aoa4eva.fullsecuritydemo.controllers;

import me.aoa4eva.fullsecuritydemo.models.Role;
import me.aoa4eva.fullsecuritydemo.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
public class MainController {
    @Autowired
    RoleRepository roleRepository;

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/login")
    public String login(Principal p)
    {
        return "login";
    }

    @RequestMapping("/testRoles")
    public @ResponseBody String showRoles()
    {
        Iterable <Role> r = roleRepository.findAll();
        String x="<h2>ROLE DETAILS</h2>";
        for(Role item:r)
        {
            x+="Role details:"+item.getRole()+" has an ID of "+item.getId()+"<br/>";
        }

        Role findR = roleRepository.findByRole("ADMIN");
        x+=findR.getRole()+" was found with an ID of "+findR.getId();
        return x;

    }
}

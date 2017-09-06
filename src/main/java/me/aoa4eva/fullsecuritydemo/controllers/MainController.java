package me.aoa4eva.fullsecuritydemo.controllers;

import me.aoa4eva.fullsecuritydemo.models.Role;
import me.aoa4eva.fullsecuritydemo.models.Userz;
import me.aoa4eva.fullsecuritydemo.repositories.RoleRepository;
import me.aoa4eva.fullsecuritydemo.repositories.UserzRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
public class MainController {
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserzRepository userzRepository;

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/login")
    public String login()
    {
        return "login";
    }

    @RequestMapping("/testRoles")
    public @ResponseBody String showRoles(Principal p)
    {
        p.getName();

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

    @RequestMapping("/adduser")
    public @ResponseBody String addUser()
    {
        Userz u = new Userz();
        u.setEmail("someone@somewhere.com");
        u.setUsername("newuser");
        u.setEnabled(true);
        u.setPassword("password");
        u.addRole(roleRepository.findByRole("USER"));
        userzRepository.save(u);
        return "User added";
    }
}

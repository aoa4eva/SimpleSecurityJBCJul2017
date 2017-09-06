package me.aoa4eva.fullsecuritydemo.services;

import me.aoa4eva.fullsecuritydemo.models.Role;
import me.aoa4eva.fullsecuritydemo.models.Userz;
import me.aoa4eva.fullsecuritydemo.repositories.UserzRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Transactional
@Service
public class SSUserDetailsService implements UserDetailsService{

    private UserzRepository uRep;


    public SSUserDetailsService(UserzRepository u)
    {
        this.uRep=u;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        try{
            Userz u = uRep.findByUsername(username);
            if(u==null){
                System.out.println("User not found");
                return null;
            }
            System.out.println("User from username"+u.toString());
            return new org.springframework.security.core.userdetails.User(
                    u.getUsername(), u.getPassword(), getAuthorities(u));
        } catch (Exception e){
            throw new UsernameNotFoundException(("Unable to find user"));
        }


    }


    private Set<GrantedAuthority> getAuthorities(Userz u)
    {
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        for(Role role : u.getRoles()) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRole());
            authorities.add(grantedAuthority);
        }
        System.out.println("user authorities are " + authorities.toString());
        return authorities;
    }

}

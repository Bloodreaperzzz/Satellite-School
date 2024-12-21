package com.Satellite.service;

import com.Satellite.model.*;
import com.Satellite.repository.CampusRepository;
import com.Satellite.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CampusRepository campusRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =userRepository.findByUsername(username);
        if(user==null)
        {
            throw new UsernameNotFoundException("user not found with username "+username);

        }
        List<GrantedAuthority> authorities=getAuthorities(user);
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);//in video they did user.getEmail()






    }
    private List<GrantedAuthority> getAuthorities(User user)
    {
        List<GrantedAuthority> authorities=new ArrayList<>();
if (user instanceof Teacher) {
    Teacher teacher = (Teacher) user;
    authorities.add(new SimpleGrantedAuthority("TEACHER"));
    if (isPrincipal(teacher)) {
        authorities.add(new SimpleGrantedAuthority("PRINCIPAL"));

    }
}
    else if(user instanceof Student)
    {System.out.println("student is here");
        authorities.add(new SimpleGrantedAuthority("STUDENT"));
    }
    else if(user instanceof FounderManager)
{
    authorities.add(new SimpleGrantedAuthority("FOUNDER_MANAGER"));
}
    else if(user instanceof TransportWorker)
{
    authorities.add(new SimpleGrantedAuthority("TRANSPORT_WORKER"));
}
    return authorities;


    }

    private boolean isPrincipal(Teacher teacher)
    {
        return campusRepository.existsByPrincipal(teacher);
    }
}

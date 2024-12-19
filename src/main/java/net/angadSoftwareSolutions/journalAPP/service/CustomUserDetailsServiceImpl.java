package net.angadSoftwareSolutions.journalAPP.service;

import net.angadSoftwareSolutions.journalAPP.entity.User;
import net.angadSoftwareSolutions.journalAPP.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepo userRepo;




    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepo.findByUsername(username);
        System.out.println("ndcndinv");
        System.out.println(user);
        if(user!=null){
          return   org.springframework.security.core.userdetails.User.builder().username(user.getUsername())
                    .password(user.getPassword())
                    .roles(user.getRoles().toArray(new String[0])).build();
        }
        throw new UsernameNotFoundException("user is not found with this username : "+username);
    }
}

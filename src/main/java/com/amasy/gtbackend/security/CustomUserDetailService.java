package com.amasy.gtbackend.security;

import com.amasy.gtbackend.entities.SrcUser;
import com.amasy.gtbackend.entities.TpUser;
import com.amasy.gtbackend.exceptions.ApiException;
import com.amasy.gtbackend.repositories.SrcUserRepo;
import com.amasy.gtbackend.repositories.TpUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private TpUserRepo tpUserRepo;
    @Autowired
    private SrcUserRepo srcUserRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(this.tpUserRepo.findByUserName(username).isPresent()){
            TpUser tpUser = this.tpUserRepo.findByUserName(username).orElseThrow(() -> new ApiException("TP User or SRC User not found with " + username + " this userName !!"));
            return tpUser;
        }
        else {
            SrcUser srcUser = this.srcUserRepo.findByUserName(username).orElseThrow(() -> new ApiException("TP User or SRC User not found with " + username + " this userName !!"));
            return srcUser;
        }
    }
}

package com.cristal.stefanie.cursomc.services;

import com.cristal.stefanie.cursomc.domain.Cliente;
import com.cristal.stefanie.cursomc.repositores.ClienteRepository;
import com.cristal.stefanie.cursomc.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Primary
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ClienteRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Cliente cliente =  repository.findByEmail(email);
        if(cliente == null){
            throw new UsernameNotFoundException(email);
        }
        return new UserSS(cliente.getId(),cliente.getEmail(),cliente.getSenha(),cliente.getPerfis());
    }
}

package ru.geekbrains.Lesson4.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.geekbrains.Lesson4.entities.Customer;
import ru.geekbrains.Lesson4.repositiry.CustomerRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    CustomerRepository customerRepository;

    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer findByName(String username) {
        return customerRepository.findByName(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = findByName(username);
        if (customer == null) {
            throw new UsernameNotFoundException(String.format("User '%s' not found", username)) ;
        }
        return new User(customer.getName(), customer.getPassword(), mapRoleToAuthority(customer));
    }

    private Collection <? extends GrantedAuthority> mapRoleToAuthority (Customer customer) {
        Collection<String> roles = new ArrayList<>();
        roles.add(customer.getRole());
        return roles.stream().map(r->new SimpleGrantedAuthority(customer.getRole())).collect(Collectors.toList());
    }
}


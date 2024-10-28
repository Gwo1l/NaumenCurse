package ru.Robert.NauJava.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.Robert.NauJava.CRUDRepositories.ContactRepository;
import ru.Robert.NauJava.Entities.Contact;
import ru.Robert.NauJava.Security.Role;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class ContactDetailsService implements UserDetailsService {
    private final ContactRepository contactRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ContactDetailsService(ContactRepository contactRepository, PasswordEncoder passwordEncoder,
                                 List<Contact> contacts) {
        this.contactRepository = contactRepository;
        this.passwordEncoder = passwordEncoder;
    }



    public void addContact(Contact contact) throws Exception {
        try {
            Contact contactFromDb = contactRepository.findByName(contact.getName()).getFirst();
            throw new IllegalArgumentException("user exists");
        }
        catch (NoSuchElementException e) {
            //contact.setRole(contact.getRole());
            contact.setPassword(passwordEncoder.encode(contact.getPassword()));
            contactRepository.save(contact);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Contact appContact = contactRepository.findByName(username).getFirst();
            org.springframework.security.core.userdetails.User user = new
                    org.springframework.security.core.userdetails.User(
                    appContact.getName(), appContact.getPassword(), mapRoles(appContact));
            return user;
        }
        catch (Exception e) {
            throw new UsernameNotFoundException("user not found");
        }
    }

    private Collection<GrantedAuthority> mapRoles(Contact appContact)
    {
        List<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new SimpleGrantedAuthority("ROLE_" + appContact.getRole().name()));
        return collect;
    }
}

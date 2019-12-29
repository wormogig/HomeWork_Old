package com.client.service;

import com.client.model.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.security.core.userdetails.User.UserBuilder;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final static String SERVER_URL = "http://localhost:8080/";
    private HttpHeaders header = null;

    // Инициализация header для инмемори аутентификации
    public UserServiceImpl() {
        header = new HttpHeaders();
        header.setBasicAuth("admin", "admin");
    }

    @Override
    public List<User> allUsers() {
        RestTemplate template = new RestTemplate();
        String uri = SERVER_URL + "api/list";
        ResponseEntity<List<User>> response = template.exchange(
                uri,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<User>>(){});
        return response.getBody();
    }

    @Override
    public void add(User user) {
        RestTemplate template = new RestTemplate();
        String uri = SERVER_URL + "api/admin/add";
        HttpEntity<User> req = new HttpEntity<>(user, header);
        template.postForLocation(uri, req);
    }

    @Override
    public void delete(long id) {
        RestTemplate template = new RestTemplate();
        String uri = SERVER_URL + "api/admin/delete/" + id;
        HttpEntity req = new HttpEntity(header);
        template.exchange(
                uri,
                HttpMethod.DELETE,
                req,
                String.class);
    }

    @Override
    public void edit(User user) {
        RestTemplate template = new RestTemplate();
        String uri = SERVER_URL + "api/admin/edit";
        HttpEntity<User> req = new HttpEntity<>(user, header);
        template.put(uri, req);
    }

    @Override
    public User getById(long id) {
        RestTemplate template = new RestTemplate();
        String uri = SERVER_URL + "user/" + id;
        User user = template.getForObject(uri, User.class);
        return user;
    }

    @Override
    public User getByName(String login) {
        RestTemplate template = new RestTemplate();
        String uri = SERVER_URL + "api/login";
        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(uri)
                    .queryParam("login", login);
            User user = template.getForObject(builder.toUriString(), User.class);
            return user;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = getByName(login);
        UserBuilder builder = null;
        if (user != null) {
            builder = org.springframework.security.core.userdetails.User.withUsername(login);
            builder.password(user.getPassword());
            String authorities = user.getRole().name();
            builder.authorities(authorities);
        } else {
            throw new UsernameNotFoundException("User not found.");
        }
        return builder.build();
    }

}

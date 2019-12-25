package com.howtographql.hackernews.andy.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.howtographql.hackernews.andy.entities.Link;
import com.howtographql.hackernews.andy.entities.User;
import com.howtographql.hackernews.andy.repository.UserRepository;

import java.util.List;

public class LinkResolver implements GraphQLResolver<Link> {
    private final UserRepository userRepository;

    public LinkResolver(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /*
    name of this method can be getUser or user
     */
    public List<User> user(Link link){
        return userRepository.getUsersByLink(link.getId());
    }
    // not a usual or a normal approach but the resolver can be declared even for simple properties
    // as shown below
    public String url(Link link){
        return "www.google.com";
    }
}

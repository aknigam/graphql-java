package com.howtographql.hackernews.andy;

import com.coxautodev.graphql.tools.GraphQLRootResolver;
import com.howtographql.hackernews.andy.entities.Link;
import com.howtographql.hackernews.andy.entities.User;
import com.howtographql.hackernews.andy.input.UserInput;
import com.howtographql.hackernews.andy.service.LinkService;
import com.howtographql.hackernews.andy.service.UserService;

public class Mutation implements GraphQLRootResolver {

    private final LinkService linkRepository;

    private final UserService userService;

    public Mutation(LinkService linkRepository, UserService userService) {
        this.userService = userService;
        this.linkRepository = linkRepository;
    }

    public Link createLink(String url, String description) {
        Link newLink = new Link(url, description);
        linkRepository.create(newLink);
        return newLink;
    }




    public User createUser(UserInput input ){

        return userService.create(new User(input.getName(), input.getEmail(), input.getPassword()));

    }
}

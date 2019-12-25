package com.howtographql.hackernews.andy;

import com.coxautodev.graphql.tools.GraphQLRootResolver;
import com.howtographql.hackernews.andy.entities.Link;
import com.howtographql.hackernews.andy.entities.User;
import com.howtographql.hackernews.andy.input.UserInput;
import com.howtographql.hackernews.andy.service.LinkService;
import com.howtographql.hackernews.andy.service.UserService;

public class UserQueryAndMutation implements GraphQLRootResolver {

    private final UserService userService;

    public UserQueryAndMutation(UserService userService) {
        this.userService = userService;
    }

    public User createUser(UserInput input ){

     return userService.create(new User(input.getName(), input.getEmail(), input.getPassword()));

    }


}

package com.howtographql.hackernews.andy;

import com.coxautodev.graphql.tools.GraphQLRootResolver;
import com.howtographql.hackernews.andy.entities.Link;
import com.howtographql.hackernews.andy.service.LinkService;

import java.util.List;

public class Query implements GraphQLRootResolver {

    private final LinkService linkService;

    public Query(LinkService linkService) {
        this.linkService = linkService;
    }

    public List<Link> allLinks() {
        return linkService.getAllLinks();
    }
}

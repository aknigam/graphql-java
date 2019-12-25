package com.howtographql.hackernews.andy;

import com.howtographql.hackernews.andy.entities.Link;

import java.util.ArrayList;
import java.util.List;

public class LinkRepositoryDummy implements  LinkRepository{

    private final List<Link> links;

    public LinkRepositoryDummy() {
        links = new ArrayList<>();
        //add some links to start off with
        links.add(new Link("http://howtographql.com", "Your favorite GraphQL page"));
        links.add(new Link("http://graphql.org/learn/", "The official docks"));
    }

    public List<Link> getAllLinks() {
        return links;
    }

    public void saveLink(Link link) {
        links.add(link);
    }

    @Override
    public void updateLink(Link link) {

    }

    @Override
    public Link getLink(Integer linkId) {
        return null;
    }

    @Override
    public void deleteLink(Integer linkId) {

    }
}

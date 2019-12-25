package com.howtographql.hackernews.andy;

import com.howtographql.hackernews.andy.entities.Link;

import java.util.List;

public interface LinkRepository {

    List<Link> getAllLinks() ;

    void saveLink(Link newLink);

    void updateLink(Link link);

    Link getLink(Integer linkId);

    void deleteLink(Integer linkId);
}

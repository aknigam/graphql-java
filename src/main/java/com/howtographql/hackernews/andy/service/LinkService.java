package com.howtographql.hackernews.andy.service;

import com.howtographql.hackernews.andy.entities.Link;


import java.util.List;

public interface LinkService {
  void update(Integer linkId, Link link);

  Link get(Integer linkId);

  void create(Link link);

  void delete(Integer linkId);

  List<Link> getAllLinks();
}

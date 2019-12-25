package com.howtographql.hackernews.andy.service;

import com.howtographql.hackernews.andy.entities.Link;
import com.howtographql.hackernews.andy.LinkRepository;
import org.apache.ibatis.session.SqlSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class LinkServiceImpl implements LinkService {
  public static Logger log = LoggerFactory.getLogger(LinkServiceImpl.class);

  private final SqlSessionManager sessionManager;

  private LinkRepository linkRepository;

  public LinkServiceImpl(LinkRepository linkRepository, SqlSessionManager sessionManager) {
    this.linkRepository = linkRepository;
    this.sessionManager = sessionManager;
  }

  @Override
  public void update(Integer linkId, Link link) {
    linkRepository.updateLink(link);

  }

  @Override
  public Link get(Integer linkId) {
    return linkRepository.getLink(linkId);
  }

  @Override
  public void create(Link link) {
    sessionManager.startManagedSession();
    try {

      linkRepository.saveLink(link);
    }
    finally {
      sessionManager.commit();
      sessionManager.close();
    }


  }

  @Override
  public void delete(Integer linkId) {
    linkRepository.deleteLink(linkId);
  }

  @Override
  public List<Link> getAllLinks() {
    sessionManager.startManagedSession();
    try {

      List<Link> links = linkRepository.getAllLinks();
      return links;
    }
    finally {
      sessionManager.commit();
      sessionManager.close();
    }
  }
}

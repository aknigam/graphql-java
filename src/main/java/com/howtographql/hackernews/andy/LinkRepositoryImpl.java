package com.howtographql.hackernews.andy;

import com.howtographql.hackernews.andy.entities.Link;
import com.howtographql.hackernews.andy.entities.User;
import com.howtographql.hackernews.andy.mapper.LinkMapper;
import com.howtographql.hackernews.andy.mapper.UserMapper;
import org.apache.ibatis.session.SqlSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.List;


public class LinkRepositoryImpl implements LinkRepository {

    public static Logger log = LoggerFactory.getLogger(LinkRepositoryImpl.class);
    private final SqlSessionManager sqlSessionFactory;
//  private LinkMapper linkMapper;

    public LinkRepositoryImpl(SqlSessionManager sqlSessionfactory) {
        this.sqlSessionFactory = sqlSessionfactory;

    }

    /**
     * even if the users are returned from this query then also the resolver will be invoked if it is configured
     * if resolver is not configured then what ever is returned from here will be returned as is
     * @return
     */
    public List<Link> getAllLinks() {
        try {
            LinkMapper mapper = sqlSessionFactory.getMapper(LinkMapper.class);
            List<Link> links = mapper.getAllLinks();
            return links;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }



    }

    @Override
    public void saveLink(Link link) {

        LinkMapper linkMapper = sqlSessionFactory.getMapper(LinkMapper.class);


        linkMapper.createLink(link);

//        if (link.getUser() != null) {
//            for (User user : link.getUser()) {
//                createUser(user);
//            }
//        }
    }

    private void createUser(User user) {
        UserMapper userMapper = sqlSessionFactory.getMapper(UserMapper.class);
        userMapper.createUser(user);
    }

    public Link getLink(Integer linkId) {


        LinkMapper linkMapper = sqlSessionFactory.getMapper(LinkMapper.class);
        Link link = linkMapper.getLink(linkId);
        return link;


    }


    public void updateLink(Link link) {
        java.lang.Integer linkId = link.getId();
        LinkMapper linkMapper = sqlSessionFactory.getMapper(LinkMapper.class);
        Link existingLink = linkMapper.getLink(linkId);
        if (existingLink == null) {
            throw new RuntimeException(String.format("Link with id {} does not exists", link.getId()));
        }
        linkMapper.updateLink(link);
//        if (link.getUser() != null) {
//            if (existingLink.getUser() != null) {
//                existingLink.getUser().removeAll(link.getUser());
//            }
//            for (User user : link.getUser()) {
//                if (user.getId() != null) {
//                    updateUser(user);
//                } else {
////                    user.setUserLinkId(link.getId());
//                    createUser(user);
//                }
//            }
//        }
//        if (existingLink.getUser() != null) {
//            for (User user : existingLink.getUser()) {
////                linkMapper.unLinkLinkUser(linkId, user.getId());
//                deleteUser(user.getId());
//            }
//        }
    }
    private void deleteUser(java.lang.Integer userId) {
        UserMapper userMapper = sqlSessionFactory.getMapper(UserMapper.class);
        User user = userMapper.getUser(userId);
        if (user == null) {
            return;
        }
        userMapper.deleteUser(userId);
    }

    private void updateUser(User user) {
        UserMapper userMapper = sqlSessionFactory.getMapper(UserMapper.class);
        java.lang.Integer userId = user.getId();
        User existingUser = userMapper.getUser(userId);
        if (existingUser == null) {
            throw new RuntimeException(String.format("User with id {} does not exists", user.getId()));
        }
        userMapper.updateUser(user);
    }


    public void deleteLink(java.lang.Integer linkId) {
        LinkMapper linkMapper = sqlSessionFactory.getMapper(LinkMapper.class);
        Link link = linkMapper.getLink(linkId);
        if (link == null) {
            return;
        }
//        List<User> users = link.getUser();
//        if (users != null) {
//            for (User user : users) {
////                linkMapper.unLinkLinkUser(linkId, user.getId());
//                deleteUser(user.getId());
//            }
//        }
//        linkMapper.deleteLink(linkId);
    }
}

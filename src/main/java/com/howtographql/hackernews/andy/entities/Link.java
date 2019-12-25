package com.howtographql.hackernews.andy.entities;


import lombok.Data;

import java.util.List;
import java.util.Objects;
import java.lang.Integer;
import java.lang.String;

@Data
public class Link {

    public Link() {}

    private Integer id;


    private String url;


    private String description;

    private List<User> user;


    public Link(String url, String description) {
        this.url = url;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Link) {
            final Link other = (Link) obj;
            return Objects.equals(id, other.id);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}

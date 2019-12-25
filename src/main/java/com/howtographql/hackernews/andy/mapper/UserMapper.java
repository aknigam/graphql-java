package com.howtographql.hackernews.andy.mapper;

import com.howtographql.hackernews.andy.entities.Link;
import com.howtographql.hackernews.andy.entities.User;

import java.util.List;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Select(
            " SELECT id, name, email, password, user_link_id" + " FROM user" + " WHERE (id = #{userId})")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "email", property = "email"),
            @Result(column = "password", property = "password"),
            @Result(column = "user_link_id", property = "userLinkId")
    })
    User getUser(@Param("userId") Integer userId);

    // ------------------------ OTM methods

    @Select(
            " SELECT id, name, email, password, user_link_id"
                    + " FROM user"
                    + " WHERE (user_link_id = #{linkId})")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "email", property = "email"),
            @Result(column = "password", property = "password"),
            @Result(column = "user_link_id", property = "userLinkId")
    })
    List<User> getUsersByLink(@Param("linkId") Integer linkId);

    @Insert(
            " INSERT INTO user"
                    + "  (name, email, password, user_link_id)"
                    + " VALUES (#{user.name}, #{user.email}, #{user.password}, #{user.userLinkId})")
    @Options(useGeneratedKeys = true, keyProperty = "user.id")
    void createUser(@Param("user") User user);

    @Update(
            " UPDATE user"
                    + " SET name = #{user.name}, email = #{user.email}, password = #{user.password}, user_link_id = #{user.userLinkId}"
                    + " WHERE (id = #{user.id})")
    void updateUser(@Param("user") User user);

    @Delete(" DELETE FROM user" + " WHERE (id =  #{userId})")
    void deleteUser(@Param("userId") Integer userId);
}

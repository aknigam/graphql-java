package com.howtographql.hackernews.andy.mapper;

import com.howtographql.hackernews.andy.entities.Link;


import java.util.List;
import org.apache.ibatis.annotations.*;

@Mapper
public interface LinkMapper {

  @Select(" SELECT id, url, description" + " FROM link" + " WHERE (id = #{linkId})")
  @Results({
          @Result(column = "id", property = "id"),
          @Result(column = "url", property = "url"),
          @Result(column = "description", property = "description"),
          @Result(
                  column = "id",
                  property = "user",
                  javaType = List.class,
                  many =
                  @Many(
                          select =
                                  "com.howtographql.hackernews.andy.mapper.UserMapper.getUsersByLink"))
  })
  Link getLink(@Param("linkId") Integer linkId);

  @Insert(
      " INSERT INTO link" + "  (url, description)" + " VALUES (#{link.url}, #{link.description})")
  @Options(useGeneratedKeys = true, keyProperty = "link.id")
  void createLink(@Param("link") Link link);

  @Update(
      " UPDATE link"
          + " SET url = #{link.url}, description = #{link.description}"
          + " WHERE (id = #{link.id})")
  void updateLink(@Param("link") Link link);

  @Delete(" DELETE FROM link" + " WHERE (id =  #{linkId})")
  void deleteLink(@Param("linkId") Integer linkId);

  @Select(" SELECT id, url, description" + " FROM link")
  @Results({
          @Result(column = "id", property = "id"),
          @Result(column = "url", property = "url"),
          @Result(column = "description", property = "description"),
//          @Result(
//                  column = "id",
//                  property = "user",
//                  javaType = List.class,
//                  many =
//                  @Many(
//                          select =
//                                  "com.howtographql.hackernews.andy.mapper.UserMapper.getUsersByLink"))
  })
  List<Link> getAllLinks();

}

package com.zhouer.azurhoooos.mapper;

import com.zhouer.azurhoooos.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
   @Select("select * from user")
    List<User> findAll();

   @Select("select id from user where account=#{account} and password=#{password}")
   Integer selectUserId(User user);
   //根据账号密码查找用户id

    @Select("select role_id from user_roles where user_id=#{userId}")
   List<Integer> selectRoleId(Integer userId);
   //根据用户id查找角色id,可能查找出多个角色id


    List<Integer> selectAccessId(List<Integer> roleId);

   List<String> selectAccess(List<Integer> accessId);
}

package com.lanmushan.authservice.mapper;

import com.lanmushan.framework.mapper.QueryMapper;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

import com.lanmushan.authservice.entity.AuthTbUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户表(AuthTbUser)表数据库访问层
 *
 * @author daiyu
 * @since 2020-06-15 22:13:48
 */
@org.apache.ibatis.annotations.Mapper
public interface AuthTbUserMapper extends QueryMapper<AuthTbUser>, IdListMapper<AuthTbUser, Long>, InsertListMapper<AuthTbUser> {

    /**
     * 根据账号查询用户信息
     *
     * @param account 用户账号
     * @return
     */
    AuthTbUser selectLoginUser(@Param("account") String account);


    /**
     * 根据角色编码查询用户列表
     *
     * @param roleCode 角色编码
     * @return
     */
    List<AuthTbUser> selectUserByRoleCode(@Param("roleCode") String roleCode);
}
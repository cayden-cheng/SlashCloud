package com.lanmushan.authservice.vo;

import com.lanmushan.authservice.entity.AuthTbRole;
import com.lanmushan.authservice.entity.AuthTbUser;
import lombok.Data;

import java.util.List;

/**
 * @Author dy
 * @Date 2020/7/4 11:19
 * @Version 1.0
 */
@Data
public class VoAuthTbUser extends AuthTbUser {
    List<AuthTbRole> authTbRoleList;
}

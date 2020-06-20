package com.lanmushan.dyauthservice.controller;

import com.lanmushan.dyauthservice.entity.AuthTbResource;
import com.lanmushan.dyauthservice.entity.AuthTbRole;
import com.lanmushan.dyauthservice.mapper.AuthTbRoleMapper;
import com.lanmushan.dyauthservice.service.AuthTbResourceService;
import com.lanmushan.framework.entity.CurrentUser;
import com.lanmushan.framework.util.CurrentUserUtil;
import org.springframework.web.bind.annotation.*;
import com.lanmushan.framework.dto.Message;
import com.lanmushan.framework.dto.QueryInfo;
import com.lanmushan.dyauthservice.bo.BoAuthTbResource;
import com.lanmushan.dyauthservice.mapper.AuthTbResourceMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 菜单表(AuthTbResource)表控制层
 *
 * @author makejava
 * @since 2020-06-15 22:13:48
 */
@RestController
@RequestMapping("/authTbResource")
@Slf4j
public class AuthTbResourceController {
    @Autowired
    private AuthTbResourceMapper authTbResourceMapper;
    @Autowired
    private AuthTbResourceService authTbResourceService;
    @Autowired
    private AuthTbRoleMapper authTbRoleMapper;

    @GetMapping("/selectById")
    public Message selectById(@RequestParam("id") Long id) {
        Message msg = new Message();
        AuthTbResource authTbResource = authTbResourceMapper.selectByPrimaryKey(id);
        msg.setRow(authTbResource);
        return msg;
    }

    @PostMapping("/add")
    public Message add(@RequestBody @Valid BoAuthTbResource obj) {
        Message msg = new Message();
        authTbResourceService.insertService(obj);
        msg.setRow(obj).success("添加成功");
        return msg;
    }

    /**
     * @param obj
     * @return
     */
    @PostMapping("/update")
    public Message update(@RequestBody @Valid BoAuthTbResource obj) {
        Message msg = new Message();
        authTbResourceService.updateService(obj);
        msg.setRow(obj).success("更新成功");
        return msg;
    }

    /**
     * @param ids
     * @return
     */
    @DeleteMapping("/delete")
    public Message delete(@RequestParam List<Long> ids) {
        Message msg = new Message();
        authTbResourceService.deleteServiceByIds(ids);
        msg.success("删除成功");
        return msg;
    }

    /**
     * @param session
     * @param type    资源类型
     * @return
     */
    @RequestMapping("/select/{type}")
    public Message selectMenus(HttpSession session, @PathVariable("type") String type) {

        Message msg = new Message();
        CurrentUser currentUser = CurrentUserUtil.getCurrentUser();
        List<AuthTbRole> roles = authTbRoleMapper.selectRolesByUserId(currentUser.getUserId());
        if (roles.size() == 0) {
            msg.error("您没有角色，请联系管理员");
            return msg;
        }
        String roleIds = "";
        for (AuthTbRole ar : roles) {
            roleIds += ar.getId().toString() + ",";
        }
        if (roleIds.length() > 1) {
            roleIds = roleIds.substring(0, roleIds.length() - 1);
        }
        List<AuthTbResource> authResourceList = authTbResourceMapper.selectResourceByRoleIds(roleIds, type);
        Collections.sort(authResourceList, new Comparator<AuthTbResource>() {
            @Override
            public int compare(AuthTbResource s1, AuthTbResource s2) {
                int result = s1.getFkParentId().compareTo(s2.getFkParentId());
                if (result != 0) {
                    return result;
                }
                result = s1.getOrderIndex().compareTo(s2.getOrderIndex());
                if (result != 0) {
                    return result;
                }
                return s1.getId().compareTo(s2.getId());
            }
        });

        if (authResourceList.size() > 0) {
            msg.setRows(authResourceList);
            msg.success("查询成功");
        } else {
            msg.error("没有您权限范围内的资源");
        }
        return msg;
    }
}
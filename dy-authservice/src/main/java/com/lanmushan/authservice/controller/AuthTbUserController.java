package com.lanmushan.authservice.controller;

import com.lanmushan.authservice.entity.AuthTbUser;
import com.lanmushan.authservice.service.AuthTbUserService;
import org.springframework.web.bind.annotation.*;
import com.lanmushan.framework.dto.Message;
import com.lanmushan.authservice.bo.BoAuthTbUser;
import com.lanmushan.authservice.mapper.AuthTbUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
import java.util.List;

/**
 * 用户表(AuthTbUser)表控制层
 *
 * @author makejava
 * @since 2020-06-15 22:13:48
 */
@RestController
@RequestMapping("/authTbUser")
@Slf4j
public class AuthTbUserController {
    @Autowired
    private AuthTbUserMapper authTbUserMapper;
    @Autowired
    private AuthTbUserService authTbUserService;

    @GetMapping("/selectById")
    public Message selectById(@RequestParam("id") Long id) {
        Message msg = new Message();
        AuthTbUser authTbUser = authTbUserMapper.selectByPrimaryKey(id);
        msg.setRow(authTbUser);
        return msg;
    }

    @PostMapping("/add")
    public Message add(@RequestBody BoAuthTbUser obj) {
        Message msg = new Message();
        authTbUserService.insertService(obj);
        msg.setRow(obj).success("添加成功");
        return msg;
    }

    /**
     * @param obj
     * @return
     */
    @PostMapping("/update")
    public Message update(@RequestBody @Valid BoAuthTbUser obj) {
        Message msg = new Message();
        authTbUserService.updateService(obj);
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
        authTbUserService.deleteServiceByIds(ids);
        msg.success("删除成功");
        return msg;
    }

}
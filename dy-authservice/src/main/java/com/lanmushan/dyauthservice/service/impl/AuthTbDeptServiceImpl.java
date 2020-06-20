package com.lanmushan.dyauthservice.service.impl;


import com.lanmushan.dyauthservice.mapper.AuthTbDeptMapper;
import com.lanmushan.dyauthservice.service.AuthTbDeptService;
import com.lanmushan.framework.constant.HTTPCode;
import com.lanmushan.framework.exception.OperateException;
import com.lanmushan.dyauthservice.bo.BoAuthTbDept;
import com.lanmushan.dyauthservice.entity.AuthTbDept;
import com.lanmushan.framework.dto.QueryInfo;
import com.lanmushan.framework.util.date.DateUtil;
import com.lanmushan.framework.util.uuid.MyUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;


/**
 * 部门表(AuthTbDept)表服务实现类
 *
 * @author makejava
 * @since 2020-06-15 22:13:48
 */
@Service("authTbDeptService")
public class AuthTbDeptServiceImpl implements AuthTbDeptService {
    @Autowired
    private AuthTbDeptMapper authTbDeptMapper;

    @Override
    public List selectList(QueryInfo queryInfo) {
        return authTbDeptMapper.selectList(queryInfo);
    }

    @Override
    public void insertService(BoAuthTbDept boAuthTbDept) {
        Date now = DateUtil.now();
        boAuthTbDept.setCreateTime(now);
        boAuthTbDept.setUpdateTime(now);
        authTbDeptMapper.insertSelective(boAuthTbDept);
    }

    @Override
    public void insertServiceList(List<BoAuthTbDept> boAuthTbDeptList) {
        Date now = DateUtil.now();
        boAuthTbDeptList.forEach(it -> {
            it.setCreateTime(now);
            it.setUpdateTime(now);
            it.setId(MyUUID.getInstance().nextId());
        });
        authTbDeptMapper.insertList(boAuthTbDeptList);
    }

    @Override
    public void updateService(BoAuthTbDept boAuthTbDept) {
        boAuthTbDept.setUpdateTime(DateUtil.now());
        int reuslt = authTbDeptMapper.updateByPrimaryKeySelective(boAuthTbDept);
        if (reuslt != 1) {
            throw new OperateException("新增失败", HTTPCode.Fail);
        }
    }

    @Override
    public void deleteServiceByIds(List<Long> ids) {
        authTbDeptMapper.deleteByIdList(ids);
    }
}
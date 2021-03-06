package com.lanmushan.authservice.service.impl;


import com.lanmushan.authservice.mapper.AuthFkDeptPostMapper;
import com.lanmushan.authservice.service.AuthFkDeptPostService;
import com.lanmushan.framework.constant.HTTPCode;
import com.lanmushan.framework.exception.OperateException;
import com.lanmushan.authservice.bo.BoAuthFkDeptPost;
import com.lanmushan.framework.dto.QueryInfo;
import com.lanmushan.framework.util.date.DateUtil;
import com.lanmushan.framework.util.uuid.MyUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


/**
 * 角色和部门关联表(AuthFkDeptPost)表服务实现类
 *
 * @author daiyu
 * @since 2020-06-15 22:13:47
 */
@Service("authFkDeptPostService")
public class AuthFkDeptPostServiceImpl implements AuthFkDeptPostService {
    @Autowired
    private AuthFkDeptPostMapper authFkDeptPostMapper;

    @Override
    public List selectList(QueryInfo queryInfo) {
        return authFkDeptPostMapper.selectList(queryInfo);
    }

    @Override
    public void insertService(BoAuthFkDeptPost boAuthFkDeptPost) {
        Date now = DateUtil.now();
        boAuthFkDeptPost.setCreateTime(now);
        boAuthFkDeptPost.setUpdateTime(now);
        authFkDeptPostMapper.insertSelective(boAuthFkDeptPost);
    }

    @Override
    public void insertServiceList(List<BoAuthFkDeptPost> boAuthFkDeptPostList) {
        Date now = DateUtil.now();
        boAuthFkDeptPostList.forEach(it -> {
            it.setCreateTime(now);
            it.setUpdateTime(now);
            it.setId(MyUUID.getInstance().nextId());
        });
        authFkDeptPostMapper.insertList(boAuthFkDeptPostList);
    }

    @Override
    public void updateService(BoAuthFkDeptPost boAuthFkDeptPost) {
        boAuthFkDeptPost.setUpdateTime(DateUtil.now());
        int reuslt = authFkDeptPostMapper.updateByPrimaryKeySelective(boAuthFkDeptPost);
        if (reuslt != 1) {
            throw new OperateException("新增失败", HTTPCode.Fail);
        }
    }

    @Override
    public void deleteServiceByIds(List<Long> ids) {
        authFkDeptPostMapper.deleteByIdList(ids);
    }
}
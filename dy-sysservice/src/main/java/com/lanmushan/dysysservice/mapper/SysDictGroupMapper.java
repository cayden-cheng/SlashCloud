package com.lanmushan.dysysservice.mapper;

import com.lanmushan.dysysservice.entity.SysDictGroup;
import com.lanmushan.framework.mapper.QueryMapper;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.special.InsertListMapper;


/**
 * @author Administrator
 */
@org.apache.ibatis.annotations.Mapper
public interface SysDictGroupMapper extends QueryMapper<SysDictGroup>, IdListMapper<SysDictGroup,Long>, InsertListMapper<SysDictGroup> {

}

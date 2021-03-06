package com.lanmushan.sysservice.entity;

import java.util.Date;
import java.io.Serializable;

import com.lanmushan.framework.util.uuid.MyUUID;
import com.lanmushan.framework.util.uuid.SeqGenId;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import com.alibaba.excel.annotation.ExcelProperty;
import tk.mybatis.mapper.annotation.KeySql;

/**
 * (SysTbDict)实体类
 *
 * @author daiyu
 * @since 2020-06-14 21:15:02
 */
@Table(name = "sys_tb_dict")
@Data
public class SysTbDict implements Serializable {
    private static final long serialVersionUID = -29556091738693085L;

    @Id
    @KeySql(genId = SeqGenId.class)
    @ExcelProperty(value = " ")
    private Long id;
    /**
    * 映射值
    */    
    @ExcelProperty(value = " 映射值")
    private String dictValue;
    /**
    * 映射名称
    */    
    @ExcelProperty(value = " 映射名称")
    private String dictName;
    /**
    * 映射编码
    */    
    @ExcelProperty(value = " 映射编码")
    private String dictCode;
    /**
    * 所属分组
    */    
    @ExcelProperty(value = " 所属分组")
    private String fkDictGroupCode;
    /**
    * 创建时间
    */    
    @ExcelProperty(value = " 创建时间")
    private Date createTime;
    /**
    * 更新时间
    */    
    @ExcelProperty(value = " 更新时间")
    private Date updateTime;
    /**
    * 禁用
    */    
    @ExcelProperty(value = " 禁用")
    private Integer disabled;
    /**
    * 创建人
    */    
    @ExcelProperty(value = " 创建人")
    private Integer createUserId;
    /**
    * 默认
    */    
    @ExcelProperty(value = " 默认")
    private Integer isDefault;
    /**
    * 排序
    */    
    @ExcelProperty(value = " 排序")
    private Integer orderIndex;
    /**
    * 允许编辑
    */    
    @ExcelProperty(value = " 允许编辑")
    private Integer allowEdit;


}
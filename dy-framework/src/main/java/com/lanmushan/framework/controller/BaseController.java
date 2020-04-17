package com.lanmushan.framework.controller;

import com.github.pagehelper.PageHelper;
import com.lanmushan.framework.util.CommonUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 通用Controller,所有Controller需要继承该基类
 * @author dy
 */
public class BaseController {
    /**
     * 开始分页
     */
   public void startPage()
   { Integer currentPage=1;
      Integer pageSize=10;
      HttpServletRequest request =((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
      String currentPageStr= request.getParameter("currentPage");
      String pageSizeStr= request.getParameter("pageSize");
      if( StringUtils.isNotBlank(currentPageStr))
      {
           currentPage=Integer.parseInt(currentPageStr);
      }
      if(StringUtils.isNotBlank(pageSizeStr))
      {
          pageSize=Integer.parseInt(pageSizeStr);
      }
      startPage(currentPage,pageSize);
   }

    /**
     * 开始分页
     * @param currentPage 当前页
     * @param pageSize 分页大小
     */
    public void startPage(Integer currentPage,Integer pageSize){
        PageHelper.startPage(currentPage,pageSize);
        PageHelper.orderBy(getOrders());
    }

    /**
     * 获取排序
     * @return
     */
    public String getOrders(){
        String field=null;
        String sort="desc";
        HttpServletRequest request =((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        field=request.getParameter("field");
        if (StringUtils.isEmpty(field))
        {
            return null;
        }
        field= CommonUtil.camelToUnderline(field,'_');
        return field+" "+sort;
    }
}

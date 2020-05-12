package com.llm.demo.mapper;

import com.llm.demo.entity.Announcement;
import org.apache.ibatis.annotations.Insert;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface AnnouncementMapper extends Mapper<Announcement> {


    @Insert("insert into t_announcement (indexNo,category,organization,creattime,name,roles,content) values(#{indexno},#{category},#{organization},#{creattime},#{name},#{roles},#{content})")
    void addsecurities(Announcement announcement);
}
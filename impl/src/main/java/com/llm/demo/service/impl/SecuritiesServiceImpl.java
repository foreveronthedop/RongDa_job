package com.llm.demo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.llm.demo.SecuritiesService;
import com.llm.demo.entity.Announcement;
import com.llm.demo.mapper.AnnouncementMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Service
@Component
public class SecuritiesServiceImpl implements SecuritiesService{
    @Autowired
    private AnnouncementMapper mapper;


    @Override
    public void addsecurities(Announcement announcement) {
        mapper.addsecurities(announcement);
    }
}

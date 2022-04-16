package com.zy.maintenance.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zy.maintenance.bean.News;
import com.zy.maintenance.mapper.NewsMapper;
import com.zy.maintenance.service.NewsService;
import org.springframework.stereotype.Service;

@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements NewsService {

    @Override
    public Boolean saveNews(News news) {
        if (news.getNewId() == null) {
            return save(news);
        } else {
            return updateById(news);
        }
    }
}

package com.zy.maintenance.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zy.maintenance.bean.News;

public interface NewsService extends IService<News> {

    Boolean saveNews(News news);
}

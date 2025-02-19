package com.hzy.service;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzy.common.utils.PageUtils;
import com.hzy.common.utils.Query;

import com.hzy.dao.NewsDao;
import com.hzy.entity.NewsEntity;

@Service("newsService")
public class NewsService extends ServiceImpl<NewsDao, NewsEntity> {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<NewsEntity> page = this.page(
            new Query<NewsEntity>().getPage(params),
                new QueryWrapper<NewsEntity>()
            );

        return new PageUtils(page);
    }

}
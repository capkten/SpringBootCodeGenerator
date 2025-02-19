package com.hzy.service;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzy.common.utils.PageUtils;
import com.hzy.common.utils.Query;

import com.hzy.dao.NewsLeagueDao;
import com.hzy.entity.NewsLeagueEntity;

@Service("newsLeagueService")
public class NewsLeagueService extends ServiceImpl<NewsLeagueDao, NewsLeagueEntity> {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<NewsLeagueEntity> page = this.page(
            new Query<NewsLeagueEntity>().getPage(params),
                new QueryWrapper<NewsLeagueEntity>()
            );

        return new PageUtils(page);
    }

}
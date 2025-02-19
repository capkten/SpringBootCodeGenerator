package com.hzy.service;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzy.common.utils.PageUtils;
import com.hzy.common.utils.Query;

import com.hzy.dao.NewsTeamDao;
import com.hzy.entity.NewsTeamEntity;

@Service("newsTeamService")
public class NewsTeamService extends ServiceImpl<NewsTeamDao, NewsTeamEntity> {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<NewsTeamEntity> page = this.page(
            new Query<NewsTeamEntity>().getPage(params),
                new QueryWrapper<NewsTeamEntity>()
            );

        return new PageUtils(page);
    }

}
package com.hzy.service;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzy.common.utils.PageUtils;
import com.hzy.common.utils.Query;

import com.hzy.dao.PostsLeagueDao;
import com.hzy.entity.PostsLeagueEntity;

@Service("postsLeagueService")
public class PostsLeagueService extends ServiceImpl<PostsLeagueDao, PostsLeagueEntity> {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PostsLeagueEntity> page = this.page(
            new Query<PostsLeagueEntity>().getPage(params),
                new QueryWrapper<PostsLeagueEntity>()
            );

        return new PageUtils(page);
    }

}
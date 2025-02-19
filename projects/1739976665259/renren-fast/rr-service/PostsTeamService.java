package com.hzy.service;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzy.common.utils.PageUtils;
import com.hzy.common.utils.Query;

import com.hzy.dao.PostsTeamDao;
import com.hzy.entity.PostsTeamEntity;

@Service("postsTeamService")
public class PostsTeamService extends ServiceImpl<PostsTeamDao, PostsTeamEntity> {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PostsTeamEntity> page = this.page(
            new Query<PostsTeamEntity>().getPage(params),
                new QueryWrapper<PostsTeamEntity>()
            );

        return new PageUtils(page);
    }

}
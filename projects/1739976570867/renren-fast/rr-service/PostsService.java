package com.hzy.service;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzy.common.utils.PageUtils;
import com.hzy.common.utils.Query;

import com.hzy.dao.PostsDao;
import com.hzy.entity.PostsEntity;

@Service("postsService")
public class PostsService extends ServiceImpl<PostsDao, PostsEntity> {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PostsEntity> page = this.page(
            new Query<PostsEntity>().getPage(params),
                new QueryWrapper<PostsEntity>()
            );

        return new PageUtils(page);
    }

}
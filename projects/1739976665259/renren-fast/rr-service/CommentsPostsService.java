package com.hzy.service;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzy.common.utils.PageUtils;
import com.hzy.common.utils.Query;

import com.hzy.dao.CommentsPostsDao;
import com.hzy.entity.CommentsPostsEntity;

@Service("commentsPostsService")
public class CommentsPostsService extends ServiceImpl<CommentsPostsDao, CommentsPostsEntity> {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CommentsPostsEntity> page = this.page(
            new Query<CommentsPostsEntity>().getPage(params),
                new QueryWrapper<CommentsPostsEntity>()
            );

        return new PageUtils(page);
    }

}
package com.hzy.service;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzy.common.utils.PageUtils;
import com.hzy.common.utils.Query;

import com.hzy.dao.CommentsNewsDao;
import com.hzy.entity.CommentsNewsEntity;

@Service("commentsNewsService")
public class CommentsNewsService extends ServiceImpl<CommentsNewsDao, CommentsNewsEntity> {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CommentsNewsEntity> page = this.page(
            new Query<CommentsNewsEntity>().getPage(params),
                new QueryWrapper<CommentsNewsEntity>()
            );

        return new PageUtils(page);
    }

}
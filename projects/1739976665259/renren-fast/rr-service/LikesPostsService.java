package com.hzy.service;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzy.common.utils.PageUtils;
import com.hzy.common.utils.Query;

import com.hzy.dao.LikesPostsDao;
import com.hzy.entity.LikesPostsEntity;

@Service("likesPostsService")
public class LikesPostsService extends ServiceImpl<LikesPostsDao, LikesPostsEntity> {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<LikesPostsEntity> page = this.page(
            new Query<LikesPostsEntity>().getPage(params),
                new QueryWrapper<LikesPostsEntity>()
            );

        return new PageUtils(page);
    }

}
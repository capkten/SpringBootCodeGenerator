package com.hzy.service;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzy.common.utils.PageUtils;
import com.hzy.common.utils.Query;

import com.hzy.dao.CollectPostsDao;
import com.hzy.entity.CollectPostsEntity;

@Service("collectPostsService")
public class CollectPostsService extends ServiceImpl<CollectPostsDao, CollectPostsEntity> {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CollectPostsEntity> page = this.page(
            new Query<CollectPostsEntity>().getPage(params),
                new QueryWrapper<CollectPostsEntity>()
            );

        return new PageUtils(page);
    }

}
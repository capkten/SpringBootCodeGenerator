package com.hzy.service;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzy.common.utils.PageUtils;
import com.hzy.common.utils.Query;

import com.hzy.dao.CollectNewsDao;
import com.hzy.entity.CollectNewsEntity;

@Service("collectNewsService")
public class CollectNewsService extends ServiceImpl<CollectNewsDao, CollectNewsEntity> {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CollectNewsEntity> page = this.page(
            new Query<CollectNewsEntity>().getPage(params),
                new QueryWrapper<CollectNewsEntity>()
            );

        return new PageUtils(page);
    }

}
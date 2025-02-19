package com.hzy.service;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzy.common.utils.PageUtils;
import com.hzy.common.utils.Query;

import com.hzy.dao.LikesNewsDao;
import com.hzy.entity.LikesNewsEntity;

@Service("likesNewsService")
public class LikesNewsService extends ServiceImpl<LikesNewsDao, LikesNewsEntity> {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<LikesNewsEntity> page = this.page(
            new Query<LikesNewsEntity>().getPage(params),
                new QueryWrapper<LikesNewsEntity>()
            );

        return new PageUtils(page);
    }

}
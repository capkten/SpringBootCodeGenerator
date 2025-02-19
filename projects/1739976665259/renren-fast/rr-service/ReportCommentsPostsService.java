package com.hzy.service;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzy.common.utils.PageUtils;
import com.hzy.common.utils.Query;

import com.hzy.dao.ReportCommentsPostsDao;
import com.hzy.entity.ReportCommentsPostsEntity;

@Service("reportCommentsPostsService")
public class ReportCommentsPostsService extends ServiceImpl<ReportCommentsPostsDao, ReportCommentsPostsEntity> {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ReportCommentsPostsEntity> page = this.page(
            new Query<ReportCommentsPostsEntity>().getPage(params),
                new QueryWrapper<ReportCommentsPostsEntity>()
            );

        return new PageUtils(page);
    }

}
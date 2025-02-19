package com.hzy.service;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzy.common.utils.PageUtils;
import com.hzy.common.utils.Query;

import com.hzy.dao.ReportPostsDao;
import com.hzy.entity.ReportPostsEntity;

@Service("reportPostsService")
public class ReportPostsService extends ServiceImpl<ReportPostsDao, ReportPostsEntity> {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ReportPostsEntity> page = this.page(
            new Query<ReportPostsEntity>().getPage(params),
                new QueryWrapper<ReportPostsEntity>()
            );

        return new PageUtils(page);
    }

}
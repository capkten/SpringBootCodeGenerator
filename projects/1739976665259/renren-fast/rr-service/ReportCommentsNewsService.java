package com.hzy.service;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzy.common.utils.PageUtils;
import com.hzy.common.utils.Query;

import com.hzy.dao.ReportCommentsNewsDao;
import com.hzy.entity.ReportCommentsNewsEntity;

@Service("reportCommentsNewsService")
public class ReportCommentsNewsService extends ServiceImpl<ReportCommentsNewsDao, ReportCommentsNewsEntity> {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ReportCommentsNewsEntity> page = this.page(
            new Query<ReportCommentsNewsEntity>().getPage(params),
                new QueryWrapper<ReportCommentsNewsEntity>()
            );

        return new PageUtils(page);
    }

}
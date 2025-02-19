package com.hzy.service;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzy.common.utils.PageUtils;
import com.hzy.common.utils.Query;

import com.hzy.dao.LeagueDao;
import com.hzy.entity.LeagueEntity;

@Service("leagueService")
public class LeagueService extends ServiceImpl<LeagueDao, LeagueEntity> {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<LeagueEntity> page = this.page(
            new Query<LeagueEntity>().getPage(params),
                new QueryWrapper<LeagueEntity>()
            );

        return new PageUtils(page);
    }

}
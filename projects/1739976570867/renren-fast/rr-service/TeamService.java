package com.hzy.service;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzy.common.utils.PageUtils;
import com.hzy.common.utils.Query;

import com.hzy.dao.TeamDao;
import com.hzy.entity.TeamEntity;

@Service("teamService")
public class TeamService extends ServiceImpl<TeamDao, TeamEntity> {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<TeamEntity> page = this.page(
            new Query<TeamEntity>().getPage(params),
                new QueryWrapper<TeamEntity>()
            );

        return new PageUtils(page);
    }

}
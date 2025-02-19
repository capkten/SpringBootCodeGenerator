package com.hzy.service;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzy.common.utils.PageUtils;
import com.hzy.common.utils.Query;

import com.hzy.dao.UsersDao;
import com.hzy.entity.UsersEntity;

@Service("usersService")
public class UsersService extends ServiceImpl<UsersDao, UsersEntity> {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UsersEntity> page = this.page(
            new Query<UsersEntity>().getPage(params),
                new QueryWrapper<UsersEntity>()
            );

        return new PageUtils(page);
    }

}
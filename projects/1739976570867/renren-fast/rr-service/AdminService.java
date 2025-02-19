package com.hzy.service;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzy.common.utils.PageUtils;
import com.hzy.common.utils.Query;

import com.hzy.dao.AdminDao;
import com.hzy.entity.AdminEntity;

@Service("adminService")
public class AdminService extends ServiceImpl<AdminDao, AdminEntity> {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AdminEntity> page = this.page(
            new Query<AdminEntity>().getPage(params),
                new QueryWrapper<AdminEntity>()
            );

        return new PageUtils(page);
    }

}
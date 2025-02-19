package com.hzy.service;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzy.common.utils.PageUtils;
import com.hzy.common.utils.Query;

import com.hzy.dao.NewsEditorDao;
import com.hzy.entity.NewsEditorEntity;

@Service("newsEditorService")
public class NewsEditorService extends ServiceImpl<NewsEditorDao, NewsEditorEntity> {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<NewsEditorEntity> page = this.page(
            new Query<NewsEditorEntity>().getPage(params),
                new QueryWrapper<NewsEditorEntity>()
            );

        return new PageUtils(page);
    }

}
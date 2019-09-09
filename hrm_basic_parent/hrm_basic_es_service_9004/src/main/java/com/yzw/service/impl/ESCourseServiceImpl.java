package com.yzw.service.impl;

import com.yzw.domain.ESCourse;
import com.yzw.query.ESCourseQuery;
import com.yzw.repository.ESRepository;
import com.yzw.service.IESCourseService;
import com.yzw.util.PageList;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yzw
 * @since 2019-09-05
 */
@Service
public class ESCourseServiceImpl implements IESCourseService {
    @Autowired
    private ESRepository esRepository;
    @Override
    public List<ESCourse> selectList(Object o) {
        Page page = (Page) esRepository.findAll();
        return page.getContent();
    }

    @Override
    public ESCourse selectById(Long id) {
        return esRepository.findById(id).get();
    }

    @Override
    public void deleteById(Long id) {
        esRepository.deleteById(id);
    }

    @Override
    public void insert(ESCourse course) {
        esRepository.save(course);
    }

    @Override
    public void updateById(ESCourse course) {
        esRepository.save(course);
    }

    @Override
    public void batchSave(List<ESCourse> esCourseList) {
        esRepository.saveAll(esCourseList);
    }

    @Override
    public void batchDel(List<ESCourse> esCourseList) {
        esRepository.deleteAll(esCourseList);
    }

    @Override
    public PageList<ESCourse> selectListPage(ESCourseQuery query) {
        //创建一个本地的搜索查询构造器
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        //拿到bool
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        //must
        boolQuery.must(QueryBuilders.matchQuery("intro", "zs"));
        //拿到filter
        List<QueryBuilder> filter = boolQuery.filter();
        filter.add(QueryBuilders.rangeQuery("age").gte(10).lte(40));
        //排序
        builder.withPageable(PageRequest.of(query.getPage()-1, query.getRows()));
        //构建查询条件
        NativeSearchQuery esQuery = builder.build();
        Page<ESCourse> page = esRepository.search(esQuery);
        return new PageList<>(page.getTotalElements(),page.getContent());
    }

}

package com.yzw.repository;

import com.yzw.domain.ESCourse;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ESRepository extends ElasticsearchRepository<ESCourse,Long> {
}

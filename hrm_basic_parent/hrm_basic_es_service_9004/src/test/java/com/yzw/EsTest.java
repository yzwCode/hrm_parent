package com.yzw;

import com.yzw.domain.ESCourse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ESApplication9004.class)
public class EsTest {
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    @Test
    public void test() throws Exception{
        elasticsearchTemplate.createIndex(ESCourse.class);
        elasticsearchTemplate.putMapping(ESCourse.class);
    }
}

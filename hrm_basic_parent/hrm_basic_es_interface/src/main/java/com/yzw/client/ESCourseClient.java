package com.yzw.client;
import com.yzw.domain.ESCourse;
import com.yzw.query.ESCourseQuery;
import com.yzw.util.AjaxResult;
import com.yzw.util.PageList;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@FeignClient(value = "HRM-ES",configuration = FeignClientsConfiguration.class,
        fallbackFactory = ESCourseClientHystrixFallbackFactory.class)
@RequestMapping("/esCourse")
public interface ESCourseClient {
    /**
     * 保存和修改公用的
     * @param course  传递的实体
     * @return Ajaxresult转换结果
     */
    @RequestMapping(value="/save",method= RequestMethod.POST)
    AjaxResult save(ESCourse course);

    /**
     * 删除对象信息
     * @param id
     * @return
     */
    @RequestMapping(value="/delete/{id}",method=RequestMethod.DELETE)
    AjaxResult delete(@PathVariable("id") Integer id);

    //获取用户
    @RequestMapping("/{id}")
    ESCourse get(@RequestParam(value="id",required=true) Long id);


    /**
     * 查看所有的员工信息
     * @return
     */
    @RequestMapping("/list")
    public List<ESCourse> list();

    /**
     * 分页查询数据
     *
     * @param query 查询对象
     * @return PageList 分页对象
     */
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    PageList<ESCourse> json(@RequestBody ESCourseQuery query);
    @PostMapping("/onLine")
    void batchSave(@RequestBody List<ESCourse> esCourseList);
    @PostMapping("/offLine")
    void batchDel(@RequestBody List<ESCourse> esCourseList);
}

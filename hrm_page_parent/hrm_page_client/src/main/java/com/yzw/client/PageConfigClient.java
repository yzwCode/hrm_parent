package com.yzw.client;
import com.yzw.domain.PageConfig;
import com.yzw.query.PageConfigQuery;
import com.yzw.util.AjaxResult;
import com.yzw.util.PageList;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@FeignClient(value = "HRM-ZUUL",configuration = FeignClientsConfiguration.class,
        fallbackFactory = PageConfigClientHystrixFallbackFactory.class)
@RequestMapping("/pageConfig")
public interface PageConfigClient {
    /**
     * 保存和修改公用的
     * @param pageConfig  传递的实体
     * @return Ajaxresult转换结果
     */
    @RequestMapping(value="/save",method= RequestMethod.POST)
    AjaxResult save(PageConfig pageConfig);

    /**
     * 删除对象信息
     * @param id
     * @return
     */
    @RequestMapping(value="/delete/{id}",method=RequestMethod.DELETE)
    AjaxResult delete(@PathVariable("id") Integer id);

    //获取用户
    @RequestMapping("/{id}")
    PageConfig get(@RequestParam(value="id",required=true) Long id);


    /**
     * 查看所有的员工信息
     * @return
     */
    @RequestMapping("/list")
    public List<PageConfig> list();

    /**
     * 分页查询数据
     *
     * @param query 查询对象
     * @return PageList 分页对象
     */
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    PageList<PageConfig> json(@RequestBody PageConfigQuery query);
}

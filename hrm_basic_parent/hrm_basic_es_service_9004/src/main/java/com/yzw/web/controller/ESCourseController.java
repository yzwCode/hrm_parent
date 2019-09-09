package com.yzw.web.controller;

import com.yzw.domain.ESCourse;
import com.yzw.query.ESCourseQuery;
import com.yzw.service.IESCourseService;
import com.yzw.util.AjaxResult;
import com.yzw.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/esCourse")
public class ESCourseController {
    @Autowired
    public IESCourseService esCourseService;

    /**
    * 保存和修改公用的
    * @param course  传递的实体
    * @return Ajaxresult转换结果
    */
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody ESCourse course){
        try {
            if(course.getId()!=null){
                esCourseService.updateById(course);
            }else{
                esCourseService.insert(course);
            }
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("保存对象失败！"+e.getMessage());
        }
    }

    /**
    * 删除对象信息
    * @param id
    * @return
    */
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") Long id){
        try {
            esCourseService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("删除对象失败！"+e.getMessage());
        }
    }

    //获取用户
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ESCourse get(@PathVariable("id")Long id)
    {
        return esCourseService.selectById(id);
    }


    /**
    * 查看所有的员工信息
    * @return
    */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<ESCourse> list(){

        return esCourseService.selectList(null);
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public PageList<ESCourse> json(@RequestBody ESCourseQuery query){
        return esCourseService.selectListPage(query);
    }
    @PostMapping("/onLine")
    AjaxResult batchSave(@RequestBody List<ESCourse> esCourseList){
        System.out.println(esCourseList.size());
        try {
            esCourseService.batchSave(esCourseList);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("批量添加失败!"+e.getMessage());
        }
    }
    @PostMapping("/offLine")
    AjaxResult batchDel(@RequestBody List<ESCourse> esCourseList){
        try {
            esCourseService.batchDel(esCourseList);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("批量删除失败!"+e.getMessage());
        }
    }
}

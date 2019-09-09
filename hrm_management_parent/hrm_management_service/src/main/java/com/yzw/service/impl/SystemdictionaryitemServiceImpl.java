package com.yzw.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.yzw.domain.Systemdictionary;
import com.yzw.domain.Systemdictionaryitem;
import com.yzw.mapper.SystemdictionaryMapper;
import com.yzw.mapper.SystemdictionaryitemMapper;
import com.yzw.service.ISystemdictionaryitemService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yzw
 * @since 2019-08-30
 */
@Service
public class SystemdictionaryitemServiceImpl extends ServiceImpl<SystemdictionaryitemMapper, Systemdictionaryitem> implements ISystemdictionaryitemService {
    @Autowired
    private SystemdictionaryitemMapper systemdictionaryitemMapper;
    @Autowired
    private SystemdictionaryMapper systemdictionaryMapper;
    private Logger logger = LoggerFactory.getLogger(SystemdictionaryitemServiceImpl.class);
    @Override
    public List<Systemdictionaryitem> listByParentSn(String sn) {
        //对wrapper设置一个条件
        Wrapper<Systemdictionary> wrapper = new EntityWrapper<Systemdictionary>().eq("sn", sn);
        //将上面的条件放入进行查询
        List<Systemdictionary> systemdictionaries = systemdictionaryMapper.selectList(wrapper);
        //判断systemdictionaries是否为空
        if(systemdictionaries!=null&&systemdictionaries.size()<1){
            //记录错误信息日志
            logger.error("Systemdictionary is not exist");
            //返回空
            return null;
        }
        //由于没有查询得到一个的方法,所以查询所有,但是list中只有一个值,取出0位即可
        Systemdictionary systemdictionary = systemdictionaries.get(0);
        //在做条件查询
        return systemdictionaryitemMapper.selectList(new EntityWrapper<Systemdictionaryitem>().eq("parent_id", systemdictionary.getId()));
    }
}

package com.yzw.mapper;

import com.yzw.domain.Tenant;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yzw
 * @since 2019-09-02
 */
public interface TenantMapper extends BaseMapper<Tenant> {
    /**
     * 保存机构和套餐的中间表信息
     * @param mealsMap
     */
    void saveTenantMeals(List<Map<String, Long>> mealsMap);
}

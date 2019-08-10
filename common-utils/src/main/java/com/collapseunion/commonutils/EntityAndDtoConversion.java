package com.collapseunion.commonutils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Mybatis-plus实体对象和DTO对象互相转换
 *
 * @author CloudSen
 */
public class EntityAndDtoConversion {

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    /**
     * mybatis plus Page分页实体对象转换为DTO分页对象
     *
     * @param entityPage 实体分页对象
     * @param dClass     DTO对象类型
     * @param <E>        实体对象类型
     * @param <D>        DTO对象类型
     * @return DTO分页对象
     */
    public static <E, D> Page<D> pageEntity2Dto(IPage<E> entityPage, Class<D> dClass) {
        Page<D> dtoPage = new Page<D>()
                .setCurrent(entityPage.getCurrent())
                .setOptimizeCountSql(entityPage.optimizeCountSql())
                .setSearchCount(entityPage.isSearchCount())
                .setSize(entityPage.getSize())
                .setTotal(entityPage.getTotal());
        List<D> tempDtoList = new ArrayList<>();
        entityPage.getRecords().forEach(entity -> tempDtoList.add(MODEL_MAPPER.map(entity, dClass)));
        dtoPage.setRecords(tempDtoList);
        return dtoPage;
    }

    public static <E, D> List<D> listEntity2Dto(List<E> entityList, Class<D> dClass) {
        List<D> tempDtoList = new ArrayList<>();
        entityList.forEach(entity -> tempDtoList.add(MODEL_MAPPER.map(entity, dClass)));
        return tempDtoList;
    }
}

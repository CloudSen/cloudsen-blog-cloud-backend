package com.collapseunion.demo.service.impl;

import com.collapseunion.commonapi.entity.TestJpaEntity;
import com.collapseunion.commonapi.repository.TestJpaRepository;
import com.collapseunion.commonutils.TimeUtil;
import com.collapseunion.commonutils.customexceptions.DataNotFoundException;
import com.collapseunion.commonutils.customexceptions.DuplicateDataException;
import com.collapseunion.demo.dto.TestJpaDto;
import com.collapseunion.demo.service.TestJpaService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author CloudSen
 */
@Slf4j
@Service
public class TestJpaServiceImpl implements TestJpaService {

    private final TestJpaRepository testJpaRepository;

    public TestJpaServiceImpl(TestJpaRepository testJpaRepository) {
        this.testJpaRepository = testJpaRepository;
    }

    @Override
    public TestJpaEntity findById(String uuid) {
        log.info(Constants.FINDING_BY_ID, uuid);
        return this.testJpaRepository.findById(uuid).orElse(null);
    }

    @Override
    @HystrixCommand(fallbackMethod = "findByNow")
    public List<TestJpaEntity> findByCreateDate(Date createDate) {
        log.info(Constants.FINDING_BY_CREATE_DATE,
                TimeUtil.parseDateToString(createDate, TimeUtil.YYYY_MM_DD));
        List<TestJpaEntity> resultList = this.testJpaRepository.findByCreateDate(createDate);
        if (CollectionUtils.isEmpty(resultList)) {
            throw new DataNotFoundException(Constants.DATA_NOT_FOUND);
        }
        return resultList;
    }

    @Override
    public List<TestJpaEntity> findAll() {
        log.info(Constants.FINDING_ALL);
        List<TestJpaEntity> data = this.testJpaRepository.findAll();
        if (CollectionUtils.isEmpty(data) || data.size() == 3) {
            throw new NullPointerException("null data");
        }
        return data;
    }

    @Override
    public List<TestJpaEntity> findByCondition(TestJpaDto condition) {
        log.info(Constants.FINDING_BY_CONDITION, condition);
        return this.testJpaRepository.findAll(this.getTestJpaExample(condition));
    }

    @Override
    public TestJpaEntity createNew(TestJpaDto testJpaDto) {
        log.info(Constants.CREATING_TEST_ENTITY, testJpaDto);
        List<TestJpaEntity> existsEntities = this.findByCondition(testJpaDto);
        if (!CollectionUtils.isEmpty(existsEntities)) {
            throw new DuplicateDataException(Constants.TEST_ENTITY_EXISTS);
        }
        TestJpaEntity newEntity = new TestJpaEntity()
                .setId(UUID.randomUUID().toString())
                .setCreateDate(new Date())
                .setName(testJpaDto.getName());
        return this.testJpaRepository.save(newEntity);
    }

    @Override
    public void deleteById(String uuid) {
        log.info(Constants.DEL_TEST_ENTITY, uuid);
        this.testJpaRepository.deleteById(uuid);
    }

    @Override
    public TestJpaEntity update(TestJpaDto testJpaDto) {
        TestJpaEntity oldEntity = this.findById(testJpaDto.getId());
        if (oldEntity == null) {
            throw new DataNotFoundException(Constants.TEST_ENTITY_NOT_EXISTS);
        }
        log.info(Constants.UPDATING_TEST_ENTITY, oldEntity.getId());
        TestJpaEntity needUpdateEntity = oldEntity
                .setUpdateDate(new Date())
                .setName(testJpaDto.getName());
        return this.testJpaRepository.save(needUpdateEntity);
    }

    @Override
    public Page<TestJpaEntity> pagingByCondition(TestJpaDto condition, Pageable pageable) {
        log.info(Constants.PAGING_BY_CONDITION, condition);
        return this.testJpaRepository.findAll(this.getTestJpaExample(condition), pageable);
    }

    /**
     * 获取混合条件查询的example
     *
     * @param condition 查询条件
     * @return example
     */
    private Example<TestJpaEntity> getTestJpaExample(TestJpaDto condition) {
        TestJpaEntity testEntity = new TestJpaEntity()
                .setId(condition.getId())
                .setName(condition.getName())
                .setCreateDate(condition.getCreateDate());
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withMatcher("id", ExampleMatcher.GenericPropertyMatcher::contains)
                .withMatcher("name", ExampleMatcher.GenericPropertyMatcher::contains);
        return Example.of(testEntity, exampleMatcher);
    }

    private List<TestJpaEntity> findByNow(Date createDate) {
        log.warn(Constants.HYSTRIX_TRIGGER);
        return Arrays.asList(
                new TestJpaEntity()
                        .setName("Hystrix Default Name")
                        .setCreateDate(new Date())
                        .setUpdateDate(new Date())
                        .setId(UUID.randomUUID().toString()),
                new TestJpaEntity()
                        .setName("Hystrix Something Wrong, Please Waite...")
                        .setCreateDate(new Date())
                        .setUpdateDate(new Date())
                        .setId(UUID.randomUUID().toString())
        );
    }

}

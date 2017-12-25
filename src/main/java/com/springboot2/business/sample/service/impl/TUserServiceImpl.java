package com.springboot2.business.sample.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springboot2.business.sample.dataset.TUser;
import com.springboot2.business.sample.dataset.TUserExample;
import com.springboot2.business.sample.mapper.TUserMapper;
import com.springboot2.business.sample.service.TUserService;

@Repository
public class TUserServiceImpl implements TUserService {

    @Autowired
    TUserMapper tUserMapper;

    @Override
    public long countByExample(TUserExample example) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int deleteByExample(TUserExample example) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Short id) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int insert(TUser record) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int insertSelective(TUser record) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<TUser> selectByExample(TUserExample example) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public TUser selectByPrimaryKey(Short id) {

        return tUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByExampleSelective(TUser record, TUserExample example) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int updateByExample(TUser record, TUserExample example) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(TUser record) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int updateByPrimaryKey(TUser record) {
        // TODO Auto-generated method stub
        return 0;
    }

}

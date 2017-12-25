package com.springboot2.business.sample.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot2.business.sample.dataset.TUser;
import com.springboot2.business.sample.facade.SampleFacade;
import com.springboot2.business.sample.service.TUserService;

@Service
public class SampleFacadeImpl implements SampleFacade {

    @Autowired
    TUserService tUserService;

    @Override
    public TUser selectByPrimaryKey(Short id) {

        return tUserService.selectByPrimaryKey(id);
    }

}

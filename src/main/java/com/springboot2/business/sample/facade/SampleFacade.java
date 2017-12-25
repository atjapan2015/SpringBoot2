package com.springboot2.business.sample.facade;

import com.springboot2.business.sample.dataset.TUser;

public interface SampleFacade {

    TUser selectByPrimaryKey(Short id);
}

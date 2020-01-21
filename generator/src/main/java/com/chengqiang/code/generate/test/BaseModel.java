package com.chengqiang.code.generate.test;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BaseModel {
    protected Date createTime;
    protected Date updateTime;
}

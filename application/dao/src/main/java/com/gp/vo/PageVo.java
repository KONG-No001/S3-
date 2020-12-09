package com.gp.vo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

public class PageVo<T> extends Page<T> {
    public PageVo(long current, long size) {
        super(current, size);
    }
}

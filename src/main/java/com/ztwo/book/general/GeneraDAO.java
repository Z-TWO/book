package com.ztwo.book.general;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @Author ZTwo
 * @Date 2021/12/19 21:47
 */
public interface GeneraDAO<T> extends Mapper<T>, MySqlMapper<T> {
}

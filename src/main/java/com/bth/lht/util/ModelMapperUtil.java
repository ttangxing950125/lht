package com.bth.lht.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

/**
 * @program: lht
 * @description: 对象映射工具类
 * @author: Antony
 * @create: 2018-12-21 12:48
 **/
public class ModelMapperUtil {
    /**
     * 严格匹配模式
     * @return modelMapper
     */
    public static ModelMapper getStrictModelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }
}

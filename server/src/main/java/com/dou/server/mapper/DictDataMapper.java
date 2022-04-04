package com.dou.server.mapper;

import com.dou.server.model.DictData;
import com.dou.server.tag.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author yangjd
 */
public interface DictDataMapper extends MyMapper<DictData> {

    @Select("SELECT dictData.id,dictData.name,dictData.value FROM sys_dict_data dictData " +
        "left join sys_dict dict on dict.id = dictData.pid " +
        "where dict.sign = #{sign} order by dictData.sort")
    List<DictData> getListByCode(@Param("sign") String sign);
}
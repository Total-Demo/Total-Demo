package com.barry.droolsdemo.mapper;



import com.barry.droolsdemo.domain.ActivityRule;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author lgp
 * 数据库连接类
 */
@Mapper
public interface ActivityRuleMapper {

    ActivityRule selectByPrimaryKey(Integer id);

    List<ActivityRule> selectAll();

}
package cn.lili.modules.order.order.service;

import cn.lili.modules.order.order.entity.dos.AtmUserGradeRule;
import cn.lili.modules.order.order.entity.dto.AtmUserGradeDTO;

import java.util.List;

public interface AtmUserGradeRuleService {

    //添加
    void add(AtmUserGradeRule atmUserGradeRule);
    //修改
    void update(AtmUserGradeRule atmUserGradeRule);
    //删除
    void delete(AtmUserGradeRule atmUserGradeRule);
    //查询
    AtmUserGradeRule queryByGradle(AtmUserGradeDTO atmUserGradeRule);
    //查询列表
    List<AtmUserGradeRule> queryList(AtmUserGradeDTO atmUserGradeRule);
}

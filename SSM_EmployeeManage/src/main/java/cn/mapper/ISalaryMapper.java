package cn.mapper;

import org.springframework.stereotype.Service;

import cn.pojo.Salary;

@Service("ISalaryMapper")
public interface ISalaryMapper {
    public Integer saveSal(Salary sa);//个人薪资录入
    public Integer deleteSal(Integer eid);//个人薪资删除
    public Integer updateSal(Salary sa);//个人薪资修改
    public Salary searchSalById(Integer eid);//个人薪资查询
}

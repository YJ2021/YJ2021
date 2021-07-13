package cn.mapper;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.pojo.Employee;
import cn.pojo.PageBean;

@Service("IEmployeeMapper")
public interface IEmployeeMapper {
     public Integer saveEmp(Employee emp);//员工信息录入
     public Integer deleteEmp(Integer eid);//员工信息删除
     public Integer updateEmp(Employee emp);//员工信息修改
     public Employee searchEmpById(Integer eid);//员工信息单个查询
     public List<Employee> searchEmpPageAll(PageBean pe);//员工信息分页查询
     public Integer maxRows();//总记录数查询
     public Integer searchEid();//员工编号查询
}

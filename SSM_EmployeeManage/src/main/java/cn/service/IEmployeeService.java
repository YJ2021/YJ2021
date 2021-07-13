package cn.service;

import java.util.List;

import cn.pojo.Employee;
import cn.pojo.PageBean;

public interface IEmployeeService {
	public boolean saveEmp(Employee emp);//员工信息录入
    public boolean deleteEmp(Integer eid);//员工信息删除
    public boolean updateEmp(Employee emp);//员工信息修改
    public Employee searchEmpById(Integer eid);//员工信息单个查询
    public List<Employee> searchEmpPageAll(PageBean pe);//员工信息分页查询
    public Integer maxRows();//总记录数查询
}

package cn.mapper;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.pojo.Department;
import cn.pojo.Employee;
@Service("IDepartmentMapper")
public interface IDepartmentMapper {
    public List<Department> findDeptAll();//�����б��ѯ
    public Department searchDeptName(Integer eid);
}

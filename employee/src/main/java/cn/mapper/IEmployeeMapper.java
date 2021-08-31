package cn.mapper;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.pojo.Employee;
import cn.pojo.PageBean;

@Service("IEmployeeMapper")
public interface IEmployeeMapper {
     public Integer saveEmp(Employee emp);//Ա����Ϣ¼��
     public Integer deleteEmp(Integer eid);//Ա����Ϣɾ��
     public Integer updateEmp(Employee emp);//Ա����Ϣ�޸�
     public Employee searchEmpById(Integer eid);//Ա����Ϣ������ѯ
     public List<Employee> searchEmpPageAll(PageBean pe);//Ա����Ϣ��ҳ��ѯ
     public Integer maxRows();//�ܼ�¼����ѯ
     public Integer searchEid();//Ա����Ų�ѯ
}

package cn.service;

import java.util.List;

import cn.pojo.Employee;
import cn.pojo.PageBean;

public interface IEmployeeService {
	public boolean saveEmp(Employee emp);//Ա����Ϣ¼��
    public boolean deleteEmp(Integer eid);//Ա����Ϣɾ��
    public boolean updateEmp(Employee emp);//Ա����Ϣ�޸�
    public Employee searchEmpById(Integer eid);//Ա����Ϣ������ѯ
    public List<Employee> searchEmpPageAll(PageBean pe);//Ա����Ϣ��ҳ��ѯ
    public Integer maxRows();//�ܼ�¼����ѯ
}

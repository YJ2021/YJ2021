package cn.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.pojo.Employee;
import cn.pojo.Employee_Welfare;
import cn.pojo.PageBean;
import cn.pojo.Salary;
import cn.pojo.Welfare;
import cn.service.IEmployeeService;
import cn.util.EmpServiceUtil;
@Service("EmployeeServiceImpl")
@Transactional
public class EmployeeServiceImpl implements IEmployeeService{
	@Resource(name="EmpServiceUtil")
	private EmpServiceUtil esu;
	public EmpServiceUtil getEsu() {
		return esu;
	}

	public void setEsu(EmpServiceUtil esu) {
		this.esu = esu;
	}
	@Override
	public boolean saveEmp(Employee emp) {
		Integer rows=esu.getEmpDao().saveEmp(emp);
		//��ѯ�մ����Ա�����
		Integer eid=esu.getEmpDao().searchEid();
		//н�ʱ�н��¼��
		esu.getSalDao().saveSal(new Salary(eid,emp.getSalary()));
		String []welfares=emp.getWids();
			if(welfares.length>0) {
				for (int i = 0; i < welfares.length; i++) {
					Employee_Welfare ew=new Employee_Welfare(eid,new Integer(welfares[i]));
					    //Ա�����˸���¼��
					    esu.getEmp_WelfDao().saveEmp_Welf(ew);
					}
				}
				if(rows>0) {
					return true;
				}
		return false;
	}

	@Override
	public boolean deleteEmp(Integer eid) {
		//ɾ�������м���Ϣ
		esu.getEmp_WelfDao().deleteEmp_Welf(eid);
		//ɾ������н�ʱ�
		esu.getSalDao().deleteSal(eid);
		//ɾ��������Ϣ
		Integer rows=esu.getEmpDao().deleteEmp(eid);
		if(rows>0) {
					return true;
				}
		return false;
	}

	@Override
	public boolean updateEmp(Employee emp) {
		//Ա��������Ϣ�޸�
		Integer rows=esu.getEmpDao().updateEmp(emp);
		//Ա��н���޸�
		esu.getSalDao().updateSal(new Salary(emp.getEid(),emp.getSalary()));
		//ɾ���ɵĸ���
		esu.getEmp_WelfDao().deleteEmp_Welf(emp.getEid());
		   String []welfares=emp.getWids();
			    if(welfares.length>0) {
			    	for (int i = 0; i < welfares.length; i++) {
			    		Employee_Welfare ew=new Employee_Welfare(emp.getEid(),new Integer(welfares[i]));
				    	//���µĸ��˸�����ӵ���ϵ��
			    		esu.getEmp_WelfDao().saveEmp_Welf(ew);
			    	}	
			     }
			    	if(rows>0) {
		    	    	return true;
		    	    }
		return false;
	}

	@Override
	public Employee searchEmpById(Integer eid) {
		Employee em=esu.getEmpDao().searchEmpById(eid);
		if(null!=em) {
			Float sal=esu.getSalDao().searchSalById(eid).getSalary();
			if(sal!=null) {
				em.setSalary(sal);
			}
			List<Welfare> liswelf=esu.getEmp_WelfDao().searchByIdEmp_Welf(eid);
			if(liswelf!=null) {
				em.setWelfares(liswelf);
			}
			String [] wids=new String[liswelf.size()];
			for (int i = 0; i < liswelf.size(); i++) {
				String wid=liswelf.get(i).getWid().toString();
				wids[i]=wid;
				
			}
			em.setWids(wids);
		}
		return em;
	}
	@Override
	public List<Employee> searchEmpPageAll(PageBean pe) {
		List<Employee> lisem=esu.getEmpDao().searchEmpPageAll(pe);
		/*
		 * if(null!=lisem) { for (int i = 0; i < lisem.size(); i++) { Employee
		 * em=lisem.get(i); Salary sal=esu.getSalDao().searchSalById(em.getEid());
		 * List<Welfare> welfares=esu.getEmp_WelfDao().searchByIdEmp_Welf(em.getEid());
		 * em.setSalary(sal.getSalary()); em.setWelfares(welfares); } }
		 */
		return lisem;
	}

	@Override
	public Integer maxRows() {
		return esu.getEmpDao().maxRows();
	}
}

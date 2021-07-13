package cn.util;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.mapper.IDepartmentMapper;
import cn.mapper.IEmp_WelfareMapper;
import cn.mapper.IEmployeeMapper;
import cn.mapper.ISalaryMapper;
import cn.mapper.IWelfareMapper;

@Service("EmpServiceUtil")
public class EmpServiceUtil {
	@Resource(name="IDepartmentMapper")
    private IDepartmentMapper deptDao;
	@Resource(name="IEmp_WelfareMapper")
    private IEmp_WelfareMapper emp_WelfDao;
	@Resource(name="IEmployeeMapper")
    private IEmployeeMapper empDao;
	@Resource(name="ISalaryMapper")
    private ISalaryMapper salDao;
	@Resource(name="IWelfareMapper")
    private IWelfareMapper welfDao;
	public IDepartmentMapper getDeptDao() {
		return deptDao;
	}
	public void setDeptDao(IDepartmentMapper deptDao) {
		this.deptDao = deptDao;
	}
	public IEmp_WelfareMapper getEmp_WelfDao() {
		return emp_WelfDao;
	}
	public void setEmp_WelfDao(IEmp_WelfareMapper emp_WelfDao) {
		this.emp_WelfDao = emp_WelfDao;
	}
	public IEmployeeMapper getEmpDao() {
		return empDao;
	}
	public void setEmpDao(IEmployeeMapper empDao) {
		this.empDao = empDao;
	}
	public ISalaryMapper getSalDao() {
		return salDao;
	}
	public void setSalDao(ISalaryMapper salDao) {
		this.salDao = salDao;
	}
	public IWelfareMapper getWelfDao() {
		return welfDao;
	}
	public void setWelfDao(IWelfareMapper welfDao) {
		this.welfDao = welfDao;
	}
	
}

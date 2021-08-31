package cn.util;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.service.IDepartmentService;
import cn.service.IWelfareService;
import cn.service.IEmployeeService;

@Service("EmpControllerUtil")
public class EmpControllerUtil {
	@Resource(name="DepartmentImpl")
private IDepartmentService deptService;
	@Resource(name="WelfareServiceImpl")
private IWelfareService welfService;
	@Resource(name="EmployeeServiceImpl")
private IEmployeeService empService;
	public IDepartmentService getDeptService() {
		return deptService;
	}
	public void setDeptService(IDepartmentService deptService) {
		this.deptService = deptService;
	}
	
	public IWelfareService getWelfService() {
		return welfService;
	}
	public void setWelfService(IWelfareService welfService) {
		this.welfService = welfService;
	}
	public IEmployeeService getEmpService() {
		return empService;
	}
	public void setEmpService(IEmployeeService empService) {
		this.empService = empService;
	}
	
}

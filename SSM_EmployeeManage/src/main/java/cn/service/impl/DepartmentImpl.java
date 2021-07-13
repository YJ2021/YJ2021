package cn.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.pojo.Department;
import cn.service.IDepartmentService;
import cn.util.EmpServiceUtil;
@Service("DepartmentImpl")
@Transactional
public class DepartmentImpl implements IDepartmentService{
    @Resource(name="EmpServiceUtil")
	private EmpServiceUtil esu;
	public EmpServiceUtil getEsu() {
		return esu;
	}

	public void setEsu(EmpServiceUtil esu) {
		this.esu = esu;
	}

	@Override
	public List<Department> findDeptAll() {
		return esu.getDeptDao().findDeptAll();
	}

}

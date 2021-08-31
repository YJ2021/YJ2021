package cn.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.pojo.Employee_Welfare;
import cn.pojo.Welfare;
import cn.service.IWelfareService;
import cn.util.EmpServiceUtil;
@Service("WelfareServiceImpl")
@Transactional
public class WelfareServiceImpl implements IWelfareService{
	@Resource(name="EmpServiceUtil")
	private EmpServiceUtil esu;
	public EmpServiceUtil getEsu() {
		return esu;
	}

	public void setEsu(EmpServiceUtil esu) {
		this.esu = esu;
	}
	@Override
	public List<Welfare> searchWelfareAll() {
		
		return esu.getWelfDao().searchWelfareAll();
	}
	
}

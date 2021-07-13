package cn.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;

import cn.pojo.Department;
import cn.pojo.Employee;
import cn.pojo.PageBean;
import cn.pojo.Welfare;
import cn.util.AJAX_JSON;
import cn.util.EmpControllerUtil;


@Controller
public class EmployeeController implements IController{
    @Resource(name="EmpControllerUtil")
	private EmpControllerUtil ecu;
    
	public EmpControllerUtil getEcu() {
		return ecu;
	}

	public void setEcu(EmpControllerUtil ecu) {
		this.ecu = ecu;
	}

	@Override
	@RequestMapping(value = "/saveEmp")
	public String saveEmp(HttpServletRequest request, HttpServletResponse response, Employee emp) {
		System.out.println("添加Servlet开始工作......");
		System.out.println("emp:"+emp);
		//获取服务器根路径
		String realpath=request.getSession().getServletContext().getRealPath("/");
		HttpSession session=request.getSession();
		/**文件上传start*/
		//获取文件上传对象
		MultipartFile multipartFile=emp.getPic();
		if(!multipartFile.isEmpty()&&null!=multipartFile) {
			//获取上传对象名称
			String fname=multipartFile.getOriginalFilename();
			if(fname.lastIndexOf(".")!=-1) {
				//截取后缀
				String ext=fname.substring(fname.lastIndexOf("."));
				//修改名称
				String newFname=new Date().getTime()+ext;
				String path=realpath+"/uppic/"+newFname;
				File file=new File(	path);
				System.out.println("文件路径："+path);
				//上传，复制
				try {
					FileUtils.copyInputStreamToFile(multipartFile.getInputStream(),file );
				} catch (IOException e) {
					e.printStackTrace();
				}
			    emp.setPhoto(newFname);
			    /**文件上传end*/
			}
		}
		boolean flag=ecu.getEmpService().saveEmp(emp);
		if(flag){
			AJAX_JSON.returnJson(response, "1");
		}else{
			AJAX_JSON.returnJson(response, "0");
		}
		return null;
	}

	@Override
	@RequestMapping(value = "/deleteEmp")
	public String deleteEmp(HttpServletRequest request, HttpServletResponse response, Integer eid) {
		//获取旧照片
	    String oldfname=ecu.getEmpService().searchEmpById(eid).getPhoto();
		boolean flag=ecu.getEmpService().deleteEmp(eid);
		if(flag){
			String realpath =request.getSession().getServletContext().getRealPath("/");
			File oldfile=new File(realpath+"/uppic/"+oldfname);
		    if(oldfile.exists()&&!oldfname.equals("default.jpg")) {
		    	oldfile.delete();
		    }
			AJAX_JSON.returnJson(response, "1");
		}else{
			AJAX_JSON.returnJson(response, "0");
		}
		return null;
	}

	@Override
	@RequestMapping(value = "/updateEmp")
	public String updateEmp(HttpServletRequest request, HttpServletResponse response, Employee emp) {
		System.out.println("进入修改方法");
		//获取旧照片
		String oldfname=ecu.getEmpService().searchEmpById(emp.getEid()).getPhoto();
		//获取根路径
		String realpath =request.getSession().getServletContext().getRealPath("/");
		System.out.println("realpath:"+realpath);
		/**文件上传start*/
		//获取文件上传对象
		MultipartFile multipartFile=emp.getPic();
		if(!multipartFile.isEmpty()&&null!=multipartFile) {
			//获取上传对象名称
			String fname=multipartFile.getOriginalFilename();
			if(fname.lastIndexOf(".")!=-1) {
				//截取后缀
				String ext=fname.substring(fname.lastIndexOf("."));
				//修改名称
				String newFname=new Date().getTime()+ext;
				String path=realpath+"/uppic/"+newFname;
				File file=new File(	path);
				System.out.println("文件路径："+path);
				//上传，复制
				try {
					FileUtils.copyInputStreamToFile(multipartFile.getInputStream(),file );
				} catch (IOException e) {
					e.printStackTrace();
				}
			    emp.setPhoto(newFname);
			    //删除旧照片
			    File oldfile=new File(realpath+"/uppic/"+oldfname);
			    if(oldfile.exists()&&!oldfname.equals("default.jpg")) {
			    	oldfile.delete();
			    }
			}
		}else {
			//文件不修改使用原来的
			emp.setPhoto(oldfname);
			}
		boolean flag=ecu.getEmpService().updateEmp(emp);
		if(flag){
			AJAX_JSON.returnJson(response, "1");
		}else{
			AJAX_JSON.returnJson(response, "0");
		}
		return null;
	}

	@Override
	@RequestMapping(value = "/searchEmpById")
	public String searchEmpById(HttpServletRequest request, HttpServletResponse response, Integer eid) {
		Employee oldem=ecu.getEmpService().searchEmpById(eid);
		System.out.println(oldem);
		PropertyFilter propertyFilter=AJAX_JSON.filterProperts("birthday","pic");
		String jsonstr=JSONObject.toJSONString(oldem,propertyFilter, SerializerFeature.DisableCircularReferenceDetect);
		 System.out.println("jsonstr："+jsonstr);
		 AJAX_JSON.returnJson(response, jsonstr);
		return null;
	}

	@Override
	@RequestMapping(value = "/searchDetail")
	public String searchDetail(HttpServletRequest request, HttpServletResponse response, Integer eid) {
		Employee oldem=ecu.getEmpService().searchEmpById(eid);
		PropertyFilter propertyFilter=AJAX_JSON.filterProperts("birthday","pic");
		String jsonstr=JSONObject.toJSONString(oldem,propertyFilter, SerializerFeature.DisableCircularReferenceDetect);
		 System.out.println("jsonstr："+jsonstr);
		 AJAX_JSON.returnJson(response, jsonstr);
		return null;
	}

	@Override
	@RequestMapping(value = "/searchEmpPageAll")
	public String searchEmpPageAll(HttpServletRequest request, HttpServletResponse response, Integer page,
			Integer rows) {
		Map<String,Object> map=new HashMap<String,Object>();
		PageBean pb=new PageBean();
		page=page==null||page<1?pb.getPage():page;
		rows=rows==null||rows<1?pb.getRows():rows;
		pb.setPage(page);
		pb.setRows(rows);
		List<Employee> lisem=ecu.getEmpService().searchEmpPageAll(pb);
		Integer maxrows=ecu.getEmpService().maxRows();
		map.put("page", page);//eazyUI规定“page”：当前页码
		map.put("rows", lisem);//eazyUI规定“rows”：查询列表集合
		map.put("total", maxrows);//eazyUI规定“total”：总记录数
		PropertyFilter propertyFilter=AJAX_JSON.filterProperts("birthday","pic");
		String jsonstr=JSONObject.toJSONString(map,propertyFilter, SerializerFeature.DisableCircularReferenceDetect);
		 System.out.println("jsonstr："+jsonstr);
		 AJAX_JSON.returnJson(response, jsonstr);
		return null;
	}

	@Override
	@RequestMapping(value = "/searchDept_Welf")
	public String searchDept_Welf(HttpServletRequest request, HttpServletResponse response) {
		List<Welfare> liswelf=ecu.getWelfService().searchWelfareAll();
		List<Department> lisdept=ecu.getDeptService().findDeptAll();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("liswelf", liswelf);
		map.put("lisdept", lisdept);
		PropertyFilter propertyFilter=AJAX_JSON.filterProperts("birthday","pic");
		String jsonstr=JSONObject.toJSONString(map,propertyFilter, SerializerFeature.DisableCircularReferenceDetect);
		 System.out.println("jsonstr："+jsonstr);
		 AJAX_JSON.returnJson(response, jsonstr);
		return null;
	}

	
}

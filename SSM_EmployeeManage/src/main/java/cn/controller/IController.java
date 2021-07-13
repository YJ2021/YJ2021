package cn.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.pojo.Employee;

public interface IController {
  public String saveEmp(HttpServletRequest request,HttpServletResponse response,Employee emp);
  public String deleteEmp(HttpServletRequest request,HttpServletResponse response,Integer eid);
  public String updateEmp(HttpServletRequest request,HttpServletResponse response,Employee emp);
  public String searchEmpById(HttpServletRequest request,HttpServletResponse response,Integer eid);
  public String searchDetail(HttpServletRequest request,HttpServletResponse response,Integer eid);
  public String searchEmpPageAll(HttpServletRequest request,HttpServletResponse response,Integer page,Integer rows);
  public String searchDept_Welf(HttpServletRequest request,HttpServletResponse response);

}

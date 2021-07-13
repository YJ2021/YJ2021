package cn.pojo;

import java.io.Serializable;

public class Employee_Welfare implements Serializable{
   /**
	 * 员工与福利中间表
	 */
	private static final long serialVersionUID = 1L;
private Integer ewId;//中间表Id
   private Integer eid;//员工编号
   private Integer wid;//福利Id
public Employee_Welfare() {
	super();
	// TODO Auto-generated constructor stub
}
public Employee_Welfare(Integer ewId, Integer eid, Integer wid) {
	super();
	this.ewId = ewId;
	this.eid = eid;
	this.wid = wid;
}

public Employee_Welfare(Integer eid, Integer wid) {
	super();
	this.eid = eid;
	this.wid = wid;
}
public Integer getEwId() {
	return ewId;
}
public void setEwId(Integer ewId) {
	this.ewId = ewId;
}
public Integer getEid() {
	return eid;
}
public void setEid(Integer eid) {
	this.eid = eid;
}
public Integer getWid() {
	return wid;
}
public void setWid(Integer wid) {
	this.wid = wid;
}
@Override
public String toString() {
	return "Employee_Welfare [ewId=" + ewId + ", eid=" + eid + ", wid=" + wid + "]";
}
   
}

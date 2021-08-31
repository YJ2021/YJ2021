package cn.pojo;

import java.io.Serializable;

public class Employee_Welfare implements Serializable{
   /**
	 * Ա���븣���м��
	 */
	private static final long serialVersionUID = 1L;
private Integer ewId;//�м��Id
   private Integer eid;//Ա�����
   private Integer wid;//����Id
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

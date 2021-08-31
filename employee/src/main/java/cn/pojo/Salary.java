package cn.pojo;

import java.io.Serializable;

public class Salary implements Serializable{
	/**
	 * ���ʱ�
	 */
	private static final long serialVersionUID = 1L;
	private Integer sid;
	private Integer eid;//Ա�����
	private float salary;//Ա������

	public Salary() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Salary(Integer sid, Integer eid, float salary) {
		super();
		this.sid = sid;
		this.eid = eid;
		this.salary = salary;
	}

	public Salary(Integer eid, float salary) {
		super();
		this.eid = eid;
		this.salary = salary;
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public Integer getEid() {
		return eid;
	}

	public void setEid(Integer eid) {
		this.eid = eid;
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Salary [sid=" + sid + ", eid=" + eid + ", salary=" + salary + "]";
	}

}

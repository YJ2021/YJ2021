package cn.pojo;

import java.io.Serializable;

public class Welfare implements Serializable{
     /**
	 * ������
	 */
	private static final long serialVersionUID = 1L;
	private Integer wid;//����Id
     private String welfare;//��������
	public Welfare() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Welfare(Integer wid, String welfare) {
		super();
		this.wid = wid;
		this.welfare = welfare;
	}
	public Integer getWid() {
		return wid;
	}
	public void setWid(Integer wid) {
		this.wid = wid;
	}
	public String getWelfare() {
		return welfare;
	}
	public void setWelfare(String welfare) {
		this.welfare = welfare;
	}
	@Override
	public String toString() {
		return "Welfare [wid=" + wid + ", welfare=" + welfare + "]";
	}
     
}

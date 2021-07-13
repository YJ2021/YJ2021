package cn.pojo;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class Employee implements Serializable{
    /**
	 * 员工表
	 */
	private static final long serialVersionUID = 1L;
	private Integer eid;//员工编号
    private String ename;//员工姓名
    private String sex;//性别
    private String address;//地址
    private Date birthday;//生日
    private String photo;//照片
    private Integer deptId;//部门编号
    /**临时属性*/
    private String deptName;//部门名称
    private String edate;//日期转换临时属性
    private Float salary;//工资
    private MultipartFile pic;
    private Integer wid;//福利Id
    private String welfare;//福利名称
    private String []wids;//存储福利Id
    private List<Welfare> welfares;//用于福利集合展示
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Employee(Integer eid, String ename, String sex, String address, Date birthday, String photo, Integer deptId,
			String deptName, String edate, Float salary, MultipartFile pic, Integer wid, String welfare, String[] wids,
			List<Welfare> welfares) {
		super();
		this.eid = eid;
		this.ename = ename;
		this.sex = sex;
		this.address = address;
		this.birthday = birthday;
		this.photo = photo;
		this.deptId = deptId;
		this.deptName = deptName;
		this.edate = edate;
		this.salary = salary;
		this.pic = pic;
		this.wid = wid;
		this.welfare = welfare;
		this.wids = wids;
		this.welfares = welfares;
	}
	/**
	 * 添加
	 * @param ename
	 * @param sex
	 * @param address
	 * @param birthday
	 * @param photo
	 * @param deptId
	 */
	public Employee(String ename, String sex, String address, Date birthday, String photo, Integer deptId) {
		super();
		this.ename = ename;
		this.sex = sex;
		this.address = address;
		this.birthday = birthday;
		this.photo = photo;
		this.deptId = deptId;
	}
	public Integer getEid() {
		return eid;
	}
	public void setEid(Integer eid) {
		this.eid = eid;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getEdate() {
		if(birthday!=null) { 
			edate=new SimpleDateFormat("yyyy-MM-dd").format(birthday); }
		return edate;
	}
	public void setEdate(String edate) {
		if(edate!=null&&!edate.trim().equals("")) { 
			try { 
				birthday=new SimpleDateFormat("yyyy-MM-dd").parse(edate); 
				} 
		    catch (ParseException e) {
				  e.printStackTrace(); 
				} 
		}
		this.edate = edate;
	}
	public Float getSalary() {
		return salary;
	}
	public void setSalary(Float salary) {
		this.salary = salary;
	}
	public MultipartFile getPic() {
		return pic;
	}
	public void setPic(MultipartFile pic) {
		this.pic = pic;
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
	public String[] getWids() {
		return wids;
	}
	public void setWids(String[] wids) {
		this.wids = wids;
	}
	public List<Welfare> getWelfares() {
		return welfares;
	}
	public void setWelfares(List<Welfare> welfares) {
		this.welfares = welfares;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Employee [eid=" + eid + ", ename=" + ename + ", sex=" + sex + ", address=" + address + ", birthday="
				+ birthday + ", photo=" + photo + ", deptId=" + deptId + ", deptName=" + deptName + ", edate=" + edate
				+ ", salary=" + salary + ", pic=" + pic + ", wid=" + wid + ", welfare=" + welfare + ", wids="
				+ Arrays.toString(wids) + ", welfares=" + welfares + "]";
	}
	
    
}

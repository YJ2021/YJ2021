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
	 * Ա����
	 */
	private static final long serialVersionUID = 1L;
	private Integer eid;//Ա�����
    private String ename;//Ա������
    private String sex;//�Ա�
    private String address;//��ַ
    private Date birthday;//����
    private String photo;//��Ƭ
    private Integer deptId;//���ű��
    /**��ʱ����*/
    private String deptName;//��������
    private String edate;//����ת����ʱ����
    private Float salary;//����
    private MultipartFile pic;
    private Integer wid;//����Id
    private String welfare;//��������
    private String []wids;//�洢����Id
    private List<Welfare> welfares;//���ڸ�������չʾ
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
	 * ���
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

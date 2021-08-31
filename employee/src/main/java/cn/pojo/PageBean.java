package cn.pojo;

import java.io.Serializable;
import java.util.List;

public class PageBean implements Serializable{
      /**
	 * Ա����Ϣ��
	 */
	private static final long serialVersionUID = 1L;
	private Integer page=1;//��ǰҳ��
      private Integer rows=5;//ÿҳ��¼��
      private Integer maxPage;//��ҳ��
      private Integer maxRows;//�ܼ�¼��
      private List<?> pe;//���ü���
	public PageBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PageBean(Integer page, Integer rows, Integer maxPage, Integer maxRows, List<?> pe) {
		super();
		this.page = page;
		this.rows = rows;
		this.maxPage = maxPage;
		this.maxRows = maxRows;
		this.pe = pe;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public Integer getMaxPage() {
		return maxPage;
	}
	public void setMaxPage(Integer maxPage) {
		this.maxPage = maxPage;
	}
	public Integer getMaxRows() {
		return maxRows;
	}
	public void setMaxRows(Integer maxRows) {
		this.maxRows = maxRows;
	}
	public List<?> getPe() {
		return pe;
	}
	public void setPe(List<?> pe) {
		this.pe = pe;
	}
	@Override
	public String toString() {
		return "PageBean [page=" + page + ", rows=" + rows + ", maxPage=" + maxPage + ", maxRows=" + maxRows + ", pe="
				+ pe + "]";
	}
      
}

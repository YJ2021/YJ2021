package cn.mapper;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.pojo.Employee_Welfare;
import cn.pojo.Welfare;

@Service("IEmp_WelfareMapper")
public interface IEmp_WelfareMapper {
    public Integer saveEmp_Welf(Employee_Welfare ew);
    public Integer deleteEmp_Welf(Integer eid);
    public List<Welfare> searchByIdEmp_Welf(Integer eid);
}

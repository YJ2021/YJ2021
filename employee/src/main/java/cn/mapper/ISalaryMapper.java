package cn.mapper;

import org.springframework.stereotype.Service;

import cn.pojo.Salary;

@Service("ISalaryMapper")
public interface ISalaryMapper {
    public Integer saveSal(Salary sa);//����н��¼��
    public Integer deleteSal(Integer eid);//����н��ɾ��
    public Integer updateSal(Salary sa);//����н���޸�
    public Salary searchSalById(Integer eid);//����н�ʲ�ѯ
}

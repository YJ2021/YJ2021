package cn.mapper;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.pojo.Welfare;

@Service("IWelfareMapper")
public interface IWelfareMapper {
      public List<Welfare> searchWelfareAll();//�����б��ѯ
}

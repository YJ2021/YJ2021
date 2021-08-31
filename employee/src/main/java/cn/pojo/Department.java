package cn.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.extern.log4j.Log4j;

import java.io.Serializable;
@Data//注解在类上，提供该类所有属性的getter/setter方法，还提供了equals、canEqual、hashCode、toString方法
//@Log4j//作用于类上，为该类提供一个属性名为log的log4j日志对象
@AllArgsConstructor//作用于类上，为该类提供一个包含全部参的构造方法，注意此时默认构造方法不会提供
@NoArgsConstructor//作用于类上，提供一个无参的构造方法。可以和@AllArgsConstructor同时使用，
// 此时会生成两个构造方法：无参构造方法和全参构造方法。
public class Department implements Serializable{
	/**
	 * ���ű�
	 */
	private static final long serialVersionUID = 1L;
	@NonNull//作用于属性上，提供关于此参数的非空检查，如果参数为空，则抛出空指针异常
	private Integer deptId;//���ű��
	private String deptName;//��������

}

package com.es.employee.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.es.employee.domain.Employee;
import com.es.employee.domain.PageBean;
import com.es.employee.service.EmployeeService;
import com.es.employee.dao.EmployeeDao;

public class EmployeeServiceImpl implements EmployeeService {
private EmployeeDao employeeDao;
/*��service�����DAO��ע��*/
public void setEmployeeDao(EmployeeDao employeeDao) {
	this.employeeDao = employeeDao;
}
/*ҵ����е�һ�ֵ�¼�ķ���*/
@Override
public Employee login(Employee employee) {
	// TODO Auto-generated method stub
	/*ҵ����뵽��ɵ�¼�Ļ�������Ҫ����Dao*/
	Employee existEmployee=employeeDao.findByUsernameAndPassword(employee);
	return existEmployee;
}
//������ķ�����������ȥ����
@Transactional
@Override
public void save(Employee employee) {
	employeeDao.save(employee);
	
}
@Override
//��ҳ��ѯ�ķ���
public PageBean<Employee> findByPage(Integer currPage) {
	PageBean<Employee> pageBean=new PageBean<Employee>();
	//pagebean���ݵķ�װ
	//��װ��ǰ��ҳ��
	pageBean.setCurrPage(currPage);//��ǰ��ҳ���Ǵ���紫������
	//��װÿҳ��ʾ�ļ�¼��,Ҳ��������pageBeanҳʵ��ҳ�е�PageSize
	int pageSize=3;
	pageBean.setPageSize(pageSize);
	//��װ�ܵļ�¼��
	//�Ȳ�ѯ��¼����Dao�㣬���־ò�
	int totalCount=employeeDao.findCount();
	pageBean.setTotalCount(totalCount);
	//��װ��ҳ����һ�����ܼ�¼������ÿҳ�ļ�¼��
	double tc=totalCount;//��intת��Ϊdouble����
	Double num=Math.ceil(tc/pageSize);//���֮��ȡ��
	pageBean.setTotalPage(num.intValue());//��װ��ҳ����Ȼ��double����ת��Ϊint����
	//��װÿҳ��ʾ�����ݣ�
	int begin=(currPage-1)*pageSize;
	List<Employee> list=employeeDao.findByPage(begin,pageSize);//�������ݿ��в�ѯ���е������Լ�ҳ��
	pageBean.setList(list);//
	return pageBean;
}
@Override
//ҵ�������û���id��ѯ�û��ķ���
public Employee findById(Integer eid) {
	
	return employeeDao.findById(eid);
}
//���༭��ķ�����������ȥ��������ͻ����ֻ��״̬
@Transactional
@Override
public void update(Employee employee) {
	employeeDao.update(employee);
	
}
//��ɾ���ķ�����������ȥ����
@Transactional
@Override
public void delete(Employee employee) {
	employeeDao.delete(employee);
	
}

}


package com.es.employee.action;

import com.es.employee.domain.Employee;
import com.es.employee.domain.PageBean;
import com.es.employee.service.EmployeeService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/*Ա�������action��*/
/*ʹ��ģ������ȥ�������ݣ��ṩ�������û��������룩*/
public class EmployeeAction extends ActionSupport implements
		ModelDriven<Employee> {
	/* getModel�����û������ύ�������ύ���û���������ͱ���װ����Employee��������ȥ�� */
	private Employee employee = new Employee();

	@Override
	public Employee getModel() {

		return employee;
	}

	/* ��¼ִ�еķ��� */
	/* ���չ���������login�Ϳ���ʹ������� */
	/* action��Ҫȥ����service�� */
	/* �ṩһ����¼�ķ��� */
	public String login() {
		System.out.println("login����ִ���ˡ���������");
		/* ������service ���login�ķ��� ������Employee�Ķ��� */

		Employee existEmployee = employeeService.login(employee);
		if (existEmployee == null) {
			// ��¼ʧ��
			this.addActionError("�û������������");
			return INPUT;
		} else {
			// ��¼�ɹ�,���û��ĵ�¼��Ϣ������session�� Ҫ����Ǹ����� ����Ķ���
			ActionContext.getContext().getSession()
					.put("existEmployee", existEmployee);
			/* ������֮�󷵻�success��ҳ�� */
			return SUCCESS;
		}

	}

	public String register() {
		return "register";
	}

	public String save() {
		employeeService.save(employee);
		return "saveSuccess";
	}

	private Integer currPage = 1;
	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}

	// ���մ�������ֵ����Ҫʹ��set����

	// �ṩһ����ѯ�ķ���
	public String findAll() {
		PageBean<Employee> pageBean=employeeService.findByPage(currPage);
		//��pageBean���뵽ֵջ��
		ActionContext.getContext().getValueStack().push(pageBean);
        return "findAll";
	}
    public String saveUI(){
    	//��ѯ���е��û�
    	return "saveUI";
    }
    //�༭�޸��û�����Ϣ
    public String edit(){
    	//����id����Ϣ���в�ѯ������id����Ϣ�Ѿ���װ��ģ�������У�ֱ�ӵ���ҵ���ȥ��ѯ���ɣ�����action��ֵ������ƣ������漰��ѯ
    	employee=employeeService.findById(employee.getEid());
    	return "editSuccess";
    }
    //�޸�Ա��һ��ִ�еķ���
    public String update(){
    	employeeService.update(employee);
    	return "updateSuccess";
    }

    public String delete(){
    	employee=employeeService.findById(employee.getEid());
    	employeeService.delete(employee);
    	return "deleteSuccess";
    }
	/* ��action�����service��ע�� */
	private EmployeeService employeeService;

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
}

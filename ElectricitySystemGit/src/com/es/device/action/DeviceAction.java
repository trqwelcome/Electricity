package com.es.device.action;

import com.es.device.domain.Device;
import com.es.device.service.DeviceService;
import com.es.employee.domain.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class DeviceAction extends ActionSupport implements ModelDriven<Device> {
	//ģ����������lid�������ǽ���device�ģ������豸�ͺ�ʵ�����Ǵ���һ�Զ�Ĺ�ϵ������Ҳ�ǿ��Խ��յ���
/*	Ҳ�����Լ�����һ������
	private Integer lid;
	����lid��Ȼ���ѯ���е�һ���Ͷ༶
	public void setLid(Integer lid) {
		this.lid = lid;
	}*/
	private Integer lid;
	
	public Integer getLid() {
		return lid;
	}

	public void setLid(Integer lid) {
		this.lid = lid;
	}

	private Device device = new Device();
	@Override
	public Device getModel() {
		return device;
	}
	
	/*lid�Ѿ������ˣ�����Ҫ���յ�ǰ��ҳ��*/
private int currPage;
	public void setCurrPage(int currPage) {
	this.currPage = currPage;
}

	/*�����Լ������Ѿ���library�б���ѯ�����������йصĵ����ݷ���session�У�����session�����ݲ���ȱʧ������ֱ�ӿ���ȥ��ȡ*/
	/*��ѯ�����Ľ������PageBean�Ϳ����ˣ���Ϊ�����溬��������Щ�ļ���*/
	public String findByLid(){
		/*ҳ���л�ȡ������*/
		PageBean<Device> pageBean= deviceService.findByPageLid(lid,currPage);//�����Լ�����Ҳ����ʵ���Ҳ�ѯ�豸
		//��PageBean���뵽ֵջ��ȥ
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByDid";
	}
	/*ע��deviceService����ȡdeviceService�Ķ���*/
	private DeviceService deviceService;
	public void setDeviceService(DeviceService deviceService) {
		this.deviceService = deviceService;
	}
	
}

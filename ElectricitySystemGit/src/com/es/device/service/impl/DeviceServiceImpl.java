package com.es.device.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.es.device.dao.DeviceDao;
import com.es.device.domain.Device;
import com.es.device.service.DeviceService;
import com.es.employee.domain.PageBean;
@Transactional

public class DeviceServiceImpl implements DeviceService {

	
	private DeviceService deviceService;

	public void setDeviceService(DeviceService deviceService) {
		this.deviceService = deviceService;
	}
/*����һ������Ҳ����ʵ���ҵ�lid���з�ҳ��ѯ��Ʒ*/
	@Override
	public PageBean<Device> findByPageLid(Integer lid, int currPage) {
		/*����ֻ��Ҫ����һ��PageBean������,���Դ�������һ������,�����Ǹմ���������pageBean�����������沢û��ʲô����*/
		PageBean<Device> pageBean=new PageBean<Device>();
		pageBean.setCurrPage(currPage);//���õ�ǰ��ҳ����
		 int pageSize=15;
		 pageBean.setPageSize(pageSize);//����ÿҳ��ʾ�ļ�¼��
		 int totalCount=0;
		 totalCount=deviceDao.findCountLid(lid);
		 pageBean.setTotalCount(totalCount);
		 //�����ܵ�ҳ��
		 int totalPage=0;
		 if(totalCount%pageSize==0){
			 totalPage=totalCount/pageSize;
		 }else{
			 totalPage=totalCount/pageSize+1;
		 }
		 pageBean.setTotalPage(totalPage);
		 //ÿҳ��ʾ�����ݼ���
		 int begin=(currPage-1)*pageSize;
		 List<Device> list=deviceDao.findByPageLid(lid,begin,pageSize);
		 pageBean.setList(list);
		return pageBean;
	}
	private DeviceDao deviceDao;

	public void setDeviceDao(DeviceDao deviceDao) {
		this.deviceDao = deviceDao;
	}
	

}

package com.es.library.domain;

import java.util.HashSet;
import java.util.Set;

import com.es.device.domain.Device;

/**
 * @author ������
 *
 */
//����ʵ����ʵ�������
public class Library {
private Integer lid;
private String lname;
private String location;
//ʵ��������һ�����豸���ڶ෽��һ�����ж෽�ļ���
private Set<Device> devices=new HashSet<Device>();
//����ʵ��������Ե�get��set����

public Integer getLid() {
	return lid;
}
public Set<Device> getDevices() {
	return devices;
}
public void setDevices(Set<Device> devices) {
	this.devices = devices;
}
public void setLid(Integer lid) {
	this.lid = lid;
}
public String getLname() {
	return lname;
}
public void setLname(String lname) {
	this.lname = lname;
}
public String getLocation() {
	return location;
}
public void setLocation(String location) {
	this.location = location;
}

}

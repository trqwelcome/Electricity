package com.es.library.action;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.es.library.domain.Library;
import com.es.library.service.LibraryService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

//��װLIbrary������
public class LibraryAction extends ActionSupport implements
		ModelDriven<Library> {
	private Library library = new Library();

	@Override
	public Library getModel() {

		return library;
	}

	private LibraryService libraryService;

	public void setLibraryService(LibraryService libraryService) {
		this.libraryService = libraryService;
	}
public String findLi(){
	// ��ѯ����ʵ���ҵļ���
	List<Library> clist = libraryService.findLibAll();
	//��ʵ���ҵ����м��ϴ��뵽session��
     ActionContext.getContext().getSession().put("clist",clist);
     return "show";
}
}

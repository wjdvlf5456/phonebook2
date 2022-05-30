package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PersonVo;

@WebServlet("/pbc")
public class PhoneController extends HttpServlet {

	//필드
	private static final long serialVersionUID = 1L;
	
	// 디폴트 생성자 사용
    
    //메소드 - gs
	
	//메소드 - 일반
	//get방식으로 요청시 호출 메소드
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 코드작성
		System.out.println("controller");
		
		
		PhoneDao phoneDao = new PhoneDao();
		List<PersonVo> phoneList = phoneDao.personSelect();
		System.out.println(phoneList);
		
		//request에 데이터 추가
		request.setAttribute("pList", phoneList);
		
		//데이터 + html --> jsp 시킨다
		RequestDispatcher rd = request.getRequestDispatcher("/list.jsp");
		rd.forward(request, response);
		
	}
	
	//post형식으로 요청시 호출 메소드
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

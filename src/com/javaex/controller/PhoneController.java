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
import com.javaex.util.WebUitl;
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
		
		//포스트 방식일 때 한글깨짐 방지
		request.setCharacterEncoding("UTF-8");
		
		
		// 코드작성
		// action 파라미터일 때 꺼내기
		String action = request.getParameter("action");
		System.out.println(action);
		
		if ("list".equals(action)) {	//리스트일 때
			
			//포워드
			//RequestDispatcher rd = request.getRequestDispatcher("./writeForm.jsp");
			//rd.forward(request, response);
			
			PhoneDao phoneDao = new PhoneDao();
			List<PersonVo> phoneList = phoneDao.personSelect();
			System.out.println(phoneList);
			
			//request에 데이터 추가
			request.setAttribute("pList", phoneList);
			
			//데이터 + html --> jsp 시킨다
			WebUitl.forward(request, response, "/WEB-INF/list.jsp");
			
			
			
			//RequestDispatcher rd = request.getRequestDispatcher("/list.jsp");
			//rd.forward(request, response);
			
		} else if("writeForm".equals(action)) { //등록폼일 때
			//포워드
			WebUitl.forward(request, response, "/WEB-INF/writeForm.jsp");
			
			/*
			RequestDispatcher rd = request.getRequestDispatcher("/writeForm.jsp");
			rd.forward(request, response);
			*/
		} else if("write".equals(action)) {	//등록일 때	
			
			//파라미터에서 값 꺼내기(name, hp, company)
			String name = request.getParameter("name");
			String hp = request.getParameter("hp");
			String company = request.getParameter("company");
			
			//vo만들어서 값 초기화
			PersonVo personVo = new PersonVo(name,hp, company);
			System.out.println(personVo);
			
			//phoneDao.personInsert()를 통해 저장하기
			PhoneDao phoneDao = new PhoneDao();
			int count = phoneDao.personinsert(personVo);
			System.out.println(count);
			
			//리다이렉트 list
			WebUitl.forward(request, response,"/WEB-INF/list.jsp");
			
			//response.sendRedirect("/phonebook2/pbc?action=list");
			
		} else if("delete".equals(action)){	 //삭제일때
			
			//파라미터에서 id값을 꺼낸다.
			int id = Integer.parseInt(request.getParameter("id"));
			
			//phoneDao.personDelete를 통해 삭제하기
			PhoneDao phoneDao = new PhoneDao();
			int count = phoneDao.persondelete(id);
			
			//리다이렉트 list
			WebUitl.forward(request, response, "/WEB-INF/list.jsp");
			
			/*
			response.sendRedirect("./pbc?action=list");
			*/
		} else if("updateForm".equals(action)) { //등록폼일 때
			//포워드
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/updateForm.jsp");
			rd.forward(request, response);
			
			
		} else if("update".equals(action)){	// 수정할 때
			//파라미터 꺼내기
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			String hp = request.getParameter("hp");
			String company = request.getParameter("company");
			
			//PersonVo 만들기
			PersonVo personVo = new PersonVo(id, name, hp, company);
			
			//PhoneDao personUpdate()로 수정하기
			PhoneDao phoneDao = new PhoneDao();	
			int count = phoneDao.personUpdate(personVo);
			
			//리다이렉트 list
			response.sendRedirect("/phonebook2/pbc?action=list");
			
			
			
		} else {
			System.out.println("action 파라미터 없음");
		}
		
	}
	
	//post형식으로 요청시 호출 메소드
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
		
	}

}

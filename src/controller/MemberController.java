package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDao;
import dto.MemberDto;
import net.sf.json.JSONObject;

@WebServlet("/member")
public class MemberController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req,resp);
	}
	
	public void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("MemberController doProcess");
		req.setCharacterEncoding("utf-8");
		
		String param=req.getParameter("param");
		
		if(param.equals("login")){
			resp.sendRedirect("member/login.jsp");
		}
		else if(param.equals("regi")) {
			resp.sendRedirect("member/regi.jsp");
		}
		else if(param.equals("checkid")) {
			String id=req.getParameter("id");
			System.out.println("id:"+id);

			//DB 접속 Data 산출
			MemberDao dao=MemberDao.getInstance();
			boolean b=dao.getId(id);
			
			String str = "NO";
			if(b == false) {
				str = "OK";
			}
			
			JSONObject obj = new JSONObject();
			obj.put("str",str);
			
			resp.setContentType("application/x-json; charset=utf-8");
			resp.getWriter().print(obj);
		}
		else if(param.equals("regiAf")) {
			String id=req.getParameter("id");
			String pwd=req.getParameter("pwd");
			String name=req.getParameter("name");
			int age=Integer.parseInt(req.getParameter("age"));
			String birth=req.getParameter("birth");
			String address=req.getParameter("address");
			int height=Integer.parseInt(req.getParameter("height"));
			
			
			MemberDao dao = MemberDao.getInstance();
			MemberDto dto=new MemberDto(id,pwd,name,age,birth,address,height);
			boolean isS = dao.addMember(dto);
			
			String msg="OK";
			if(isS == false) {
				msg="NG";
			}
			resp.sendRedirect("message.jsp?proc=regi&msg="+msg);
		}
		else if(param.equals("loginAf")) {
			System.out.println("loginAf controller come in");
			String id=req.getParameter("id");
			String pwd=req.getParameter("pwd");
			
			MemberDao dao=MemberDao.getInstance();
			MemberDto dto=dao.login(new MemberDto(id,pwd,null,0,null,null,0));
			String msg="";
			if(dto != null && !dto.getId().equals("")){
				req.getSession().setAttribute("login", dto);;
				msg="yes_login";
			}else {
				msg="no_login";
			}
			resp.sendRedirect("message.jsp?proc=login&msg="+msg);
		}
		else if(param.equals("addressAf")) {
			String postcode=req.getParameter("postcode");
			String roadAddress=req.getParameter("roadAddress");
			String jibunAddress=req.getParameter("jibunAddress");
			String detailAddress=req.getParameter("detailAddress");
			String extraAddress=req.getParameter("extraAddress");
			
			String address=postcode+" "+roadAddress+jibunAddress+detailAddress+extraAddress;
			System.out.println(address);
			//resp.sendRedirect("member/regi.jsp?address="+address);
		}
		else if(param.equals("mypage")) {
			String id=req.getParameter("id");
			MemberDao dao=MemberDao.getInstance();
			MemberDto dto=dao.getinfo(new MemberDto(id,null,null,0,null,null,0));
			
			req.setAttribute("id", dto.getId());
			req.setAttribute("name", dto.getName());
			req.setAttribute("age", dto.getAge());
			req.setAttribute("birth", dto.getBirth());
			req.setAttribute("address", dto.getAddress());
			req.setAttribute("height", dto.getHeight());
			
			forward("member/mypage.jsp", req, resp);
		}
		else if(param.equals("delete")) {
			String id=req.getParameter("id");
			String pwd=req.getParameter("pwd");
			
			MemberDao dao=MemberDao.getInstance();
			boolean isS=dao.delete(new MemberDto(id,pwd,null,0,null,null,0));
			System.out.println(isS);
			String msg="";
			if(isS) {
				msg="delete_success";
			}else {
				msg="delete_error";
			}
			resp.sendRedirect("message.jsp?proc=delete&msg="+msg);
		}
		
	}
	public void forward(String arg,HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatch = req.getRequestDispatcher(arg);
		dispatch.forward(req, resp);
	}
}

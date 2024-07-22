package servlet.http;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

@WebServlet("/form")
public class FormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf-8");
		
		String id = request.getParameter("userId");
		String pwd = request.getParameter("userPwd");
		String gender = request.getParameter("gender");
		String[] menuList = request.getParameterValues("menu");
		
//		System.out.println(id);
		//System.out.println(pwd);
		//System.out.println(gender);
		//System.out.println(Arrays.toString(menuList));
		
		// form으로 해당 요청 값 받아서 응답 결과 출력
		PrintWriter pw = response.getWriter();
		pw.println("<html><body>");
		pw.println("<h1>정보를 출력합니다..</h1>");
		pw.println("<p>당신의 아이디는 " + id + "</p>");
		pw.println("<p>당신의 비밀번호는 " + pwd + "</p>");
		
		String genderResult = gender.equals("M") ? "남자" : "여자";
		pw.println("<p>당신의 성별은 " + genderResult +"</p>");
		if(menuList!=null) {
			pw.println("<ul>");
			for(String menu : menuList) {
				pw.println("<li>" + menu + "</li>");
			}
			pw.println("</ul>");
			pw.println("</body></html>");
		}
		
		
	}

}

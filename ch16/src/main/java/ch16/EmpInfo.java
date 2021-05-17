package ch16;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/EmpInfo")
public class EmpInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int deptno = Integer.parseInt(request.getParameter("deptno"));
		EmpDao ed =EmpDao.getInstance();
		List<Emp> list = ed.list(deptno);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		for(Emp emp : list) {
			String result = emp.getEmpno()+" "+emp.getEname()+" "+
				emp.getJob()+" "+emp.getMgr()+" "+emp.getHiredate()+" "+
				emp.getSal()+" "+emp.getComm()+" "+emp.getDeptno()+"<br>";
			out.println(result);
		}
		out.close();
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
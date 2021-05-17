package ch16;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.*;
import javax.sql.DataSource;
public class EmpDao {
	private static EmpDao instance = new EmpDao();
	private EmpDao() {}
	public static EmpDao getInstance() {
		return instance;
	}
	private Connection getConnection() {
		Connection conn = null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/OracleDB");
			conn = ds.getConnection();
		}catch (Exception e) {
			System.out.println("연결에러 : "+e.getMessage());
		}
		return conn;
	}
	public List<Emp> list(int deptno) {
		List<Emp> list = new ArrayList<Emp>();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Connection conn = getConnection();
		String sql = "select * from emp";
		try {
			if (deptno == 0) {
				pstmt = conn.prepareStatement(sql);
			} else {
				sql += " where deptno=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, deptno);
			}
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Emp emp = new Emp();
				emp.setEmpno(rs.getInt("empno"));
				emp.setEname(rs.getString("ename"));
				emp.setJob(rs.getString("job"));
				emp.setMgr(rs.getInt("mgr"));
				emp.setHiredate(rs.getDate("hiredate"));
				emp.setSal(rs.getInt("sal"));
				emp.setComm(rs.getInt("comm"));
				emp.setDeptno(rs.getInt("deptno"));

				list.add(emp);
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			}catch (Exception e) {		}
		}
		return list;
	}
}

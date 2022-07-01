package domain.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.entity.User;
import domain.jdbc.DBConnectionMgr;

public class UserDaoImpl implements UserDao{
	private DBConnectionMgr pool;
	
	private String sql;
	
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public UserDaoImpl() {
		pool = DBConnectionMgr.getInstance();
	}
	@Override
	public int save(User user) throws Exception {	// 변수들을 전부다 잡아줌	// user 쿼리를 들고온것	// 얘는 0번부터가 아닌 1번부터임.
		int result = 0;
		
		sql = "INSERT INTO \r\n"
				+ "	user_mst\r\n"
				+ "VALUES(\r\n"
				+ "	0,\r\n"
				+ "	?,\r\n"
				+ "	?,\r\n"
				+ "	?,\r\n"
				+ "	?,\r\n"
				+ "	?,\r\n"
				+ "	?,\r\n"
				+ "	NOW(),\r\n"
				+ "	NOW()\r\n"
				+ ");";
		con = pool.getConnection();	// 데이터베이스 연결
		try {
		pstmt = con.prepareStatement(sql);	// 데이터베이스에 연결후 해당 쿼리문을 연결함, 미완성쿼리문을 완성시켜주는게 preparestatement
		pstmt.setString(1, user.getName());	// 앞에 자료형이 int자료형이면 setString 대신 setInt	
		pstmt.setString(2, user.getEmail());	// 작은따옴표 ' 도 넣어서 해주기때문에 위 쿼리문에서 ' 지워줘야댐
		pstmt.setString(3, user.getUsername());
		pstmt.setString(4, user.getPassword());
		pstmt.setString(5, user.getRoles());
		pstmt.setString(6, user.getProvider());
		result = pstmt.executeUpdate(); // 쿼리실행하는거
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con, pstmt); // 연결을 끊어주는거	
		}
		return result;
	}

	@Override
	public User findUserByUsername(String username) throws Exception {
		return null;
	}

	@Override
	public int modify(int user_code) throws Exception {
		return 0;
	}

	@Override
	public int remove(int user_code) throws Exception {
		return 0;
	}
	
}

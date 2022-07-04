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
		User user = null;
		
		sql = "SELECT \r\n"
				+ "	um.user_code,\r\n"
				+ "	um.name,\r\n"
				+ "	um.email,\r\n"
				+ "	um.username,\r\n"
				+ "	um.password,\r\n"
				+ "	um.roles,\r\n"
				+ "	um.provider,\r\n"
				+ "	ud.user_profile_img,\r\n"
				+ "	ud.user_address,\r\n"
				+ "	ud.user_phone,\r\n"
				+ "	ud.user_gender\r\n"
				+ "FROM \r\n"
				+ "	user_mst um\r\n"
				+ "	LEFT OUTER JOIN user_dtl ud ON(ud.user_code = um.user_code)\r\n"
				+ "WHERE \r\n"
				+ "	um.username = ?";
		
		con = pool.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();	// select는 무조건 rs에 
			
			if(rs.next()) {
				user = User.builder()
						.user_code(rs.getInt(1))	// 1은 컬럼번호임, 컬럼은 0번부터가 아닌 1번부터임
						.name(rs.getString(2))
						.email(rs.getString(3))
						.username(rs.getString(4))
						.password(rs.getNString(5))
						.roles(rs.getString(6))
						.provider(rs.getString(7))
						.user_profile_img(rs.getString(8))
						.user_address(rs.getString(9))
						.user_phone(rs.getString(10))
						.user_gender(rs.getInt(11))
						.build();
		}
	}catch (SQLException e) {
		e.printStackTrace();
	}finally {
		pool.freeConnection(con, pstmt, rs);
	}
		return user;
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

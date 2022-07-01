package domain.dao;

import domain.entity.User;

public interface UserDao {
	public int save(User user) throws Exception;	// 얘가 호출되면 데이터베이스에 저장하겠다
	public User findUserByUsername(String username) throws Exception;
	public int modify(int user_code) throws Exception;
	public int remove(int user_code) throws Exception;
}

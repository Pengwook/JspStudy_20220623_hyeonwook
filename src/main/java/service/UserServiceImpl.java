package service;

import domain.dao.UserDao;
import domain.dao.UserDaoImpl;
import web.dto.SignupReqDto;

public class UserServiceImpl implements UserService{
	
	private final UserDao userDao;
	
	public UserServiceImpl() {
		userDao = new UserDaoImpl();
	}
	
	public boolean checkUsername(String username) throws Exception {
		return userDao.findUserByUsername(username) != null;
	}
	
	@Override
	public boolean createUser(SignupReqDto signupReqDto) throws Exception {
		return userDao.save(signupReqDto.toEntity()) > 0;
	}

	@Override
	public void getUser() throws Exception {
		
	}

	@Override
	public boolean updateUser() throws Exception {
		return false;
	}

	@Override
	public boolean deleteUser() throws Exception {
		return false;
	}
	
}

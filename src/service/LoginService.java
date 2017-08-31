package service;

import java.sql.Connection;

import beans.User;
import dao.UserDao;
import util.ChipherUtil;
import util.CloseableUtil;
import util.DBUtil;

public class LoginService {
	public User login(String account,String password){
		Connection connection = null;

		try{
			connection = DBUtil.getConnection();
			UserDao userDao = new UserDao();
			String encPassword = ChipherUtil.encrypt(password);
			User user = userDao.getUser(connection,account,encPassword);
			DBUtil.commit(connection);
			return user;

		}catch(RuntimeException e){
			DBUtil.rollback(connection);
			throw e;
		}catch(Error e){
			DBUtil.rollback(connection);
			throw e;
		}finally{
			try{
				CloseableUtil.close(connection);
			}catch(RuntimeException e){
				DBUtil.rollback(connection);
				throw e;
			}catch(Error e){
				DBUtil.rollback(connection);
				throw e;
			}
		}
	}
}

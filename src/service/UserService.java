package service;

import static util.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import beans.User;
import dao.UserDao;
import util.ChipherUtil;
import util.CloseableUtil;

public class UserService {

	public void register(User user) {

		Connection connection = null;
		try {
			connection = getConnection();

			String encPassword = ChipherUtil.encrypt(user.getPassword());
			user.setPassword(encPassword);

			UserDao userDao = new UserDao();
			//userDao.getBranch(connection, user);
			//userDao.getDivision(connection, user);
			//System.out.println(user.getBranchID());
			//System.out.println(user.getDivisionID());
			userDao.insert(connection, user);

			commit(connection);
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			CloseableUtil.close(connection);
		}
	}

	public void update(User user,int userid) {

		Connection connection = null;
		try {
			connection = getConnection();

			String encPassword = ChipherUtil.encrypt(user.getPassword());
			user.setPassword(encPassword);

			UserDao userDao = new UserDao();
			userDao.update(connection, user,userid);

			commit(connection);
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			CloseableUtil.close(connection);
		}
	}

	public List<User> getUser(int userId) {

		Connection connection = null;
		try {
			connection = getConnection();

			UserDao userDao = new UserDao();
			List<User> user = userDao.getUser(connection, userId);

			commit(connection);

			return user;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			CloseableUtil.close(connection);
		}
	}

	public List<User> getUser() {

		Connection connection = null;
		try {
			connection = getConnection();
			UserDao userDao = new UserDao();
			List<User> user = userDao.getUser(connection);

			commit(connection);

			return user;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			CloseableUtil.close(connection);
		}
	}

}

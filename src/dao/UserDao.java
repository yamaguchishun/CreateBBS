package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import beans.User;
import exception.NoRowsUpdatedRuntimeException;
import exception.SQLRuntimeException;
import util.CloseableUtil;

public class UserDao {

	public User getUser(Connection connection, String account,
			String password) {

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM yamaguchi_shun.users WHERE account = ? AND password = ?";

			ps = connection.prepareStatement(sql);
			ps.setString(1, account);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();
			List<User> userList = toUserList(rs);
			if (userList.isEmpty() == true) {
				return null;
			} else if (2 <= userList.size()) {
				throw new IllegalStateException("2 <= userList.size()");
			} else {
				return userList.get(0);
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			CloseableUtil.close(ps);
		}
	}

	private List<User> toUserList(ResultSet rs) throws SQLException {

		List<User> ret = new ArrayList<User>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String account = rs.getString("account");
				String name = rs.getString("name");
				int branchid = rs.getInt("branch_id");
				int divisionid = rs.getInt("division_id");
				String password = rs.getString("password");
				Timestamp insertDate = rs.getTimestamp("insert_date");
				Timestamp updateDate = rs.getTimestamp("update_date");
				User user = new User();
				user.setID(id);
				user.setAccount(account);
				user.setName(name);
				user.setBranchID(branchid);
				user.setPassword(password);
				user.setDivisionID(divisionid);
				user.setInsertDate(insertDate);
				user.setUpdateDate(updateDate);

				ret.add(user);
			}
			return ret;
		} finally {
			CloseableUtil.close(rs);
		}
	}

	private List<User> toBranchList(ResultSet rs) throws SQLException {

		List<User> ret = new ArrayList<User>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				Timestamp insertDate = rs.getTimestamp("insert_date");
				Timestamp updateDate = rs.getTimestamp("update_date");
				User user = new User();
				user.setBranchID(id);
				user.setBranchName(name);
				user.setInsertDate(insertDate);
				user.setUpdateDate(updateDate);

				ret.add(user);
			}
			return ret;
		} finally {
			CloseableUtil.close(rs);
		}
	}

	private List<User> toDivisionList(ResultSet rs) throws SQLException {

		List<User> ret = new ArrayList<User>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				Timestamp insertDate = rs.getTimestamp("insert_date");
				Timestamp updateDate = rs.getTimestamp("update_date");
				User user = new User();
				user.setDivisionID(id);
				user.setDivisionName(name);
				user.setInsertDate(insertDate);
				user.setUpdateDate(updateDate);

				ret.add(user);
			}
			return ret;
		} finally {
			CloseableUtil.close(rs);
		}
	}

	public void insert(Connection connection, User user) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO yamaguchi_shun.users ( ");
			//sql.append("id");
			sql.append("account");
			sql.append(", name");
			sql.append(", branch_id");
			sql.append(", password");
			sql.append(", division_id");
			//sql.append(", icon");
			//sql.append(", insert_date");
			//sql.append(", update_date");
			sql.append(") VALUES (");
			//sql.append("NEXT VALUE FOR my_seq "); // id
			sql.append("?"); // account
			sql.append(", ?"); // name
			sql.append(", ?"); // email
			sql.append(", ?"); // password
			sql.append(", ?"); // description
			//sql.append(", ?"); // icon
			//sql.append(", CURRENT_TIMESTAMP"); // insert_date
			//sql.append(", CURRENT_TIMESTAMP"); // update_date
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, user.getAccount());
			ps.setString(2, user.getName());
			//ps.setInt(3, user.getBranchID());
			ps.setInt(3, 1);
			ps.setString(4, user.getPassword());
			//ps.setInt(5, user.getDivisionID());
			ps.setInt(5, 1);

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			CloseableUtil.close(ps);
		}
	}

	public void update(Connection connection, User user,int userid) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE yamaguchi_shun.users SET");
			sql.append("  account = ?");
			sql.append(", name = ?");
			sql.append(", branch_id = ?");
			sql.append(", password = ?");
			sql.append(", division_id = ?");
			//sql.append(", update_date = CURRENT_TIMESTAMP");

			sql.append(" WHERE");
			sql.append(" id = ?");
			//sql.append(" AND");
			//sql.append(" update_date = ?");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, user.getAccount());
			ps.setString(2, user.getName());
			ps.setInt(3, user.getBranchID());
			ps.setString(4, user.getPassword());
			ps.setInt(5, user.getDivisionID());
			ps.setInt(6, userid);

			int count = ps.executeUpdate();
			if (count == 0) {
				throw new NoRowsUpdatedRuntimeException();
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			CloseableUtil.close(ps);
		}

	}

	public List<User> getUser(Connection connection, int id) {

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM yamaguchi_shun.users WHERE id = ?";

			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			List<User> userList = toUserList(rs);
			/*if (userList.isEmpty() == true) {
				return null;
			} else if (2 <= userList.size()) {
				throw new IllegalStateException("2 <= userList.size()");
			} else {
				return userList;
			}*/
			return userList;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			CloseableUtil.close(ps);
		}
	}

	public List<User> getUser(Connection connection) {

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM yamaguchi_shun.users";

			ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			List<User> userList = toUserList(rs);
			/*if (userList.isEmpty() == true) {
				return null;
			} else if (2 <= userList.size()) {
				throw new IllegalStateException("2 <= userList.size()");
			} else {
				return userList ;
			}*/
			return userList ;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			CloseableUtil.close(ps);
		}
	}

	public  User getBranch(Connection connection, User user) {

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM yamaguchi_shun.branches WHERE NAME = ?";

			ps = connection.prepareStatement(sql);
			ps.setString(1, user.getBranchName());

			ResultSet rs = ps.executeQuery();
			List<User> branchList = toBranchList(rs);
			if (branchList.isEmpty() == true) {
				return null;
			} else if (2 <= branchList.size()) {
				throw new IllegalStateException("2 <= branchList.size()");
			} else {
				return branchList.get(0);
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			CloseableUtil.close(ps);
		}
	}

	public  User getDivision(Connection connection, User user) {

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM yamaguchi_shun.divisions WHERE NAME = ?";

			ps = connection.prepareStatement(sql);
			ps.setString(1, user.getBranchName());

			ResultSet rs = ps.executeQuery();
			List<User> divisionList = toDivisionList(rs);
			if (divisionList.isEmpty() == true) {
				return null;
			} else if (2 <= divisionList.size()) {
				throw new IllegalStateException("2 <= branchList.size()");
			} else {
				return divisionList.get(0);
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			CloseableUtil.close(ps);
		}
	}

}


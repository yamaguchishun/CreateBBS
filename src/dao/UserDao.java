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

	public List<User> getUser(Connection connection) {

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM yamaguchi_shun.users";

			ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			List<User> userList = toUserList(rs);

			return userList ;
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
				int isworking = rs.getInt("is_working");
				Timestamp insertDate = rs.getTimestamp("insert_date");
				Timestamp updateDate = rs.getTimestamp("update_date");

				User user = new User();
				user.setId(id);
				user.setAccount(account);
				user.setName(name);
				user.setBranchId(branchid);
				user.setPassword(password);
				user.setDivisionId(divisionid);
				user.setIsWorking(isworking);
				user.setInsertDate(insertDate);
				user.setUpdateDate(updateDate);

				ret.add(user);
			}
			return ret;
		} finally {
			CloseableUtil.close(rs);
		}
	}

	private User toUser(ResultSet rs) throws SQLException {

		User user = new User();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String account = rs.getString("account");
				String name = rs.getString("name");
				int branchid = rs.getInt("branch_id");
				int divisionid = rs.getInt("division_id");
				String password = rs.getString("password");
				int isworking = rs.getInt("is_working");
				Timestamp insertDate = rs.getTimestamp("insert_date");
				Timestamp updateDate = rs.getTimestamp("update_date");

				user.setId(id);
				user.setAccount(account);
				user.setName(name);
				user.setBranchId(branchid);
				user.setPassword(password);
				user.setDivisionId(divisionid);
				user.setIsWorking(isworking);
				user.setInsertDate(insertDate);
				user.setUpdateDate(updateDate);
			}
			return  user;
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
			sql.append(", is_Working");
			sql.append(") VALUES (");
			sql.append("?");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(", ?");

			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, user.getAccount());
			ps.setString(2, user.getName());
			ps.setInt(3, user.getBranchId());
			//ps.setInt(3, 1);
			ps.setString(4, user.getPassword());
			ps.setInt(5, user.getDivisionId());
			ps.setInt(6, 0);

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			CloseableUtil.close(ps);
		}
	}

	public void update(Connection connection, User user) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();

			if (user.getPassword() != null){
				if(user.getPassword() != ""){
					sql.append("UPDATE yamaguchi_shun.users SET");
					sql.append("  account = ?");
					sql.append(", name = ?");
					sql.append(", branch_id = ?");
					sql.append(", password = ?");
					sql.append(", division_id = ?");
					sql.append(" WHERE");
					sql.append(" id = ?");

					ps = connection.prepareStatement(sql.toString());

					ps.setString(1, user.getAccount());
					ps.setString(2, user.getName());
					ps.setInt(3, user.getBranchId());
					ps.setString(4, user.getPassword());
					ps.setInt(5, user.getDivisionId());
					ps.setInt(6, user.getId());
				}else{
					sql.append("UPDATE yamaguchi_shun.users SET");
					sql.append("  account = ?");
					sql.append(", name = ?");
					sql.append(", branch_id = ?");
					sql.append(", division_id = ?");
					sql.append(" WHERE");
					sql.append(" id = ?");

					ps = connection.prepareStatement(sql.toString());

					ps.setString(1, user.getAccount());
					ps.setString(2, user.getName());
					ps.setInt(3, user.getBranchId());
					ps.setInt(4, user.getDivisionId());
					ps.setInt(5, user.getId());
				}

			}else{
				sql.append("UPDATE yamaguchi_shun.users SET");
				sql.append("  account = ?");
				sql.append(", name = ?");
				sql.append(", branch_id = ?");
				sql.append(", division_id = ?");
				sql.append(" WHERE");
				sql.append(" id = ?");

				ps = connection.prepareStatement(sql.toString());

				ps.setString(1, user.getAccount());
				ps.setString(2, user.getName());
				ps.setInt(3, user.getBranchId());
				ps.setInt(4, user.getDivisionId());
				ps.setInt(5, user.getId());
			}

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

	public void Status(Connection connection,int isworking, int userid) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE yamaguchi_shun.users SET");
			sql.append("  is_working = ?");
			sql.append(" WHERE");
			sql.append(" id = ?");

			ps = connection.prepareStatement(sql.toString());

			ps.setInt(1, isworking);
			ps.setInt(2, userid);

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

			return userList;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			CloseableUtil.close(ps);
		}
	}

	public User checkStatus(Connection connection, int id) {

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM yamaguchi_shun.users WHERE id = ?";

			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			User user = toUser(rs);

			return user;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			CloseableUtil.close(ps);
		}
	}

	public boolean checkUser(Connection connection, String account,User sessionUser){

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM yamaguchi_shun.users WHERE account = ?";
			User user = new User();

			ps = connection.prepareStatement(sql);
			ps.setString(1, account);

			ResultSet rs = ps.executeQuery();

			List<User> userList = toUserList(rs);
			if (userList.isEmpty() == true) {
				return true;
			}else if(user.getId() == sessionUser.getId()){
				return true;
			}else{
				return false;
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			CloseableUtil.close(ps);
		}

	}
}



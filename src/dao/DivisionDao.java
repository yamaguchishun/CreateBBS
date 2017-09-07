package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Division;
import exception.SQLRuntimeException;
import util.CloseableUtil;

public class DivisionDao {
	public List<Division> getDivision(Connection connection) {

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM yamaguchi_shun.divisions";

			ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			List<Division> divisionList = toDivisionList(rs);
			return divisionList;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			CloseableUtil.close(ps);
		}
	}

	private List<Division> toDivisionList(ResultSet rs) throws SQLException {

		List<Division> ret = new ArrayList<Division>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");

				Division division = new Division();
				division.setId(id);
				division.setName(name);

				ret.add(division);
			}
			return ret;
		} finally {
			CloseableUtil.close(rs);
		}
	}

}

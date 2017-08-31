package dao;

import static util.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import beans.Post;
import exception.SQLRuntimeException;
import util.CloseableUtil;

public class UserPostDao {
	public List<Post> getPost(Connection connection,int num){
		PreparedStatement ps = null;
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("select * from yamaguchi_shun.posts");
			sql.append(" order by insert_date DESC limit " + num);

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<Post>ret = toPostList(rs);
			return ret;
		}catch(SQLException e){
			throw new SQLRuntimeException(e);
		}finally{
			close(ps);
		}
	}

	private List<Post> toPostList(ResultSet rs)throws SQLException{
		List<Post>ret = new ArrayList<Post>();
		try{
			while(rs.next()){
				int branchId = rs.getInt("branch_id");
				int divisionId = rs.getInt("division_id");
				int id = rs.getInt("id");
				int userId = rs.getInt("user_id");
				String subJect = rs.getString("subject");
				String cateGory = rs.getString("category");
				String text = rs.getString("text");
				Timestamp insertDate = rs.getTimestamp("insert_date");

				Post post = new Post();
				post.setBranchID(branchId);
				post.setDivisionID(divisionId);
				post.setId(id);
				post.setUserID(userId);
				post.setSubject(subJect);
				post.setCategory(cateGory);
				post.setText(text);
				post.setInsertDate(insertDate);

				ret.add(post);
			}
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			CloseableUtil.close(rs);
		}
	}
}

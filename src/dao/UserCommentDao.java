package dao;

import static util.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import beans.Comment;
import exception.SQLRuntimeException;

public class UserCommentDao {
	public List<Comment> getComment(Connection connection,int num){
		PreparedStatement ps = null;
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("select * from yamaguchi_shun.comments");
			sql.append(" order by insert_date DESC limit " + num);

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<Comment>ret = toCommentList(rs);
			return ret;
		}catch(SQLException e){
			throw new SQLRuntimeException(e);
		}finally{
			close(ps);
		}
	}

	private List<Comment> toCommentList(ResultSet rs)throws SQLException{
		List<Comment>ret = new ArrayList<Comment>();
		try{
			while(rs.next()){
				int branchId = rs.getInt("branch_id");
				int divisionId = rs.getInt("division_id");
				int id = rs.getInt("id");
				int userId = rs.getInt("user_id");
				int postId = rs.getInt("post_id");
				String text = rs.getString("text");

				Timestamp insertDate = rs.getTimestamp("insert_date");

				Comment comment = new Comment();
				comment.setBranchID(branchId);
				comment.setDivisionID(divisionId);
				comment.setId(id);
				comment.setUserId(userId);
				comment.setPostID(postId);
				comment.setText(text);
				comment.setInsertDate(insertDate);

				System.out.println(comment.getText());

				ret.add(comment);
			}
			return ret;
		}finally{
			close(rs);
		}
	}
}

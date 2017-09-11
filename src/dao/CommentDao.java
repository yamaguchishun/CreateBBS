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
import util.CloseableUtil;

public class CommentDao {
	public void insert(Connection connection,Comment comment,int userid){
		PreparedStatement ps = null;
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO yamaguchi_shun.comments(");
			sql.append("user_id,post_id,text,branch_id,division_id)");
			sql.append("VALUES(?,?,?,?,?)");

			ps = connection.prepareStatement(sql.toString());

			ps.setInt(1,comment.getUserId());
			ps.setInt(2, comment.getPostId());
			ps.setString(3, comment.getText());
			ps.setInt(4,comment.getBranchId());
			ps.setInt(5, comment.getDivisionId());

			ps.executeUpdate();
		}catch(SQLException e){
			throw new SQLRuntimeException(e);
		}finally{
			close(ps);
		}
	}

	public void delete(Connection connection,int commentid){
		PreparedStatement ps = null;
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM yamaguchi_shun.comments");
			sql.append(" WHERE id = ?");

			ps = connection.prepareStatement(sql.toString());

			ps.setInt(1,commentid);
			ps.executeUpdate();
		}catch(SQLException e){
			throw new SQLRuntimeException(e);
		}finally{
			close(ps);
		}
	}

	public List<Comment> getMyComment(Connection connection, int userid) {

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM yamaguchi_shun.comments WHERE user_id = ?";

			ps = connection.prepareStatement(sql);
			ps.setInt(1, userid);

			ResultSet rs = ps.executeQuery();
			List<Comment> commentList = toCommentList(rs);

			return commentList;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			CloseableUtil.close(ps);
		}
	}

	private List<Comment> toCommentList(ResultSet rs) throws SQLException {

		List<Comment> ret = new ArrayList<Comment>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String text = rs.getString("text");
				int userID = rs.getInt("user_id");
				int postID = rs.getInt("post_id");
				int branchID = rs.getInt("branch_id");
				int divisionID = rs.getInt("division_id");
				Timestamp insertDate = rs.getTimestamp("insert_date");
				Comment comment = new Comment();

				comment.setId(id);
				comment.setText(text);
				comment.setUserId(userID);
				comment.setBranchId(branchID);
				comment.setPostId(postID);
				comment.setDivisionId(divisionID);
				comment.setInsertDate(insertDate);
				ret.add(comment);
			}
			return ret;
		} finally {
			CloseableUtil.close(rs);
		}
	}

	public List<Comment> getComment(Connection connection,int num){
		PreparedStatement ps = null;
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("select * from yamaguchi_shun.comments");
			sql.append(" order by insert_date DESC ");

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


}

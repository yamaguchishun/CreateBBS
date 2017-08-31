package dao;

import static util.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import beans.Comment;
import exception.SQLRuntimeException;

public class CommentDao {
	public void insert(Connection connection,Comment comment){
		PreparedStatement ps = null;
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO yamaguchi_shun.comments(");
			sql.append("user_id,post_id,text,branch_id,division_id)");
			//sql.append("VALUES(?,?,?,?,?,?)");
			sql.append("VALUES(?,?,?,?,?)");

			ps = connection.prepareStatement(sql.toString());

//			ps.setInt(1,userComment.getId());
			ps.setInt(1,comment.getUserId());
			ps.setInt(2, comment.getPostID());
			ps.setString(3, comment.getText());
			ps.setInt(4,comment.getBranchID());
			ps.setInt(5, comment.getDivisionID());

			ps.executeUpdate();
		}catch(SQLException e){
			throw new SQLRuntimeException(e);
		}finally{
			close(ps);
		}
	}
}

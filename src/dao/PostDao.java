package dao;

import static util.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import beans.Post;
import exception.SQLRuntimeException;

public class PostDao {
	public void insert(Connection connection,Post post){
		PreparedStatement ps = null;
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO yamaguchi_shun.posts(");
			sql.append("user_id,subject,category,text,branch_id,division_id)");
			//sql.append("VALUES(?,?,?,?,?,?)");
			sql.append("VALUES(?,?,?,?,?,?)");

			ps = connection.prepareStatement(sql.toString());

			ps.setInt(1,post.getUserID());
			ps.setString(2,post.getSubject());
			ps.setString(3, post.getCategory());
			ps.setString(4, post.getText());
			ps.setInt(5,post.getBranchID());
			ps.setInt(6, post.getDivisionID());

			ps.executeUpdate();
		}catch(SQLException e){
			throw new SQLRuntimeException(e);
		}finally{
			close(ps);
		}
	}
}

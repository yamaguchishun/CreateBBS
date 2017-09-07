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

			ps.setInt(1,post.getUserId());
			ps.setString(2,post.getSubject());
			ps.setString(3, post.getCategory());
			ps.setString(4, post.getText());
			ps.setInt(5,post.getBranchId());
			ps.setInt(6, post.getDivisionId());

			ps.executeUpdate();
		}catch(SQLException e){
			throw new SQLRuntimeException(e);
		}finally{
			close(ps);
		}
	}

	public void delete(Connection connection,int postid){
		PreparedStatement ps = null;
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM yamaguchi_shun.posts");
			sql.append(" WHERE id = ?");

			ps = connection.prepareStatement(sql.toString());

//			ps.setInt(1,userComment.getId());
			ps.setInt(1,postid);
			ps.executeUpdate();
		}catch(SQLException e){
			throw new SQLRuntimeException(e);
		}finally{
			close(ps);
		}
	}

	public  List<Post> getPost(Connection connection,int num,String startdate,String enddate){
		PreparedStatement ps = null;
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("select * from yamaguchi_shun.posts");
			sql.append(" where insert_date >= ? and insert_date <= ? ");
			sql.append(" order by insert_date DESC limit " + num);

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1,startdate);
			ps.setString(2,enddate);
			ResultSet rs = ps.executeQuery();
			List<Post>ret = toPostList(rs);

			return ret;
		}catch(SQLException e){
			throw new SQLRuntimeException(e);
		}finally{
			close(ps);
		}
	}

	public  List<Post> getPost(Connection connection,
			String startdate,String enddate,String category){
		PreparedStatement ps = null;
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("select * from yamaguchi_shun.posts");
			sql.append(" where insert_date >= ? and insert_date <= ? and category=?");
			sql.append(" order by insert_date DESC limit ");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1,startdate);
			ps.setString(2,enddate);
			ps.setString(3,category);
			ResultSet rs = ps.executeQuery();
			List<Post>ret = toPostList(rs);

			return ret;
		}catch(SQLException e){
			throw new SQLRuntimeException(e);
		}finally{
			close(ps);
		}
	}

	public List<Post> getCategory(Connection connection){
		PreparedStatement ps = null;
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT category FROM yamaguchi_shun.posts");
			sql.append(" GROUP BY category");

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<Post>ret = toCategoryList(rs);

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
				post.setBranchId(branchId);
				post.setDivisionId(divisionId);
				post.setId(id);
				post.setUserId(userId);
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

	private List<Post> toCategoryList(ResultSet rs)throws SQLException{
		List<Post>ret = new ArrayList<Post>();
		try{
			while(rs.next()){
				String cateGory = rs.getString("category");
				Post post = new Post();
				post.setCategory(cateGory);

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

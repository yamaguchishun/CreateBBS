package service;

import static util.CloseableUtil.*;
import static util.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import beans.Post;
import dao.PostDao;
import dao.UserPostDao;


public class PostService {
	public void register(Post post){

		Connection connection = null;
		try{
			connection = getConnection();
			PostDao postDao = new PostDao();
			postDao.insert(connection,post);
			commit(connection);
		}catch(RuntimeException e){
			rollback(connection);
			throw e;
		}catch(Error e){
			rollback(connection);
			throw e;
		}finally{
			try{
				if(connection != null){
					close(connection);
				}

			}catch(RuntimeException e){
				rollback(connection);
				throw e;
			}catch(Error e){
				rollback(connection);
				throw e;
			}
		}

	}

	private static final int LIMIT_NUM = 1000;
	public List<Post>getMesaage(){
		Connection connection = null;
		try{
			connection = getConnection();

			UserPostDao userPostDao = new UserPostDao();
			List<Post> ret = userPostDao.getPost(connection,LIMIT_NUM);
			commit(connection);
			return ret;
		}catch(RuntimeException e){
			rollback(connection);
			throw e;
		}catch(Error e){
			rollback(connection);
			throw e;
		}finally{
			try{
				if(connection !=null){
					close(connection);
				}
			}catch(RuntimeException e){
				rollback(connection);
				throw e;
			}catch(Error e){
				rollback(connection);
				throw e;
			}
		}
	}
}

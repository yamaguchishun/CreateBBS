package service;

import static util.CloseableUtil.*;
import static util.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import beans.Post;
import dao.PostDao;


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
	public List<Post>getMesaage(String startdate,String enddate){
		Connection connection = null;
		try{
			connection = getConnection();

			PostDao postDao = new PostDao();
			List<Post> ret = postDao.getPost(connection,LIMIT_NUM,startdate,enddate);
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

	public List<Post>getMesaage(String startdate,String enddate,String category){
		Connection connection = null;
		try{
			connection = getConnection();

			PostDao postDao = new PostDao();
			List<Post> ret = postDao.getPost(connection,LIMIT_NUM,startdate,enddate,category);
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

	public List<Post>getCategory(){
		Connection connection = null;
		try{
			connection = getConnection();

			PostDao postDao = new PostDao();
			List<Post> ret = postDao.getCategory(connection);
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

	public void deletePost(int postid){
		Connection connection = null;
		try{
			connection = getConnection();

			PostDao postDao = new PostDao();
			postDao.delete(connection,postid);
			commit(connection);
			return ;
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

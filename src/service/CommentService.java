package service;

import static util.CloseableUtil.*;
import static util.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import beans.Comment;
import dao.CommentDao;


public class CommentService {
	public void register(Comment Comment,int userid){

		Connection connection = null;
		try{
			connection = getConnection();
			CommentDao commentDao = new CommentDao();
			commentDao.insert(connection,Comment,userid);
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
	public List<Comment>getComment(){
		Connection connection = null;
		try{
			connection = getConnection();

			CommentDao CommentDao = new CommentDao();
			List<Comment> ret = CommentDao.getComment(connection,LIMIT_NUM);
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

	public void deleteComment(int commentid){
		Connection connection = null;
		try{
			connection = getConnection();

			CommentDao CommentDao = new CommentDao();
			CommentDao.delete(connection,commentid);
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

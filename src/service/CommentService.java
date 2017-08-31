package service;

import static util.CloseableUtil.*;
import static util.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import beans.Comment;
import dao.CommentDao;
import dao.UserCommentDao;


public class CommentService {
	public void register(Comment Comment){

		Connection connection = null;
		try{
			connection = getConnection();
			CommentDao commentDao = new CommentDao();
			commentDao.insert(connection,Comment);
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

			UserCommentDao userCommentDao = new UserCommentDao();
			List<Comment> ret = userCommentDao.getComment(connection,LIMIT_NUM);
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

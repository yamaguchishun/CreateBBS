package service;

import static util.CloseableUtil.*;
import static util.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import beans.Branch;
import dao.BranchDao;

public class BranchService {

	public List<Branch>getBranch(){
		Connection connection = null;
		try{
			connection = getConnection();

			BranchDao branchDao = new BranchDao();
			List<Branch> ret = branchDao.getBranch(connection);
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
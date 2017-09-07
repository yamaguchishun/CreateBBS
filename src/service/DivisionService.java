package service;

import static util.CloseableUtil.*;
import static util.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import beans.Division;
import dao.DivisionDao;

public class DivisionService {

	public List<Division>getDivision(){
		Connection connection = null;
		try{
			connection = getConnection();

			DivisionDao divisionDao = new DivisionDao();
			List<Division> ret = divisionDao.getDivision(connection);
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

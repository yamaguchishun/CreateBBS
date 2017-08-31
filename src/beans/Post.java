package beans;

import java.io.Serializable;
import java.util.Date;

public class Post implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private int userId;
	private int branchID;
	private int divisionID;
	private Date insertDate;
	private String subject;
	private String category;
	private String text;

	public int getId(){
		return id;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getUserID(){
		return userId;
	}

	public void setUserID(int userId){
		this.userId = userId;
	}

	public String getText(){
		return text;
	}

	public void setText(String text){
		this.text = text;
	}

	public Date getInsertDate(){
		return insertDate;
	}

	public void setInsertDate(Date insertDate){
		this.insertDate = insertDate;
	}

	public String getSubject(){
		return subject;
	}

	public void setSubject(String subject){
		this.subject = subject;
	}

	public String getCategory(){
		return category;
	}

	public void setCategory(String category){
		this.category = category;
	}

	public int getBranchID(){
		return branchID;
	}

	public void setBranchID(int branchID){
		this.branchID = branchID;
	}

	public int getDivisionID(){
		return divisionID;
	}

	public void setDivisionID(int divisionID){
		this.divisionID = divisionID;
	}
}

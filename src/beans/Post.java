package beans;

import java.io.Serializable;
import java.util.Date;

public class Post implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private int userId;
	private int branchId;
	private int divisionId;
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

	public int getUserId(){
		return userId;
	}

	public void setUserId(int userId){
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

	public int getBranchId(){
		return branchId;
	}

	public void setBranchId(int branchID){
		this.branchId = branchID;
	}

	public int getDivisionId(){
		return divisionId;
	}

	public void setDivisionId(int divisionID){
		this.divisionId = divisionID;
	}

	public String[] getSplitedText() {
        return text.split("\n");
    }
}

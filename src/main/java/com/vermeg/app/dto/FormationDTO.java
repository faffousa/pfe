package com.vermeg.app.dto;




import java.util.Date;
import java.util.List;




public class FormationDTO {
	
	
	private long idFormation ;
	private long idUserr;
	private String coursename;
	private Date date;
	private String category;
	private String difficultylevel;
	private String coursesummary;
	private String instructorname;
	private String descriptioncourse;
	private String descriptiontrainer;
	private float price;
	private List<String> idQuiz;
	private Boolean test;
	private String typefile;
	public FormationDTO() {
		super();
	}
	public FormationDTO(long idFormation, long idUserr, String coursename, Date date, String category,
			String difficultylevel, String coursesummary, String instructorname, String descriptioncourse,
			String descriptiontrainer, float price, List<String> idQuiz, Boolean test, String typefile) {
		super();
		this.idFormation = idFormation;
		this.idUserr = idUserr;
		this.coursename = coursename;
		this.date = date;
		this.category = category;
		this.difficultylevel = difficultylevel;
		this.coursesummary = coursesummary;
		this.instructorname = instructorname;
		this.descriptioncourse = descriptioncourse;
		this.descriptiontrainer = descriptiontrainer;
		this.price = price;
		this.idQuiz = idQuiz;
		this.test = test;
		this.typefile = typefile;
	}
	public long getIdFormation() {
		return idFormation;
	}
	public void setIdFormation(long idFormation) {
		this.idFormation = idFormation;
	}
	public long getIdUserr() {
		return idUserr;
	}
	public void setIdUserr(long idUserr) {
		this.idUserr = idUserr;
	}
	public String getCoursename() {
		return coursename;
	}
	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDifficultylevel() {
		return difficultylevel;
	}
	public void setDifficultylevel(String difficultylevel) {
		this.difficultylevel = difficultylevel;
	}
	public String getCoursesummary() {
		return coursesummary;
	}
	public void setCoursesummary(String coursesummary) {
		this.coursesummary = coursesummary;
	}
	public String getInstructorname() {
		return instructorname;
	}
	public void setInstructorname(String instructorname) {
		this.instructorname = instructorname;
	}
	public String getDescriptioncourse() {
		return descriptioncourse;
	}
	public void setDescriptioncourse(String descriptioncourse) {
		this.descriptioncourse = descriptioncourse;
	}
	public String getDescriptiontrainer() {
		return descriptiontrainer;
	}
	public void setDescriptiontrainer(String descriptiontrainer) {
		this.descriptiontrainer = descriptiontrainer;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public List<String> getIdQuiz() {
		return idQuiz;
	}
	public void setIdQuiz(List<String> idQuiz) {
		this.idQuiz = idQuiz;
	}
	public Boolean getTest() {
		return test;
	}
	public void setTest(Boolean test) {
		this.test = test;
	}
	public String getTypefile() {
		return typefile;
	}
	public void setTypefile(String typefile) {
		this.typefile = typefile;
	}
	
}  



 
 

 



package com.daniel;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class TesterClass {	 
public static void main(String[] args) {
	Scanner scanner=new Scanner(System.in); 
	// instantiate  the student file class 
	StudentFile sf=new StudentFile();
	Address address=new Address();
	Department department=new Department();
	while(true) {
	System.out.println("**SELECT THE OPRATION YOU WANT TO USE *** ");
	System.out.println("Enter  1 ,For Student Insertion ");
	System.out.println("Enter  2,For  All Student Selection ");
	System.out.println("Enter  3, For  Student Select Single Student ");
	System.out.println("Enter  4, Student Delete ");
	System.out.println("Enter  5, Student Update ");
	System.out.println("Enter  0, To exit ");
	System.out.println("Enter  6, List Of Student Update ");
	System.out.println("Enter  7,  One to one Select Student with it's Address ");
	System.out.println("Enter  8, Select Student 	ONE TO ONE UPDATE");
	System.out.println(" ENTER 9 , FOR ONE TO ONE DELETE ");
	System.out.println(" ENTER 10 , FOR ONE TO MANY FEATCH ");
	System.out.println(" ENTER 11 , FOR DELETE MANY TO MANY  ");
	int choose = scanner.nextInt();
	if(choose==0) {
		System.out.println("**Thank You Very Much**");
		break;
	}
	switch (choose) {
	case 1:
		System.out.println("Enter The Student Name ");
		sf.setName(scanner.next());
		System.out.println("Enter The Student City ");
		address.setCity(scanner.next());
		System.out.println("Enter The Student Sub City ");
		address.setSub_city(scanner.next());
		System.out.println("Enter The Student Department");
		department.setDepartmentName(scanner.next());
		// pass the student object to the address to have relation 
		sf.setAddress(address);
		sf.setDepartment(department);
		commitTransaction(sf,address,department);
		break;
	case 2:
		List <StudentFile> stud=getAllInformations();
		for(StudentFile s :stud) {
			System.out.println("Name :"+s.getName());
			System.out.println("ID:"+s.getId());
			}
		break;
	case 3:
		System.out.println("Enter The Id To Select Single Student");
		int studentid=scanner.nextInt();
		StudentFile studentinfo=getSingleStudent(studentid);
		System.out.println("Name :"+studentinfo.getName());
		System.out.println("ID:"+studentinfo.getId());
		break;
	case 4:
		System.out.println("Enter The Id To Delete Student");
		int stID=scanner.nextInt();
		int backAnswer =	delateStudent(stID);
		System.out.println(backAnswer );
		break;
	case 5:
		System.out.println("Enter The Id To Update");
		int updateID=scanner.nextInt();
		System.out.println("Enter The Student Name To Update ");
		sf.setName(scanner.next());
		udateStudentInformation(updateID,sf);
		break;
	case 6:
		System.out.println("MANY TO MANY SAVE ");
		manyToManyRelation();
		break;
	case 7:
		System.out.println("ONE  TO ONE FEATCH ");
		one_to_one_featch();
		break;
	case 8:
		System.out.println("ONE TO ONE UPDATE ");
		one_to_one_update();
		break;
	case 9:
		System.out.println("ONE TO ONE DELETE ");
		one_to_one_delete();
		break;
	case 10:
		System.out.println("ONE TO MANY FEATCH  ");
		one_to_many_featch();
		break;
	case 11:
		System.out.println("DELETE MANY TO MANY  ");
		one_to_many_delete();
		break;
	default:
		System.out.println("Incorrect Input !!");
		break;
	}
	
	}
	
}
private static void one_to_many_delete() {
	Session session_update=getConfiguration();
	Transaction transation=session_update.beginTransaction();
	Department department=(Department) session_update.get(Department.class, 1);
	session_update.delete(department);
	//System.out.println(department.getDepartmentName());
	transation.commit();
	session_update.close();	
	
}
private static void one_to_many_featch() {
	Session session_update=getConfiguration();
	Transaction transation=session_update.beginTransaction();
	Department department=(Department) session_update.get(Department.class, 1);
	System.out.println("The Number of student in the department is :"+department.getStudent().size());
	int number=1;
	System.out.println("Student In The department ");
	for(StudentFile stud:department.getStudent()) {
		System.out.println(number+" "+stud.getName());
	}
	transation.commit();
	session_update.close();	
}
private static void one_to_one_delete() {
	Session session_update=getConfiguration();
	Transaction transation=session_update.beginTransaction();
	Scanner b=new Scanner(System.in);
	System.out.println("Enter  the Studet Id to Delete ");
	int idupdate=b.nextInt();
	// the the data fetch of delete
	StudentFile student_one=(StudentFile) session_update.get(StudentFile.class, idupdate);
    session_update.delete(student_one);
	transation.commit();
	session_update.close();	
	
}
private static void one_to_one_update() {
Session session_update=getConfiguration();
Transaction transation=session_update.beginTransaction();
Scanner b=new Scanner(System.in);
Address adress=new Address();
System.out.println("Enter  the Studet Id to update ");
int idupdate=b.nextInt();
StudentFile student_one=(StudentFile) session_update.get(StudentFile.class, idupdate);
adress.setSub_city(b.next());
System.out.println("Enter  the new subsity ");
adress.setCity(b.next());
System.out.println("Enter  the new Sub subsity ");
adress.setSub_city(b.next());
// make updation at here 
session_update.save(adress);
student_one.setAddress(adress);

transation.commit();
session_update.close();
}
private static void one_to_one_featch() {
	Scanner a=new Scanner(System.in);
	System.out.println("Enter the id of student you want.");
	int idSelect=a.nextInt();
	Session session=getConfiguration();
	Transaction transaction=session.beginTransaction();
	StudentFile studentFile=(StudentFile)session.get(StudentFile.class, idSelect);
	System.out.println("Student Name:"+studentFile.getName());
	System.out.println("Student Address:"+studentFile.getAddress().getCity());
	transaction.commit();
	session.close();
	
}
private static int udateStudentInformation(int id, StudentFile sf) {
	Session session=getConfiguration();
	Transaction transaction=session.beginTransaction();
    StudentFile student= (StudentFile) session.createQuery("FROM StudentFile WHERE id =:Id").setInteger("Id", id).uniqueResult();
    student.setName(sf.getName());
	transaction.commit();
	session.close();
	return 0;
}
private static int delateStudent(int i) {
	Session session=getConfiguration();
	Transaction transaction=session.beginTransaction();
	Query query=session.createQuery("DELETE FROM StudentFile WHERE Id=:studentID");
	query.setParameter("studentID", i);
	int deleted =query.executeUpdate();
	transaction.commit();
	session.close();
    return deleted;	
}
private static StudentFile getSingleStudent(int id) {
	Session session=getConfiguration();
	Transaction transaction=session.beginTransaction();
    StudentFile student= (StudentFile) session.createQuery("FROM StudentFile WHERE id =:Id").setInteger("Id", id).uniqueResult();
	transaction.commit();
	session.close();
	return student;
}
private static List<StudentFile> getAllInformations() {
	Session session=getConfiguration();
	Transaction transaction=session.beginTransaction();
	List<StudentFile> studentfile= session.createQuery("FROM StudentFile").list();
	transaction.commit();
	session.close();
	return studentfile;
}
private static Session getConfiguration() {
	Configuration cfg=new Configuration();
    cfg.configure("hibernate.cfg.xml");
	Session session =cfg.buildSessionFactory().openSession();
	return session;
}
private static void   commitTransaction(StudentFile sf,Address add,Department dept) {
	Session session=getConfiguration();
	Transaction transaction=session.beginTransaction();
	session.save(dept);
	session.save(add);
	session.save(sf);
	transaction.commit();
	session.close();
	System.out.println("Your Data is saved");
	}
private static void manyToManyRelation() {
//	Set<StudentFile> studnetD=new HashSet<StudentFile>();
//	Set<Project> projectList=new HashSet<Project>();
	Scanner sc=new Scanner(System.in); 
	StudentFile studf=new StudentFile();
	Project project1=new Project();
	System.out.println("Enter The Studenets Who Have same Project");
	studf.setName(sc.next());
	// add in to the student list
	System.out.println("Enter Project Participated ");
	project1.setProjectName(sc.next());	
	// after making the relation .
	Session session=getConfiguration();
	Transaction transaction=session.beginTransaction();
	 studf.getProject().add(project1);
	// project1.getSetudents().add(studf);
	//save the data in to the database .
    session.save(studf);
    session.save(project1);
	transaction.commit();
	session.close();
	System.out.println("Your Data is saved");
}
}

package com.nbl.hibernateCRUD.Entity;

import com.nbl.hibernateCRUD.common.StudentValidations;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentBO {

    public Session createSession(){
        SessionFactory sessionFactory = new Configuration().configure().addAnnotatedClass(Student.class).buildSessionFactory();
        return sessionFactory.openSession();
    }

    public void closeSession(Session session){
        session.close();
    }

    public int displayOptionsAndReadOptions(){
        int optionVal = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println(StudentValidations.divider);
        System.out.println(StudentValidations.welcomeMessage);
        System.out.println(StudentValidations.divider);

        //Hibernate CRUD Operations

        System.out.println(StudentValidations.selectOptionMessage);
        System.out.println(StudentValidations.option1Create); //Create
        System.out.println(StudentValidations.option2Read); //Read
        System.out.println(StudentValidations.option3Update); //Update
        System.out.println(StudentValidations.option4Delete); //Delete
        System.out.println(StudentValidations.option0Exit);//Exit
        optionVal = Integer.parseInt(sc.nextLine());
        return optionVal;
    }

    public List<Student> readStudentDetails() {
        List<Student> students = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.println(StudentValidations.numberOfStudents);
        long numOfStudents = Long.parseLong(sc.nextLine());

        if(numOfStudents>0){
            for(int i=1; i<=numOfStudents; i++){
                System.out.println(StudentValidations.divider);
                System.out.println(StudentValidations.studentDetail+i+":");
                System.out.println(StudentValidations.divider);

                Student student = readStudentInput(sc, false);

                students.add(student);

            }
        } else {
            System.out.println(StudentValidations.divider);
            System.out.println(StudentValidations.numberOfStudentsLTZero);
            System.out.println(StudentValidations.divider);
        }

        return students;
    }

    public Student readStudentInput(Scanner sc, boolean updateFlag){
        Student student = new Student();
        if(!updateFlag) {
            System.out.println(StudentValidations.studentId);
            student.setStudentId(Long.parseLong(sc.nextLine()));
        }
        System.out.println(StudentValidations.studentName);
        student.setStudentName(sc.nextLine());
        System.out.println(StudentValidations.studentDivision);
        student.setStudentDivision(sc.nextLine());
        return student;
    }

    public void writeStudentDetails(List<Student> students,Session session){

        if(!students.isEmpty()) {
            session.beginTransaction();
            for (Student student : students) {
                session.persist(student);
            }
            session.beginTransaction().commit();
            System.out.println(StudentValidations.divider);
            System.out.println(StudentValidations.studentDataWriteSuccess);
            System.out.println(StudentValidations.divider);
        } else {
            System.out.println(StudentValidations.divider);
            System.out.println(StudentValidations.numberOfStudentsLTZero);
            System.out.println(StudentValidations.divider);
        }
    }

    public void readAndDisplayStudent(Session session){
        Scanner sc = new Scanner(System.in);
        System.out.println(StudentValidations.divider);
        System.out.println(StudentValidations.studentIdRequest);
        long studentId = Long.parseLong(sc.nextLine());
        if(studentId!=0) {
            Student student = session.find(Student.class, studentId);
            if(null != student){
                System.out.println(StudentValidations.divider);
                System.out.println(StudentValidations.studentFetchSuccess);
                System.out.println(StudentValidations.studentName + student.getStudentName());
                System.out.println(StudentValidations.studentDivision + student.getStudentDivision());
                System.out.println(StudentValidations.divider);
            } else {
                System.out.println(StudentValidations.studentFetchFailed);
                System.out.println(StudentValidations.divider);
            }
        } else {
            System.out.println(StudentValidations.invalidStudentId);
            System.out.println(StudentValidations.divider);
        }
    }

    public void UpdateStudent(Session session){

        Scanner sc = new Scanner(System.in);
        System.out.println(StudentValidations.divider);
        System.out.println(StudentValidations.studentIdRequest);
        long studId = Long.parseLong(sc.nextLine());
        boolean updateFlag = false;
        boolean insertFlag = false;
        if(studId!=0){
            Student student = session.find(Student.class, studId);
            if(null!=student)
                updateFlag = true;
            else{
                System.out.println(StudentValidations.studentFetchFailed);
                System.out.println(StudentValidations.addNewStudentQuestion);
                char yesOrNo = sc.nextLine().charAt(0);
                if(yesOrNo=='Y' || yesOrNo=='y')
                    insertFlag=true;
            }
        } else {
            System.out.println(StudentValidations.invalidStudentId);
        }
        if(updateFlag || insertFlag){
            Student updateStudent = readStudentInput(sc, updateFlag);
            if(!insertFlag)
                updateStudent.setStudentId(studId);
            session.merge(updateStudent);
            session.beginTransaction().commit();
        }
        System.out.println(StudentValidations.divider);

    }

    public void deleteStudent(Session session){
        Scanner sc = new Scanner(System.in);
        System.out.println(StudentValidations.divider);
        System.out.println(StudentValidations.studentIdRequest);
        long studId = Long.parseLong(sc.nextLine());
        if(studId!=0) {
            Student student = session.find(Student.class, studId);
            if(null!=student){
                session.remove(student);
                session.beginTransaction().commit();
            } else
                System.out.println(StudentValidations.studentFetchFailed);
        } else
            System.out.println(StudentValidations.invalidStudentId);
        System.out.println(StudentValidations.divider);
    }

}

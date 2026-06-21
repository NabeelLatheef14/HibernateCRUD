package com.nbl.hibernateCRUD;

import com.nbl.hibernateCRUD.Entity.Student;
import com.nbl.hibernateCRUD.Entity.StudentBO;
import com.nbl.hibernateCRUD.common.StudentValidations;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        boolean exit = false;
        StudentBO  sb = new StudentBO();
        Session session = sb.createSession();

        while(!exit){
            int selectedOpt = 0;
            selectedOpt = sb.displayOptionsAndReadOptions();
            if(selectedOpt==1){

                //Selected for Create
                List<Student> students = sb.readStudentDetails();
                sb.writeStudentDetails(students, session);

            } else if(selectedOpt==2){

                //Selected for Read
                sb.readAndDisplayStudent(session);

            } else if (selectedOpt==3){

                //Selected for Update
                sb.UpdateStudent(session);

            } else if (selectedOpt==4) {

                //Selected for Delete
                sb.deleteStudent(session);

            } else if (selectedOpt==0){
                exit = true;
                sb.closeSession(session);
            } else {
                System.out.println(StudentValidations.invalidOption);
            }
        }

        Scanner scanner = new Scanner(System.in);
        List<Student> students = new ArrayList<>();

//        SessionFactory sessionFactory = new Configuration().configure().addAnnotatedClass(Student.class).buildSessionFactory();
//        Session session = sessionFactory.openSession();

        int selectedOpt = Integer.parseInt(scanner.nextLine());//Should Removed

        if(selectedOpt==1){

        } else if (selectedOpt==2){

        } else if (selectedOpt==3) {
            //U - Update
            System.out.println(StudentValidations.divider);
            System.out.println(StudentValidations.studentIdRequest);
            long studId = Long.parseLong(scanner.nextLine());
            boolean updateFlag = false;
            if(studId!=0){
                Student student = session.find(Student.class, studId);
                if(null!=student)
                    updateFlag = true;
                else{
                    System.out.println(StudentValidations.studentFetchFailed);
                    System.out.println(StudentValidations.addNewStudentQuestion);
                    char yesOrNo = scanner.nextLine().charAt(0);
                    if(yesOrNo=='Y' || yesOrNo=='y')
                        updateFlag=true;
                }
            } else {
                System.out.println(StudentValidations.invalidStudentId);
            }
            if(updateFlag){
                Student updateStudent = new Student();
                System.out.println(StudentValidations.studentId);
                updateStudent.setStudentId(Long.parseLong(scanner.nextLine()));
                System.out.println(StudentValidations.studentName);
                updateStudent.setStudentName(scanner.nextLine());
                System.out.println(StudentValidations.studentDivision);
                updateStudent.setStudentDivision(scanner.nextLine());
                session.merge(updateStudent);
                session.beginTransaction().commit();
            }
            System.out.println(StudentValidations.divider);
        } else if (selectedOpt==4){
            //D - Delete
            System.out.println(StudentValidations.divider);
            System.out.println(StudentValidations.studentIdRequest);
            long studId = Long.parseLong(scanner.nextLine());
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
        } else {
            System.out.println(StudentValidations.invalidOption);
        }

    }
}
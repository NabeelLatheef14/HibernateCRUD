package com.nbl.hibernateCRUD.common;

public final class StudentValidations {

    private StudentValidations(){
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static final String divider = "++++++++++++++++++++++++++++++++++++";
    public static final String welcomeMessage = "Welcome to Student Management System";

    public static final String selectOptionMessage = "Please Select An Option";

    public static final String option1Create = "1. Add New Students";
    public static final String option2Read = "2. Get Student Details";
    public static final String option3Update = "3. Update Student Details";
    public static final String option4Delete = "4. Delete Student Details";
    public static final String option0Exit = "0. Exit From Application";

    public static final String numberOfStudents = "Enter the number of students: ";
    public  static final String numberOfStudentsLTZero = "Enter at least One Student Data";

    public static final String studentDetail = "Enter Student Details ";

    public static final String studentId = "Student ID      : ";
    public static final String studentName = "Student Name    : ";
    public static final String studentDivision = "Student Division: ";

    public static final String studentIdRequest = "Provide the Student ID: ";

    public static final String studentFetchSuccess = "Student Fetched Successfully";
    public static final String studentFetchFailed = "Student Details Not Found!";

    public static final String invalidStudentId = "Invalid ID, Student ID can't be 0";

    public static final String addNewStudentQuestion = "Do You Want to Add New Student (Y/N)";

    public static final String invalidOption = "Invalid Option! Select a valid Option.";

    public static final String studentDataWriteSuccess = "Student Written to DB Successfully";

}

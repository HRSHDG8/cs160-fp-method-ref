package edu.sdsu.cs160l.lab10.assignment;

import edu.sdsu.cs160l.lab10.assignment.institute.Registrar;
import edu.sdsu.cs160l.lab10.assignment.institute.student.Student;
import edu.sdsu.cs160l.lab10.assignment.institute.student.StudentLevel;
import edu.sdsu.cs160l.lab10.assignment.institute.student.StudentMajor;
import edu.sdsu.cs160l.lab10.exceptions.ClassFullException;
import edu.sdsu.cs160l.lab10.exceptions.StudentAlreadyEnrolledException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Lab10Test {
  private Registrar registrar;

  @BeforeEach
  public void init() {
    registrar = new Registrar();
    Student hmac = new Student(825000001L, "hmac", 3.8, StudentLevel.SENIOR, StudentMajor.COMPUTER_SCIENCE);
    Student john = new Student(825000002L, "john", 3.7, StudentLevel.FRESHMAN, StudentMajor.BIOLOGY);
    Student jane = new Student(825000003L, "jane", 3.6, StudentLevel.SOPHOMORE, StudentMajor.COMPUTER_ENGINEERING);
    int i = 1;
    for (StudentLevel level : StudentLevel.values()) {
      for (StudentMajor major : StudentMajor.values()) {
        Student student = new Student(825000003L + i, "Name" + i, (3.0 + ((i % 10) / 10.0)), level, major);
        i++;
        for (String courseName : registrar.availableCourseNames()) {
          enrollStudentToACourse(student, courseName);
        }
      }
    }
    try {
      registrar.enrollStudent("CS160L", hmac);
      registrar.enrollStudent("CS210", hmac);
      registrar.enrollStudent("CS310", hmac);
      registrar.enrollStudent("CS160L", jane);
      registrar.enrollStudent("CS210", jane);
      registrar.enrollStudent("CS160L", john);
      registrar.enrollStudent("BO170", john);
      registrar.enrollStudent("CE150", john);
    } catch (ClassFullException | StudentAlreadyEnrolledException e) {
      e.printStackTrace();
    }
    System.out.println(registrar);
  }

  private void enrollStudentToACourse(Student student, String courseName) {
    try {
      if (student.getMajor() == StudentMajor.COMPUTER_ENGINEERING || student.getMajor() == StudentMajor.COMPUTER_SCIENCE) {
        if (courseName.startsWith("C")) {
          registrar.enrollStudent(courseName, student);
        }
      } else {
        if (!courseName.startsWith("C")) {
          registrar.enrollStudent(courseName, student);
        }
      }
    } catch (ClassFullException | StudentAlreadyEnrolledException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void printEnrolledStudents() {
    registrar.getStudentsEnrolled()
       .forEach(System.out::println);
  }

  //TODO write test cases below
  @Test
  public void printEnrolledStudentsByMajorAndCourseName(){

  }
}

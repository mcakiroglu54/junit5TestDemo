package junit5demo;

import java.util.ArrayList;
import java.util.List;

public class StudentManager {
   private List<Student> studentList = new ArrayList<>();

   public void createStudent(String fName, String lName, String pNumber) {
      Student student = new Student(fName, lName, pNumber);
      checkStudent(student);
      isStudentAlreadyExist(student);
      studentList.add(student);
   }

   public List<Student> getAllStudent() {
      return studentList;
   }

   private void checkStudent(Student student) {
      student.checkFirstName();
      student.checkLastName();
      student.checkPhoneNumber();
   }

   private void isStudentAlreadyExist(Student student) {
      for (Student w : studentList) {
         if (w.getPhoneNumber() == student.getPhoneNumber()) {
            throw new RuntimeException("Student already exists");
         }
      }
   }
}

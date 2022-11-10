package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

private final studentRepository StudentRepository;

      @Autowired
      public StudentService(studentRepository studentRepository) {
            this.StudentRepository = studentRepository;
      }

      public List<Student> getStudents() {
          return StudentRepository.findAll();

      }

      public void addNewStudent(Student student) {
            Optional<Student> studentOptional = StudentRepository.findStudentByEmail(student.getEmail());
            if(studentOptional.isPresent()){
                  throw new IllegalStateException("Email Taken");
            }
            StudentRepository.save(student);
            //System.out.println(student);
      }

      public void deleteStudent(Long studentId) {
            boolean exist = StudentRepository.existsById(studentId);
            if(!exist) {
                throw  new IllegalStateException("student with id " + studentId + " does not exist");
            }
            StudentRepository.deleteById(studentId);

      }

      @Transactional
      public void updateStudent(Long studentId,
                                String name,
                                String email) {

            Student student = StudentRepository.findById(studentId)
                    .orElseThrow(() -> new IllegalStateException("Student with "+ studentId +
                            "does not exist"));

                  if(name != null && name.length() > 0 && !Objects.equals(student.getName(),name )){
                        student.setName(name);
            }

                  if(email != null && email.length() > 0 && !Objects.equals(student.getEmail(),email)) {
                        Optional<Student> studentOptional = StudentRepository.findStudentByEmail(email);
                        if(studentOptional.isPresent()) {
                              throw  new IllegalStateException("email Taken");
                        }
                        student.setEmail(email);
                  }





      }
}

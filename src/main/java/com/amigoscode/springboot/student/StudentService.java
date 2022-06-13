package com.amigoscode.springboot.student;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

  private final StudentRepository studentRepository;

  @Autowired
  public StudentService(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  public List<Student> getStudents() {
    return studentRepository.findAll();
  }

  public void addStudent(Student student) {
    Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
    if(studentOptional.isPresent()) {
      throw new IllegalStateException("email is taken");
    }
    studentRepository.save(student);
  }

  public void deleteStudent(Long studentId) {
    if(!studentRepository.existsById(studentId)){
      throw new IllegalStateException("student with id " + studentId + " does not exists");
    }
    studentRepository.deleteById(studentId);
  }

  @Transactional
  public void updateStudent(Long studentId,String name, String email) {
    Student student = studentRepository.findById(studentId)
        .orElseThrow(() ->new IllegalStateException("student with id " + studentId + " does not exists"));
    if(name!= null && name.length() > 0 && !Objects.equals(name,student.getName())) {
      student.setName(name);
    }
    if(email!= null && email.length() > 0 && !Objects.equals(email,student.getEmail())) {
      student.setEmail(email);
    }
  }
}

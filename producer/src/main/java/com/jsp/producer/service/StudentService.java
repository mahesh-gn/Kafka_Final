package com.jsp.producer.service;

import com.jsp.producer.model.Student;
import com.jsp.producer.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;

    @Autowired
    private StudentProducer studentProducer;

    @Autowired
    ModelMapper modelMapper;

    public Student createStudent(Student student) {
        Student savedStudent = repository.save(student);
        studentProducer.addStudent("add-student", 0, modelMapper.map(savedStudent, String.class));
        return savedStudent;
    }

    public Student getStudent(int id) {
        Student student = repository.findById(id).orElse(null);
        if (student != null) {
            studentProducer.getStudent("get-student", 5, student.toString());
        }
        return student;
    }

    public Student updateStudent(int id, Student updatedStudent) {
        if (repository.existsById(id)) {
            updatedStudent.setId(id);
            Student savedStudent = repository.save(updatedStudent);
            studentProducer.updateStudent("update-student", 4, savedStudent);
            return savedStudent;
        }
        return null;
    }

    public boolean deleteStudent(int id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            studentProducer.deleteStudent("delete-student", 2, new Student(id));
            return true;
        }
        return false;
    }

    public List<Student> getAllStudents() {
        List<Student> students = repository.findAll();
        students.forEach(student -> studentProducer.getStudent("get-student", 1, student.toString()));
        return students;
    }
}

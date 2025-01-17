package ru.slavmirol.esa_lr3rest.controller;

import org.springframework.http.MediaType;
import ru.slavmirol.esa_lr3rest.model.Student;
import ru.slavmirol.esa_lr3rest.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // JSON и XML для всех студентов
    @GetMapping(value = "/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Student>> getAllStudentsAsJson() {
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @GetMapping(value = "/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> getAllStudentsAsXml() throws JsonProcessingException {
        List<Student> students = studentService.getAllStudents();

        if (students == null || students.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);

        // Создание общего корневого элемента
        StringBuilder answer = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        answer.append("<?xml-stylesheet type=\"text/xsl\" href=\"/templates/student.xsl\"?>\n");
        answer.append("<Students>\n");

        for (Student student : students) {
            String xml = xmlMapper.writeValueAsString(student);
            answer.append(xml).append("\n");
        }

        answer.append("</Students>");

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_XML)
                .body(answer.toString());
    }

    // JSON и XML для конкретного студента
    @GetMapping(value = "/{id}/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> getCourseByIdAsJson(@PathVariable Long id) {
        return studentService.getStudentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @GetMapping(value = "/{id}/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> getStudentByIdAsXml(@PathVariable Long id) throws JsonProcessingException {
        Student student = studentService.getStudentById(id).orElse(null);

        if (student == null) {
            return ResponseEntity.notFound().build();
        }

        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);

        // Создание XML с корневым элементом <Students>
        StringBuilder answer = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        answer.append("<?xml-stylesheet type=\"text/xsl\" href=\"/templates/student.xsl\"?>\n");
        answer.append("<Students>\n");

        String studentXml = xmlMapper.writeValueAsString(student);
        answer.append(studentXml).append("\n");

        answer.append("</Students>");

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_XML)
                .body(answer.toString());
    }

    @PostMapping("/new")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student createdStudent = studentService.createStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        Student updatedStudent = studentService.updateStudent(id, student);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}
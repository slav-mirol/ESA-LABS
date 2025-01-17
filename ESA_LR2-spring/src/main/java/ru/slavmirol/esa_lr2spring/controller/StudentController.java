package ru.slavmirol.esa_lr2spring.controller;

import ru.slavmirol.esa_lr2spring.model.Student;
import ru.slavmirol.esa_lr2spring.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{id}/courses")
    public String coursesOfStudent(@PathVariable Long id, Model model) {
        model.addAttribute("courses", studentService.findCoursesByStudentId(id));
        model.addAttribute("studentId", id);
        return "students/courses";
    }

    @GetMapping
    public String getAllStudents(Model model) {
        model.addAttribute("students", studentService.findAll());
        return "students/list";
    }

    @GetMapping("/new")
    public String createStudentForm(Model model) {
        model.addAttribute("student", new Student());
        return "students/new";
    }

    @PostMapping
    public String saveStudent(@ModelAttribute("student") Student student) {
        studentService.save(student);
        return "redirect:/students";
    }
}
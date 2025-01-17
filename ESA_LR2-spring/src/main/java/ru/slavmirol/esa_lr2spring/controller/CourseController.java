package ru.slavmirol.esa_lr2spring.controller;

import ru.slavmirol.esa_lr2spring.model.Course;
import ru.slavmirol.esa_lr2spring.service.CourseService;
import ru.slavmirol.esa_lr2spring.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;
    private final StudentService studentService;

    public CourseController(CourseService courseService, StudentService studentService) {
        this.courseService = courseService;
        this.studentService = studentService;
    }

    @GetMapping("/{id}/students")
    public String studentsInCourse(@PathVariable Long id, Model model) {
        model.addAttribute("students", courseService.findStudentsByCourseId(id));
        model.addAttribute("courseId", id); // Передаем courseId в шаблон
        return "courses/students";
    }

    @PostMapping("/{id}/enroll")
    public String enrollStudent(@PathVariable Long id, @RequestParam Long studentId) {
        courseService.enrollStudent(id, studentId);
        return "redirect:/courses/" + id + "/students";
    }

    @GetMapping
    public String getAllCourses(Model model) {
        model.addAttribute("courses", courseService.findAll());
        return "courses/list";
    }

    @GetMapping("/new")
    public String createCourseForm(Model model) {
        model.addAttribute("course", new Course());
        return "courses/new";
    }

    @PostMapping
    public String saveCourse(@ModelAttribute("course") Course course) {
        courseService.save(course);
        return "redirect:/courses";
    }
}
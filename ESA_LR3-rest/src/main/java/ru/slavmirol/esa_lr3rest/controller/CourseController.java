package ru.slavmirol.esa_lr3rest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import ru.slavmirol.esa_lr3rest.model.Course;
import ru.slavmirol.esa_lr3rest.service.CourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // JSON и XML для всех курсов
    @GetMapping(value = "/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Course>> getAllCoursesAsJson() {
        List<Course> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }


    @GetMapping(value = "/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> getAllCoursesAsXml() throws JsonProcessingException {
        List<Course> courses = courseService.getAllCourses();

        if (courses == null || courses.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);

        // Создание общего корневого элемента
        StringBuilder answer = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        answer.append("<?xml-stylesheet type=\"text/xsl\" href=\"/templates/course.xsl\"?>\n");
        answer.append("<Courses>\n");

        for (Course course : courses) {
            String xml = xmlMapper.writeValueAsString(course);
            answer.append(xml).append("\n");
        }

        answer.append("</Courses>");

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_XML)
                .body(answer.toString());
    }

    // JSON и XML для конкретного курса
    @GetMapping(value = "/{id}/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Course> getCourseByIdAsJson(@PathVariable Long id) {
        return courseService.getCourseById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }


    @GetMapping(value = "/{id}/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> getCourseByIdAsXml(@PathVariable Long id) throws JsonProcessingException {
        Course course = courseService.getCourseById(id).orElse(null);

        if (course == null) {
            return ResponseEntity.notFound().build();
        }

        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);

        // Создание общего корневого элемента
        StringBuilder answer = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        answer.append("<?xml-stylesheet type=\"text/xsl\" href=\"/templates/course.xsl\"?>\n");
        answer.append("<Courses>\n");

        String xml = xmlMapper.writeValueAsString(course);
        answer.append(xml).append("\n");

        answer.append("</Courses>");

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_XML)
                .body(answer.toString());
    }

    // Создать курс
    @PostMapping("/new")
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        Course createdCourse = courseService.createCourse(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCourse);
    }

    // Обновить курс
    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody Course course) {
        Course updatedCourse = courseService.updateCourse(id, course);
        return ResponseEntity.ok(updatedCourse);
    }

    // Удалить курс
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
}

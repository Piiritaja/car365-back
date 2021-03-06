package ee.taltech.cars.c_theory.question14.lessons;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("lesson")
@RestController
public class LessonsController {
    interface LessonService {
        enum Order {
            MOST_VISITORS, LEAST_VISITORS, LECTURER
        }

        Lesson getLesson(String id);

        Lesson saveLesson(Lesson lesson);

        Lesson updateLesson(String id, Lesson lesson);

        void deleteLesson(String id);

        Lesson updateLessonName(String id, String name);

        List<Students> getStudentsFromLesson(String id);

        List<Lesson> getLessons(String courseId, int year, Order orderBy);
    }

    //todo for question 14 there are 4 assignments in total
    // Each person has to do only 1. So 2 person team has to do 2 different ones, 3 person - 3, 4 person - 4.
    // Make sure to commit under your user otherwise points won't count.
    // I didn't number these so you can pick your favorite

    //todo
    // You are creating a rest controller for lessons. Think page where you are looking at lessons like echo360.
    // You need to add necessary annotations and methods to this class.
    // This class should compile.
    // It should run successfully when moved to your application package.
    // Method body is not important and will not be graded.
    // Modifying other classes is unnecessary and will not be graded.

    //todo A add necessary annotations on the class

    //todo B create a method to query lessons (plural)

    //todo C create a method to query single lesson

    //todo D create a method to save a lesson

    //todo E create a method to update a lesson

    //todo F create a method to delete a lesson

    //todo G assuming each Lesson has students (one-to-many relation) create a method to query lesson's students

    //todo H create a method to update lesson's name (and nothing else)

    //todo G modify correct method to support searching lessons by course id while keeping original functionality

    //todo H modify correct method to support searching by year with default being current year (2020)
    // (you can ignore semesters or use year-semester string)

    //todo K modify correct method to order lessons
    // * by most visitors first
    // * by least visitors first
    // (you can assume that by default it searches by predefined lecturer's order)

    LessonService lessonService;

    @GetMapping
    public List<Lesson> getLessons(@RequestParam(required = false) String courseId,
                                   @RequestParam(required = false, defaultValue = "2020") int year,
                                   @RequestParam(required = false, defaultValue = "LECTURER") LessonService.Order orderBy) {
        return lessonService.getLessons(courseId, year, orderBy);
    }

    @GetMapping("{id}")
    public Lesson getLessonById(@PathVariable String id) {
        return lessonService.getLesson(id);
    }

    @PostMapping
    public Lesson saveLesson(@RequestBody Lesson lesson) {
        return lessonService.saveLesson(lesson);
    }

    @PutMapping("{id}")
    public Lesson updateLesson(@PathVariable String id, @RequestBody Lesson lesson) {
        return lessonService.updateLesson(id, lesson);
    }

    @DeleteMapping("{id}")
    public void deleteLesson(@PathVariable String id) {
        lessonService.deleteLesson(id);
    }

    @GetMapping("{id}/students")
    public List<Students> getStudentsFromLesson(@PathVariable String id) {
        return lessonService.getStudentsFromLesson(id);
    }

    @PutMapping("{id}/rename")
    public Lesson updateLessonName(@PathVariable String id, @RequestParam String newName) {
        return lessonService.updateLessonName(id, newName);
    }
}

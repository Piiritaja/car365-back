package ee.taltech.cars.b_theory.question11;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Nr5isD {

    //todo this is a contribution based question so make sure to keep commits separate
    //todo A What does D stand for in SOLID? Explain the principle.
    //  Answer: D stands for Dependency inversion principle
    //  This means that classes shouldn't be dependent on multiple other classes directly. Rather they should be dependent
    //  on in-between layer (like abstract class) from which other classes are dependent on. It makes code more expendable.
}

//todo B Give an example. Write actual or pseudo code.
// this is an example without Dependency inversion
class Teacher {
    private List<IAIB> iaibStudents = new ArrayList<>();
    private List<IAAB> iaabStudents = new ArrayList<>();

    public void addIAIB(IAIB student) {
        this.iaibStudents.add(student);
    }

    public void addIAAB(IAAB student) {
        this.iaabStudents.add(student);
    }

    public List<IAIB> getIAIB() {
        return iaibStudents;
    }

    public List<IAAB> getIAAB() {
        return iaabStudents;
    }
}

//todo this is example with Dependency inversion, using abstract class Student, we can only use only one list, get and
// add methods. Without modifying Teacher (s)he can teach IABB or anyother StudyProgramme

class TeacherD {
    private List<StudyProgramme> students = new ArrayList<>();

    public void addStudent(StudyProgramme student) {
        this.students.add(student);
    }

    public List<StudyProgramme> getStudents(Class<? extends StudyProgramme> studyProgramme) {
        return students.stream().filter(student -> student.getClass() == studyProgramme).collect(Collectors.toList());
    }
}

abstract class StudyProgramme {
}

class IAIB extends StudyProgramme {
}

class IAAB extends StudyProgramme {
}

class IABB extends StudyProgramme {
}




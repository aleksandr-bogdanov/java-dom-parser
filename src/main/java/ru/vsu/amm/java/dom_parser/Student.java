package ru.vsu.amm.java.dom_parser;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Student class
 */
public final class Student implements Comparable<Student> {
    public static final DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyyMMdd");

    private final String   firstName;
    private final String   lastName;
    private final DateTime birthday;
    private final int      course;
    private final int      group;

    // Builder-only access
    private Student (
            final String firstName,
            final String lastName,
            final DateTime birthday,
            final int course,
            final int group
    ) {
        this.firstName  = firstName;
        this.lastName   = lastName;
        this.birthday   = birthday;
        this.course     = course;
        this.group      = group;
    }


    public String getFirstName() {
        return this.firstName;
    }
    public String getLastName() {
        return this.lastName;
    }
    public DateTime getBirthday() {
        return this.birthday;
    }
    public int getCourse() {
        return this.course;
    }
    public int getGroup() {
        return this.group;
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthday=" + birthday +
                ", course=" + course +
                ", group=" + group +
                '}';
    }

    public int compareTo(Student o) {
        return (firstName + lastName).compareTo(o.getFirstName() + o.getLastName());
    }

    public static class StudentBuilder {
        private String   nestedFirstName;
        private String   nestedLastName;
        private DateTime nestedBirthday;
        private int      nestedCourse;
        private int      nestedGroup;

        public StudentBuilder() {
        }

        public StudentBuilder firstName(final String nestedFirstName) {
            this.nestedFirstName = nestedFirstName;
            return this;
        }
        public StudentBuilder lastName(final String nestedLastName) {
            this.nestedLastName = nestedLastName;
            return this;
        }
        public StudentBuilder birthday(final DateTime nestedBirthday) {
            this.nestedBirthday = nestedBirthday;
            return this;
        }
        public StudentBuilder birthday(final String nestedBirthday) {
            this.nestedBirthday = fmt.parseDateTime(nestedBirthday);
            return this;
        }
        public StudentBuilder course(final int nestedCourse) {
            this.nestedCourse = nestedCourse;
            return this;
        }
        public StudentBuilder group(final int nestedGroup) {
            this.nestedGroup = nestedGroup;
            return this;
        }

        public Student build() {
            return new Student(nestedFirstName, nestedLastName, nestedBirthday, nestedCourse, nestedGroup);
        }
    }
}

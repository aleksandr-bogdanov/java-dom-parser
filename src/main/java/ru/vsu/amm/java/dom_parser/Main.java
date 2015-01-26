package ru.vsu.amm.java.dom_parser;

import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            List<Student> students = new StudentXmlReader().parseXml("xml/in.xml");

            Student st1 = new Student.StudentBuilder()
                    .firstName("Andrey")
                    .lastName("Panevin")
                    .birthday("19930214")
                    .course(4)
                    .group(3)
                    .build();
            students.add(st1);

            Student st2 = new Student.StudentBuilder()
                    .firstName("Alexander")
                    .lastName("Yakovlev")
                    .birthday("19930923")
                    .course(4)
                    .group(8)
                    .build();
            students.add(st2);

            Collections.sort(students, new NameComparator());

            StudentXmlWriter studentXmlWriter = new StudentXmlWriter();
            studentXmlWriter.writeXml(students, "xml/out.xml");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

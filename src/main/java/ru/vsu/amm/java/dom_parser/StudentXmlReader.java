package ru.vsu.amm.java.dom_parser;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;
import java.util.List;

public class StudentXmlReader {

    // JodaTime Class for date parsing
    private static final DateTimeFormatter dateStringFormat = DateTimeFormat.forPattern("yyyyMMdd");

    public List<Student> parseXml(String xmlPath) throws Exception {

        // Get the DOM Builder Factory
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        // Get the DOM Builder
        DocumentBuilder builder = factory.newDocumentBuilder();
        // List of students to be parsed from the XML file
        List<Student> students = new ArrayList<>();
        // Load and parse the XML file as a tree
        Document document = builder.parse(xmlPath);
        // Iterate through the nodes and parse the data
        NodeList nodeList = document.getDocumentElement().getChildNodes();

        for (int i = 0; i < nodeList.getLength(); i++) {
            // The first <university> tag
            Node node = nodeList.item(i);
            if (node instanceof Element) {
                Student.StudentBuilder student = new Student.StudentBuilder();

                NodeList childNodes = node.getChildNodes();
                for (int j = 0; j < childNodes.getLength(); j++) {
                    Node childNode = childNodes.item(j);

                    // Get the information from the correct tag
                    if (childNode instanceof Element) {
                        String content = childNode.getLastChild().getTextContent().trim();
                        switch (childNode.getNodeName()) {
                            case "first-name":
                                student.firstName( content );
                                break;
                            case "last-name":
                                student.lastName( content );
                                break;
                            case "birthday":
                                student.birthday( dateStringFormat.parseDateTime(content) );
                                break;
                            case "course":
                                student.course( Integer.parseInt(content) );
                                break;
                            case "group":
                                student.group( Integer.parseInt(content) );
                                break;
                        }
                    }
                }
                // Build a Student and
                students.add(student.build());
            }
        }
        System.out.println("File loaded from specified path!");
        return students;
    }
}

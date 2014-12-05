package ru.vsu.amm.java.dom_parser;

import java.io.File;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class StudentXmlWriter {

	public void writeXml(List<Student> students, String xmlPath) {
		try {
			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

			// define root elements
			Document document = documentBuilder.newDocument();
			Element rootElement = document.createElement("university");
			document.appendChild(rootElement);

			for (Student student: students) {
				// school tag definition
				Element school = document.createElement("student");
				rootElement.appendChild(school);

				// first-name tag definition
				Element firstName = document.createElement("first-name");
				firstName.appendChild(document.createTextNode(student.getFirstName()));
				school.appendChild(firstName);

				// last-name tag definition
				Element lastName = document.createElement("last-name");
				lastName.appendChild(document.createTextNode(student.getLastName()));
				school.appendChild(lastName);

				// birthday tag definition
				Element birthday = document.createElement("birthday");
				birthday.appendChild(document.createTextNode( String.valueOf(student.getBirthday()) ));
				school.appendChild(birthday);

				// course tag definition
				Element course = document.createElement("course");
				course.appendChild(document.createTextNode( String.valueOf(student.getCourse()) ));
				school.appendChild(course);

				// group tag definition
				Element group = document.createElement("group");
				group.appendChild(document.createTextNode( String.valueOf(student.getGroup()) ));
				school.appendChild(group);

			}

			// creating and writing to xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource domSource = new DOMSource(document);
			StreamResult streamResult = new StreamResult(new File(xmlPath));

			transformer.transform(domSource, streamResult);

			System.out.println("File saved to specified path!");

		} catch (ParserConfigurationException | TransformerException e) {
			e.printStackTrace();
		}
	}
}


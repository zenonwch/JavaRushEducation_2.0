package com.javarush.task.task33.task3309;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/*
Комментарий внутри xml
*/
public class Solution {
    private static final Pattern XML_ESCAPES = Pattern.compile(".*[<>&'\"].*");

    public static String toXmlWithComment(final Object obj, final String tagName, final String comment) {
        final StringWriter writer = new StringWriter();
        try {
            final DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            final DocumentBuilder builder = builderFactory.newDocumentBuilder();
            final Document  doc = builder.newDocument();
            doc.setXmlStandalone(false);

            final JAXBContext ctx = JAXBContext.newInstance(obj.getClass());
            final Marshaller marshaller = ctx.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(obj, doc);

            final NodeList nodes = doc.getElementsByTagName("*");
            for (int i = 0, l = nodes.getLength(); i < l; i++) {
                final Node node = nodes.item(i);
                final NodeList children = node.getChildNodes();
                final Node firstChild = children.item(0);
                final short firstChildType = firstChild.getNodeType();
                final String nodeContent = node.getTextContent();
                if (firstChildType == Node.TEXT_NODE && XML_ESCAPES.matcher(nodeContent).matches()) {
                    final Node cdataNode = doc.createCDATASection(nodeContent);
                    node.replaceChild(cdataNode, firstChild);
                }
                final String nodeName = node.getNodeName();
                if (nodeName.equals(tagName)) {
                    final Node commentNode = doc.createComment(comment);
                    node.getParentNode().insertBefore(commentNode, node);
                }
            }

            final Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.transform(new DOMSource(doc), new StreamResult(writer));
        } catch (final JAXBException | ParserConfigurationException | TransformerException e) {
            System.out.println(e.getMessage());
        }
        return writer.toString();
    }

    public static void main(final String[] args) {
        System.out.println(toXmlWithComment(new FirstSecondObject(), "second", "it's a comment"));
    }

    @XmlRootElement(name = "first")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class FirstSecondObject {

        @XmlElement(name = "second")
        private final String[] arr = {"", "some string", "need CDATA for < and >"};

        @XmlElement(name = "third")
        private final List<String> list = new ArrayList<>();

        {
            list.add("");
            list.add(null);
            list.add("some string");
            list.add("\"");
            list.add("&");
            list.add("'test'test");
            list.add("&amp;");
            list.add("need CDATA because of < and > tag <second>");
        }
    }
}

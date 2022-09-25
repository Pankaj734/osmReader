package reader;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class Main {

    public static void main(String[] args) {
        try {
            String path = "D:\\bengaluru.osm_01.osm";
            File file = new File(path);

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            NodeList rootList = doc.getElementsByTagName("osm");

            for (int itr = 0; itr < rootList.getLength(); itr++)
            {
                Node node = rootList.item(itr);
                NodeList childList = node.getChildNodes();
                for (int itr2 = 0; itr2 < childList.getLength(); itr2++)
                {
                    Node childNode = childList.item(itr2);
                    String x = childNode.getNodeName();
                    if(x.equals("node")){
                        NodeList nodeChildNodes = childNode.getChildNodes();
                        if(nodeChildNodes.getLength() > 0){
                            for (int itr3 = 0; itr3 < childList.getLength(); itr3++)
                            {
                                Node currentItem = nodeChildNodes.item(itr3);
                                if(currentItem!=null){
                                    String name = currentItem.getNodeName();
                                    NamedNodeMap namedNodeMap = currentItem.getAttributes();
                                    if(namedNodeMap!=null){
                                        String k = namedNodeMap.getNamedItem("k").getTextContent();
                                        String v = namedNodeMap.getNamedItem("v").getTextContent();
                                        if(name.equals("tag") && k.equals("addr:street")){
                                            System.out.println(v);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playermp3;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
/**
 *
 * @author Umer
 */
public class FXMLDocumentController implements Initializable {
        MediaPlayerSample s =new MediaPlayerSample();
        
        public boolean fileexistsroot()
        {
       File file = new File("root.xml");
        if (file.exists())
        {
          
            return true;
        }
        else
        {
            createfileroot();
            return false;
        }
            
        }
        
        public boolean fileexistsuser()
        {
        File file = new File("user.xml");
        if (file.exists())
        {
          
            return true;
        }
        else
        {
            createfileuser();
            return false;
        }
        }
       public boolean fileexists()
    {
        File file = new File("admin.xml");
        if (file.exists())
        {
          
                    return  readfile();
        }
        else
        {  
            createfile();
         return  readfile();
           
        }
    }
    
       
       public boolean readfile()
    {
        try{
            File fXmlFile = new File("admin.xml");
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(fXmlFile);

	doc.getDocumentElement().normalize();

	System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

	NodeList nList = doc.getElementsByTagName("ADMIN");

	System.out.println("----------------------------");

		Node nNode = nList.item(0);

		System.out.println("\nCurrent Element :" + nNode.getNodeName());

		if (nNode.getNodeType() == Node.ELEMENT_NODE) {

			Element eElement = (Element) nNode;

			System.out.println("NAME : " + eElement.getElementsByTagName("NAME").item(0).getTextContent());
			System.out.println("PASSWORD : " + eElement.getElementsByTagName("PASSWORD").item(0).getTextContent());
			//System.out.println("Salary : " + eElement.getElementsByTagName("salary").item(0).getTextContent());
//String id = eElement.getElementsByTagName("ID").item(0).getTextContent();
String name =  eElement.getElementsByTagName("NAME").item(0).getTextContent();
                        String pass =  eElement.getElementsByTagName("PASSWORD").item(0).getTextContent();
if (name.equals("?") && pass.equals("?"))
{
 //   dispose();

   return false;
    
}

		
    }
        }
        
    catch (Exception e) {
	e.printStackTrace();
    }
        return true;
    }
    public void createfile()
    {
        try {

		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

		// root elements
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("MPLAYER");
		doc.appendChild(rootElement);

		// staff elements
		Element staff = doc.createElement("ADMIN");
		rootElement.appendChild(staff);

		// set attribute to staff element
		Attr attr = doc.createAttribute("ID");
		attr.setValue("1");
		staff.setAttributeNode(attr);

		// shorten way
		// staff.setAttribute("id", "1");

		// firstname elements
		Element name = doc.createElement("NAME");
		name.appendChild(doc.createTextNode("?"));
		staff.appendChild(name);
		// nickname elements
		Element password = doc.createElement("PASSWORD");
		password.appendChild(doc.createTextNode("?"));
		staff.appendChild(password);
		Element Date = doc.createElement("DATE");
		Date.appendChild(doc.createTextNode("?"));
		staff.appendChild(Date);

		// salary elements
		
		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("admin.xml"));

		// Output to console for testing
		// StreamResult result = new StreamResult(System.out);

		transformer.transform(source, result);

		System.out.println("File saved!");

	  } catch (ParserConfigurationException pce) {
		pce.printStackTrace();
	  } catch (TransformerException tfe) {
		tfe.printStackTrace();
	  }
    }
 
 
      public void createfileuser()
    {
        try {

		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
                Document doc = docBuilder.newDocument();
		
	
		Element rootElement = doc.createElement("MPLAYER");
		doc.appendChild(rootElement);
		// set attribute to staff element
                for (int i=0;i<100;i++){	
                   

		// staff elements
		Element staff = doc.createElement("USER");
		rootElement.appendChild(staff);

                    
		Attr attr = doc.createAttribute("ID");
		attr.setValue(Integer.toString(i));
		staff.setAttributeNode(attr);

		// shorten way
		// staff.setAttribute("id", "1");

		// firstname elements
	
		// nickname elements
		Element password = doc.createElement("PASSWORD");
		password.appendChild(doc.createTextNode("?"));
		staff.appendChild(password);
		Element Date = doc.createElement("DATE");
		Date.appendChild(doc.createTextNode("?"));
		staff.appendChild(Date);
                }
		// salary elements
		
		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("user.xml"));

		// Output to console for testing
		// StreamResult result = new StreamResult(System.out);

		transformer.transform(source, result);

		System.out.println("File saved!");

	  } catch (ParserConfigurationException pce) {
		pce.printStackTrace();
	  } catch (TransformerException tfe) {
		tfe.printStackTrace();
	  }
    }
 
      public void createfileroot()
    {
        try {

		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
                Document doc = docBuilder.newDocument();
		
	
		Element rootElement = doc.createElement("MPLAYER");
		doc.appendChild(rootElement);
		

		// staff elements
		Element staff = doc.createElement("ROOT");
		rootElement.appendChild(staff);

                    
		Attr attr = doc.createAttribute("ID");
		attr.setValue(Integer.toString(0));
		staff.setAttributeNode(attr);

		// shorten way
		// staff.setAttribute("id", "1");

		// firstname elements
	
		// nickname elements
                
		Element name = doc.createElement("NAME");
		name.appendChild(doc.createTextNode("?"));
		staff.appendChild(name);
		Element password = doc.createElement("PASSWORD");
		password.appendChild(doc.createTextNode("?"));
		staff.appendChild(password);
		
                
		// salary elements
		
		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("root.xml"));

		// Output to console for testing
		// StreamResult result = new StreamResult(System.out);

		transformer.transform(source, result);

		System.out.println("File saved!");

	  } catch (ParserConfigurationException pce) {
		pce.printStackTrace();
	  } catch (TransformerException tfe) {
		tfe.printStackTrace();
	  }
    }
 
    @FXML
private Button btn1;    
       @FXML
private Button btn2;
       @FXML
private Button btn3;
        
//       @FXML
//private ListView listview;
//        
 
       @FXML
    private void Button1Action(ActionEvent event) {
       boolean check= fileexists();
         fileexistsuser();
       if (check==false)
       {
        CreateAdminAccount c =new    CreateAdminAccount();
        c.setVisible(true);
       }
       else{
        AdminLogin admin = new AdminLogin();
    admin.setVisible(true);
       }
    
    }
    @FXML
   private void Button2Action(ActionEvent event) {
       fileexistsuser();
   GeneralUserAccount root = new GeneralUserAccount();
   root.setVisible(true);
   }
    @FXML
    private void Button3Action(ActionEvent event) {
       fileexistsroot();
       fileexistsuser();
       fileexists();
        
    RootSigin show = new RootSigin();
    show.setVisible(true);
         
        } 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

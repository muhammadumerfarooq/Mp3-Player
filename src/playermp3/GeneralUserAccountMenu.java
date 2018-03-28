/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playermp3;
import javax.swing.*;
import sun.audio.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;
import sun.audio.*;
import java.awt.event.*;
import java.io.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
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
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import java.awt.Window;
import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.*;
import javafx.embed.swing.JFXPanel;
import javafx.event.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.media.*;
import javafx.util.Duration;
import javax.swing.*;
import static playermp3.MediaPlayerSample.test;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


/**
 *
 * @author Umer
 */
public class GeneralUserAccountMenu extends javax.swing.JFrame {
public String daysleft;
public int rowsno;
public int colsno;
public    MediaPlayer player ;
 
 DefaultListModel dmimage=new DefaultListModel();
private ChangeListener<Duration> progressChangeListener;
    /**
     * Creates new form GeneralUserAccountMenu
     */
    public GeneralUserAccountMenu(String d) {
        daysleft=d;
      
        
        initComponents();
        createfile();
        checkfile();
        adddata();
        jButton2.setVisible(false);
       jButton1.setVisible(false);
  
         String song = playsong();
       if ( !song.equals(""))
       {
        //   music(song);
//   try {
//            FileInputStream fis = new FileInputStream(song);
//            BufferedInputStream bis = new BufferedInputStream(fis);
//            player = new MediaPlayer(new Media(song));
//        } catch (Exception e) {
//            System.out.println("Problem playing file " + song);
//            System.out.println(e);
//        }
    
    //       music(song);
           String splitsong[] = song.split(".mp3");
           int i=splitsong[0].length()-1;
           while (i>=0)
           {
               char val=splitsong[0].charAt(i);
              
               if (val=='\\')
                   break;
               i--;
           }
           
           String path = song.substring(0, i);
           String songname=song.substring(i+1, song.length());
           System.out.println(path+ " "+songname);
          ArrayList<String> pathlist = new ArrayList<String>();
          for (int j=0;j<100;j++)
          pathlist.add(path);
          
          ArrayList<String> songlist = new ArrayList<String>();
          for (int j=0;j<100;j++)
              songlist.add(songname);
          
           MediaPlayerSample songplayer = new MediaPlayerSample();
          songplayer.initAndShowGUI(pathlist,songlist);
         
        //   playingsong(path,songname);
         
          
       }
    }
    	public static void music(String song){
            try{

		String bip = song;
Media hit = new Media(new File(bip).toURI().toString());
MediaPlayer mediaPlayer = new MediaPlayer(hit);
mediaPlayer.setVolume(0.05);

      
      //System.out.println( mediaPlayer.getOnEndOfMedia().toString());
       
          mediaPlayer.setOnPlaying(new Runnable() {
        @Override public void run() {
          mediaPlayer.play(); 
          //mediaPlayer.setOnRepeat(mediaPlayer.onRepeatProperty().getValue());
          System.out.println("Playing ");

        }
      });
//         System.out.println("Playing/ ");

        } catch (Exception e) { System.err.println(e.getMessage()); }
	
        }
    
     private MediaPlayer createPlayer(String aMediaSrc) {
     
    System.out.println("Creating player for: " + aMediaSrc);
    final MediaPlayer player = new MediaPlayer(new Media(aMediaSrc));
    player.setOnError(new Runnable() {
      @Override public void run() {
        System.out.println("Media error occurred: " + player.getError());
      }
    });
    return player;
  }
    public void playingsong(String val,String song) {
  
System.out.println(" File "+val);
   String songname= "";
    
   songname= song;
   String mydirect="";
  mydirect= val;
    
       
    

    // create some media players.
    final List<MediaPlayer> players = new ArrayList<MediaPlayer>();
   
        final String direct = mydirect;//System.getProperty("user.dir");
        System.out.println("current dir = " + direct);
     
    File dir = new File(direct);
    
    String mysong=songname;
    System.out.println(mysong);
    if (!dir.exists() || !dir.isDirectory()) {
      System.out.println("Cannot find video source directory: " + dir);
      Platform.exit();
      
    }
    
    for (String file : dir.list(new FilenameFilter() {
       
      @Override public boolean accept(File dir, String name) {
           
         boolean value=(name.equals(mysong) &&( name.endsWith(".mp3") || name.endsWith(".mp4")));
         
        return (value); 
      }
         
    })) 
        players.add(createPlayer("file:///" + (dir + "\\" + file).replace("\\", "/").replaceAll(" ", "%20")));
  
   //   System.out.println("wahh");
        
    
    
  System.out.println(" okk ");
        
    if (players.isEmpty()) {
     
      Platform.exit();
    }    

    // create a view to show the mediaplayers.
    final MediaView mediaView = new MediaView(players.get(0));
   System.out.println("Playing");
    // play each audio file in turn.
    while(true){
       player     = players.get(0);
      //final MediaPlayer nextPlayer = players.get((i + 1) % players.size());
      player.setOnEndOfMedia(new Runnable() {
        @Override public void run() {
          player.currentTimeProperty().removeListener(progressChangeListener);
          mediaView.setMediaPlayer(player);
          player.setVolume(0.1);
          player.play();
       
          // if (Platform.isImplicitExit()==false) 
        }
      });
    }
    }

public String playsong()
{
     try {
		String filepath = "musicsong.xml";
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		Document doc = docBuilder.parse(filepath);

		Node company = doc.getFirstChild();
	     Node staff = doc.getElementsByTagName("MUSICSONG").item(0);
		NamedNodeMap attr = staff.getAttributes();
		NodeList list = staff.getChildNodes();
                
                     Node node = list.item(0);
                     Element eElement = (Element) node;

 node = list.item(0);
		   if ("backgroundsong".equals(node.getNodeName())) {
return node.getTextContent().toString();

                   }
                       TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(filepath));
		transformer.transform(source, result);

                }
     
	    catch (ParserConfigurationException pce) {
		pce.printStackTrace();
	   } catch (TransformerException tfe) {
		tfe.printStackTrace();
	   } catch (IOException ioe) {
		ioe.printStackTrace();
	   } catch (SAXException sae) {
		sae.printStackTrace();
	   }
    return "";
}
    public void checkfile()
    {
        File file= new File("songsroot.xml");
    
        if (!file.exists())
                {
                    createsongfile();
                }
        else
        {
            file.delete();
                createsongfile();
        }
    }
    
    public void createsongfile()
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
		Element staff = doc.createElement("SONGSROOT");
		rootElement.appendChild(staff);

                    
		Element password = doc.createElement("SONGADD");
		password.appendChild(doc.createTextNode("?"));
		staff.appendChild(password);
                
                Element songname = doc.createElement("SONGNAME");
		songname.appendChild(doc.createTextNode("?"));
		staff.appendChild(songname);
	
		
                }
		// salary elements
		
		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("songsroot.xml"));

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
    
 public void insertsongaddress(String song, String address)
 {
     try {
		String filepath = "songsroot.xml";
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		Document doc = docBuilder.parse(filepath);

		Node company = doc.getFirstChild();
		int index=0;
                while(index<dmimage.getSize()){
                Node staff = doc.getElementsByTagName("SONGSROOT").item(index);
		NamedNodeMap attr = staff.getAttributes();
		NodeList list = staff.getChildNodes();
                
 //                   System.out.println(list.getLength());
                     Node node = list.item(0);
                     Element eElement = (Element) node;

 node = list.item(0);
		   if ("SONGADD".equals(node.getNodeName())) {
		if (node.getTextContent().toString().equals("?"))
                {
 System.out.print(node.getNodeName()+" "+node.getTextContent());
 
                    node.setTextContent(address);
                
                    node = list.item(1);
                       node.setTextContent(song);
 System.out.println(" "+node.getTextContent().toString());
                

//break;
                
                }
		   }
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(filepath));
		transformer.transform(source, result);

//		System.out.println("Done");
                index++;
                }
	   } catch (ParserConfigurationException pce) {
		pce.printStackTrace();
	   } catch (TransformerException tfe) {
		tfe.printStackTrace();
	   } catch (IOException ioe) {
		ioe.printStackTrace();
	   } catch (SAXException sae) {
		sae.printStackTrace();
	   }
 
 }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        mesage = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        DaysLeft = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 255));
        jLabel1.setText("LIST OF SONGS IN GALLERY");

        mesage.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        mesage.setForeground(new java.awt.Color(255, 0, 0));

        jButton2.setBackground(new java.awt.Color(51, 51, 51));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(51, 51, 255));
        jButton2.setText("Play Selected Song");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(51, 51, 51));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 0, 255));
        jButton1.setText("Play All Songs On Panel");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        DaysLeft.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        DaysLeft.setForeground(new java.awt.Color(255, 0, 0));

        jList1.setDragEnabled(true);
        jScrollPane1.setViewportView(jList1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(123, 123, 123)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addGap(76, 76, 76)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(DaysLeft, javax.swing.GroupLayout.PREFERRED_SIZE, 745, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(193, 193, 193)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(mesage, javax.swing.GroupLayout.PREFERRED_SIZE, 769, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 745, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(99, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(DaysLeft, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addComponent(mesage, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
       
    public String returnfilepath(String filename)
    {
        try{
            File fXmlFile = new File("songs.xml");
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(fXmlFile);

	doc.getDocumentElement().normalize();

//	System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

	NodeList nList = doc.getElementsByTagName("SONGS");

	System.out.println("----------------------------");

for (int i=0;i<100;i++){
		Node nNode = nList.item(i);
//		System.out.println("\nCurrent Element :" + nNode.getNodeName());

		if (nNode.getNodeType() == Node.ELEMENT_NODE) {

			Element eElement = (Element) nNode;
                        String path =  eElement.getElementsByTagName("PATH").item(0).getTextContent();
                      
                        String n =  eElement.getElementsByTagName("USERNAME").item(0).getTextContent();
                       
                        String filnme =  eElement.getElementsByTagName("NAME").item(0).getTextContent();
                       
                        String d =  eElement.getElementsByTagName("DATE").item(0).getTextContent();
                        String p =  eElement.getElementsByTagName("TIMES").item(0).getTextContent();
                        System.out.println(path + " "+n +" "+d+" "+p);
                      
             if (n.equals(filename))
             {
                 return path+"\\"+filnme;
             }
                      
                }
}

                
                    
                }
catch(Exception e)
{
    
}
        
        return "";
    }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
 mesage.setText("");
        
//      //   DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
//         int row= 0;//jTable1.getSelectedRow();
//         int col= 0;//jTable1.getSelectedColumn();
//                   
//      int size=dtm.getRowCount();
//        if(size>0 && row!=-1 && col!=-1){ 
//   
//         Object str=dtm.getValueAt(row, 0);
//        
//         System.out.println(str);
//         String s = str.toString();
//         s=returnfilepath(str.toString());
//         System.out.println("path "+s);
//         String songname="";
//         String file="";
//         for (int j=s.length()-1;j>=0;j--)
//         {
//        if (s.charAt(j)=='\\')
//        
//        {
//            file=file+s.substring(0, (j));
//            break;
//        }
//        else
//        { 
//           
//            songname=songname+ s.charAt(j);
//        
//        }
//         }
//
//         String mysong="";
//          for (int k=songname.length()-1;k>=0;k--)
//         {
//             mysong=mysong+songname.charAt(k);
//         }
//         
//          System.out.println("Song name "+mysong);
//        SingleSongPlayer sample = new SingleSongPlayer();
//         sample.initAndShowGUI(file,mysong);
//         
////      incrementsinglesong(mysong);
//  //       dispose();
//  
//  
//  
//        }
//    
//         else
//         {
//             mesage.setText(" *** Songs Gallery Is Empty Or All the Songs Are Played 3 Times in a Day So wait for 4 Hours ***");
//         }
//         
      

 
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
            mesage.setText("");
        
        // DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
         int row= 0;//jTable1.getSelectedRow();
         int col= 0;//jTable1.getSelectedColumn();
                   row=0;
     
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        System.out.println("oh babay");
        System.exit(0);
    }//GEN-LAST:event_formWindowClosed

    public boolean checksongs(String songname)
    {
        
        try{
        String filepath = "songs.xml";
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		Document doc = docBuilder.parse(filepath);

		Node company = doc.getFirstChild();


                int index=0;
                while(index<100){
		Node  staff = doc.getElementsByTagName("SONGS").item(index);

		NamedNodeMap attr = staff.getAttributes();
		Node nodeAttr = attr.getNamedItem("ID");
		NodeList list = staff.getChildNodes();
                
                     Node node = list.item(2);
                     Element eElement = (Element) node;
                    
                      if (node.getTextContent().equals(songname))
                       {
                              return false;
		        }
                    
                   
                       
           index++; }     
              TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(filepath));
		transformer.transform(source, result); }
        catch (Exception e){
		
        }
                  
               
        return true;
    }
    
    
    public void addnewsong(String song,String newsong)
{
        try{
        String filepath = "songs.xml";
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		Document doc = docBuilder.parse(filepath);

		Node company = doc.getFirstChild();


                int index=0;
                while(index<100){
		Node  staff = doc.getElementsByTagName("SONGS").item(index);

		NamedNodeMap attr = staff.getAttributes();
		Node nodeAttr = attr.getNamedItem("ID");
		NodeList list = staff.getChildNodes();
                
                     Node node = list.item(2);
                     Element eElement = (Element) node;
            System.out.println(node.getTextContent());
                      if (node.getTextContent().equals(song))
                       {
                             node.setTextContent(newsong);
                             break;
		        }
                    
                   
                       
           index++; }     
              TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(filepath));
		transformer.transform(source, result); }
        catch (Exception e){
		
        }
                  
               
    
//}void incrementsong(ArrayList<String> songname)
//    {
//                DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
// int si = dtm.getRowCount();
// for (int i=0;i<si;i++)
//     dtm.removeRow(0);
//                            try{ 
//                            Date today = Calendar.getInstance().getTime();   
//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
// String dt = sdf.format(today);
//Calendar c = Calendar.getInstance();
//c.setTime(sdf.parse(dt));
//c.add(Calendar.DATE, 0);  // number of days to add
//dt = sdf.format(c.getTime());  
//sdf.setTimeZone(c.getTimeZone());
//sdf.setCalendar(c);
//Date day = c.getTime();   
//String reportDate = sdf.format(day);
//System.out.println(reportDate);
//
//               String filepath = "songs.xml";
//		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
//		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
//		Document doc = docBuilder.parse(filepath);
//
//		Node company = doc.getFirstChild();
//
//for (int i=0;i<songname.size();i++){
//                int index=0;
//     
//                while(true){
//		Node  staff = doc.getElementsByTagName("SONGS").item(index);
//
//		NamedNodeMap attr = staff.getAttributes();
//		Node nodeAttr = attr.getNamedItem("ID");
//		NodeList list = staff.getChildNodes();
//                
//                //for (int i = 0; i < list.getLength(); i++) {
//                //    System.out.println(list.item(i));
//               // }
//
//		// System.out.println(list.getLength());
//                     Node node = list.item(0);
//                     Element eElement = (Element) node;
//                    
// node = list.item(0);
// System.out.println(node.getTextContent()+" alhamdullilah ");
// String name = node.getTextContent();
// //String splitname[]=name.split("\\");
//   //                   int s =splitname.length;
//                       if (name.equals(songname.get(i)))
//                       {
//                      //     System.out.println(jTextField1.getText());
//                              System.out.println("helo");
//                            node = list.item(1);
//		   if ("DATE".equals(node.getNodeName())) {
//				node.setTextContent(reportDate);
//		   }
//                     node = list.item(2);
// System.out.println(node.getNodeName());
// String times= node.getTextContent();
// int mytimes=Integer.parseInt(times);
// mytimes++;
// String v = Integer.toString(mytimes);
//                     if ("TIMES".equals(node.getNodeName())) {
//			node.setTextContent(v);
//		   }
//                     break;
//                       }
//                       
//           index++;}     
//}
//		TransformerFactory transformerFactory = TransformerFactory.newInstance();
//		Transformer transformer = transformerFactory.newTransformer();
//		DOMSource source = new DOMSource(doc);
//		StreamResult result = new StreamResult(new File(filepath));
//		transformer.transform(source, result);
//   
//            }
//                       catch(Exception e)
//                       {
//                           
//                       }
//                            adddata();
//   
//    }
//   
//    void incrementsinglesong(String song)
//    {
//             DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
// int si = dtm.getRowCount();
// for (int i=0;i<si;i++)
//     dtm.removeRow(0);
//                            try{ 
//                            Date today = Calendar.getInstance().getTime();   
//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
// String dt = sdf.format(today);
//Calendar c = Calendar.getInstance();
//c.setTime(sdf.parse(dt));
//c.add(Calendar.DATE, 0);  // number of days to add
//dt = sdf.format(c.getTime());  
//sdf.setTimeZone(c.getTimeZone());
//sdf.setCalendar(c);
//Date day = c.getTime();   
//String reportDate = sdf.format(day);
//System.out.println(reportDate);
//
//               String filepath = "songs.xml";
//		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
//		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
//		Document doc = docBuilder.parse(filepath);
//
//		Node company = doc.getFirstChild();
//
//
//                int index=0;
//     
//                while(true){
//		Node  staff = doc.getElementsByTagName("SONGS").item(index);
//
//		NamedNodeMap attr = staff.getAttributes();
//		Node nodeAttr = attr.getNamedItem("ID");
//		NodeList list = staff.getChildNodes();
//                
//                //for (int i = 0; i < list.getLength(); i++) {
//                //    System.out.println(list.item(i));
//               // }
//
//		// System.out.println(list.getLength());
//                     Node node = list.item(0);
//                     Element eElement = (Element) node;
//                    
// node = list.item(0);
// System.out.println(node.getTextContent()+" alhamdullilah ");
// String name = node.getTextContent();
// //String splitname[]=name.split("\\");
//   //                   int s =splitname.length;
//                       if (name.equals(song))
//                       {
//                      //     System.out.println(jTextField1.getText());
//                              System.out.println("helo");
//                            node = list.item(1);
//		   if ("DATE".equals(node.getNodeName())) {
//				node.setTextContent(reportDate);
//		   }
//                     node = list.item(2);
// System.out.println(node.getNodeName());
// String times= node.getTextContent();
// int mytimes=Integer.parseInt(times);
// mytimes++;
// String v = Integer.toString(mytimes);
//                     if ("TIMES".equals(node.getNodeName())) {
//			node.setTextContent(v);
//		   }
//                     break;
//                       }
//                       
//           index++;}     
//
//		TransformerFactory transformerFactory = TransformerFactory.newInstance();
//		Transformer transformer = transformerFactory.newTransformer();
//		DOMSource source = new DOMSource(doc);
//		StreamResult result = new StreamResult(new File(filepath));
//		transformer.transform(source, result);
//   
//            }
//                       catch(Exception e)
//                       {
//                           
//                       }
//                            adddata();
//  
//    }
//   
}
    public void adddata ()
    {
        mesage.setText("");
        // DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
 
        mesage.setText("");
           
try{
            File fXmlFile = new File("songs.xml");
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(fXmlFile);

	doc.getDocumentElement().normalize();

//	System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

	NodeList nList = doc.getElementsByTagName("SONGS");

	System.out.println("----------------------------");
   dmimage.clear();
  int index = 0;
for (int i=0;i<100;i++){
		Node nNode = nList.item(i);
//		System.out.println("\nCurrent Element :" + nNode.getNodeName());

		if (nNode.getNodeType() == Node.ELEMENT_NODE) {

			Element eElement = (Element) nNode;
                        String n =  eElement.getElementsByTagName("USERNAME").item(0).getTextContent();
                       
                        String d =  eElement.getElementsByTagName("DATE").item(0).getTextContent();
                        String p =  eElement.getElementsByTagName("TIMES").item(0).getTextContent();
                        
                   
                        if( !(n.equals("?")) && !(p.equals("?"))&& !(d.equals("?")) && Integer.parseInt(p)<3) {
                                 Date today = Calendar.getInstance().getTime(); 
                   
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
 String dt = d;
Calendar c = Calendar.getInstance();
c.setTime(sdf.parse(dt));
c.add(Calendar.DATE, 0);  // number of days to add
dt = sdf.format(c.getTime());  
sdf.setTimeZone(c.getTimeZone());
sdf.setCalendar(c);
Date day = c.getTime();   
String reportDate = sdf.format(day);
//System.out.println(reportDate);

    Vector v = new Vector();
    
         if (n.endsWith(".mp3"))
        dmimage.addElement(new ImageNText(n+" "+"SongPlayed "+p+" Times Today", new ImageIcon(getClass().getClassLoader().getResource("playermp3/songpic.jpg"))));
           
         
         else if (n.endsWith(".mp4"))
        dmimage.addElement(new ImageNText(n+" "+"SongPlayed "+p+" Times Today", new ImageIcon(getClass().getClassLoader().getResource("playermp3/video.jpg"))));

         
                  insertsongaddress(n,dmimage.getElementAt(index).toString());
                    index++;    
//      System.out.println(dmimage.elementAt(0));
        
               //                 v.add(p);
                                
  long diff = day.getTime() - today.getTime();
        long diffSeconds = diff / 1000 % 60;
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000);
        int diffInDays = (int) ((day.getTime() - today.getTime()) / (1000 * 60 * 60 * 24));

        if (diffInDays > 1) {
            System.err.println("Difference in number of days (2) : " + diffInDays);
                 
            //v.add("No of Days Left: "+ diffInDays);
 String  name [] = n.split("\\");
 int s= name.length-1;
            v.add(name[s]);
            v.add("SongPlayed "+p+" Times Today");
                                          
           
        } else if (diffHours > 24) {

            System.err.println(">24");
 String  name [] = n.split("\\");
 int s= name.length-1;
            v.add(name[s]);
            v.add("SongPlayed "+p+" Times Today");
            
          //     jList1.setCellRenderer(new Renderer());
       // jList1.setModel(dmimage);
            
            
          //  v.add("No of Hours Left:"+ diffInDays);
           
   //  mesage.setText("Welcome");
                         
            //                    dtm.addRow(v);
        } else if ((diffHours == 24) && (diffMinutes >= 1)) {
            System.err.println("minutes");
              //v.add("No of Minutes:"+ diffInDays);
                v.add(n);
            v.add("SongPlayed "+p+" Times Today");
             
          //                      dtm.addRow(v);
        
            //   jList1.setCellRenderer(new Renderer());
        //jList1.setModel(dmimage);
        }
        else if (diffInDays==0)
        {
            System.out.println(" difference "+diffInDays);
                v.add(n);
            v.add("SongPlayed "+p+" Times Today");
            
          //  dtm.addRow(v);
            
            
  //             jList1.setCellRenderer(new Renderer());
      //  jList1.setModel(dmimage);
        
        }
 
                             
                        
                        }
                                 
                      
                }
}
             jList1.setCellRenderer(new Renderer());
  
 jList1.setModel(dmimage);

int size =dmimage.getSize();
System.out.println(" Size "+size);
if(size==0)
    mesage.setText("No Data in DataBase ");
}
                catch(Exception e)
                {
                    
                }
    
         
   
    }    
    
    public String getpath(String song)
    {
        try{
                       String filepath = "songs.xml";
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		Document doc = docBuilder.parse(filepath);

		Node company = doc.getFirstChild();


                int index=0;
                while(index<100){
		Node  staff = doc.getElementsByTagName("SONGS").item(index);

		NamedNodeMap attr = staff.getAttributes();
		Node nodeAttr = attr.getNamedItem("ID");
		NodeList list = staff.getChildNodes();
                
                     Node node = list.item(2);
                     Element eElement = (Element) node;
                    
                    if (node.getTextContent().equals(song))
                       {
                      node = list.item(0);
                       if ("PATH".equals(node.getNodeName())) {
                   
        return node.getTextContent().toString();
		   }
                  
		   }
                    
                    index++;   }
                       
           
           	TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(filepath));
		transformer.transform(source, result);
        }
        catch (Exception e)
        {
            
        }
                  

        return "";
    }

    
    public String getsongname(String song)
    {
        try{
                       String filepath = "songs.xml";
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		Document doc = docBuilder.parse(filepath);

		Node company = doc.getFirstChild();


                int index=0;
                while(index<100){
		Node  staff = doc.getElementsByTagName("SONGS").item(index);

		NamedNodeMap attr = staff.getAttributes();
		Node nodeAttr = attr.getNamedItem("ID");
		NodeList list = staff.getChildNodes();
                
                     Node node = list.item(2);
                     Element eElement = (Element) node;
                    
                    if (node.getTextContent().equals(song))
                       {
                      node = list.item(1);
                       if ("NAME".equals(node.getNodeName())) {
                   
        return node.getTextContent().toString();
		   }
                  
		   }
                    
                    index++;   }
                       
           
           	TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(filepath));
		transformer.transform(source, result);
        }
        catch (Exception e)
        {
            
        }
                  

        return "";
    }
    public void createfile()
    {
        DaysLeft.setText(" ******** "+daysleft+" ********** ");
        
        File file = new File("songs.xml");
        if (!file.exists())
        {      
                try {

		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

		// root elements
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("MPLAYER");
		doc.appendChild(rootElement);

		// staff elements
		Element staff = doc.createElement("SONGS");
		rootElement.appendChild(staff);
for (int i=0;i<100;i++){
		// set attribute to staff element
		Attr attr = doc.createAttribute("ID");
		attr.setValue(Integer.toString(i));
		staff.setAttributeNode(attr);

		// shorten way
		// staff.setAttribute("id", "1");

		// firstname elements
		Element name = doc.createElement("NAME");
		name.appendChild(doc.createTextNode("?"));
		staff.appendChild(name);
		// nickname elements
		Element Date = doc.createElement("DATE");
		Date.appendChild(doc.createTextNode("?"));
		staff.appendChild(Date);
                
		Element TIMES = doc.createElement("TIMES");
		TIMES.appendChild(doc.createTextNode("?"));
		staff.appendChild(TIMES);

		// salary elements
		
		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("songs.xml"));

		// Output to console for testing
		// StreamResult result = new StreamResult(System.out);

		transformer.transform(source, result);

		System.out.println("File saved!");
}
	  } catch (ParserConfigurationException pce) {
		pce.printStackTrace();
	  } catch (TransformerException tfe) {
		tfe.printStackTrace();
	  }
    
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GeneralUserAccountMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GeneralUserAccountMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GeneralUserAccountMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GeneralUserAccountMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GeneralUserAccountMenu("").setVisible(true);
            }
        });
        



    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel DaysLeft;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel mesage;
    // End of variables declaration//GEN-END:variables
}

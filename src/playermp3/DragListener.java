/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playermp3;

import java.awt.Label;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
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
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Umer
 */
public class DragListener implements DropTargetListener{

public List<MediaPlayer> players = new ArrayList<MediaPlayer>();
private ChangeListener<Duration> progressChangeListener;
 
            ArrayList<String> listofsongs = new ArrayList<String>();
            ArrayList<String> listofsongsname = new ArrayList<String>();
  MediaPlayer nextPlayer;
            
    public MediaView mediaView;
    
    DefaultListModel dtm = new DefaultListModel();
    JList list = new JList();
  public int timesplayed;
  public String songplaying;
 public String getsongname(String address)
 {
     try {
		String filepath = "songsroot.xml";
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		Document doc = docBuilder.parse(filepath);

		Node company = doc.getFirstChild();
		int index=0;
                while(index<100){
                Node staff = doc.getElementsByTagName("SONGSROOT").item(index);
		NamedNodeMap attr = staff.getAttributes();
		NodeList list = staff.getChildNodes();
                
 //                   System.out.println(list.getLength());
                     Node node = list.item(0);
                     Element eElement = (Element) node;

 node = list.item(0);
// System.out.println(node.getNodeName()+" "+node.getTextContent());
 
		   if ("SONGADD".equals(node.getNodeName())) {
		if (node.getTextContent().toString().equals(address))
                {
                    ///node.setTextContent(address);
                
                    node = list.item(1);
                   String song = node.getTextContent().toString();
                    
                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(filepath));
		transformer.transform(source, result);
                return song;
                
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
 return "";
 }
    public DragListener(JList list)
    {
        timesplayed=0;
        this.list=list;
               nextPlayer=null;
       // jButton1.setVisible(false);
     players.clear();

    }
    public String skipsong()
         {
             String mesage="";
           System.out.println( "status "+ nextPlayer.getStatus().toString());
           //  mesage.setText("");
               final MediaPlayer curPlayer = mediaView.getMediaPlayer();
        //test.player = players.get((players.indexOf(curPlayer) + 1) % players.size());
        nextPlayer.stop();
       
           System.out.println( "status "+ nextPlayer.getStatus().toString());
           if(players.size()>0){
        nextPlayer = players.get((players.indexOf(curPlayer) + 1) % players.size());
        mediaView.setMediaPlayer(nextPlayer);
        curPlayer.currentTimeProperty().removeListener(progressChangeListener);
        
        String myname=(listofsongs.get((players.indexOf(curPlayer) + 1) % players.size())+"\\"+(listofsongsname.get((players.indexOf(curPlayer) + 1) % players.size())));
        //test.player.currentTimeProperty().removeListener(progressChangeListener);
        if (checksong(listofsongsname.get((players.indexOf(curPlayer) + 1) % players.size()))){ 
        final String songname=listofsongsname.get((players.indexOf(curPlayer) + 1) % players.size());
        
    mesage="Now Playing "+listofsongsname.get((players.indexOf(curPlayer) + 1) % players.size());
             DefaultListModel dtm = (DefaultListModel) list.getModel();
           int index=0;
            for (int j=0;j<dtm.getSize();j++)
           {
               if (dtm.getElementAt(j).toString().equals(listofsongsname.get((players.indexOf(curPlayer) + 1) % players.size())))
               {
                   index=j;
                   break;
               }
           }
            System.out.println(index);
            list.setSelectedIndex(index);
        curPlayer.stop();
        incrementsong(songname);
        songplaying=songname;
        
        nextPlayer.play();
        
        }
        else{
          nextPlayer.stop();
              String songname= listofsongsname.get((players.indexOf(curPlayer) + 1) % players.size());
           mesage=songname+" has played 3 times ";
        
            DefaultListModel dtm = (DefaultListModel) list.getModel();
           int index=0;
            for (int j=0;j<dtm.getSize();j++)
           {
               if (dtm.getElementAt(j).toString().equals(songname))
               {
                   index=j;
                   break;
               }
           }
            System.out.println("index  = "+index);
            if (dtm.getSize()>0)
            dtm.removeElementAt(index);
        //    System.out.println(listofsongsname.get((players.indexOf(curPlayer) + 1)% players.size()));
        if (listofsongs.size()>0)
        listofsongs.remove((players.indexOf(curPlayer) + 1) % players.size());
        if (players.size()>0)
            players.remove(index);
        }
        }
        return mesage;
         }
    public  void incrementsong(String songname)
    {
        System.out.println("media player "+songname);
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
                    
 node = list.item(2);
 String name = node.getTextContent();
                       if (name.equals(songname))
                       {
 System.out.println("song find "+name);
 
                    //       System.out.println("helo");
                            node = list.item(3);
Date today = Calendar.getInstance().getTime();   
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
 String dt = sdf.format(today);
Calendar c = Calendar.getInstance();
c.setTime(sdf.parse(dt));
c.add(Calendar.DATE, 0);  // number of days to add
dt = sdf.format(c.getTime());  
sdf.setTimeZone(c.getTimeZone());
sdf.setCalendar(c);
Date day = c.getTime();   
String reportDate = sdf.format(day);
System.out.println(reportDate);
long diff = day.getTime() - today.getTime();
        int diffInDays = (int) ((day.getTime() - today.getTime()) / (1000 * 60 * 60 * 24));

        if (diffInDays > 1) {
            System.err.println("Difference in number of days (2) : " + diffInDays);
            
        }             
                            if ("DATE".equals(node.getNodeName())) {
				node.setTextContent(reportDate);
		   }
                     node = list.item(4);
 System.out.println(node.getNodeName());
 String times= node.getTextContent();
 int mytimes=Integer.parseInt(times);
 mytimes++;
 String v = Integer.toString(mytimes);
 System.out.println(v);
                     if ("TIMES".equals(node.getNodeName())) {
                         
			node.setTextContent(v);
		   timesplayed = mytimes;
                     }
                     break;
                       }
                       
                     index++;}     

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(filepath));
		transformer.transform(source, result);
   
                            }            
                       catch(Exception e)
                       {
                           
                       }
    }
    public  boolean checksong(String songname)
    {
        
        System.out.println("check player "+songname);
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
                    
 node = list.item(2);
 String name = node.getTextContent();
                       if (name.equals(songname))
                       {
 System.out.println("song find "+name);
 
 //                          System.out.println("helo");
                            node = list.item(3);
                     
                            if ("DATE".equals(node.getNodeName())) {
//				node.setTextContent(reportDate);
		   }
                     node = list.item(4);
 System.out.println(node.getNodeName()+" "+node.getTextContent().toString());
 String times= node.getTextContent();
 int mytimes=Integer.parseInt(times);
 mytimes++;
 String v = Integer.toString(mytimes);
                     if ("TIMES".equals(node.getNodeName())) {
		System.out.println("Times:: "+Integer.parseInt(node.getTextContent().toString()));
                     if (Integer.parseInt(node.getTextContent().toString())>=3)
                     {
                         return false;
                         
                     }
                     else
                     {
                         return true;
                     }
                    
                
                     }
                       }   
           index++;       
                }
    		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(filepath));
		transformer.transform(source, result);

                   }     
   catch(Exception e)
   {
       
   }   

                           return false;
    
                            }
      public void addsongsgallery()
    {
            
        DefaultListModel dtm =  (DefaultListModel) list.getModel();
      ArrayList<String> songsnames= new ArrayList<String>();
                   ArrayList<String> songspath= new ArrayList<String>();
                   
      int size=dtm.getSize();
       System.out.println("size "+size);
        if(size>0){
            
 boolean newsong=false; 
    for (int i=0;i<size;i++){  
         Object str=dtm.get(i).toString();
         String songname=str.toString();
         System.out.println("song name "+songname);
         String pathfile=getpath(songname);
         int index=0;
        boolean check=true;
         for (index=0;index<listofsongs.size();index++)
         {
             if (listofsongs.get(index).toString().equals(songname))
             {
                 check=false;
             }
         }
         if (check==true){
             
         listofsongs.add(pathfile.substring(0, pathfile.length()-1));
        listofsongsname.add(songname);
        System.out.println(getpath(songname)+" "+songname);
        songsnames.add(songname);
        songspath.add(pathfile.substring(0, pathfile.length()-1));
newsong=true;
System.out.println("song exists");
         }
         }
         if(newsong==true)
            addplayer(songsnames,songspath);
      }
        else
        {
         //   mesage.setText("Please Add play list ");
        }
   
    }
  /*
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
 */
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

    @Override
    public void dragEnter(DropTargetDragEvent dtde) {
    //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void dragOver(DropTargetDragEvent dtde) {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void dropActionChanged(DropTargetDragEvent dtde) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void dragExit(DropTargetEvent dte) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void drop(DropTargetDropEvent dtde) {
         //System.out.println(" Ohh ");
         dtde.acceptDrop(DnDConstants.ACTION_COPY);
        Transferable t = dtde.getTransferable();
        try {
               DataFlavor [] df = t.getTransferDataFlavors();
              System.out.println(t.getTransferDataFlavors());
            String value=  (String)t.getTransferData(DataFlavor.stringFlavor);
        String [] val = value.split("mp3.");
         
         
            Object obj=t.getTransferData(DataFlavor.stringFlavor).getClass();
           // System.out.println(t.getTransferData(DataFlavor.stringFlavor).toString());
           String song= getsongname(t.getTransferData(DataFlavor.stringFlavor).toString());
           System.out.println(song);
            adddata(song);
            /*for (DataFlavor f:df)
            {
            try{
            System.out.println(f);
            if (f.isFlavorJavaFileListType())
            {
            System.out.println(f);
            List<File> files= (List<File>) t.getTransferData(f);
            for (File file:files)
            {
             value=file.getName();
            System.out.println("value "+value);
            adddata(value);
            }
            
            }*/
            }
            catch (Exception ex)
            {
            JOptionPane.showMessageDialog(null, ex);
            }
            }
      
    private void addplayer( ArrayList<String> song, ArrayList<String> val)
{
    ArrayList<String> songname= new ArrayList<String>();
   songname= song;
   ArrayList<String> mydirect=new ArrayList<String>();
  mydirect = val;
       
    
 
 for(int i=0;i<mydirect.size();i++)
    {
        final String direct = mydirect.get(i);
        System.out.println("current dir = " + direct);
     
    File dir = new File(direct);
    
    String mysong=songname.get(i);
    System.out.println(mysong);
    if (!dir.exists() || !dir.isDirectory()) {
      System.out.println("Cannot find video source directory: " + dir);
      
      
    }
    
 System.out.println("create playerr ");
    for (String file : dir.list(new FilenameFilter() {
       
      @Override public boolean accept(File dir, String name) {
           
         boolean value=(name.equals(mysong) &&( name.endsWith(".mp3") || name.endsWith(".mp4")));
         
        return (value); 
      }
         
    })) 
        players.add(createPlayer("file:///" + (dir + "\\" + file).replace("\\", "/").replaceAll(" ", "%20")));
  
}
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


     private void setCurrentlyPlaying(final MediaPlayer newPlayer) {
    progressChangeListener = new ChangeListener<Duration>() {
      @Override public void changed(ObservableValue<? extends Duration> observableValue, Duration oldValue, Duration newValue) {
     //   progress.setProgress(1.0 * newPlayer.getCurrentTime().toMillis() / newPlayer.getTotalDuration().toMillis());
      }
    };
    newPlayer.currentTimeProperty().addListener(progressChangeListener);

    String source = newPlayer.getMedia().getSource();
    source = source.substring(0, source.length() - ".mp4".length());
    source = source.substring(source.lastIndexOf("/") + 1).replaceAll("%20", " ");

  }
      public String playsong(ArrayList<String>song,ArrayList<String>val)
    {
        String message="";
    
        mediaView = new MediaView(players.get(0));
        for (int i = 0; i < players.size(); i++) {
       nextPlayer = players.get((i + 1) % players.size());
      
          System.out.println(val.get((i + 1) % players.size())+" "+song.get((i + 1) % players.size()));
          
          if(checksong(song.get((i + 1) % players.size()))){
             System.out.println("after checking ...");
  final String songname= song.get((i + 1) % players.size());
        message="Now Playing "+song.get((i + 1) % players.size());
        songplaying= song.get((i + 1) % players.size());
        
            DefaultListModel dtm = (DefaultListModel) list.getModel();
           int index=0;
            for (int j=0;j<dtm.getSize();j++)
           {
               if (dtm.getElementAt(j).toString().equals(song.get((i + 1) % players.size())))
               {
                   index=j;
                   break;
               }
           }
            list.setSelectedIndex(index);
            
          final MediaPlayer player = mediaView.getMediaPlayer();
         player.setOnEndOfMedia(new Runnable() {
        @Override public void run() {
         player.currentTimeProperty().removeListener(progressChangeListener);
          mediaView.setMediaPlayer(nextPlayer);
  
         
          nextPlayer.play();
          
 if ( nextPlayer.getStatus().toString().equals("PLAYING"))
             incrementsong(songname);
  
        }
      });
        
          }
          else
          {
            DefaultListModel dtm = (DefaultListModel) list.getModel();
           int index=0;
            for (int j=0;j<dtm.getSize();j++)
           {
               if (dtm.getElementAt(j).toString().equals(song.get((i + 1) % players.size())))
               {
                   index=j;
                   break;
               }
           }
              String songname= song.get((i + 1) % players.size());
             message=songname+" has played 3 times ";

            players.remove(index);
          
            dtm.removeElementAt(index);
    //        jList1.remove(index);
              
              
          nextPlayer.stop();
        //  return songname+" has played 3 times ";
          }
         
    //display the name of the currently playing track.
    mediaView.mediaPlayerProperty().addListener(new ChangeListener<MediaPlayer>() {
      @Override public void changed(ObservableValue<? extends MediaPlayer> observableValue, MediaPlayer oldPlayer, MediaPlayer newPlayer) {
        setCurrentlyPlaying(newPlayer);
      }
    });
    

    // start playing the first track.
   if (players.size()>0){
    mediaView.setMediaPlayer(players.get(0));
    mediaView.getMediaPlayer().play();
    setCurrentlyPlaying(mediaView.getMediaPlayer());
   }
    }
        return message;

    }
      public String pausesong()
      {
          if (nextPlayer.getStatus().toString().equals("PAUSED"))
          {    nextPlayer.play();
          return "Paused";
          }
          else
          nextPlayer.pause();
          return "Playing";
      }
    public void adddata(String value)
    {
        
        System.out.println(value+" hi");
        Label label =new Label (value);   
        dtm.addElement(value);
       
        list.setModel(dtm);
      
     addsongsgallery();
         
    }
    
}


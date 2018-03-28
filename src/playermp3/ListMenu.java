/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playermp3;

import java.awt.dnd.DropTarget;
import java.io.File;
import java.io.FilenameFilter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Umer
 */
public class ListMenu extends javax.swing.JFrame {
    
public List<MediaPlayer> players = new ArrayList<MediaPlayer>();
private ChangeListener<Duration> progressChangeListener;
 DragListener dt;
            ArrayList<String> listofsongs = new ArrayList<String>();
            ArrayList<String> listofsongsname = new ArrayList<String>();
  MediaPlayer nextPlayer;
            
    public MediaView mediaView;
            /**
     * Creates new form ListMenu
     */
    public ListMenu() {
        dt=null;
        initComponents();
        connecttodragdrop();
        nextPlayer=null;
        jButton1.setVisible(false);
     players.clear();
     
        times.setVisible(false);
        timeslbl.setVisible(false);

    }
    
    /*
public  boolean checksong(String songname)
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
 
                           System.out.println("helo");
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
                         return true;
                         
                     }
                     else
                     {
                         return false;
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
    
                            }*/            
                       
    
    
    /*
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
 
                           System.out.println("helo");
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
    }*/
    private void connecttodragdrop()
    {
         dt = new DragListener(jList1);
        new DropTarget(this,dt);
  //  addsongsgallery();
    }
    /*
    private void playsong(ArrayList<String>song,ArrayList<String>val)
    {
        
        
        
        
        mesage.setText("");
        System.out.println("Song playerr");
        mediaView = new MediaView(players.get(0));
        for (int i = 0; i < players.size(); i++) {
       nextPlayer = players.get((i + 1) % players.size());
      
          System.out.println(val.get((i + 1) % players.size())+" "+song.get((i + 1) % players.size()));
          
          if(true){
             System.out.println("after checking ...");
  final String songname= song.get((i + 1) % players.size());
          mesage.setText("Now Playing "+song.get((i + 1) % players.size()));
            DefaultListModel dtm = (DefaultListModel) jList1.getModel();
           int index=0;
            for (int j=0;j<dtm.getSize();j++)
           {
               if (dtm.getElementAt(j).toString().equals(song.get((i + 1) % players.size())))
               {
                   index=j;
                   break;
               }
           }
            jList1.setSelectedIndex(index);
 
          final MediaPlayer player = mediaView.getMediaPlayer();
         player.setOnEndOfMedia(new Runnable() {
        @Override public void run() {
         player.currentTimeProperty().removeListener(progressChangeListener);
          mediaView.setMediaPlayer(nextPlayer);
  
       //      incrementsong(songname);
         
          nextPlayer.play();
        }
      });
          }
          else
          {
            DefaultListModel dtm = (DefaultListModel) jList1.getModel();
           int index=0;
            for (int j=0;j<dtm.getSize();j++)
           {
               if (dtm.getElementAt(j).toString().equals(song.get((i + 1) % players.size())))
               {
                   index=j;
                   break;
               }
           }
              players.remove(index);
          
            dtm.removeElementAt(index);
    //        jList1.remove(index);
              
              String songname= song.get((i + 1) % players.size());
              mesage.setText(songname+" has played 3 times ");
              
          nextPlayer.stop();
          }
         
    // display the name of the currently playing track.
    mediaView.mediaPlayerProperty().addListener(new ChangeListener<MediaPlayer>() {
      @Override public void changed(ObservableValue<? extends MediaPlayer> observableValue, MediaPlayer oldPlayer, MediaPlayer newPlayer) {
        setCurrentlyPlaying(newPlayer);
      }
    });

    // start playing the first track.
    mediaView.setMediaPlayer(players.get(0));
    mediaView.getMediaPlayer().play();
    setCurrentlyPlaying(mediaView.getMediaPlayer());

    }

    }*/
    
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



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel1 = new java.awt.Panel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        mesage = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        timeslbl = new javax.swing.JLabel();
        times = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        panel1.setBackground(new java.awt.Color(0, 204, 0));
        panel1.setForeground(new java.awt.Color(0, 51, 204));

        jList1.setModel(new DefaultListModel ());
        jScrollPane1.setViewportView(jList1);

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 0, 204));
        jLabel1.setText("                       Songs PlayList");

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 0, 204));
        jButton1.setText("Play Songs");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        mesage.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        mesage.setForeground(new java.awt.Color(255, 0, 0));

        jButton2.setBackground(new java.awt.Color(51, 51, 51));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 0, 204));
        jButton2.setText("Play");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(51, 51, 51));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(51, 0, 255));
        jButton3.setText("Skip");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        timeslbl.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        timeslbl.setText("Times Played");
        timeslbl.setToolTipText("");

        times.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jButton4.setBackground(new java.awt.Color(51, 51, 51));
        jButton4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(0, 0, 204));
        jButton4.setText("Pause");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(183, 183, 183)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(mesage, javax.swing.GroupLayout.PREFERRED_SIZE, 659, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(timeslbl, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(times, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(38, 38, 38))
            .addGroup(layout.createSequentialGroup()
                .addGap(133, 133, 133)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(timeslbl, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mesage, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(times, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
              mesage.setText("");
         DefaultListModel dtm =  (DefaultListModel) jList1.getModel();
      //   DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
         //int row= jTable1.getSelectedRow();
         //int col= jTable1.getSelectedColumn();
           //        row=0;
//                   
//      int size=dtm.getSize();
//        if(size>0){ 
//            ArrayList<String> listofsongs = new ArrayList<String>();
//            ArrayList<String> listofsongsname = new ArrayList<String>();
//  
//    for (int i=0;i<size;i++){  
//         Object str=dtm.get(i).toString();
//         String songname=str.toString();
//         String pathfile=getpath(songname);
//         
//         listofsongs.add(pathfile.substring(0, pathfile.length()-1));
//        listofsongsname.add(getsongname(songname));
//        System.out.println(getpath(songname)+" "+getsongname(songname));
//      }
//         
//      
//         MediaPlayerSample sample = new MediaPlayerSample();
//         sample.initAndShowGUI(listofsongs,listofsongsname);
// 
//  
//        
//         }
//         else
//         {
//             mesage.setText(" *** Songs Gallery Is Empty Or All the Songs Are Played 3 Times in a Day So wait for 24 Hours ***");
//         }
//   
    }//GEN-LAST:event_jButton1ActionPerformed
/*
    public void addsongsgallery()
    {
        //System.out.println("hello");
        mesage.setText("");
            
        DefaultListModel dtm =  (DefaultListModel) jList1.getModel();
    //    System.out.println("");
      //  System.out.println("hi");
        
        ArrayList<String> songsnames= new ArrayList<String>();
                   ArrayList<String> songspath= new ArrayList<String>();
                   
      int size=dtm.getSize();
      // System.out.println("size "+size);
        if(size>0){
            
 boolean newsong=false; 
    for (int i=0;i<size;i++){  
         Object str=dtm.get(i).toString();
         String songname=str.toString();
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
        listofsongsname.add(getsongname(songname));
    //    System.out.println(getpath(songname)+" "+getsongname(songname));
        songsnames.add(getsongname(songname));
        songspath.add(pathfile.substring(0, pathfile.length()-1));
newsong=true;
//System.out.println("song exists");
         }
         }
         if(newsong==true)
            addplayer(songsnames,songspath);
      }
        else
        {
            mesage.setText("Please Add play list ");
        }
   
    }
    */
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    mesage.setText("");
       
        if(dt.nextPlayer==null)
    {
      String mes= dt.playsong(dt.listofsongsname, dt.listofsongs);
      
      mesage.setText(mes);
      if (mes.startsWith("Now")){
      timeslbl.setVisible(true);
      times.setVisible(true);
      times.setText(Integer.toString(dt.timesplayed));
       
      }
      else
      {
          timeslbl.setVisible(false);
      times.setVisible(false);
      times.setText("");
      }
    }
 ///   System.out.println("song is playingg");
//        if (nextPlayer==null )
         //     playsong(listofsongsname,listofsongs);
   jButton2.setVisible(false);
        /*DefaultListModel dtm =  (DefaultListModel) jList1.getModel();
                   ArrayList<String> songsnames= new ArrayList<String>();
                   ArrayList<String> songspath= new ArrayList<String>();
                   
      int size=dtm.getSize();
        if(size>0){ 
 boolean newsong=false; 
    for (int i=0;i<size;i++){  
         Object str=dtm.get(i).toString();
         String songname=str.toString();
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
        listofsongsname.add(getsongname(songname));
        System.out.println(getpath(songname)+" "+getsongname(songname));
        songsnames.add(getsongname(songname));
        songspath.add(pathfile.substring(0, pathfile.length()-1));
newsong=true;
         }
         }
         if(newsong==true)
            addplayer(songsnames,songspath);
            
if (nextPlayer==null )
              playsong(songsnames,songspath);
        }
        else
        {
            mesage.setText("Please Add play list ");
        }*/
    }//GEN-LAST:event_jButton2ActionPerformed
      /*   private void skipsong()
         {
           System.out.println( "status "+ nextPlayer.getStatus().toString());
             mesage.setText("");
               final MediaPlayer curPlayer = mediaView.getMediaPlayer();
        //test.player = players.get((players.indexOf(curPlayer) + 1) % players.size());
        nextPlayer.stop();
       
           System.out.println( "status "+ nextPlayer.getStatus().toString());
        nextPlayer = players.get((players.indexOf(curPlayer) + 1) % players.size());
        mediaView.setMediaPlayer(nextPlayer);
        curPlayer.currentTimeProperty().removeListener(progressChangeListener);
        
        String myname=(listofsongs.get((players.indexOf(curPlayer) + 1) % players.size())+"\\"+(listofsongsname.get((players.indexOf(curPlayer) + 1) % players.size())));
        //test.player.currentTimeProperty().removeListener(progressChangeListener);
        if (checksong(listofsongsname.get((players.indexOf(curPlayer) + 1) % players.size()))==false){ 
        final String songname=listofsongsname.get((players.indexOf(curPlayer) + 1) % players.size());
        
        mesage.setText("Now Playing "+listofsongsname.get((players.indexOf(curPlayer) + 1) % players.size()));
             DefaultListModel dtm = (DefaultListModel) jList1.getModel();
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
            jList1.setSelectedIndex(index);
        curPlayer.stop();
        incrementsong(songname);
        
        nextPlayer.play();
        
        }
        else{
          nextPlayer.stop();
              String songname= listofsongsname.get((players.indexOf(curPlayer) + 1) % players.size());
              mesage.setText(songname+" has played 3 times ");
        
            DefaultListModel dtm = (DefaultListModel) jList1.getModel();
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
            dtm.removeElementAt(index);
System.out.println(listofsongsname.get(players.indexOf(curPlayer) + 1));
        listofsongs.remove((players.indexOf(curPlayer) + 1) % players.size());
            players.remove(index);
       
        }
         }
    */
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
         //skipsong();
            String mess= dt.skipsong();
            mesage.setText(mess);
     
      if (mess.startsWith("Now")){
      timeslbl.setVisible(true);
      times.setVisible(true);
     times.setText(Integer.toString(dt.timesplayed));        
      
      }
      else
      {
           timeslbl.setVisible(false);
      times.setVisible(false);
     times.setText("");
      }
      
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
     String mes=   dt.pausesong();
     if (mes.equals("paused"))
     {
          timeslbl.setVisible(false);
      times.setVisible(false);
mesage.setText("Song Paused");

     }
     else
     {
         mesage.setText("Now Playing "+dt.songplaying+" ");
           timeslbl.setVisible(true);
      times.setVisible(true);
     times.setText(Integer.toString(dt.timesplayed));      
     }
        
        
    }//GEN-LAST:event_jButton4ActionPerformed
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
*/

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
            java.util.logging.Logger.getLogger(ListMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel mesage;
    private java.awt.Panel panel1;
    private javax.swing.JLabel times;
    private javax.swing.JLabel timeslbl;
    // End of variables declaration//GEN-END:variables
}

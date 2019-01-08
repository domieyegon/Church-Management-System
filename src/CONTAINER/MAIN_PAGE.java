/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTAINER;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.jdbc.JDBCCategoryDataset;

import com.teknikindustries.bulksms.SMS;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import static java.lang.Thread.sleep;

import javax.mail.PasswordAuthentication;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import net.proteanit.sql.DbUtils;
import org.apache.commons.validator.routines.EmailValidator;

/**
 *
 * @author DOMINIC YEGON
 */
public class MAIN_PAGE extends javax.swing.JFrame {

Connection conn=null;
ResultSet rs=null;
PreparedStatement pst=null;

    
    
    
    /**
     * Creates new form MAIN_PAGE
     */
    public MAIN_PAGE() {
        initComponents();
        conn=Javaconnect.ConnectDB();
        
        jButton32.setVisible(false);
        jDialogstaff.setLocationRelativeTo(null);
        jDialogmember.setLocationRelativeTo(null);
         jDialogcalendar.setLocationRelativeTo(null);
         jDialogtithing.setLocationRelativeTo(null);
         jDialogbudget.setLocationRelativeTo(null);
         jDialogothers.setLocationRelativeTo(null);
          jDialogmessaging.setLocationRelativeTo(null);
          path1.setVisible(false);
        path.setVisible(false);
        currentdate();
        txt_checked_email.setVisible(false);
         jPanelEMAIL.setVisible(true);
         jPanelSMS.setVisible(false);
         txtbudgettotal.setVisible(false);
         txtbudgeted.setVisible(false);
        txtcalendaeridev.setVisible(false);
        txtvalidate.setVisible(false);
       
         attachmentname.setVisible(false);
         emailpath.setVisible(false);
         txtothersid.setVisible(false);
         txttotalothers.setVisible(false);
        TableSTAFF();
        TableMEMBER();
        TablecalendarUpdate();
        TabletithingUpdate();
        TablebudgetUpdate();
        totalintithe();
        totalbudgeted();
         available();
         TableOthersUpdate();
         totalothers();
    }
    
    private void available(){
        
        try{
            String a1=txtbudgettotal.getText();
            String a2=txtbudgeted.getText();
            String a3=txttotalothers.getText();
            int sum =Integer.parseInt(a1)+Integer.parseInt(a3)-Integer.parseInt(a2);
            String sum1=String.valueOf(sum);
            txtavailable.setText("Ksh. "+sum1);
            txtvalidate.setText(sum1);
        }catch(Exception e){
           // JOptionPane.showMessageDialog(null, e);

        }finally{
        try{
        rs.close();
        pst.close();
        }catch(Exception e){
         }
        
        }
        
    }
    
     private void totalothers(){
         try{
            String sql="select sum(amount) from Others";
            
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            
            if(rs.next()){
                
                String sum=rs.getString("sum(amount)");
                txttotalothers.setText(sum);
                
            }
            
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }finally{
        try{
        rs.close();
        pst.close();
        }catch(Exception e){
         }
        
        } available();
    }
    
     private void totalbudgeted(){
         try{
            String sql="select sum(amount) from budget";
            
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            
            if(rs.next()){
                
                String sum=rs.getString("sum(amount)");
                txtbudgeted.setText(sum);
                
            }
            
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }finally{
        try{
        rs.close();
        pst.close();
        }catch(Exception e){
         }
        
        } available();
    }
    
    
    
    private void totalintithe(){
         try{
            String sql="select sum(amount) from tithing";
            
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            
            if(rs.next()){
                
                String sum=rs.getString("sum(amount)");
                txtbudgettotal.setText(sum);
                
            }
            
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        } finally{
        try{
        rs.close();
        pst.close();
        }catch(Exception e){
         }
        
        }available();
    }
    
   
    
     private void TableSTAFF(){
        try{
        String sql="select name as 'Name',nationalID as 'National ID.',mobile as 'Mobile',email as 'Email',gender as 'Gender',age as 'Age',course as 'Course',occupation as 'Occupation' from staff";
        pst=conn.prepareStatement(sql);
        rs=pst.executeQuery();
        churchstafftable.setModel(DbUtils.resultSetToTableModel(rs));
        
        
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }finally{
            try{
                rs.close();
                pst.close();
            }catch(Exception e){
                
            }
        }
    }
    
     
      private void TableMEMBER(){
        try{
        String sql="select name as 'Name',nationalID as 'National ID.',mobile as 'Mobile',email as 'Email',gender as 'Gender',age as 'Age',course as 'Course',year as 'Year of Study' from member";
        pst=conn.prepareStatement(sql);
        rs=pst.executeQuery();
        churchmembertable.setModel(DbUtils.resultSetToTableModel(rs));
        
        
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }finally{
            try{
                rs.close();
                pst.close();
            }catch(Exception e){
                
            }
        }
    }
    
        private void TablecalendarUpdate(){
        try{
        String sql="select id as 'Serial No.',event as 'Event',date as 'Date',time as 'Time' from calendar";
        pst=conn.prepareStatement(sql);
        rs=pst.executeQuery();
        TableCalendar.setModel(DbUtils.resultSetToTableModel(rs));
        
        
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }finally{
            try{
                rs.close();
                pst.close();
            }catch(Exception e){
                
            }
        }
    }
        
         private void TabletithingUpdate(){
        try{
        String sql="select name as 'Name',nationalid as 'National ID.',date as 'Date',amount as 'amount'from tithing";
        pst=conn.prepareStatement(sql);
        rs=pst.executeQuery();
        tithingtable.setModel(DbUtils.resultSetToTableModel(rs));
        
        
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }finally{
            try{
                rs.close();
                pst.close();
            }catch(Exception e){
                
            }
        }
    }
         
           private void TablebudgetUpdate(){
        try{
        String sql="select budgetid as 'Budget ID',activity as 'Activity',amount as 'Amount' from budget";
        pst=conn.prepareStatement(sql);
        rs=pst.executeQuery();
        jTablebudget.setModel(DbUtils.resultSetToTableModel(rs));
        
        
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }finally{
            try{
                rs.close();
                pst.close();
            }catch(Exception e){
                
            }
        }
    }
    
           
           private void TableOthersUpdate(){
        try{
        String sql="select id as 'Serial No.',date as 'Date',income as 'Income Type',amount as 'Amount' from Others";
        pst=conn.prepareStatement(sql);
        rs=pst.executeQuery();
        otherstable.setModel(DbUtils.resultSetToTableModel(rs));
        
        
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }finally{
            try{
                rs.close();
                pst.close();
            }catch(Exception e){
                
            }
        }
    }
           
            public void currentdate(){
        
  
     //to make it dynamic
     
     Thread clock=new Thread(){
        public void run(){
            
            for(;;){
                
                       
   Calendar cal=new GregorianCalendar();
int month=cal.get(Calendar.MONTH);
int year=cal.get(Calendar.YEAR);
int day=cal.get(Calendar.DAY_OF_MONTH);

txtrealdate.setText("Date: "+day+"/"+(month+1)+"/"+year);


//time

int second=cal.get(Calendar.SECOND);
int minute=cal.get(Calendar.MINUTE);
int hour=cal.get(Calendar.HOUR);
txtrealtime.setText("Time:  "+hour+":"+(minute)+":"+second);
  
        
                
                try {
                    sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MAIN_PAGE.class.getName()).log(Level.SEVERE, null, ex);
                }
                  
            }
            
        }
         
         
     };
     clock.start();
    }
        

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialogstaff = new javax.swing.JDialog();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtstaffmobile = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtstaffcourse = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtstaffemail = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        rbtnmalestaff = new javax.swing.JRadioButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        rbtnfemalestaff = new javax.swing.JRadioButton();
        txtstaffage = new javax.swing.JTextField();
        txtstaffoccupation = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtstaffid = new javax.swing.JTextField();
        txtstaffname = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        churchstafftable = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        path = new javax.swing.JTextField();
        jPanel22 = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        txtfilter = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        txt_checked_email = new javax.swing.JTextField();
        staffbuttongroup = new javax.swing.ButtonGroup();
        jDialogmember = new javax.swing.JDialog();
        jScrollPane2 = new javax.swing.JScrollPane();
        churchmembertable = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        txtmembermobile = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtmembercourse = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtmemberemail = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        rbtnmalemember = new javax.swing.JRadioButton();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtmembername = new javax.swing.JTextField();
        rbtnfemalemember = new javax.swing.JRadioButton();
        txtmemberage = new javax.swing.JTextField();
        txtmemberyearofstudy = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtmemberid = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        btnstaffimage1 = new javax.swing.JButton();
        jDesktopPane2 = new javax.swing.JDesktopPane();
        lblmemberimage = new javax.swing.JLabel();
        path1 = new javax.swing.JTextField();
        jPanel21 = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        txtsearch = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        buttonGroupmember = new javax.swing.ButtonGroup();
        jDialogcalendar = new javax.swing.JDialog();
        jScrollPane3 = new javax.swing.JScrollPane();
        TableCalendar = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txtcalendardate = new com.toedter.calendar.JDateChooser();
        txtcalendarevent = new javax.swing.JTextField();
        txtcalendartime = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jButton13 = new javax.swing.JButton();
        txtcalendaeridev = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        calendershowdate1 = new com.toedter.calendar.JDateChooser();
        jLabel26 = new javax.swing.JLabel();
        calendarshowdate2 = new com.toedter.calendar.JDateChooser();
        jButton14 = new javax.swing.JButton();
        jPanel19 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jDialogtithing = new javax.swing.JDialog();
        jPanel12 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        txttithid = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        txtamount = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        txttithdate = new com.toedter.calendar.JDateChooser();
        txttithname = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        tithingtable = new javax.swing.JTable();
        jPanel23 = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        jDialogbudget = new javax.swing.JDialog();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTablebudget = new javax.swing.JTable();
        jPanel13 = new javax.swing.JPanel();
        txtbudgetamount = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        txtbudgetactivity = new javax.swing.JTextField();
        txtbudgetid = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jPanel20 = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        txtbudgettotal = new javax.swing.JTextField();
        txtbudgeted = new javax.swing.JTextField();
        txtavailable = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        txtvalidate = new javax.swing.JTextField();
        jButton32 = new javax.swing.JButton();
        jButton33 = new javax.swing.JButton();
        jDialogothers = new javax.swing.JDialog();
        jScrollPane8 = new javax.swing.JScrollPane();
        otherstable = new javax.swing.JTable();
        jPanel26 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        txtothersamount = new javax.swing.JTextField();
        txtothersdate = new com.toedter.calendar.JDateChooser();
        txtothersincome = new javax.swing.JTextField();
        jPanel25 = new javax.swing.JPanel();
        jButton28 = new javax.swing.JButton();
        jButton29 = new javax.swing.JButton();
        jButton30 = new javax.swing.JButton();
        jButton31 = new javax.swing.JButton();
        jPanel27 = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        txtothersid = new javax.swing.JTextField();
        txttotalothers = new javax.swing.JTextField();
        jDialogmessaging = new javax.swing.JDialog();
        jPanel15 = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jButton23 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jPanelMAIN = new javax.swing.JPanel();
        jPanelSMS = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jButton25 = new javax.swing.JButton();
        txtsmsuser = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        txtsmspass = new javax.swing.JPasswordField();
        jLabel37 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        txtsmsrecipient = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtsmscompose = new javax.swing.JTextArea();
        jPanelEMAIL = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        TXTEMAILFROM = new javax.swing.JTextField();
        txtsmsfrom2 = new javax.swing.JLabel();
        jButton26 = new javax.swing.JButton();
        TXTEMAILTO = new javax.swing.JTextField();
        jButton27 = new javax.swing.JButton();
        txtsmsfrom4 = new javax.swing.JLabel();
        txtsmsfrom3 = new javax.swing.JLabel();
        TXTEMAILPASSWORD = new javax.swing.JPasswordField();
        txtsmsfrom1 = new javax.swing.JLabel();
        JLABLE = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        TXTEMAILCOMPOSE = new javax.swing.JTextArea();
        TXTEMAILSUBJECT = new javax.swing.JTextField();
        attachmentname = new javax.swing.JTextField();
        emailpath = new javax.swing.JTextField();
        jToolBar1 = new javax.swing.JToolBar();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtrealdate = new javax.swing.JLabel();
        txtrealtime = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        lbldisplay = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnstaff = new javax.swing.JButton();
        btnmember = new javax.swing.JButton();
        btncalendar = new javax.swing.JButton();
        btntithing = new javax.swing.JButton();
        btnbudget = new javax.swing.JButton();
        btnothers = new javax.swing.JButton();
        btnmessaging = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        jMenuItem3 = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        jMenuItem4 = new javax.swing.JMenuItem();

        jDialogstaff.setMinimumSize(new java.awt.Dimension(1100, 600));
        jDialogstaff.setModal(true);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Staff Details", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(102, 102, 255))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Phone NO:");

        txtstaffmobile.setEditable(false);
        txtstaffmobile.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtstaffmobileKeyTyped(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("National ID:");

        txtstaffcourse.setEditable(false);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Gender:");

        txtstaffemail.setEditable(false);
        txtstaffemail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtstaffemailMouseExited(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Course:");

        staffbuttongroup.add(rbtnmalestaff);
        rbtnmalestaff.setText("Male");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Occupation:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Age:");

        staffbuttongroup.add(rbtnfemalestaff);
        rbtnfemalestaff.setText("Female");

        txtstaffage.setEditable(false);
        txtstaffage.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtstaffageKeyTyped(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Email Address:");

        txtstaffid.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtstaffidFocusLost(evt);
            }
        });
        txtstaffid.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtstaffidMouseExited(evt);
            }
        });
        txtstaffid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtstaffidKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtstaffidKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtstaffidKeyTyped(evt);
            }
        });

        txtstaffname.setEditable(false);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Full Name:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(76, 76, 76)
                                .addComponent(txtstaffage, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtstaffname)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addComponent(rbtnmalestaff)
                                                .addGap(34, 34, 34)
                                                .addComponent(rbtnfemalestaff))
                                            .addComponent(txtstaffmobile, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtstaffemail, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(txtstaffid, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtstaffcourse, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtstaffoccupation, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(32, 32, 32))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(txtstaffid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtstaffname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtstaffmobile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtstaffemail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(rbtnmalestaff)
                    .addComponent(rbtnfemalestaff))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtstaffage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtstaffcourse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtstaffoccupation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addContainerGap())
        );

        churchstafftable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        churchstafftable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                churchstafftableMouseClicked(evt);
            }
        });
        churchstafftable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                churchstafftableKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(churchstafftable);
        if (churchstafftable.getColumnModel().getColumnCount() > 0) {
            churchstafftable.getColumnModel().getColumn(0).setResizable(false);
            churchstafftable.getColumnModel().getColumn(1).setResizable(false);
            churchstafftable.getColumnModel().getColumn(2).setResizable(false);
            churchstafftable.getColumnModel().getColumn(3).setResizable(false);
        }

        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 102, 255), new java.awt.Color(153, 204, 255), new java.awt.Color(51, 51, 255), new java.awt.Color(153, 153, 255)));

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/CONTAINER/delete44.jpg"))); // NOI18N
        jButton4.setText("Delete");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/CONTAINER/clear44.jpg"))); // NOI18N
        jButton2.setText("Clear");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/CONTAINER/update144.jpg"))); // NOI18N
        jButton3.setText("Update");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/CONTAINER/save144.png"))); // NOI18N
        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addContainerGap())
        );

        jPanel22.setBackground(new java.awt.Color(0, 0, 255));
        jPanel22.setForeground(new java.awt.Color(255, 255, 255));

        jLabel42.setFont(new java.awt.Font("Perpetua", 1, 24)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(255, 255, 255));
        jLabel42.setText("The  Staff Registration Page");

        txtfilter.setBackground(new java.awt.Color(0, 0, 255));
        txtfilter.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtfilter.setForeground(new java.awt.Color(255, 255, 255));
        txtfilter.setToolTipText("Search");
        txtfilter.setBorder(null);
        txtfilter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtfilterKeyReleased(evt);
            }
        });

        jLabel43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/CONTAINER/search1.jpeg"))); // NOI18N
        jLabel43.setToolTipText("Search");

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                .addGap(310, 310, 310)
                .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 106, Short.MAX_VALUE)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(jLabel43)
                        .addGap(0, 0, 0)
                        .addComponent(txtfilter, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator3))
                .addContainerGap())
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtfilter))
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jDialogstaffLayout = new javax.swing.GroupLayout(jDialogstaff.getContentPane());
        jDialogstaff.getContentPane().setLayout(jDialogstaffLayout);
        jDialogstaffLayout.setHorizontalGroup(
            jDialogstaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogstaffLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDialogstaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jDialogstaffLayout.createSequentialGroup()
                        .addGroup(jDialogstaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(path))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jDialogstaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txt_checked_email, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );
        jDialogstaffLayout.setVerticalGroup(
            jDialogstaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialogstaffLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jDialogstaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jDialogstaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(path, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_checked_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(68, 68, 68))
        );

        jDialogmember.setMinimumSize(new java.awt.Dimension(1100, 600));
        jDialogmember.setModal(true);

        churchmembertable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        churchmembertable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                churchmembertableMouseClicked(evt);
            }
        });
        churchmembertable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                churchmembertableKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(churchmembertable);
        if (churchmembertable.getColumnModel().getColumnCount() > 0) {
            churchmembertable.getColumnModel().getColumn(0).setResizable(false);
            churchmembertable.getColumnModel().getColumn(1).setResizable(false);
            churchmembertable.getColumnModel().getColumn(3).setResizable(false);
        }

        jPanel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 102, 255), new java.awt.Color(153, 204, 255), new java.awt.Color(51, 51, 255), new java.awt.Color(153, 153, 255)));

        jButton5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/CONTAINER/delete44.jpg"))); // NOI18N
        jButton5.setText("Delete");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/CONTAINER/clear44.jpg"))); // NOI18N
        jButton6.setText("Clear");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/CONTAINER/update144.jpg"))); // NOI18N
        jButton7.setText("Update");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/CONTAINER/save144.png"))); // NOI18N
        jButton8.setText("Save");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton8)
                .addGap(18, 18, 18)
                .addComponent(jButton6)
                .addGap(18, 18, 18)
                .addComponent(jButton7)
                .addGap(18, 18, 18)
                .addComponent(jButton5)
                .addContainerGap())
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Member Details", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(102, 102, 255))); // NOI18N

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("Phone NO:");

        txtmembermobile.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtmembermobileKeyTyped(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("National ID:");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("Gender:");

        txtmemberemail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtmemberemailMouseExited(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setText("Course:");

        buttonGroupmember.add(rbtnmalemember);
        rbtnmalemember.setText("Male");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setText("Year of study:");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setText("Age:");

        buttonGroupmember.add(rbtnfemalemember);
        rbtnfemalemember.setText("Female");

        txtmemberage.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtmemberageKeyTyped(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setText("Email Address:");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel20.setText("Full Name:");

        txtmemberid.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtmemberidFocusLost(evt);
            }
        });
        txtmemberid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtmemberidActionPerformed(evt);
            }
        });
        txtmemberid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtmemberidKeyTyped(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel21.setText("Image:");

        btnstaffimage1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnstaffimage1.setText("Choose an Image");
        btnstaffimage1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnstaffimage1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15))
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtmembermobile, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtmemberid)))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(txtmemberemail))))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20)
                            .addComponent(jLabel17)
                            .addComponent(jLabel16)
                            .addComponent(jLabel18)
                            .addComponent(jLabel21))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(rbtnmalemember)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(rbtnfemalemember))
                            .addComponent(txtmemberage)
                            .addComponent(txtmembercourse)
                            .addComponent(txtmemberyearofstudy)
                            .addComponent(btnstaffimage1, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                            .addComponent(txtmembername))))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtmembername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtmemberid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtmembermobile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txtmemberemail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(rbtnmalemember)
                    .addComponent(rbtnfemalemember))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtmemberage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(btnstaffimage1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtmembercourse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtmemberyearofstudy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jDesktopPane2.setLayer(lblmemberimage, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane2Layout = new javax.swing.GroupLayout(jDesktopPane2);
        jDesktopPane2.setLayout(jDesktopPane2Layout);
        jDesktopPane2Layout.setHorizontalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblmemberimage, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                .addContainerGap())
        );
        jDesktopPane2Layout.setVerticalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblmemberimage, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel21.setBackground(new java.awt.Color(0, 0, 255));

        jLabel41.setFont(new java.awt.Font("Perpetua", 1, 24)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(255, 255, 255));
        jLabel41.setText("Member Registration Page");

        txtsearch.setBackground(new java.awt.Color(0, 0, 255));
        txtsearch.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtsearch.setForeground(new java.awt.Color(255, 255, 255));
        txtsearch.setBorder(null);
        txtsearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtsearchKeyReleased(evt);
            }
        });

        jLabel50.setIcon(new javax.swing.ImageIcon(getClass().getResource("/CONTAINER/search1.jpeg"))); // NOI18N

        jSeparator6.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addGap(376, 376, 376)
                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator6)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(jLabel50)
                        .addGap(0, 0, 0)
                        .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap(52, Short.MAX_VALUE)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                        .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel50))
                        .addGap(1, 1, 1)
                        .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6))))
        );

        javax.swing.GroupLayout jDialogmemberLayout = new javax.swing.GroupLayout(jDialogmember.getContentPane());
        jDialogmember.getContentPane().setLayout(jDialogmemberLayout);
        jDialogmemberLayout.setHorizontalGroup(
            jDialogmemberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogmemberLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDialogmemberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jDialogmemberLayout.createSequentialGroup()
                        .addComponent(path1, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 760, Short.MAX_VALUE))
                    .addGroup(jDialogmemberLayout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jDialogmemberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDesktopPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2)))
                .addContainerGap())
        );
        jDialogmemberLayout.setVerticalGroup(
            jDialogmemberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogmemberLayout.createSequentialGroup()
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jDialogmemberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialogmemberLayout.createSequentialGroup()
                        .addComponent(jDesktopPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jDialogmemberLayout.createSequentialGroup()
                        .addGroup(jDialogmemberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jDialogmemberLayout.createSequentialGroup()
                                .addGap(167, 167, 167)
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(path1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63))))
        );

        jDialogcalendar.setMinimumSize(new java.awt.Dimension(1100, 600));
        jDialogcalendar.setModal(true);

        TableCalendar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TableCalendar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableCalendarMouseClicked(evt);
            }
        });
        TableCalendar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TableCalendarKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(TableCalendar);
        if (TableCalendar.getColumnModel().getColumnCount() > 0) {
            TableCalendar.getColumnModel().getColumn(0).setResizable(false);
            TableCalendar.getColumnModel().getColumn(1).setResizable(false);
            TableCalendar.getColumnModel().getColumn(2).setResizable(false);
            TableCalendar.getColumnModel().getColumn(3).setResizable(false);
        }

        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel9.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 102, 255), new java.awt.Color(153, 204, 255), new java.awt.Color(51, 51, 255), new java.awt.Color(153, 153, 255)));

        jButton9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/CONTAINER/delete44.jpg"))); // NOI18N
        jButton9.setText("Delete");
        jButton9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/CONTAINER/clear44.jpg"))); // NOI18N
        jButton10.setText("Clear");
        jButton10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/CONTAINER/update144.jpg"))); // NOI18N
        jButton11.setText("Update");
        jButton11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/CONTAINER/save144.png"))); // NOI18N
        jButton12.setText("Save");
        jButton12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton12)
                    .addComponent(jButton10)
                    .addComponent(jButton11)
                    .addComponent(jButton9))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel24.setText("Time:");

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel22.setText("date:");

        txtcalendardate.setDateFormatString("yyyy-MM-dd");
        txtcalendardate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtcalendarevent.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtcalendartime.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel23.setText("Name of the Event:");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23)
                            .addComponent(jLabel22)
                            .addComponent(jLabel24))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtcalendardate, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
                            .addComponent(txtcalendarevent)
                            .addComponent(txtcalendartime)))
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(txtcalendarevent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel22)
                    .addComponent(txtcalendardate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(txtcalendartime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jButton13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/CONTAINER/print44.png"))); // NOI18N
        jButton13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton13MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton13MouseExited(evt);
            }
        });
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel25.setText("SHOW DETAILS BETWEEN DATE");

        calendershowdate1.setDateFormatString("yyyy-MM-dd");
        calendershowdate1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel26.setText("AND");

        calendarshowdate2.setDateFormatString("yyyy-MM-dd");
        calendarshowdate2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jButton14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton14.setText("show");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jPanel19.setBackground(new java.awt.Color(51, 51, 255));

        jLabel38.setFont(new java.awt.Font("Perpetua", 1, 18)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 255, 255));
        jLabel38.setText("Calendar Page");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(492, 492, 492)
                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel49.setFont(new java.awt.Font("Perpetua", 1, 18)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout jDialogcalendarLayout = new javax.swing.GroupLayout(jDialogcalendar.getContentPane());
        jDialogcalendar.getContentPane().setLayout(jDialogcalendarLayout);
        jDialogcalendarLayout.setHorizontalGroup(
            jDialogcalendarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogcalendarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jDialogcalendarLayout.createSequentialGroup()
                .addGroup(jDialogcalendarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jDialogcalendarLayout.createSequentialGroup()
                        .addGroup(jDialogcalendarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jDialogcalendarLayout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addGroup(jDialogcalendarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel25)
                                    .addComponent(calendarshowdate2, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(calendershowdate1, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jDialogcalendarLayout.createSequentialGroup()
                                        .addGap(83, 83, 83)
                                        .addComponent(jLabel26))))
                            .addGroup(jDialogcalendarLayout.createSequentialGroup()
                                .addGap(207, 207, 207)
                                .addComponent(jButton14))
                            .addGroup(jDialogcalendarLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(9, 9, 9)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jDialogcalendarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtcalendaeridev, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel49, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(56, 56, 56))
        );
        jDialogcalendarLayout.setVerticalGroup(
            jDialogcalendarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogcalendarLayout.createSequentialGroup()
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jDialogcalendarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE)
                    .addGroup(jDialogcalendarLayout.createSequentialGroup()
                        .addGroup(jDialogcalendarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jDialogcalendarLayout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22)
                                .addComponent(txtcalendaeridev, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(calendershowdate1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel26)
                        .addGap(18, 18, 18)
                        .addComponent(calendarshowdate2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton14)))
                .addContainerGap())
        );

        jDialogtithing.setMinimumSize(new java.awt.Dimension(1100, 600));
        jDialogtithing.setModal(true);

        jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel28.setText("Date:");

        jPanel11.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 102, 255), new java.awt.Color(153, 204, 255), new java.awt.Color(51, 51, 255), new java.awt.Color(153, 153, 255)));

        jButton15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/CONTAINER/delete44.jpg"))); // NOI18N
        jButton15.setText("Delete");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jButton16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/CONTAINER/clear44.jpg"))); // NOI18N
        jButton16.setText("Clear");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jButton17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/CONTAINER/update144.jpg"))); // NOI18N
        jButton17.setText("Update");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jButton18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/CONTAINER/save144.png"))); // NOI18N
        jButton18.setText("Save");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton18)
                    .addComponent(jButton16)
                    .addComponent(jButton17)
                    .addComponent(jButton15))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txttithid.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel30.setText("Amount :");

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel27.setText("Name:");

        txtamount.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtamount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtamountKeyTyped(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel29.setText("National ID:");

        txttithdate.setDateFormatString("yyyy-MM-dd");
        txttithdate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        txttithname.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel27)
                            .addComponent(jLabel30)
                            .addComponent(jLabel28))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtamount)
                            .addComponent(txttithname)
                            .addComponent(txttithdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel29)
                        .addGap(18, 18, 18)
                        .addComponent(txttithid)))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(txttithname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(txttithid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel28)
                    .addComponent(txttithdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(txtamount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tithingtable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tithingtable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tithingtableMouseClicked(evt);
            }
        });
        tithingtable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tithingtableKeyReleased(evt);
            }
        });
        jScrollPane4.setViewportView(tithingtable);

        jPanel23.setBackground(new java.awt.Color(0, 0, 255));

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(255, 255, 255));
        jLabel45.setText("Tithes  Page");

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(445, 445, 445)
                .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel45, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jDialogtithingLayout = new javax.swing.GroupLayout(jDialogtithing.getContentPane());
        jDialogtithing.getContentPane().setLayout(jDialogtithingLayout);
        jDialogtithingLayout.setHorizontalGroup(
            jDialogtithingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogtithingLayout.createSequentialGroup()
                .addGroup(jDialogtithingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialogtithingLayout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE))
                    .addGroup(jDialogtithingLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jDialogtithingLayout.setVerticalGroup(
            jDialogtithingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogtithingLayout.createSequentialGroup()
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDialogtithingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE)
                    .addGroup(jDialogtithingLayout.createSequentialGroup()
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jDialogbudget.setMinimumSize(new java.awt.Dimension(1100, 600));
        jDialogbudget.setModal(true);
        jDialogbudget.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                chartsopen(evt);
            }
        });

        jTablebudget.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTablebudget.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTablebudgetMouseClicked(evt);
            }
        });
        jTablebudget.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTablebudgetKeyReleased(evt);
            }
        });
        jScrollPane5.setViewportView(jTablebudget);

        jPanel13.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        txtbudgetamount.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtbudgetamount.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtbudgetamountFocusLost(evt);
            }
        });
        txtbudgetamount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtbudgetamountKeyTyped(evt);
            }
        });

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel32.setText("Amount Allocated:");

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel33.setText("Activity:");

        txtbudgetactivity.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        txtbudgetid.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel31.setText("Budget ID:");

        jPanel14.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 102, 255), new java.awt.Color(153, 204, 255), new java.awt.Color(51, 51, 255), new java.awt.Color(153, 153, 255)));

        jButton19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/CONTAINER/delete44.jpg"))); // NOI18N
        jButton19.setText("Delete");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        jButton20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/CONTAINER/clear44.jpg"))); // NOI18N
        jButton20.setText("Clear");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        jButton21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/CONTAINER/update144.jpg"))); // NOI18N
        jButton21.setText("Update");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        jButton22.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/CONTAINER/save144.png"))); // NOI18N
        jButton22.setText("Save");
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton22)
                    .addComponent(jButton20)
                    .addComponent(jButton21)
                    .addComponent(jButton19))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel32)
                            .addComponent(jLabel33)
                            .addComponent(jLabel31))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtbudgetactivity, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
                            .addComponent(txtbudgetamount)
                            .addComponent(txtbudgetid)))
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtbudgetamount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel31)
                            .addComponent(txtbudgetid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel33)
                            .addComponent(txtbudgetactivity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addComponent(jLabel32)))
                .addGap(18, 18, 18)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel20.setBackground(new java.awt.Color(51, 51, 255));

        jLabel40.setFont(new java.awt.Font("Perpetua", 1, 24)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(255, 255, 255));
        jLabel40.setText("The Budgeting Page");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(447, 447, 447)
                .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                .addContainerGap())
        );

        txtavailable.setEditable(false);
        txtavailable.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel44.setText("TOTAL AMOUNT AVAILABLE IN ACCOUNT");

        jButton32.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton32.setText("View  bar graph");
        jButton32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton32ActionPerformed(evt);
            }
        });

        jButton33.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton33.setText("Print");
        jButton33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton33ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDialogbudgetLayout = new javax.swing.GroupLayout(jDialogbudget.getContentPane());
        jDialogbudget.getContentPane().setLayout(jDialogbudgetLayout);
        jDialogbudgetLayout.setHorizontalGroup(
            jDialogbudgetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogbudgetLayout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addGroup(jDialogbudgetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialogbudgetLayout.createSequentialGroup()
                        .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtavailable, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialogbudgetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jDialogbudgetLayout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jDialogbudgetLayout.createSequentialGroup()
                            .addComponent(txtbudgettotal, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(115, 115, 115)
                            .addComponent(txtbudgeted, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(42, 42, 42)
                            .addComponent(txtvalidate, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jDialogbudgetLayout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(jButton32, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jDialogbudgetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialogbudgetLayout.createSequentialGroup()
                        .addGap(252, 252, 252)
                        .addComponent(jButton33, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jDialogbudgetLayout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(35, 35, 35))
            .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialogbudgetLayout.setVerticalGroup(
            jDialogbudgetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogbudgetLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(jDialogbudgetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jDialogbudgetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtavailable, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jDialogbudgetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jDialogbudgetLayout.createSequentialGroup()
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jButton32)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jDialogbudgetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtbudgettotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtbudgeted, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtvalidate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(55, 55, 55))
        );

        jDialogothers.setMinimumSize(new java.awt.Dimension(1100, 600));
        jDialogothers.setModal(true);

        otherstable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        otherstable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                otherstableMouseClicked(evt);
            }
        });
        otherstable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                otherstableKeyReleased(evt);
            }
        });
        jScrollPane8.setViewportView(otherstable);
        if (otherstable.getColumnModel().getColumnCount() > 0) {
            otherstable.getColumnModel().getColumn(0).setResizable(false);
            otherstable.getColumnModel().getColumn(1).setResizable(false);
            otherstable.getColumnModel().getColumn(2).setResizable(false);
            otherstable.getColumnModel().getColumn(3).setResizable(false);
        }

        jPanel26.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(204, 153, 255), new java.awt.Color(153, 153, 255), new java.awt.Color(255, 153, 204), new java.awt.Color(204, 204, 255)), "Other Types Of Income", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(0, 0, 255))); // NOI18N

        jPanel24.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Date:");

        jLabel47.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel47.setText("Type of Income:");

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel46.setText("Amount Collected:");

        txtothersamount.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        txtothersdate.setDateFormatString("yyyy-MM-dd");

        txtothersincome.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel46)
                            .addComponent(jLabel47))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtothersdate, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
                            .addComponent(txtothersincome)
                            .addComponent(txtothersamount))))
                .addContainerGap())
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11)
                    .addComponent(txtothersdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtothersincome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel47))
                .addGap(18, 18, 18)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46)
                    .addComponent(txtothersamount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel25.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 102, 255), new java.awt.Color(153, 204, 255), new java.awt.Color(51, 51, 255), new java.awt.Color(153, 153, 255)));

        jButton28.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/CONTAINER/delete44.jpg"))); // NOI18N
        jButton28.setText("Delete");
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });

        jButton29.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/CONTAINER/clear44.jpg"))); // NOI18N
        jButton29.setText("Clear");
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });

        jButton30.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/CONTAINER/update144.jpg"))); // NOI18N
        jButton30.setText("Update");
        jButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton30ActionPerformed(evt);
            }
        });

        jButton31.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/CONTAINER/save144.png"))); // NOI18N
        jButton31.setText("Save");
        jButton31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton31ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton31, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton29, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton30, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton28, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton31)
                    .addComponent(jButton29)
                    .addComponent(jButton30)
                    .addComponent(jButton28))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel27.setBackground(new java.awt.Color(51, 51, 255));

        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(255, 255, 255));
        jLabel48.setText("Other Types Of Income Page");

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(353, 353, 353))
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialogothersLayout = new javax.swing.GroupLayout(jDialogothers.getContentPane());
        jDialogothers.getContentPane().setLayout(jDialogothersLayout);
        jDialogothersLayout.setHorizontalGroup(
            jDialogothersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogothersLayout.createSequentialGroup()
                .addGroup(jDialogothersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialogothersLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jDialogothersLayout.createSequentialGroup()
                        .addGroup(jDialogothersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jDialogothersLayout.createSequentialGroup()
                                .addGap(0, 14, Short.MAX_VALUE)
                                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38))
                            .addGroup(jDialogothersLayout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(txtothersid, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(91, 91, 91)
                                .addComponent(txttotalothers, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jDialogothersLayout.setVerticalGroup(
            jDialogothersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogothersLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDialogothersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialogothersLayout.createSequentialGroup()
                        .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jDialogothersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtothersid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txttotalothers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 101, Short.MAX_VALUE))
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        jDialogmessaging.setMinimumSize(new java.awt.Dimension(1100, 600));
        jDialogmessaging.setModal(true);

        jPanel15.setBackground(new java.awt.Color(51, 51, 255));

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setText("The Messaging Page");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(371, 371, 371))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jPanel16.setBackground(new java.awt.Color(0, 0, 153));
        jPanel16.setForeground(new java.awt.Color(204, 204, 204));

        jButton23.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/CONTAINER/gmail44.jpg"))); // NOI18N
        jButton23.setText("Email");
        jButton23.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });

        jButton24.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/CONTAINER/sms44.jpg"))); // NOI18N
        jButton24.setText("SMS");
        jButton24.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });

        jSeparator2.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton23, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton24, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jSeparator2)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jButton24, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(202, Short.MAX_VALUE))
        );

        jPanelMAIN.setMinimumSize(new java.awt.Dimension(750, 400));

        jPanelSMS.setMinimumSize(new java.awt.Dimension(750, 400));

        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "Send an SMS", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Perpetua", 1, 14), new java.awt.Color(0, 0, 255))); // NOI18N

        jButton25.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton25.setText("Send");
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel35.setText("Password:");

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel37.setText("Message:");

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel36.setText("Recipient:");

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel34.setText("User Name:");

        txtsmsrecipient.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtsmsrecipientKeyTyped(evt);
            }
        });

        txtsmscompose.setColumns(20);
        txtsmscompose.setRows(5);
        jScrollPane6.setViewportView(txtsmscompose);

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton25)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel34)
                            .addComponent(jLabel36)
                            .addComponent(jLabel35)
                            .addComponent(jLabel37))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtsmsrecipient)
                            .addComponent(txtsmsuser)
                            .addComponent(txtsmspass)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(txtsmsuser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(txtsmspass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(txtsmsrecipient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel37)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton25)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanelSMSLayout = new javax.swing.GroupLayout(jPanelSMS);
        jPanelSMS.setLayout(jPanelSMSLayout);
        jPanelSMSLayout.setHorizontalGroup(
            jPanelSMSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSMSLayout.createSequentialGroup()
                .addGap(162, 162, 162)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(202, Short.MAX_VALUE))
        );
        jPanelSMSLayout.setVerticalGroup(
            jPanelSMSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSMSLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "Compose an Email", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Perpetua", 1, 14), new java.awt.Color(0, 0, 204))); // NOI18N

        TXTEMAILFROM.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                TXTEMAILFROMMouseExited(evt);
            }
        });
        TXTEMAILFROM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXTEMAILFROMKeyTyped(evt);
            }
        });

        txtsmsfrom2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtsmsfrom2.setText("TO:");

        jButton26.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton26.setText("ATTACH");
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });

        TXTEMAILTO.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                TXTEMAILTOMouseExited(evt);
            }
        });
        TXTEMAILTO.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXTEMAILTOKeyTyped(evt);
            }
        });

        jButton27.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton27.setText("SEND");
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });

        txtsmsfrom4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtsmsfrom4.setText("COMPOSE:");

        txtsmsfrom3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtsmsfrom3.setText("SUBJECT:");

        txtsmsfrom1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtsmsfrom1.setText("PASSWORD:");

        JLABLE.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        JLABLE.setText("FROM:");

        TXTEMAILCOMPOSE.setColumns(20);
        TXTEMAILCOMPOSE.setRows(5);
        jScrollPane7.setViewportView(TXTEMAILCOMPOSE);

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JLABLE)
                    .addComponent(txtsmsfrom3)
                    .addComponent(txtsmsfrom2)
                    .addComponent(txtsmsfrom1)
                    .addComponent(txtsmsfrom4))
                .addGap(33, 33, 33)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jButton26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton27)
                        .addGap(11, 11, 11))
                    .addComponent(TXTEMAILTO)
                    .addComponent(TXTEMAILSUBJECT)
                    .addComponent(TXTEMAILFROM)
                    .addComponent(TXTEMAILPASSWORD)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JLABLE)
                    .addComponent(TXTEMAILFROM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtsmsfrom1)
                    .addComponent(TXTEMAILPASSWORD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtsmsfrom2)
                    .addComponent(TXTEMAILTO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtsmsfrom3)
                    .addComponent(TXTEMAILSUBJECT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtsmsfrom4)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton26)
                    .addComponent(jButton27))
                .addContainerGap())
        );

        attachmentname.setText("attachment name");

        javax.swing.GroupLayout jPanelEMAILLayout = new javax.swing.GroupLayout(jPanelEMAIL);
        jPanelEMAIL.setLayout(jPanelEMAILLayout);
        jPanelEMAILLayout.setHorizontalGroup(
            jPanelEMAILLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelEMAILLayout.createSequentialGroup()
                .addGap(166, 166, 166)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addGroup(jPanelEMAILLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(attachmentname, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                    .addComponent(emailpath))
                .addGap(73, 73, 73))
        );
        jPanelEMAILLayout.setVerticalGroup(
            jPanelEMAILLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelEMAILLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelEMAILLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(attachmentname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(emailpath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(138, 138, 138))
        );

        javax.swing.GroupLayout jPanelMAINLayout = new javax.swing.GroupLayout(jPanelMAIN);
        jPanelMAIN.setLayout(jPanelMAINLayout);
        jPanelMAINLayout.setHorizontalGroup(
            jPanelMAINLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMAINLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelSMS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanelMAINLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelMAINLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanelEMAIL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanelMAINLayout.setVerticalGroup(
            jPanelMAINLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMAINLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelSMS, javax.swing.GroupLayout.PREFERRED_SIZE, 388, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanelMAINLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelMAINLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanelEMAIL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout jDialogmessagingLayout = new javax.swing.GroupLayout(jDialogmessaging.getContentPane());
        jDialogmessaging.getContentPane().setLayout(jDialogmessagingLayout);
        jDialogmessagingLayout.setHorizontalGroup(
            jDialogmessagingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogmessagingLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDialogmessagingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialogmessagingLayout.createSequentialGroup()
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanelMAIN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jDialogmessagingLayout.setVerticalGroup(
            jDialogmessagingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogmessagingLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDialogmessagingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelMAIN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jToolBar1.setRollover(true);

        jPanel1.setBackground(new java.awt.Color(0, 102, 255));

        jPanel2.setBackground(new java.awt.Color(0, 0, 255));

        jLabel1.setFont(new java.awt.Font("Perpetua", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("DKUT Christian Union");

        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Church Management System");

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/CONTAINER/church.jpg"))); // NOI18N

        txtrealdate.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtrealdate.setForeground(new java.awt.Color(255, 255, 255));

        txtrealtime.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtrealtime.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 355, Short.MAX_VALUE)
                        .addComponent(txtrealtime, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 509, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtrealdate, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(txtrealtime, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(txtrealdate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setMinimumSize(new java.awt.Dimension(50, 50));

        lbldisplay.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbldisplay.setForeground(new java.awt.Color(255, 0, 0));
        lbldisplay.setMinimumSize(new java.awt.Dimension(34, 34));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(547, 547, 547)
                .addComponent(lbldisplay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lbldisplay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));

        btnstaff.setBackground(new java.awt.Color(153, 153, 255));
        btnstaff.setIcon(new javax.swing.ImageIcon(getClass().getResource("/CONTAINER/person44.png"))); // NOI18N
        btnstaff.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnstaff.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnstaffMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnstaffMouseExited(evt);
            }
        });
        btnstaff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnstaffActionPerformed(evt);
            }
        });

        btnmember.setBackground(new java.awt.Color(153, 153, 255));
        btnmember.setIcon(new javax.swing.ImageIcon(getClass().getResource("/CONTAINER/people44.png"))); // NOI18N
        btnmember.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnmember.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnmemberMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnmemberMouseExited(evt);
            }
        });
        btnmember.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmemberActionPerformed(evt);
            }
        });

        btncalendar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/CONTAINER/calendar44.jpg"))); // NOI18N
        btncalendar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btncalendar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btncalendarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btncalendarMouseExited(evt);
            }
        });
        btncalendar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncalendarActionPerformed(evt);
            }
        });

        btntithing.setBackground(new java.awt.Color(153, 153, 255));
        btntithing.setIcon(new javax.swing.ImageIcon(getClass().getResource("/CONTAINER/budget44.jpg"))); // NOI18N
        btntithing.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btntithing.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btntithingMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btntithingMouseExited(evt);
            }
        });
        btntithing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntithingActionPerformed(evt);
            }
        });

        btnbudget.setBackground(new java.awt.Color(153, 153, 255));
        btnbudget.setIcon(new javax.swing.ImageIcon(getClass().getResource("/CONTAINER/budget (2)44.jpg"))); // NOI18N
        btnbudget.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnbudget.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnbudgetMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnbudgetMouseExited(evt);
            }
        });
        btnbudget.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbudgetActionPerformed(evt);
            }
        });

        btnothers.setBackground(new java.awt.Color(153, 153, 255));
        btnothers.setIcon(new javax.swing.ImageIcon(getClass().getResource("/CONTAINER/project44.png"))); // NOI18N
        btnothers.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnothers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnothersMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnothersMouseExited(evt);
            }
        });
        btnothers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnothersActionPerformed(evt);
            }
        });

        btnmessaging.setBackground(new java.awt.Color(153, 153, 255));
        btnmessaging.setIcon(new javax.swing.ImageIcon(getClass().getResource("/CONTAINER/messaging44.jpg"))); // NOI18N
        btnmessaging.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnmessaging.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnmessagingMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnmessagingMouseExited(evt);
            }
        });
        btnmessaging.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmessagingActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnstaff, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnbudget, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(114, 114, 114)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnmember, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnothers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(114, 114, 114)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btncalendar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnmessaging, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 110, Short.MAX_VALUE)
                .addComponent(btntithing, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btntithing, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                    .addComponent(btnstaff, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnmember, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btncalendar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(36, 36, 36)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnbudget, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                    .addComponent(btnothers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnmessaging, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jMenuBar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuBar1.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N

        jMenu1.setText("File");
        jMenu1.setFocusPainted(true);
        jMenu1.setFont(new java.awt.Font("Mongolian Baiti", 0, 18)); // NOI18N
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jMenu1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jMenu1MouseExited(evt);
            }
        });

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("User Registration");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("Change Password");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);
        jMenu1.add(jSeparator4);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setText("Logout");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);
        jMenu1.add(jSeparator5);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem4.setText("Exit");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(1214, 705));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnstaffMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnstaffMouseEntered

        lbldisplay.setText("Staff");


        // TODO add your handling code here:
    }//GEN-LAST:event_btnstaffMouseEntered

    private void btnstaffMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnstaffMouseExited

        lbldisplay.setText("");
        // TODO add your handling code here:
    }//GEN-LAST:event_btnstaffMouseExited

    private void btnmemberMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnmemberMouseEntered

        lbldisplay.setText("Members");
        // TODO add your handling code here:
    }//GEN-LAST:event_btnmemberMouseEntered

    private void btnmemberMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnmemberMouseExited

        lbldisplay.setText("");
        // TODO add your handling code here:
    }//GEN-LAST:event_btnmemberMouseExited

    private void btncalendarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btncalendarMouseEntered

         lbldisplay.setText("Calendar");
        // TODO add your handling code here:
    }//GEN-LAST:event_btncalendarMouseEntered

    private void btncalendarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btncalendarMouseExited

        lbldisplay.setText("");
        // TODO add your handling code here:
    }//GEN-LAST:event_btncalendarMouseExited

    private void btntithingMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btntithingMouseEntered

        lbldisplay.setText("Tithing");
        // TODO add your handling code here:
    }//GEN-LAST:event_btntithingMouseEntered

    private void btntithingMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btntithingMouseExited

        lbldisplay.setText("");
        // TODO add your handling code here:
    }//GEN-LAST:event_btntithingMouseExited

    private void btnbudgetMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnbudgetMouseEntered

        lbldisplay.setText("Budget");
        // TODO add your handling code here:
    }//GEN-LAST:event_btnbudgetMouseEntered

    private void btnbudgetMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnbudgetMouseExited

        lbldisplay.setText("");
        // TODO add your handling code here:
    }//GEN-LAST:event_btnbudgetMouseExited

    private void btnothersMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnothersMouseEntered

        lbldisplay.setText("Others");
        // TODO add your handling code here:
    }//GEN-LAST:event_btnothersMouseEntered

    private void btnothersMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnothersMouseExited

        lbldisplay.setText("");
        // TODO add your handling code here:
    }//GEN-LAST:event_btnothersMouseExited

    private void btnmessagingMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnmessagingMouseEntered

        lbldisplay.setText("Messaging");
        // TODO add your handling code here:
    }//GEN-LAST:event_btnmessagingMouseEntered

    private void btnmessagingMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnmessagingMouseExited

        lbldisplay.setText("");
        // TODO add your handling code here:
    }//GEN-LAST:event_btnmessagingMouseExited

    private void btnstaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnstaffActionPerformed

        jDialogstaff.setVisible(true);
        jDialogstaff.setLocationRelativeTo(null);
        jDialogstaff.setTitle("Staff");

        // TODO add your handling code here:
    }//GEN-LAST:event_btnstaffActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        try {
            if(txtstaffname.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Please input the staff name","Error",JOptionPane.ERROR_MESSAGE);
            }else if(txtstaffid.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Please input the national ID","Error",JOptionPane.ERROR_MESSAGE);
            }else if(txtstaffmobile.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Please input the phone number","Error",JOptionPane.ERROR_MESSAGE);
            }else if(txtstaffemail.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Please input the correct email address","Error",JOptionPane.ERROR_MESSAGE);
            }else if(txtstaffage.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Please input the age of staff","Error",JOptionPane.ERROR_MESSAGE);
            }else if(txtstaffcourse.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Please input the Course title","Error",JOptionPane.ERROR_MESSAGE);
            }else if(txtstaffoccupation.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Please input the occupation of staff","Error",JOptionPane.ERROR_MESSAGE);
            }else{
            
                Statement stmt;
       stmt= conn.createStatement();
       String sql1="Select nationalID from Staff where nationalID= '" + txtstaffid.getText() + "'";
      rs=stmt.executeQuery(sql1);
      if(rs.next()){
        JOptionPane.showMessageDialog( null, "The person ID already exits","Error", JOptionPane.ERROR_MESSAGE);
        txtstaffid.setText("");
        txtstaffid.requestDefaultFocus();
       return;
      }
                
                
            String sql="insert into Staff(name,nationalID,mobile,email,gender,age,image,course,occupation) values(?,?,?,?,?,?,?,?,?)";
            pst=conn.prepareStatement(sql);
            pst.setString(1, txtstaffname.getText());
            pst.setString(2, txtstaffid.getText());
            pst.setString(3, txtstaffmobile.getText());
            pst.setString(4, txtstaffemail.getText());
            //radobuttons
             rbtnmalestaff.setActionCommand("Male");
            rbtnfemalestaff.setActionCommand("Female");
            pst.setString(5,staffbuttongroup.getSelection().getActionCommand());
            pst.setString(6, txtstaffage.getText());
            pst.setBytes(7, personimage);
             pst.setString(8, txtstaffcourse.getText());
            pst.setString(9, txtstaffoccupation.getText());
            
            pst.execute();
            
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }finally{
            try{
                rs.close();
                pst.close();
            }catch(Exception e){
                
            }
        }
          
        txtstaffname.setText("");
            txtstaffid.setText("");
          txtstaffmobile.setText("");
            txtstaffemail.setText("");
            staffbuttongroup.clearSelection();
             txtstaffage.setText("");
           
             txtstaffcourse.setText("");
            txtstaffoccupation.setText("");
            
            TableSTAFF();

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

         txtstaffname.setText("");
            txtstaffid.setText("");
          txtstaffmobile.setText("");
            txtstaffemail.setText("");
            staffbuttongroup.clearSelection();
             txtstaffage.setText("");
             txtstaffcourse.setText("");
            txtstaffoccupation.setText("");
            
         jButton1.setEnabled(true);

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

         if(txtstaffid.getText().equals("") && txtstaffname.getText().equals("") && txtstaffmobile.getText().equals("") && txtstaffemail.getText().equals("") && txtstaffage.getText().equals("") && txtstaffcourse.getText().equals("")  && txtstaffoccupation.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Please fisrt select details to be Deleted", "Error" ,JOptionPane.ERROR_MESSAGE);
            
        }else{
        
         int p=JOptionPane.showConfirmDialog(null, "Do you really want to permanently delete the details of  "+txtstaffname.getText(),"Delete",JOptionPane.YES_NO_OPTION);
        if(p==0){
        String sql="delete from staff where nationalID=?";
        
        try{
            
            pst=conn.prepareStatement(sql);
            
            pst.setString(1, txtstaffid.getText());
            
            pst.execute();
            
            JOptionPane.showMessageDialog(null, "Deleted");
            
        }catch(SQLException | HeadlessException e){
            
            JOptionPane.showMessageDialog(null, e);
            
        }finally{
            try{
                rs.close();
                pst.close();
            }catch(Exception e){
                
            }
        }
        }
         }
         txtstaffname.setText("");
            txtstaffid.setText("");
          txtstaffmobile.setText("");
            txtstaffemail.setText("");
            staffbuttongroup.clearSelection();
             txtstaffage.setText("");
            
             txtstaffcourse.setText("");
            txtstaffoccupation.setText("");
             jButton1.setEnabled(true);
        
        TableSTAFF();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void churchstafftableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_churchstafftableKeyReleased
  
        if(evt.getKeyCode()==KeyEvent.VK_DOWN|| evt.getKeyCode()==KeyEvent.VK_UP){
        
        int i=churchstafftable.getSelectedRow();
        TableModel model=churchstafftable.getModel();
        txtstaffname.setText(model.getValueAt(i, 0).toString());
        txtstaffid.setText(model.getValueAt(i, 1).toString());
        txtstaffmobile.setText(model.getValueAt(i, 2).toString());
        txtstaffemail.setText(model.getValueAt(i, 3).toString());
        String gender=model.getValueAt(i, 4).toString();
         if (gender.equalsIgnoreCase("Male")){
             rbtnmalestaff.setSelected(true);
         }else{
             rbtnfemalestaff.setSelected(true);
         }
         txtstaffage.setText(model.getValueAt(i, 5).toString());
        txtstaffcourse.setText(model.getValueAt(i, 6).toString());
        txtstaffoccupation.setText(model.getValueAt(i, 7).toString());
        }   
        jButton3.setEnabled(true);
        jButton4.setEnabled(true);
        jButton1.setEnabled(false);

        // TODO add your handling code here:
    }//GEN-LAST:event_churchstafftableKeyReleased

    private void churchstafftableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_churchstafftableMouseClicked

             
        int i=churchstafftable.getSelectedRow();
        TableModel model=churchstafftable.getModel();
        txtstaffname.setText(model.getValueAt(i, 0).toString());
        txtstaffid.setText(model.getValueAt(i, 1).toString());
        txtstaffmobile.setText(model.getValueAt(i, 2).toString());
        txtstaffemail.setText(model.getValueAt(i, 3).toString());
        String gender=model.getValueAt(i, 4).toString();
         if (gender.equalsIgnoreCase("Male")){
             rbtnmalestaff.setSelected(true);
         }else{
             rbtnfemalestaff.setSelected(true);
         }
         txtstaffage.setText(model.getValueAt(i, 5).toString());
        txtstaffcourse.setText(model.getValueAt(i, 6).toString());
        txtstaffoccupation.setText(model.getValueAt(i, 7).toString());
        
       jButton1.setEnabled(false);

        // TODO add your handling code here:
    }//GEN-LAST:event_churchstafftableMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

          if(txtstaffid.getText().equals("") && txtstaffname.getText().equals("") && txtstaffmobile.getText().equals("") && txtstaffemail.getText().equals("") && txtstaffage.getText().equals("") && txtstaffcourse.getText().equals("")  && txtstaffoccupation.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Please fisrt select details to be Updated", "Error" ,JOptionPane.ERROR_MESSAGE);
            
        }else{
        
        try{
            
            String value1=txtstaffname.getText();
            String value2=txtstaffid.getText();
            String value3=txtstaffmobile.getText();
            String value4=txtstaffemail.getText();
             rbtnmalestaff.setActionCommand("Male");
            rbtnfemalestaff.setActionCommand("Female");
            String value5=staffbuttongroup.getSelection().getActionCommand();
            String value6=txtstaffage.getText();
            String value7=txtstaffcourse.getText();
            String value8=txtstaffoccupation.getText();
            
            String sql="update staff set name='"+value1+"',nationalID='"+value2+"',mobile='"+value3+"',email='"+value4+"',gender='"+value5+"',age='"+value6+"',course='"+value7+"',occupation='"+value8+"'where nationalID='"+value2+"'";
            pst=conn.prepareStatement(sql);
            pst.execute();
            
            
        }catch(Exception e){
            
            JOptionPane.showMessageDialog(null, e);
            
        }finally{
            try{
                rs.close();
                pst.close();
            }catch(Exception e){
                
            }
        }
          }
        
         txtstaffname.setText("");
            txtstaffid.setText("");
          txtstaffmobile.setText("");
            txtstaffemail.setText("");
            staffbuttongroup.clearSelection();
             txtstaffage.setText("");
           
             txtstaffcourse.setText("");
            txtstaffoccupation.setText("");
             jButton1.setEnabled(true);
        
        TableSTAFF();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnmemberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmemberActionPerformed
       
        jDialogmember.setVisible(true);
        jDialogmember.setLocationRelativeTo(null);
        
    }//GEN-LAST:event_btnmemberActionPerformed

    private void churchmembertableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_churchmembertableMouseClicked

           try{
            int row=churchmembertable.getSelectedRow();
             String Table_Click=(churchmembertable.getModel().getValueAt(row, 1).toString());
            String sql="select image from member where nationalID='"+Table_Click+"'";
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
                byte[] imagedata=rs.getBytes("image");
                ImageIcon image=new ImageIcon(new ImageIcon(imagedata).getImage().getScaledInstance(lblmemberimage.getWidth(), lblmemberimage.getHeight(), lblmemberimage.getWidth()));
                lblmemberimage.setIcon(image);
               
            }
        }catch(Exception e){
        }finally{
            try{
                rs.close();
                pst.close();
            }catch(Exception e){
                
            }
        }
        
        
        int i=churchmembertable.getSelectedRow();
        TableModel model=churchmembertable.getModel();
        txtmembername.setText(model.getValueAt(i, 0).toString());
        txtmemberid.setText(model.getValueAt(i, 1).toString());
        txtmembermobile.setText(model.getValueAt(i, 2).toString());
        txtmemberemail.setText(model.getValueAt(i, 3).toString());
        String gender=model.getValueAt(i, 4).toString();
         if (gender.equalsIgnoreCase("Male")){
             rbtnmalemember.setSelected(true);
         }else{
             rbtnfemalemember.setSelected(true);
         }
         txtmemberage.setText(model.getValueAt(i, 5).toString());
        txtmembercourse.setText(model.getValueAt(i, 6).toString());
        txtmemberyearofstudy.setText(model.getValueAt(i, 7).toString());        

         jButton8.setEnabled(false); 
           
        // TODO add your handling code here:
    }//GEN-LAST:event_churchmembertableMouseClicked

    private void churchmembertableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_churchmembertableKeyReleased

        if(evt.getKeyCode()==KeyEvent.VK_DOWN|| evt.getKeyCode()==KeyEvent.VK_UP){
         try{
            int row=churchmembertable.getSelectedRow();
             String Table_Click=(churchmembertable.getModel().getValueAt(row, 1).toString());
            String sql="select image from member where nationalID='"+Table_Click+"'";
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
                byte[] imagedata=rs.getBytes("image");
                ImageIcon image=new ImageIcon(new ImageIcon(imagedata).getImage().getScaledInstance(lblmemberimage.getWidth(), lblmemberimage.getHeight(), lblmemberimage.getWidth()));
                lblmemberimage.setIcon(image);
               
            }
        }catch(Exception e){
        }finally{
            try{
                rs.close();
                pst.close();
            }catch(Exception e){
                
            }
        }
        
        
        int i=churchmembertable.getSelectedRow();
        TableModel model=churchmembertable.getModel();
        txtmembername.setText(model.getValueAt(i, 0).toString());
        txtmemberid.setText(model.getValueAt(i, 1).toString());
        txtmembermobile.setText(model.getValueAt(i, 2).toString());
        txtmemberemail.setText(model.getValueAt(i, 3).toString());
        String gender=model.getValueAt(i, 4).toString();
         if (gender.equalsIgnoreCase("Male")){
             rbtnmalemember.setSelected(true);
         }else{
             rbtnfemalemember.setSelected(true);
         }
         txtmemberage.setText(model.getValueAt(i, 5).toString());
        txtmembercourse.setText(model.getValueAt(i, 6).toString());
        txtmemberyearofstudy.setText(model.getValueAt(i, 7).toString());
        }   
       
                jButton8.setEnabled(false);
                jButton7.setEnabled(true);
                jButton5.setEnabled(true);
        // TODO add your handling code here:
                               
    }//GEN-LAST:event_churchmembertableKeyReleased

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

          if(txtmembername.getText().equals("") && txtmemberid.getText().equals("") && txtmembermobile.getText().equals("") && txtmemberemail.getText().equals("") && txtmemberage.getText().equals("") && txtmembercourse.getText().equals("")  && txtmemberyearofstudy.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Please fisrt select details to be Deleted", "Error" ,JOptionPane.ERROR_MESSAGE);
            
        }else{
        
         int p=JOptionPane.showConfirmDialog(null, "Do you really want to permanently delete the details of  "+txtmembername.getText(),"Delete",JOptionPane.YES_NO_OPTION);
        if(p==0){
        String sql="delete from member where nationalID=?";
        
        try{
            
            pst=conn.prepareStatement(sql);
            
            pst.setString(1, txtmemberid.getText());
            
            pst.execute();
            
            JOptionPane.showMessageDialog(null, "Deleted");
            
        }catch(SQLException | HeadlessException e){
            
            JOptionPane.showMessageDialog(null, e);
            
        }finally{
            try{
                rs.close();
                pst.close();
            }catch(Exception e){
                
            }
        }
        }
          }
         txtmembername.setText("");
            txtmemberid.setText("");
          txtmembermobile.setText("");
            txtmemberemail.setText("");
            buttonGroupmember.clearSelection();
             txtmemberage.setText("");
           lblmemberimage.setIcon(null);
             txtmembercourse.setText("");
            txtmemberyearofstudy.setText("");
            
             jButton8.setEnabled(true); 
        
             TableMEMBER();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

         txtmembername.setText("");
            txtmemberid.setText("");
          txtmembermobile.setText("");
            txtmemberemail.setText("");
            buttonGroupmember.clearSelection();
             txtmemberage.setText("");
            lblmemberimage.setIcon(null);
             txtmembercourse.setText("");
            txtmemberyearofstudy.setText("");

                 jButton8.setEnabled(true);           
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed

          if(txtmembername.getText().equals("") && txtmemberid.getText().equals("") && txtmembermobile.getText().equals("") && txtmemberemail.getText().equals("") && txtmemberage.getText().equals("") && txtmembercourse.getText().equals("")  && txtmemberyearofstudy.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Please fisrt select details to be Updated", "Error" ,JOptionPane.ERROR_MESSAGE);
            
        }else{
        
        try{
            
            String value1=txtmembername.getText();
            String value2=txtmemberid.getText();
            String value3=txtmembermobile.getText();
            String value4=txtmemberemail.getText();
             rbtnmalemember.setActionCommand("Male");
            rbtnfemalemember.setActionCommand("Female");
            String value5=buttonGroupmember.getSelection().getActionCommand();
            String value6=txtmemberage.getText();
            String value7=txtmembercourse.getText();
            String value8=txtmemberyearofstudy.getText();
            
            String sql="update member set name='"+value1+"',nationalID='"+value2+"',mobile='"+value3+"',email='"+value4+"',gender='"+value5+"',age='"+value6+"',course='"+value7+"',image=?,year='"+value8+"'where nationalID='"+value2+"'";
            pst=conn.prepareStatement(sql);
             pst.setBytes(1, personimage1);
            pst.execute();
            
            
        }catch(Exception e){
            
            JOptionPane.showMessageDialog(null, e);
            
        }finally{
            try{
                rs.close();
                pst.close();
            }catch(Exception e){
                
            }
        }
          }
        
         txtmembername.setText("");
            txtmemberid.setText("");
          txtmembermobile.setText("");
            txtmemberemail.setText("");
            buttonGroupmember.clearSelection();
             txtmemberage.setText("");
            lblmemberimage.setIcon(null);
             txtmembercourse.setText("");
            txtmemberyearofstudy.setText("");
        
            TableMEMBER();
            
             jButton8.setEnabled(true); 
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed

         try {
             if(txtmembername.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Please input the member name","Error",JOptionPane.ERROR_MESSAGE);
            }else if(txtmemberid.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Please input the national ID","Error",JOptionPane.ERROR_MESSAGE);
            }else if(txtmembermobile.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Please input the phone number","Error",JOptionPane.ERROR_MESSAGE);
            }else if(txtmemberemail.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Please input the correct email address","Error",JOptionPane.ERROR_MESSAGE);
            }else if(txtmemberage.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Please input the age of the new member","Error",JOptionPane.ERROR_MESSAGE);
            }else if(txtmembercourse.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Please input the Course title","Error",JOptionPane.ERROR_MESSAGE);
            }
         else if(txtmemberyearofstudy.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Please input the year of study","Error",JOptionPane.ERROR_MESSAGE);
            }else{
             
            String sql="insert into member(name,nationalID,mobile,email,gender,age,image,course,year) values(?,?,?,?,?,?,?,?,?)";
            pst=conn.prepareStatement(sql);
            pst.setString(1, txtmembername.getText());
            pst.setString(2, txtmemberid.getText());
            pst.setString(3, txtmembermobile.getText());
            pst.setString(4, txtmemberemail.getText());
            //radobuttons
             rbtnmalemember.setActionCommand("Male");
            rbtnfemalemember.setActionCommand("Female");
            pst.setString(5,buttonGroupmember.getSelection().getActionCommand());
            pst.setString(6, txtmemberage.getText());
            pst.setBytes(7, personimage1);
             pst.setString(8, txtmembercourse.getText());
            pst.setString(9, txtmemberyearofstudy.getText());
            
            pst.execute();
            
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }finally{
            try{
                rs.close();
                pst.close();
            }catch(Exception e){
                
            }
        }
          
        txtmembername.setText("");
            txtmemberid.setText("");
          txtmembermobile.setText("");
            txtmemberemail.setText("");
            buttonGroupmember.clearSelection();
             txtmemberage.setText("");
            lblmemberimage.setIcon(null);
             txtmembercourse.setText("");
            txtmemberyearofstudy.setText("");
            
             TableMEMBER();        
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void btnstaffimage1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnstaffimage1ActionPerformed

        JFileChooser chooser=new JFileChooser();
          chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
          chooser.setFileFilter(new FileNameExtensionFilter("png|jpg|bmp","png","jpg","bmp"));
          if(chooser.showOpenDialog(this)==JFileChooser.APPROVE_OPTION){
        
        File f=chooser.getSelectedFile();
        filename=f.getAbsolutePath();
        path1.setText(filename);

        ImageIcon image=new ImageIcon(new ImageIcon(filename).getImage().getScaledInstance( lblmemberimage.getWidth(),  lblmemberimage.getHeight(),  lblmemberimage.getWidth()));
        lblmemberimage.setIcon(image);
    }else{
            }
          
          //converting image to bytes
          try{
            File img=new File(filename);
            FileInputStream fis=new FileInputStream(img);
            ByteArrayOutputStream bos=new ByteArrayOutputStream();
            byte[] buf=new byte[1024];
            for(int readnum; (readnum=fis.read(buf)) !=-1;){
                bos.write(buf,0,readnum);
            }
            personimage1=bos.toByteArray();
        }catch(Exception e){
            //JOptionPane.showMessageDialog(null, e);
        }finally{
            try{
                rs.close();
                pst.close();
            }catch(Exception e){
                
            }
        }


        // TODO add your handling code here:
    }//GEN-LAST:event_btnstaffimage1ActionPerformed

    private void btncalendarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncalendarActionPerformed

           jDialogcalendar.setVisible(true);
           jDialogcalendar.setLocationRelativeTo(null);
        
        // TODO add your handling code here:
    }//GEN-LAST:event_btncalendarActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed

         if(txtcalendarevent.getText().equals("") && txtcalendardate.getDate()==null && txtcalendartime.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Please fisrt select details to be Deleted", "Error" ,JOptionPane.ERROR_MESSAGE);
            
        }else{
        
         int p=JOptionPane.showConfirmDialog(null, "Do you really want to delete this event?","Delete",JOptionPane.YES_NO_OPTION);
        if(p==0){
        String sql="delete from calendar where id=?";
        
        try{
            
            pst=conn.prepareStatement(sql);
            
            pst.setString(1, txtcalendaeridev.getText());
            
            pst.execute();
            
            JOptionPane.showMessageDialog(null, "Deleted");
            
        }catch(SQLException | HeadlessException e){
            
            JOptionPane.showMessageDialog(null, e);
            
        }finally{
            try{
                rs.close();
                pst.close();
            }catch(Exception e){
                
            }
        }
        }
         }
        
         ((JTextField)txtcalendardate.getDateEditor().getUiComponent()).setText("");
        txtcalendarevent.setText("");
        txtcalendartime.setText("");
        
        jButton12.setEnabled(true); 
        
        TablecalendarUpdate();

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed

         ((JTextField)txtcalendardate.getDateEditor().getUiComponent()).setText("");
        txtcalendarevent.setText("");
        txtcalendartime.setText("");
        
         jButton12.setEnabled(true);        

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed

        if(txtcalendarevent.getText().equals("") && txtcalendardate.getDate()==null && txtcalendartime.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Please fisrt select details to be updated", "Error" ,JOptionPane.ERROR_MESSAGE);
            
        }else{
        
         try{
            
            String value1=txtcalendaeridev.getText();
            String value2=txtcalendarevent.getText();
            String value3=txtcalendartime.getText();
            String value4=((JTextField)txtcalendardate.getDateEditor().getUiComponent()).getText();
            
            String sql="update calendar set id='"+value1+"',date='"+value4+"',time='"+value3+"',event='"+value2+"'where id='"+value1+"'";
            pst=conn.prepareStatement(sql);
            pst.execute();
            
            JOptionPane.showMessageDialog(null, "updated");
        }catch(SQLException | HeadlessException e){
            
            JOptionPane.showMessageDialog(null, e);
            
        }finally{
            try{
                rs.close();
                pst.close();
            }catch(Exception e){
                
            }
        }
        }
          ((JTextField)txtcalendardate.getDateEditor().getUiComponent()).setText("");
        txtcalendarevent.setText("");
        txtcalendartime.setText("");
        
        jButton12.setEnabled(true); 
         
         TablecalendarUpdate();

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed

        if(txtcalendarevent.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Please enter the name of the event","Error", JOptionPane.ERROR_MESSAGE);
            
        }
          else if(txtcalendardate.getDate()==null){
            JOptionPane.showMessageDialog(null, "Please enter the date of the event","Error", JOptionPane.ERROR_MESSAGE);
           
        }
          
        
         
         else if(txtcalendartime.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Please enter the time of the event","Error", JOptionPane.ERROR_MESSAGE);
           
        }
        else{
        
        try {
            
             String sql="Insert into calendar(event,date, time)values(?,?,?)";
            pst=conn.prepareStatement(sql);
            pst.setString(1, txtcalendarevent.getText());
            pst.setString(2, ((JTextField)txtcalendardate.getDateEditor().getUiComponent()).getText());
            pst.setString(3, txtcalendartime.getText());
            pst.execute();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }finally{
            try{
                rs.close();
                pst.close();
            }catch(Exception e){
                
            }
        }
        }
         ((JTextField)txtcalendardate.getDateEditor().getUiComponent()).setText("");
        txtcalendarevent.setText("");
        txtcalendartime.setText("");
        
        TablecalendarUpdate();

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton12ActionPerformed

    private void TableCalendarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TableCalendarKeyReleased

        if(evt.getKeyCode()==KeyEvent.VK_DOWN|| evt.getKeyCode()==KeyEvent.VK_UP){
            
            try{
            int row=TableCalendar.getSelectedRow();
            String Table_Click=(TableCalendar.getModel().getValueAt(row, 0).toString());
            String sql="select * from calendar where id='"+Table_Click+"'";
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            
            if(rs.next()){
                String add1=rs.getString("id");
                txtcalendaeridev.setText(add1);
                 String add2=rs.getString("event");
                txtcalendarevent.setText(add2);
               String add3=rs.getString("date");
                java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(add3);
                txtcalendardate.setDate(date);
                 String add4=rs.getString("time");
                txtcalendartime.setText(add4);
                
                
            }
            
        }catch(SQLException | ParseException e){
            JOptionPane.showMessageDialog(null, e);
        }finally{
            try{
                rs.close();
                pst.close();
            }catch(Exception e){
                
            }
        }
        }   
        
        // TODO add your handling code here:
    }//GEN-LAST:event_TableCalendarKeyReleased

    private void TableCalendarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableCalendarMouseClicked

           try{
            int row=TableCalendar.getSelectedRow();
            String Table_Click=(TableCalendar.getModel().getValueAt(row, 0).toString());
            String sql="select * from calendar where id='"+Table_Click+"'";
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            
            if(rs.next()){
                String add1=rs.getString("id");
                txtcalendaeridev.setText(add1);
                 String add2=rs.getString("event");
                txtcalendarevent.setText(add2);
               String add3=rs.getString("date");
                java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(add3);
                txtcalendardate.setDate(date);
                 String add4=rs.getString("time");
                txtcalendartime.setText(add4);
                
               
            }
            
        }catch(SQLException | ParseException e){
            JOptionPane.showMessageDialog(null, e);
        }finally{
            try{
                rs.close();
                pst.close();
            }catch(Exception e){
                
            }
        }
           jButton12.setEnabled(false); 
        
        // TODO add your handling code here:
    }//GEN-LAST:event_TableCalendarMouseClicked

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
         
           
          MessageFormat header=new MessageFormat("DKUT CHRISTIAN UNION CALENDAR");
          MessageFormat footer=new MessageFormat("Page{0,number,integer}");
          
       
       try{
           
           TableCalendar.print(JTable.PrintMode.NORMAL, header, footer);
           
       }catch(java.awt.print.PrinterException e){
           System.err.format("Cannot print %s%n", e.getMessage());
       }finally{
            try{
                rs.close();
                pst.close();
            }catch(Exception e){
                
            }
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed

         String val1=((JTextField)calendershowdate1.getDateEditor().getUiComponent()).getText();
    String val2=((JTextField)calendarshowdate2.getDateEditor().getUiComponent()).getText();
    try{
        if(calendershowdate1.getDate()==null){
            JOptionPane.showMessageDialog(null, "Please enter the first date field","Error",JOptionPane.ERROR_MESSAGE);
        }else if(calendarshowdate2.getDate()==null){
            JOptionPane.showMessageDialog(null, "Please enter the second date field","Error",JOptionPane.ERROR_MESSAGE);
        }else{
            String sql="select * from calendar where date between '"+val1+"' and '"+val2+"'";
            pst=conn.prepareStatement(sql);
          rs=pst.executeQuery();
          TableCalendar.setModel(DbUtils.resultSetToTableModel(rs));
        }
        }catch(HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }finally{
            try{
                rs.close();
                pst.close();
            }catch(Exception e){
                
            }
        }


        // TODO add your handling code here:
    }//GEN-LAST:event_jButton14ActionPerformed

    private void btntithingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntithingActionPerformed

        jDialogtithing.setVisible(true);
        jDialogtithing.setLocationRelativeTo(null);

        // TODO add your handling code here:
    }//GEN-LAST:event_btntithingActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed

       if(txttithname.getText().equals("") && txttithid.getText().equals("") && txttithdate.getDate()==null && txtamount.getText().equals("")){
           JOptionPane.showMessageDialog(null, "Nothing to be deleted!  \n Make sure to select the details to delete");
       }else{
        
        
          int p=JOptionPane.showConfirmDialog(null, "Do you really want to delete the details of "+txttithname.getText(),"Delete",JOptionPane.YES_NO_OPTION);
        if(p==0){
        String sql="delete from tithing where nationalid=?";
        
        try{
            
            pst=conn.prepareStatement(sql);
            
            pst.setString(1, txttithid.getText());
            
            pst.execute();
            
            JOptionPane.showMessageDialog(null, "Deleted");
            
        }catch(SQLException | HeadlessException e){
            
            JOptionPane.showMessageDialog(null, e);
            
        }finally{
            try{
                rs.close();
                pst.close();
            }catch(Exception e){
                
            }
        }
        }
       }
           TabletithingUpdate();
           totalintithe();
           
          ((JTextField)txttithdate.getDateEditor().getUiComponent()).setText("");
        txttithname.setText("");
        txttithid.setText("");
        txtamount.setText("");
        
         jButton18.setEnabled(true); 

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed

        ((JTextField)txttithdate.getDateEditor().getUiComponent()).setText("");
        txttithname.setText("");
        txttithid.setText("");
        txtamount.setText("");
         
        jButton18.setEnabled(true); 
      

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed

           if(txttithname.getText().equals("") && txttithid.getText().equals("") && txttithdate.getDate()==null && txtamount.getText().equals("")){
           JOptionPane.showMessageDialog(null, "Please first Select details to be Updated");
       }else{
        
         try{
            
            String value1=txttithname.getText();
            String value2=txttithid.getText();
            String value3=txtamount.getText();
            String value4=((JTextField)txttithdate.getDateEditor().getUiComponent()).getText();
            
            String sql="update tithing set name='"+value1+"',nationalid='"+value2+"',amount='"+value3+"',date='"+value4+"'where nationalid='"+value2+"'";
            pst=conn.prepareStatement(sql);
            pst.execute();
            
            JOptionPane.showMessageDialog(null, "updated");
        }catch(SQLException | HeadlessException e){
            
            JOptionPane.showMessageDialog(null, e);
            
        }finally{
            try{
                rs.close();
                pst.close();
            }catch(Exception e){
                
            }
        }
           }
             TabletithingUpdate();
             totalintithe();
             
             ((JTextField)txttithdate.getDateEditor().getUiComponent()).setText("");
        txttithname.setText("");
        txttithid.setText("");
        txtamount.setText("");
         jButton18.setEnabled(true); 
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed

        try {
            
       
               if(txttithname.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Please enter name","Error", JOptionPane.ERROR_MESSAGE);
            
        }
                 
         else if(txttithid.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Please enter the national ID. ","Error", JOptionPane.ERROR_MESSAGE);
           
        }
         
          else if(txttithdate.getDate()==null){
            JOptionPane.showMessageDialog(null, "Please enter the date","Error", JOptionPane.ERROR_MESSAGE);
           
        }
          
        
         
         else if(txtamount.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Please enter the amount","Error", JOptionPane.ERROR_MESSAGE);
           
        }else{
               
         
       
            Statement stmt;
       stmt= conn.createStatement();
       String sql1="Select nationalid from tithing where nationalid= '" + txttithid.getText() + "'";
      rs=stmt.executeQuery(sql1);
             if(rs.next()){
        JOptionPane.showMessageDialog( null, "The national identinty already exists","Error", JOptionPane.ERROR_MESSAGE);
        txttithid.setText("");
        txttithid.requestDefaultFocus();
       return;
      }
              SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
            String dt = format1.format(txttithdate.getDate());
            String sql= "insert into tithing(name,nationalid,date,amount)values('"+ txttithname.getText() + "','"+ txttithid.getText() + "','"+ dt+ "','"+ txtamount.getText() + "')";

            pst=conn.prepareStatement(sql);
            pst.execute();
         }
        } catch (HeadlessException | SQLException e) {
        }finally{
            try{
                rs.close();
                pst.close();
            }catch(Exception e){
                
            }
        }
     
            TabletithingUpdate();
            totalintithe();
          
            ((JTextField)txttithdate.getDateEditor().getUiComponent()).setText("");
        txttithname.setText("");
        txttithid.setText("");
        txtamount.setText("");
         
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton18ActionPerformed

    private void tithingtableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tithingtableKeyReleased

         if(evt.getKeyCode()==KeyEvent.VK_DOWN|| evt.getKeyCode()==KeyEvent.VK_UP){
            
            try{
            int row=tithingtable.getSelectedRow();
            String Table_Click=(tithingtable.getModel().getValueAt(row, 1).toString());
            String sql="select * from tithing where nationalid='"+Table_Click+"'";
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            
            if(rs.next()){
                String add1=rs.getString("name");
                txttithname.setText(add1);
                 String add2=rs.getString("nationalid");
                txttithid.setText(add2);
               String add3=rs.getString("date");
                java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(add3);
                txttithdate.setDate(date);
                 String add4=rs.getString("amount");
                txtamount.setText(add4);
                
                
            }
            
        }catch(SQLException | ParseException e){
            JOptionPane.showMessageDialog(null, e);
        }finally{
            try{
                rs.close();
                pst.close();
            }catch(Exception e){
                
            }
        }
        }   
        
        // TODO add your handling code here:
    }//GEN-LAST:event_tithingtableKeyReleased

    private void tithingtableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tithingtableMouseClicked

        try{
            int row=tithingtable.getSelectedRow();
            String Table_Click=(tithingtable.getModel().getValueAt(row, 1).toString());
            String sql="select * from tithing where nationalid='"+Table_Click+"'";
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            
            if(rs.next()){
                String add1=rs.getString("name");
                txttithname.setText(add1);
                 String add2=rs.getString("nationalid");
                txttithid.setText(add2);
               String add3=rs.getString("date");
                java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(add3);
                txttithdate.setDate(date);
                 String add4=rs.getString("amount");
                txtamount.setText(add4);
                
                
            }
            
        }catch(SQLException | ParseException e){
            JOptionPane.showMessageDialog(null, e);
        }finally{
            try{
                rs.close();
                pst.close();
            }catch(Exception e){
                
            }
        }
        
        jButton17.setEnabled(true);
        jButton15.setEnabled(true);
        
         jButton18.setEnabled(false); 
        
        // TODO add your handling code here:
    }//GEN-LAST:event_tithingtableMouseClicked

    private void btnbudgetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbudgetActionPerformed

        jDialogbudget.setVisible(true);
        jDialogbudget.setLocationRelativeTo(null);

        // TODO add your handling code here:
    }//GEN-LAST:event_btnbudgetActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
      
        if(txtbudgetid.getText().equals("") && txtbudgetactivity.getText().equals("") && txtbudgetamount.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Select first the details to be deleted!", "Error", JOptionPane.ERROR_MESSAGE);
        }else{
        
          int p=JOptionPane.showConfirmDialog(null, "Do you really want to delete this permanently ?","Delete",JOptionPane.YES_NO_OPTION);
        if(p==0){
        String sql="delete from budget where budgetid=?";
        
        try{
            
            pst=conn.prepareStatement(sql);
            
            pst.setString(1, txtbudgetid.getText());
            
            pst.execute();
            
            JOptionPane.showMessageDialog(null, "Deleted");
            
        }catch(SQLException | HeadlessException e){
            
            JOptionPane.showMessageDialog(null, e);
            
        }finally{
            try{
                rs.close();
                pst.close();
            }catch(Exception e){
                
            }
        }
        }
        
        }TablebudgetUpdate();
         totalbudgeted();
         
         txtbudgetid.setText("");
        txtbudgetactivity.setText("");
        txtbudgetamount.setText("");
        
        jButton22.setEnabled(true);

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed

        txtbudgetid.setText("");
        txtbudgetactivity.setText("");
        txtbudgetamount.setText("");
        
         jButton22.setEnabled(true); 
        
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton20ActionPerformed


    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed

        if(txtbudgetid.getText().equals("") && txtbudgetactivity.getText().equals("") && txtbudgetamount.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Select first the details to be Updated!", "Error", JOptionPane.ERROR_MESSAGE);
        }else{
        
             try{
            
            String value1=txtbudgetid.getText();
            String value2=txtbudgetactivity.getText();
            String value3=txtbudgetamount.getText();
            
            
            String sql="update budget set budgetid='"+value1+"',activity='"+value2+"',amount='"+value3+"'where budgetid='"+value1+"'";
            pst=conn.prepareStatement(sql);
            pst.execute();
            
            JOptionPane.showMessageDialog(null, "updated");
        }catch(SQLException | HeadlessException e){
            
            JOptionPane.showMessageDialog(null, e);
            
        }finally{
            try{
                rs.close();
                pst.close();
            }catch(Exception e){
                
            }
        }
        }
             TablebudgetUpdate();
              totalbudgeted();
              
        txtbudgetid.setText("");
        txtbudgetactivity.setText("");
        txtbudgetamount.setText("");
        
         jButton22.setEnabled(true); 

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed

        try {
            
             if(txtbudgetid.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Please enter budget ID","Error", JOptionPane.ERROR_MESSAGE);
            
        }
                 
         else if(txtbudgetactivity.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Please enter the activity. ","Error", JOptionPane.ERROR_MESSAGE);
           
        }
         
          
         else if(txtbudgetamount.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Please enter the amount of money allocated","Error", JOptionPane.ERROR_MESSAGE);
           
        }
               String aloc=String.valueOf(txtbudgetamount.getText()); 
       String available=String.valueOf(txtvalidate.getText()); 
       if((Integer.parseInt(aloc))>Integer.parseInt(available)){
           JOptionPane.showMessageDialog(null,"The budget you are making is more than the available money! Ksh:"+txtvalidate.getText());
       }else{
             
       Statement stmt;
       stmt= conn.createStatement();
       String sql1="Select budgetid from budget where budgetid= '" + txtbudgetid.getText() + "'";
      rs=stmt.executeQuery(sql1);
             if(rs.next()){
        JOptionPane.showMessageDialog( null, "The activity name already exists","Error", JOptionPane.ERROR_MESSAGE);
        txtbudgetid.setText("");
        txtbudgetid.requestDefaultFocus();
       return;
      }
              
            String sql= "insert into budget(budgetid,activity,amount)values('"+ txtbudgetid.getText() + "','"+ txtbudgetactivity.getText() + "','"+ txtbudgetamount.getText() + "')";

            pst=conn.prepareStatement(sql);
            pst.execute();
             
       }
            
        } catch (HeadlessException | NumberFormatException | SQLException e) {
          //  JOptionPane.showMessageDialog(null, e);
        }finally{
            try{
                rs.close();
                pst.close();
            }catch(Exception e){
                
            }
        }
        TablebudgetUpdate();
         totalbudgeted();
         txtbudgetid.setText("");
        txtbudgetactivity.setText("");
        txtbudgetamount.setText("");
        
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton22ActionPerformed

    private void txtstaffmobileKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtstaffmobileKeyTyped

         char c=evt.getKeyChar();
      if (!(Character.isDigit(c)|| (c== KeyEvent.VK_BACK_SPACE)||(c==KeyEvent.VK_DELETE))){
          getToolkit().beep();
          evt.consume();
      }

        // TODO add your handling code here:
    }//GEN-LAST:event_txtstaffmobileKeyTyped

    private void txtstaffidKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtstaffidKeyTyped

       char c=evt.getKeyChar();
       if (!(Character.isDigit(c)|| (c== KeyEvent.VK_BACK_SPACE)||(c==KeyEvent.VK_DELETE))){
          getToolkit().beep();
          evt.consume();
      }
      
     

        // TODO add your handling code here:
    }//GEN-LAST:event_txtstaffidKeyTyped

    private void txtstaffageKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtstaffageKeyTyped

         char c=evt.getKeyChar();
      if (!(Character.isDigit(c)|| (c== KeyEvent.VK_BACK_SPACE)||(c==KeyEvent.VK_DELETE))){
          getToolkit().beep();
          evt.consume();
      }

        // TODO add your handling code here:
    }//GEN-LAST:event_txtstaffageKeyTyped

    private void txtmemberidKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmemberidKeyTyped

         char c=evt.getKeyChar();
      if (!(Character.isDigit(c)|| (c== KeyEvent.VK_BACK_SPACE)||(c==KeyEvent.VK_DELETE))){
          getToolkit().beep();
          evt.consume();
      }
        
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmemberidKeyTyped

    private void txtmembermobileKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmembermobileKeyTyped

         char c=evt.getKeyChar();
      if (!(Character.isDigit(c)|| (c== KeyEvent.VK_BACK_SPACE)||(c==KeyEvent.VK_DELETE))){
          getToolkit().beep();
          evt.consume();
      }

        // TODO add your handling code here:
    }//GEN-LAST:event_txtmembermobileKeyTyped

    private void txtmemberageKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmemberageKeyTyped

         char c=evt.getKeyChar();
      if (!(Character.isDigit(c)|| (c== KeyEvent.VK_BACK_SPACE)||(c==KeyEvent.VK_DELETE))){
          getToolkit().beep();
          evt.consume();
      }
        
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmemberageKeyTyped

    private void txtamountKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtamountKeyTyped

         char c=evt.getKeyChar();
      if (!(Character.isDigit(c)|| (c== KeyEvent.VK_BACK_SPACE)||(c==KeyEvent.VK_DELETE))){
          getToolkit().beep();
          evt.consume();
      }
        
        // TODO add your handling code here:
    }//GEN-LAST:event_txtamountKeyTyped

    private void txtbudgetamountKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbudgetamountKeyTyped

         char c=evt.getKeyChar();
      if (!(Character.isDigit(c)|| (c== KeyEvent.VK_BACK_SPACE)||(c==KeyEvent.VK_DELETE))){
          getToolkit().beep();
          evt.consume();
      }
        
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbudgetamountKeyTyped

    private void jTablebudgetKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTablebudgetKeyReleased

         if(evt.getKeyCode()==KeyEvent.VK_DOWN|| evt.getKeyCode()==KeyEvent.VK_UP){
            
            try{
            int row=jTablebudget.getSelectedRow();
            String Table_Click=(jTablebudget.getModel().getValueAt(row, 0).toString());
            String sql="select * from budget where budgetid='"+Table_Click+"'";
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            
            if(rs.next()){
                String add1=rs.getString("budgetid");
                txtbudgetid.setText(add1);
                 String add2=rs.getString("activity");
                txtbudgetactivity.setText(add2);
               
                 String add3=rs.getString("amount");
                txtbudgetamount.setText(add3);
                
                
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }finally{
            try{
                rs.close();
                pst.close();
            }catch(Exception e){
                
            }
        }
        } 
        
        // TODO add your handling code here:
    }//GEN-LAST:event_jTablebudgetKeyReleased

    private void jTablebudgetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablebudgetMouseClicked

           try{
            int row=jTablebudget.getSelectedRow();
            String Table_Click=(jTablebudget.getModel().getValueAt(row, 0).toString());
            String sql="select * from budget where budgetid='"+Table_Click+"'";
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            
            if(rs.next()){
                String add1=rs.getString("budgetid");
                txtbudgetid.setText(add1);
                 String add2=rs.getString("activity");
                txtbudgetactivity.setText(add2);
               
                 String add3=rs.getString("amount");
                txtbudgetamount.setText(add3);
                
                
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }finally{
            try{
                rs.close();
                pst.close();
            }catch(Exception e){
                
            }
        }
           jButton22.setEnabled(false);
          
        
        // TODO add your handling code here:
    }//GEN-LAST:event_jTablebudgetMouseClicked

    private void btnothersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnothersActionPerformed

        jDialogothers.setVisible(true);
        jDialogothers.setLocationRelativeTo(null);

        // TODO add your handling code here:
    }//GEN-LAST:event_btnothersActionPerformed

    private void btnmessagingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmessagingActionPerformed

        jDialogmessaging.setVisible(true);
        jDialogmessaging.setLocationRelativeTo(null);

        // TODO add your handling code here:
    }//GEN-LAST:event_btnmessagingActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed

        jPanelEMAIL.setVisible(true);
         jPanelSMS.setVisible(false);
      /*  //remome panel
        jPanelMAIN.removeAll();
        jPanelMAIN.repaint();
        jPanelMAIN.revalidate();
        
        //add panel
        jPanelMAIN.add(jPanelEMAIL);
        jPanelMAIN.repaint();
        jPanelMAIN.revalidate();
*/
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed

        jPanelSMS.setVisible(true);
        jPanelEMAIL.setVisible(false);
       /* //remome panel
        jPanelMAIN.removeAll();
        jPanelMAIN.repaint();
        jPanelMAIN.revalidate();
        
        //add panel
        jPanelMAIN.add(jPanelSMS);
        jPanelMAIN.repaint();
        jPanelMAIN.revalidate();

         */
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton24ActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed

        try {
            if(txtsmsuser.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Please input user name correctly","ERROR",JOptionPane.ERROR_MESSAGE);
            }else if(txtsmspass.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Please input your password correctly","ERROR",JOptionPane.ERROR_MESSAGE);
            }else if(txtsmsrecipient.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Please input the recipient","ERROR",JOptionPane.ERROR_MESSAGE);
            }else if(txtsmscompose.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Please input the message to be sent","ERROR",JOptionPane.ERROR_MESSAGE);
            }
            else{
        SMS smstut=new SMS();
        smstut.SendSMS(txtsmsuser.getText(), txtsmspass.getText(), txtsmscompose.getText(), "254"+txtsmsrecipient.getText(), "https://bulksms.vsms.net/eapi/submission/send_sms/2/2.0");
        
        
            }
            JOptionPane.showMessageDialog(null, "Message sent");
            } catch (Exception e) {
                
        }finally{
            try{
                rs.close();
                pst.close();
            }catch(Exception e){
                
            }
        }
        
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton25ActionPerformed

    private void TXTEMAILFROMKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTEMAILFROMKeyTyped

         
        
        // TODO add your handling code here:
    }//GEN-LAST:event_TXTEMAILFROMKeyTyped

    private void TXTEMAILTOKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTEMAILTOKeyTyped

      
        
        // TODO add your handling code here:
    }//GEN-LAST:event_TXTEMAILTOKeyTyped

    private void txtsmsrecipientKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsmsrecipientKeyTyped

           char c=evt.getKeyChar();
      if (!(Character.isDigit(c)|| (c== KeyEvent.VK_BACK_SPACE)||(c==KeyEvent.VK_DELETE))){
          getToolkit().beep();
          evt.consume();
      }
        
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsmsrecipientKeyTyped

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed

        try {
           
         JFileChooser chooser=new JFileChooser();
        chooser.showOpenDialog(null);
        File f=chooser.getSelectedFile();
        attachmentpath=f.getAbsolutePath();
        emailpath.setText(attachmentpath);
               } catch (Exception e) {
        }finally{
            try{
                rs.close();
                pst.close();
            }catch(Exception e){
                
            }
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton26ActionPerformed

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed

        
        if(TXTEMAILFROM.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Please input the sender's Email");
        }else  if(TXTEMAILPASSWORD.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Please input the sender's Password");
        }else  if(TXTEMAILTO.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Please input the recipient's Email");
        }else  if(TXTEMAILSUBJECT.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Please input the subject of the email");
        }else  if(TXTEMAILCOMPOSE.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Please input the message to be sent");
        }else{
        
        
           String From=TXTEMAILFROM.getText();
         String PAS=TXTEMAILPASSWORD.getText();
        String To=TXTEMAILTO.getText();
        String Subject=TXTEMAILSUBJECT.getText();
        String Email=TXTEMAILCOMPOSE.getText();
        
        
        
        Properties props=new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
         props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
         props.put("mail.smtp.port", "465");
         Session ses=Session.getDefaultInstance(props,
         new javax.mail.Authenticator(){
          protected PasswordAuthentication getPasswordAuthentication(){
           return new PasswordAuthentication(From,PAS);
          }
    }
         );
         
         try {
             
             Message mes=new MimeMessage(ses);
             mes.setFrom(new InternetAddress(From));
             mes.setRecipients(Message.RecipientType.TO, InternetAddress.parse(To));//e.g. "amosrono174@gmail.com"
             mes.setSubject(Subject);
             //mes.setText("and this is the message");
             
             MimeBodyPart mesbodypart=new MimeBodyPart();
             mesbodypart.setText(Email);
             Multipart mul= new MimeMultipart();
             mul.addBodyPart(mesbodypart);
             
             //ATTACHMENT OF FILE
             mesbodypart=new MimeBodyPart();
             DataSource source=new FileDataSource(attachmentpath);
              mesbodypart.setDataHandler(new DataHandler(source));
               mesbodypart.setFileName(attachmentname.getText());
               mul.addBodyPart(mesbodypart);
               mes.setContent(mul);
             
              // JOptionPane.showMessageDialog(null, "Sending...");
               
             Transport.send(mes);
            JOptionPane.showMessageDialog(null, "Message sent");
        } catch (MessagingException | HeadlessException e) {
             JOptionPane.showMessageDialog(null, "NO internet!\n Make sure you are connected to the internet");
             
        }finally{
            try{
                rs.close();
                pst.close();
            }catch(Exception e){
                
            }
        }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton27ActionPerformed

    private void txtfilterKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfilterKeyReleased

        
          DefaultTableModel table=(DefaultTableModel)churchstafftable.getModel();
        String search=txtfilter.getText().toLowerCase();
        TableRowSorter<DefaultTableModel> tr=new TableRowSorter<DefaultTableModel>(table);
        churchstafftable.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(search));
        
        // TODO add your handling code here:
    }//GEN-LAST:event_txtfilterKeyReleased

    private void txtstaffemailMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtstaffemailMouseExited

         if(!txtstaffemail.getText().isEmpty())
        {
          String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
      String email1 = txtstaffemail.getText();
      Boolean b = email1.matches(EMAIL_REGEX);
      if(b == true)
      {
         txt_checked_email.setText(email1);
      }
      else
      {
         JOptionPane.showMessageDialog(null,"Invalid Email"); 
         System.out.println("is e-mail: "+email1+" :Valid = " + b);
          txtstaffemail.setText("");
          txt_checked_email.setText("");
      }
        } else
        {
        
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_txtstaffemailMouseExited

    private void txtmemberemailMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtmemberemailMouseExited

         if(!txtmemberemail.getText().isEmpty())
        {
          String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
      String email1 = txtmemberemail.getText();
      Boolean b = email1.matches(EMAIL_REGEX);
      if(b == true)
      {
         txt_checked_email.setText(email1);
      }
      else
      {
         JOptionPane.showMessageDialog(null,"Invalid Email"); 
         System.out.println("is e-mail: "+email1+" :Valid = " + b);
          txtmemberemail.setText("");
          txt_checked_email.setText("");
      }
        } else
        {
        
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_txtmemberemailMouseExited

    private void TXTEMAILFROMMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TXTEMAILFROMMouseExited

         if(!TXTEMAILFROM.getText().isEmpty())
        {
          String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
      String email1 = TXTEMAILFROM.getText();
      Boolean b = email1.matches(EMAIL_REGEX);
      if(b == true)
      {
         txt_checked_email.setText(email1);
      }
      else
      {
         JOptionPane.showMessageDialog(null,"Invalid Email"); 
         System.out.println("is e-mail: "+email1+" :Valid = " + b);
          TXTEMAILFROM.setText("");
          txt_checked_email.setText("");
      }
        } else
        {
        
        }
        
        // TODO add your handling code here:
    }//GEN-LAST:event_TXTEMAILFROMMouseExited

    private void TXTEMAILTOMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TXTEMAILTOMouseExited

                if(!TXTEMAILTO.getText().isEmpty())
        {
          String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
      String email1 = TXTEMAILTO.getText();
      Boolean b = email1.matches(EMAIL_REGEX);
      if(b == true)
      {
         txt_checked_email.setText(email1);
      }
      else
      {
         JOptionPane.showMessageDialog(null,"Invalid Email"); 
         System.out.println("is e-mail: "+email1+" :Valid = " + b);
          TXTEMAILTO.setText("");
          txt_checked_email.setText("");
      }
        } else
        {
        
        }
        
        // TODO add your handling code here:
    }//GEN-LAST:event_TXTEMAILTOMouseExited

    private void txtstaffidMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtstaffidMouseExited
        
        
        // TODO add your handling code here:
    }//GEN-LAST:event_txtstaffidMouseExited

    private void txtstaffidKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtstaffidKeyReleased

        
        // TODO add your handling code here:
    }//GEN-LAST:event_txtstaffidKeyReleased

    private void txtstaffidKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtstaffidKeyPressed

       

        // TODO add your handling code here:
    }//GEN-LAST:event_txtstaffidKeyPressed

    private void txtstaffidFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtstaffidFocusLost

          //validation
       try {
          Statement stmt;
       stmt= conn.createStatement();
       String sql1="Select * from member where nationalID= '" + txtstaffid.getText() + "'";
      rs=stmt.executeQuery(sql1);
             if(!rs.next()){
        JOptionPane.showMessageDialog( null, "This National ID does not belong to any member of DKUT Christian Union\n Register first as a member to qualify Election","Error", JOptionPane.ERROR_MESSAGE);
        txtstaffid.setText("");
        txtstaffid.requestDefaultFocus();
      }else{
             String add1=rs.getString("name");
                txtstaffname.setText(add1);
                String add2=rs.getString("mobile");
                txtstaffmobile.setText(add2);
                String add3=rs.getString("email");
                txtstaffemail.setText(add3);
                String gender=rs.getString("gender");
                if (gender.equalsIgnoreCase("Male")){
             rbtnmalestaff.setSelected(true);
         }else{
             rbtnfemalestaff.setSelected(true);
         }
                String add4=rs.getString("age");
                txtstaffage.setText(add4);  
                 String add5=rs.getString("course");
                txtstaffcourse.setText(add5);
                 
                              
             }
              } catch (SQLException | HeadlessException e) {
        }finally{
            try{
                rs.close();
                pst.close();
            }catch(Exception e){
                
            }
        }
        
        
        // TODO add your handling code here:
    }//GEN-LAST:event_txtstaffidFocusLost

    private void txtmemberidFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtmemberidFocusLost
        try {
          
         Statement stmt;
       stmt= conn.createStatement();
       String sql1="Select nationalID from member where nationalID= '" + txtmemberid.getText() + "'";
      rs=stmt.executeQuery(sql1);
      if(rs.next()){
        JOptionPane.showMessageDialog( null, "The person ID already exits","Error", JOptionPane.ERROR_MESSAGE);
        txtmemberid.setText("");
        txtmemberid.requestDefaultFocus();
      }
       } catch (SQLException | HeadlessException e) {
        }finally{
            try{
                rs.close();
                pst.close();
            }catch(Exception e){
                
            }
        }
        
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmemberidFocusLost

    private void txtbudgetamountFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtbudgetamountFocusLost

     
       
        
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbudgetamountFocusLost

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed

        if(txtothersdate.getDate()==null && txtothersincome.getText().equals("") && txtothersamount.getText().equals("")){
            JOptionPane.showMessageDialog(null, "you have not selected details to delete!","Error",JOptionPane.ERROR_MESSAGE);
        }else{
        
         int p=JOptionPane.showConfirmDialog(null, "Do you really want to delete this permantly?","Delete",JOptionPane.YES_NO_OPTION);
        if(p==0){
        String sql="delete from Others where id=?";
        
        try{
            
            pst=conn.prepareStatement(sql);
            
            pst.setString(1, txtothersid.getText());
            
            pst.execute();
            
            JOptionPane.showMessageDialog(null, "Deleted");
            
        }catch(SQLException | HeadlessException e){
            
            JOptionPane.showMessageDialog(null, e);
            
        }finally{
            try{
                rs.close();
                pst.close();
            }catch(Exception e){
                
            }
        }
        } TableOthersUpdate();
        totalothers();
        
        ((JTextField)txtothersdate.getDateEditor().getUiComponent()).setText("");
        txtothersamount.setText("");
        txtothersincome.setText("");
        
         jButton31.setEnabled(true);
         jButton30.setEnabled(false);

        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton28ActionPerformed

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed

        ((JTextField)txtothersdate.getDateEditor().getUiComponent()).setText("");
        txtothersamount.setText("");
        txtothersincome.setText("");
        
       jButton31.setEnabled(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton29ActionPerformed

    private void jButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton30ActionPerformed

         if(txtothersdate.getDate()==null && txtothersincome.getText().equals("") && txtothersamount.getText().equals("")){
            JOptionPane.showMessageDialog(null, "you have not selected details to Update!","Error",JOptionPane.ERROR_MESSAGE);
        }else{
        
         try{
            
            String value1=txtothersid.getText();
            String value2=((JTextField)txtothersdate.getDateEditor().getUiComponent()).getText();
            String value3=txtothersincome.getText();
            String value4=txtothersamount.getText();
            
            
            String sql="update Others set id='"+value1+"',date='"+value2+"',income='"+value3+"',amount='"+value4+"'where id='"+value1+"'";
            pst=conn.prepareStatement(sql);
            pst.execute();
            
            JOptionPane.showMessageDialog(null, "updated");
        }catch(SQLException | HeadlessException e){
            
            JOptionPane.showMessageDialog(null, e);
            
        }finally{
            try{
                rs.close();
                pst.close();
            }catch(Exception e){
                
            }
        } 
         
         TableOthersUpdate();
        totalothers();
          ((JTextField)txtothersdate.getDateEditor().getUiComponent()).setText("");
        txtothersamount.setText("");
        txtothersincome.setText("");
         
        jButton31.setEnabled(true);
        jButton28.setEnabled(false);
       

         }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton30ActionPerformed

    private void jButton31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton31ActionPerformed
 
        
         if(txtothersdate.getDate()==null){
            JOptionPane.showMessageDialog(null, "Please enter the date field","Error", JOptionPane.ERROR_MESSAGE);
           
        }
         else if(txtothersincome.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Please enter the type of income field","Error", JOptionPane.ERROR_MESSAGE);
            
        }
          
         else if(txtothersamount.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Please enter the amount collected","Error", JOptionPane.ERROR_MESSAGE);
           
        }
        else{
        
        try {
            
             String sql="Insert into Others(date,income,amount)values(?,?,?)";
            pst=conn.prepareStatement(sql);
             pst.setString(1, ((JTextField)txtothersdate.getDateEditor().getUiComponent()).getText());
            pst.setString(2, txtothersincome.getText());
            pst.setString(3, txtothersamount.getText());
            pst.execute();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }finally{
            try{
                rs.close();
                pst.close();
            }catch(Exception e){
                
            }
        }
        } TableOthersUpdate();
        totalothers();
        
         ((JTextField)txtothersdate.getDateEditor().getUiComponent()).setText("");
        txtothersamount.setText("");
        txtothersincome.setText("");
                
        

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton31ActionPerformed

    private void otherstableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_otherstableKeyReleased

         if(evt.getKeyCode()==KeyEvent.VK_DOWN|| evt.getKeyCode()==KeyEvent.VK_UP){
            
            try{
            int row=otherstable.getSelectedRow();
            String Table_Click=(otherstable.getModel().getValueAt(row, 0).toString());
            String sql="select * from Others where id='"+Table_Click+"'";
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            
            if(rs.next()){
                String add1=rs.getString("id");
                txtothersid.setText(add1);
                 String add3=rs.getString("date");
                java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(add3);
               txtothersdate.setDate(date);
                 String add2=rs.getString("income");
                txtothersincome.setText(add2);
              
                 String add4=rs.getString("amount");
                txtothersamount.setText(add4);
                
                
            }
            
        }catch(SQLException | ParseException e){
            JOptionPane.showMessageDialog(null, e);
        }finally{
            try{
                rs.close();
                pst.close();
            }catch(Exception e){
                
            }
        }
        }   

        // TODO add your handling code here:
    }//GEN-LAST:event_otherstableKeyReleased

    private void otherstableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_otherstableMouseClicked

         try{
            int row=otherstable.getSelectedRow();
            String Table_Click=(otherstable.getModel().getValueAt(row, 0).toString());
            String sql="select * from Others where id='"+Table_Click+"'";
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            
            if(rs.next()){
                String add1=rs.getString("id");
                txtothersid.setText(add1);
                 String add3=rs.getString("date");
                java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(add3);
               txtothersdate.setDate(date);
                 String add2=rs.getString("income");
                txtothersincome.setText(add2);
              
                 String add4=rs.getString("amount");
                txtothersamount.setText(add4);
                
                
            }
            
        }catch(SQLException | ParseException e){
            JOptionPane.showMessageDialog(null, e);
        }finally{
            try{
                rs.close();
                pst.close();
            }catch(Exception e){
                
            }
        }
         jButton31.setEnabled(false);
         
        // TODO add your handling code here:
    }//GEN-LAST:event_otherstableMouseClicked

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed

       UserRegistration reg=new UserRegistration();
              reg.setVisible(true);
              this.dispose();
        
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed

        changepassword change=new changepassword();
        change.show();
        this.hide();
        

        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed

        login ln =new login();
        ln.show();
        this.hide();
        

        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed

        System.exit(0);

        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jButton32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton32ActionPerformed
  try{
            String query="select activity, amount from budget";
            JDBCCategoryDataset dataset=new JDBCCategoryDataset(conn=Javaconnect.ConnectDB(),query);
            JFreeChart chart=ChartFactory.createBarChart("Expenditure Chart",  "Activity","Expenditure", dataset, PlotOrientation.VERTICAL,false,true,true);
            
            BarRenderer renderer=null;
            CategoryPlot plot=chart.getCategoryPlot();
            plot.setRangeGridlinePaint(Color.red);
            CategoryAxis xAxis=(CategoryAxis)plot.getDomainAxis();
            xAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45 );
            
            renderer=new BarRenderer();
            ChartFrame frame=new ChartFrame("Expenditure Chart",chart);
            frame.setVisible(true);
            frame.setSize(500,400);
           frame.setAlwaysOnTop(true);
       
          
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        } finally{
            try{
                rs.close();
                pst.close();
            }catch(Exception e){
                
            }
        }

       
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton32ActionPerformed

    private void chartsopen(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_chartsopen

        

        // TODO add your handling code here:
    }//GEN-LAST:event_chartsopen

    private void jButton13MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton13MouseEntered

        jLabel49.setText("Print");

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton13MouseEntered

    private void jButton13MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton13MouseExited

        jLabel49.setText("");
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton13MouseExited

    private void jMenu1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseEntered

        jMenu1.setForeground(Color.blue);
       
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu1MouseEntered

    private void jMenu1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseExited

        jMenu1.setForeground(Color.black);
        
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu1MouseExited

    private void txtsearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsearchKeyReleased

              
          DefaultTableModel table=(DefaultTableModel)churchmembertable.getModel();
        String search=txtsearch.getText().toLowerCase();
        TableRowSorter<DefaultTableModel> tr=new TableRowSorter<DefaultTableModel>(table);
        churchmembertable.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(search));        
        
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsearchKeyReleased

    private void txtmemberidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtmemberidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmemberidActionPerformed

    private void jButton33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton33ActionPerformed

                 
          MessageFormat header=new MessageFormat("DKUT CHRISTIAN UNION EXPENSES");
              MessageFormat footer=new MessageFormat("Page{0,number,integer}");

       
       try{
           
           jTablebudget.print(JTable.PrintMode.NORMAL, header, footer);
           
       }catch(java.awt.print.PrinterException e){
           System.err.format("Cannot print %s%n", e.getMessage());
       }finally{
            try{
                rs.close();
                pst.close();
            }catch(Exception e){
                
            }
        }

        
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton33ActionPerformed
     
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
              if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                 }
               //UIManager.setLookAndFeel("com.jtattoo.plaf.aero.AeroLookAndFeel");
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MAIN_PAGE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    //</editor-fold>
    
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MAIN_PAGE().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JLABLE;
    private javax.swing.JTextArea TXTEMAILCOMPOSE;
    private javax.swing.JTextField TXTEMAILFROM;
    private javax.swing.JPasswordField TXTEMAILPASSWORD;
    private javax.swing.JTextField TXTEMAILSUBJECT;
    private javax.swing.JTextField TXTEMAILTO;
    private javax.swing.JTable TableCalendar;
    private javax.swing.JTextField attachmentname;
    private javax.swing.JButton btnbudget;
    private javax.swing.JButton btncalendar;
    private javax.swing.JButton btnmember;
    private javax.swing.JButton btnmessaging;
    private javax.swing.JButton btnothers;
    private javax.swing.JButton btnstaff;
    private javax.swing.JButton btnstaffimage1;
    private javax.swing.JButton btntithing;
    private javax.swing.ButtonGroup buttonGroupmember;
    private com.toedter.calendar.JDateChooser calendarshowdate2;
    private com.toedter.calendar.JDateChooser calendershowdate1;
    private javax.swing.JTable churchmembertable;
    private javax.swing.JTable churchstafftable;
    private javax.swing.JTextField emailpath;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JDesktopPane jDesktopPane2;
    private javax.swing.JDialog jDialogbudget;
    private javax.swing.JDialog jDialogcalendar;
    private javax.swing.JDialog jDialogmember;
    private javax.swing.JDialog jDialogmessaging;
    private javax.swing.JDialog jDialogothers;
    private javax.swing.JDialog jDialogstaff;
    private javax.swing.JDialog jDialogtithing;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanelEMAIL;
    private javax.swing.JPanel jPanelMAIN;
    private javax.swing.JPanel jPanelSMS;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JTable jTablebudget;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lbldisplay;
    private javax.swing.JLabel lblmemberimage;
    private javax.swing.JTable otherstable;
    private javax.swing.JTextField path;
    private javax.swing.JTextField path1;
    private javax.swing.JRadioButton rbtnfemalemember;
    private javax.swing.JRadioButton rbtnfemalestaff;
    private javax.swing.JRadioButton rbtnmalemember;
    private javax.swing.JRadioButton rbtnmalestaff;
    private javax.swing.ButtonGroup staffbuttongroup;
    private javax.swing.JTable tithingtable;
    private javax.swing.JTextField txt_checked_email;
    private javax.swing.JTextField txtamount;
    private javax.swing.JTextField txtavailable;
    private javax.swing.JTextField txtbudgetactivity;
    private javax.swing.JTextField txtbudgetamount;
    private javax.swing.JTextField txtbudgeted;
    private javax.swing.JTextField txtbudgetid;
    private javax.swing.JTextField txtbudgettotal;
    private javax.swing.JTextField txtcalendaeridev;
    private com.toedter.calendar.JDateChooser txtcalendardate;
    private javax.swing.JTextField txtcalendarevent;
    private javax.swing.JTextField txtcalendartime;
    private javax.swing.JTextField txtfilter;
    private javax.swing.JTextField txtmemberage;
    private javax.swing.JTextField txtmembercourse;
    private javax.swing.JTextField txtmemberemail;
    private javax.swing.JTextField txtmemberid;
    private javax.swing.JTextField txtmembermobile;
    private javax.swing.JTextField txtmembername;
    private javax.swing.JTextField txtmemberyearofstudy;
    private javax.swing.JTextField txtothersamount;
    private com.toedter.calendar.JDateChooser txtothersdate;
    private javax.swing.JTextField txtothersid;
    private javax.swing.JTextField txtothersincome;
    private javax.swing.JLabel txtrealdate;
    private javax.swing.JLabel txtrealtime;
    private javax.swing.JTextField txtsearch;
    private javax.swing.JTextArea txtsmscompose;
    private javax.swing.JLabel txtsmsfrom1;
    private javax.swing.JLabel txtsmsfrom2;
    private javax.swing.JLabel txtsmsfrom3;
    private javax.swing.JLabel txtsmsfrom4;
    private javax.swing.JPasswordField txtsmspass;
    private javax.swing.JTextField txtsmsrecipient;
    private javax.swing.JTextField txtsmsuser;
    private javax.swing.JTextField txtstaffage;
    private javax.swing.JTextField txtstaffcourse;
    private javax.swing.JTextField txtstaffemail;
    private javax.swing.JTextField txtstaffid;
    private javax.swing.JTextField txtstaffmobile;
    private javax.swing.JTextField txtstaffname;
    private javax.swing.JTextField txtstaffoccupation;
    private com.toedter.calendar.JDateChooser txttithdate;
    private javax.swing.JTextField txttithid;
    private javax.swing.JTextField txttithname;
    private javax.swing.JTextField txttotalothers;
    private javax.swing.JTextField txtvalidate;
    // End of variables declaration//GEN-END:variables

    
      
final EmailValidator emailValidator = EmailValidator.getInstance();
public String save_valid_mail;
    
     String attachmentpath;
    String filename=null;
    byte[] personimage=null;
    byte[] personimage1=null;
   
}

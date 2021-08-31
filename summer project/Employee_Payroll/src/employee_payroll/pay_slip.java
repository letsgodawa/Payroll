/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employee_payroll;

/**
 *
 * @author Dawa
 */
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class pay_slip extends JFrame implements ActionListener{

    Choice c1;
    JTextArea t1;
    JButton b1,b2;

    pay_slip(){
        
        setSize(800,700);
        setLocation(400,150);
        c1 = new Choice();
        try{
            conn c = new conn();
            ResultSet rs = c.s.executeQuery("select * from salary");
            while(rs.next()){
                c1.add(rs.getString("id"));
            }
        }catch(Exception e) { }
    
        setLayout(new BorderLayout());
        
        JPanel p1 = new JPanel();
        p1.add(new JLabel("Select Id"));
        p1.add(c1);
        add(p1,"North");
        // c1.addItemListener(this);
    
        t1 = new JTextArea(30,80);
        JScrollPane jsp = new JScrollPane(t1);
     
        Font f1 = new Font("arial",Font.BOLD,20);
        t1.setFont(f1);
        
        b1 = new JButton("Generate Pay Slip");
        
        
        JPanel p2=new JPanel();
        p2.add(b1);
        
        add(p2,"South");
        
    
        
        add(jsp,"Center");
        b1.addActionListener(this);
    
    }
    
        
   
    public void actionPerformed(ActionEvent e) {
     
        try{
            conn c = new conn();
         
            ResultSet rs = c.s.executeQuery("select * from employee where id="+c1.getSelectedItem());
            rs.next();
            String name = rs.getString("name");
            rs.close();
         
            rs = c.s.executeQuery("select * from salary where id="+c1.getSelectedItem());
            double gross=0;
            double net=0;
 
            java.util.Date d1 = new java.util.Date();
            int month = d1.getMonth();
            t1.setText(" ----------------   PAY SLIP FOR THE MONTH OF "+month+" ,2021  ------------------------");
            t1.append("\n");
  
            if(rs.next()){
          
                t1.append("\n     Employee ID "+rs.getString("id"));
                t1.append("\n     Employee Name "+name);
 
                t1.append("\n----------------------------------------------------------------");
                t1.append("\n");

                double hra = rs.getDouble("hra");
                t1.append("\n                  HRA         : "+hra);
                double da  = rs.getDouble("da");
                t1.append("\n                  DA          : "+da);
                double med  = rs.getDouble("med");
                t1.append("\n                  MED         : "+med);
                double pf  = rs.getDouble("pf");
                t1.append("\n                  PF          : "+pf);
                double basic = rs.getDouble("basic_salary");
                gross = hra+da+med+pf+basic;
                net = gross - pf;
                t1.append("\n                  BASIC SALARY : "+basic);

                t1.append("\n-------------------------------------------------------");
                t1.append("\n");
 
                t1.append("\n       GROSS SALARY :"+gross+"    \n       NET SALARY : "+net);
                t1.append("\n       Tax   :   2.1% of gross "+ (gross*2.1/100));   
                t1.append("\n -------------------------------------------------");
                t1.append("\n");
                t1.append("\n");    
                t1.append("\n");
                t1.append("   (  Signature  )      ");

            }
        }catch(Exception ee) {
            ee.printStackTrace();
        }
        
        try {
            t1.print();
        } catch (PrinterException ex) {
            Logger.getLogger(pay_slip.class.getName()).log(Level.SEVERE, null, ex);
        }
   
    }
    public static void main(String[] args){
        new pay_slip().setVisible(true);
    }
}

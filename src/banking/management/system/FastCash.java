// Source code is decompiled from a .class file using FernFlower decompiler.
package banking.management.system;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class FastCash extends JFrame implements ActionListener {
   JLabel l1;
   JTextField t1;
   JButton b1;
   JButton b2;
   JButton b3;
   JButton b4;
   JButton b5;
   JButton b6;
   JButton b7;
   JButton b8;
   JButton b9;
   String pin;
   String Accountno;

   FastCash(String pin, String Accountno) {
      this.pin = pin;
      this.Accountno = Accountno;
      this.setLayout((LayoutManager)null);
      ImageIcon h1 = new ImageIcon(ClassLoader.getSystemResource("icons/logo.jpg"));
      Image h2 = h1.getImage().getScaledInstance(100, 100, 1);
      ImageIcon h3 = new ImageIcon(h2);
      JLabel label = new JLabel(h3);
      label.setBounds(70, 30, 100, 100);
      this.add(label);
      ImageIcon j1 = new ImageIcon(ClassLoader.getSystemResource("icons/withdraw2.jpg"));
      Image j2 = j1.getImage().getScaledInstance(800, 800, 1);
      ImageIcon j3 = new ImageIcon(j2);
      JLabel label6 = new JLabel(j3);
      label6.setBounds(800, 0, 800, 800);
      this.add(label6);
      JLabel text = new JLabel("Dear Customer, Welecome to ");
      text.setForeground(Color.black);
      text.setFont(new Font("Osward", 1, 32));
      text.setBounds(200, 80, 450, 40);
      this.add(text);
      this.l1 = new JLabel("PLEASE SELECT AMOUNT YOU WANT TO WITHDRAW!!");
      this.l1.setForeground(Color.black);
      this.l1.setFont(new Font("System", 1, 18));
      this.l1.setBounds(100, 220, 700, 55);
      this.add(this.l1);
      this.b1 = new JButton("RS.100");
      this.b1.setBounds(150, 300, 150, 40);
      this.b1.setFont(new Font("Raleway", 1, 15));
      this.b1.addActionListener(this);
      this.add(this.b1);
      this.b2 = new JButton("RS.500");
      this.b2.setBounds(400, 300, 150, 40);
      this.b2.setFont(new Font("Raleway", 1, 15));
      this.b2.addActionListener(this);
      this.add(this.b2);
      this.b3 = new JButton("RS.1000");
      this.b3.setBounds(150, 350, 150, 40);
      this.b3.setFont(new Font("Raleway", 1, 15));
      this.b3.addActionListener(this);
      this.add(this.b3);
      this.b4 = new JButton("RS.2000");
      this.b4.setBounds(400, 350, 150, 40);
      this.b4.setFont(new Font("Raleway", 1, 15));
      this.b4.addActionListener(this);
      this.add(this.b4);
      this.b5 = new JButton("RS.5000");
      this.b5.setBounds(150, 400, 150, 40);
      this.b5.setFont(new Font("Raleway", 1, 15));
      this.b5.addActionListener(this);
      this.add(this.b5);
      this.b6 = new JButton("RS.10000");
      this.b6.setBounds(400, 400, 150, 40);
      this.b6.setFont(new Font("Raleway", 1, 15));
      this.b6.addActionListener(this);
      this.add(this.b6);
      this.b7 = new JButton("BACK");
      this.b7.setBounds(280, 510, 150, 40);
      this.b7.setFont(new Font("Raleway", 1, 15));
      this.b7.setBackground(new Color(0, 51, 102));
      this.b7.setForeground(Color.white);
      this.b7.addActionListener(this);
      this.add(this.b7);
      this.setSize(1600, 1200);
      this.getContentPane().setBackground(new Color(255, 204, 229));
      this.setVisible(true);
   }

   public void actionPerformed(ActionEvent ae) {
      if (ae.getSource() == this.b7) {
         this.setVisible(false);
         (new Transactions(this.pin, this.Accountno)).setVisible(true);
      } else {
         String amount = ((JButton)ae.getSource()).getText().substring(3);
         ConnectionSql c = new ConnectionSql();

         try {
            ResultSet rs = c.s.executeQuery("select *from bank where Account_No = '" + this.Accountno + "' and Login_Password = '" + this.pin + "'");
            int balance = 0;

            while(rs.next()) {
               if (rs.getString("type").equals("Deposit")) {
                  balance += Integer.parseInt(rs.getString("amount"));
               } else {
                  balance -= Integer.parseInt(rs.getString("amount"));
               }
            }

            if (ae.getSource() != this.b7 && balance < Integer.parseInt(amount)) {
               JOptionPane.showMessageDialog((Component)null, "Insuffient Balance");
               return;
            }

            Date date = new Date();
            c.s.executeUpdate("insert into bank values('" + this.pin + "', '" + this.Accountno + "', '" + date + "', 'Withdrawl', '" + amount + "')");
            JOptionPane.showMessageDialog((Component)null, "Rs. " + amount + " Debited Successfully");
            this.setVisible(false);
            (new Transactions(this.pin, this.Accountno)).setVisible(true);
         } catch (Exception var7) {
            var7.printStackTrace();
         }
      }

   }

   public static void main(String[] args) {
      (new FastCash("", "")).setVisible(true);
   }
}

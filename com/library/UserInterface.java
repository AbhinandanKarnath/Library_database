package com.library;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class UserInterface extends Frame implements KeyListener {
    Frame frame;
    TextField input;
    Label output;
    String LibraryId;
    byte[] imageData;
    ImageIcon theOutputImage;
    JLabel label = new JLabel();
    Font verFont =new Font("Verdana",Font.BOLD,18);

    public void paint(Graphics g){
        g.drawRect(50,200,100,100);
    }
    UserInterface(){
        frame=new Frame("Library Student Register Page");
        input=new TextField();
        output=new Label();

        frame.setSize(700,550);
        input.setBounds(450,100,100,30);
        frame.setBackground(new Color(71, 87, 75));
        input.setBackground(new Color(70, 100, 80));
        input.setForeground(new Color(134, 155, 68));
        input.setFont(new Font("Verdana",Font.TRUETYPE_FONT,17));
        output.setBounds(450,300,200,30);
        frame.add(input);
        frame.add(output);

        output.setFont(verFont);
        try{

            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            });
        }
        catch (Exception e){
            e.printStackTrace();
        }
        frame.setLayout(null);
        frame.setVisible(true);
        input.addKeyListener(this);
    }

    public static void main(String[] args) {
        new UserInterface();
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyPressed(KeyEvent e) {
        LibraryId=input.getText();
        imageData =null;
        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            System.out.println("Passing ID to LibDataBase");
            String Name=LibDataBase.fetch(LibraryId);
            String empty="Error";
            if(!empty.equals(Name)){
                imageData =LibDataBase.sendImage();
                DisplayOutput(Name, imageData);
            }
            else {
                label.setIcon(null);
                String ErrorMessage="ID not found ";
                output.setForeground(new Color(210, 15, 15, 185));
                output.setText(ErrorMessage);
                label.setText("INVALID ID  Enter proper ID ");
            }
        }
    }
    private void DisplayOutput(String OutputName, byte[] ImageByteData) {
        label.setBounds(80,30,250,250);
        Image img=Toolkit.getDefaultToolkit().createImage(ImageByteData);
        output.setText(OutputName);
        try{

            theOutputImage =new ImageIcon(img);
            output.setForeground(new Color(238, 233, 219));

            label.setIcon(null);
            label.setIcon(theOutputImage);
            frame.add(label);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {

    }
}
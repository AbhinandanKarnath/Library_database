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

    public void paint(Graphics g){
        g.drawRect(50,200,100,100);
    }
    UserInterface(){
        frame=new Frame("Library Student Register Page");
        input=new TextField();
        output=new Label();

        frame.setSize(700,550);
        input.setBounds(450,100,100,30);
        //frame.setBackground(Color.);
        input.setFont(new Font("Verdana",Font.PLAIN,18));
        output.setBounds(450,300,200,30);
        frame.add(input);
        frame.add(output);

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
            String Name=LibDataBase.fetch(LibraryId);
            String empty="Error";
            if(!empty.equals(Name)){
                imageData =LibDataBase.sendImage();
                DisplayOutput(Name, imageData);
            }
            else {
                output.setForeground(Color.RED);
                output.setText(Name);
            }
        }
    }

    private void DisplayOutput(String OutputName, byte[] outputImage) {
        label.setBounds(80,30,250,250);
        output.setText(OutputName);
        try{
            Image img=Toolkit.getDefaultToolkit().createImage(outputImage);
            theOutputImage =new ImageIcon(img);

            output.setForeground(Color.GREEN);
            label.setIcon(theOutputImage);
            frame.add(label);
            System.out.println("Image part Executed ");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {

    }
}
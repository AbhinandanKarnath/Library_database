package com.library;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class UserInterface extends Frame implements KeyListener {

    Frame frame;
    TextField input;
    Label output;
    public void paint(Graphics g){
        g.drawRect(50,200,100,100);
    }
    UserInterface(){
        frame=new Frame();
        input=new TextField();
        output=new Label("Null");

        frame.setSize(700,550);
        input.setBounds(450,100,100,30);
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
            System.out.println(e);
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
        String LibraryId=input.getText();
        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            String Name=LibDataBase.fetch(LibraryId);
            output.setText(Name);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

package BrickBreakerGame;
import java.awt.Color;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;
import java.applet.*;
import javax.swing.ImageIcon;
import java.net.URL;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.Timer;
import java.text.DecimalFormat;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import sun.audio.*;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;




public class BrickBreakerGames{


    public static void main(String[] args) {
        JFrame obj = new JFrame();
        Gameplay gamePlay = new Gameplay();
        obj.setBounds(10,10,700,600);
        obj.setTitle("BrickBreaker");
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        obj.add(gamePlay); 
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        obj.setLocation(dim.width/2-obj.getSize().width/2, dim.height/2-obj.getSize().height/2);
        JLabel background;
//        File Clap = new File("Fuck This Shit I'm Out.mp3");
//        PlaySound(Clap);
    }
//    static void PlaySound(File Sound){
//        try{
//            Clip clip = AudioSystem.getClip();
//            clip.open(AudioSystem.getAudioInputStream(Sound));
//            clip.start();
//        }catch (Exception e){
//    }
//    }

//    private static void Sound(String string) {
//       
//        InputStream music;
//        try
//        {
//            music = new FileInputStream(new File(filepath));
//            AudioStream audios = new AudioStream(music);
//            AudioPlayer.player.start(audios);
//        }
//        catch(Exception e){
//        }
//    }

}

    
    




            

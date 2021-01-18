package sample;

import javafx.scene.control.*;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.text.DecimalFormat;
import java.util.Random;


/**
 * Created by Stefan on 31.05.15.
 */
public class Frame extends JFrame
{

    JPanel panel1;
    JPanel panel2;
    JPanel panel3;
    JPanel panel4;

    JList liste;

    JButton newBall;
    JButton deleteBall;
    JButton ubdate;
    JButton nameSpeichern;
    JButton posxSpeichern;
    JButton posySpeichern;
    JButton speedxSpeichern;
    JButton speedySpeichern;
    JButton masseSpeichern;

    JToggleButton pause;

    JCheckBox anzeigeframe;

    JComboBox gravitation;

    int u=1;

    ListListener listListener;
    ButtonListener buttonListener;
    ItemListener itemlistener;
    caretListener caretListener;

    Random Speed=new Random();

    JLabel name;
    JLabel pos_x;
    JLabel pos_y;
    JLabel speed_x;
    JLabel speed_y;
    JLabel grav;
    JLabel masse;

    JTextField txtname;
    JTextField txtspeed_x;
    JTextField txtspeed_y;
    JTextField txtpos_x;
    JTextField txtpos_y;
    JTextField txtmasse;

    DecimalFormat df=new DecimalFormat("0.00");

    Object[] allBalls;
    String[] BallName;
    boolean[]Anzeige;
    boolean anzeige;
    static int r;
    String[] gravitat=new String[5];
    Screen draw;
    JMenuBar menu;


    public Frame(Object[]allBalls, String[]BallName, boolean[]Anzeige)
    {
        super("Ball Simulation");
        for (int i = 0; i < 5; i++)
        {
            gravitat[i]=""+i;
        }
        this.allBalls=allBalls;
        this.BallName=BallName;
        this.Anzeige=Anzeige;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1050,600);
        setResizable(false);
        setLocationRelativeTo(null);

        Container c=getContentPane();
        getContentPane().setLayout(null);

        draw=new Screen();
        draw.setBounds(0,0,1050,600);


        panel1=new JPanel();
        panel1.setBackground(Color.LIGHT_GRAY);
        panel1.setBounds(600,0,100,180);

        panel2=new JPanel();
        panel2.setBackground(Color.LIGHT_GRAY);
        panel2.setBounds(680,0,350,180);
        panel2.setLayout(null);

        panel3=new JPanel();
        panel3.setBackground(Color.LIGHT_GRAY);
        panel3.setBounds(0,0,125,180);
        panel3.setLayout(null);
        panel2.add(panel3);

        panel4=new JPanel();
        panel4.setBackground(Color.LIGHT_GRAY);
        panel4.setBounds(125,0,225,180);
        panel4.setLayout(null);
        panel2.add(panel4);

        name=new JLabel("Name Ball:",JLabel.RIGHT);
        name.setBounds(0,0,125,30);
        panel3.add(name);

        pos_x=new JLabel("Position x:",JLabel.RIGHT);
        pos_x.setBounds(0,30,125,30);
        panel3.add(pos_x);

        pos_y=new JLabel("Position y:",JLabel.RIGHT);
        pos_y.setBounds(0,60,125,30);
        panel3.add(pos_y);

        speed_x=new JLabel("Speed x:",JLabel.RIGHT);
        speed_x.setBounds(0,90,125,30);
        panel3.add(speed_x);

        speed_y=new JLabel("Speed y:",JLabel.RIGHT);
        speed_y.setBounds(0,120,125,30);
        panel3.add(speed_y);

        masse=new JLabel("Masse:",JLabel.RIGHT);
        masse.setBounds(0,150,125,30);
        panel3.add(masse);

        grav=new JLabel("Gravitationsstufe:",JLabel.LEFT);
        grav.setBounds(775,240,120,30);


        itemlistener=new ItemListener();

        gravitation=new JComboBox(gravitat);
        gravitation.setBounds(900,240,80,30);
        gravitation.setEnabled(false);
        gravitation.addItemListener(itemlistener);


        caretListener=new caretListener();

        txtname=new JTextField(BallName[r]);
        txtname.setBounds(0,0,130,30);
        txtname.setEnabled(false);
        txtname.addCaretListener(caretListener);
        panel4.add(txtname);

        txtpos_x=new JTextField();
        txtpos_x.setBounds(0,30,130,30);
        txtpos_x.setEnabled(false);
        txtpos_x.addCaretListener(caretListener);
        panel4.add(txtpos_x);

        txtpos_y=new JTextField();
        txtpos_y.setBounds(0,60,130,30);
        txtpos_y.setEnabled(false);
        txtpos_y.addCaretListener(caretListener);
        panel4.add(txtpos_y);

        txtspeed_x=new JTextField();
        txtspeed_x.setBounds(0,90,130,30);
        txtspeed_x.setEnabled(false);
        txtspeed_x.addCaretListener(caretListener);
        panel4.add(txtspeed_x);

        txtspeed_y=new JTextField();
        txtspeed_y.setBounds(0,120,130,30);
        txtspeed_y.setEnabled(false);
        txtspeed_y.addCaretListener(caretListener);
        panel4.add(txtspeed_y);

        txtmasse=new JTextField();
        txtmasse.setBounds(0,150,130,30);
        txtmasse.setEnabled(false);
        txtmasse.addCaretListener(caretListener);
        panel4.add(txtmasse);


        listListener=new ListListener();


        liste=new JList(BallName);
        liste.setFixedCellWidth(60);
        liste.setVisibleRowCount(10);
        liste.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        liste.addListSelectionListener(listListener);


        buttonListener=new ButtonListener();






        newBall=new JButton("neuer Ball");
        newBall.setBounds(637,210,100,20);
        newBall.addActionListener(buttonListener);

        deleteBall=new JButton("Ball löschen");
        deleteBall.setBounds(775,210,100,20);
        deleteBall.addActionListener(buttonListener);

        ubdate=new JButton("Laden");
        ubdate.setBounds(912,210,100,20);
        ubdate.addActionListener(buttonListener);

        pause=new JToggleButton("Pause");
        pause.setBounds(637,240,100,20);
        pause.addItemListener(itemlistener);


        anzeigeframe=new JCheckBox("Ball Anzeigen");
        anzeigeframe.setBounds(637,270,150,20);
        anzeigeframe.setEnabled(false);
        anzeigeframe.addItemListener(itemlistener);

        nameSpeichern=new JButton("Speichern");
        nameSpeichern.setBounds(130,0,100,30);
        nameSpeichern.setEnabled(false);
        nameSpeichern.addActionListener(buttonListener);
        panel4.add(nameSpeichern);

        posxSpeichern=new JButton("Speichern");
        posxSpeichern.setBounds(130,30,100,30);
        posxSpeichern.setEnabled(false);
        posxSpeichern.addActionListener(buttonListener);
        panel4.add(posxSpeichern);

        posySpeichern=new JButton("Speichern");
        posySpeichern.setBounds(130,60,100,30);
        posySpeichern.setEnabled(false);
        posySpeichern.addActionListener(buttonListener);
        panel4.add(posySpeichern);

        speedxSpeichern=new JButton("Speichern");
        speedxSpeichern.setBounds(130,90,100,30);
        speedxSpeichern.setEnabled(false);
        speedxSpeichern.addActionListener(buttonListener);
        panel4.add(speedxSpeichern);

        speedySpeichern=new JButton("Speichern");
        speedySpeichern.setBounds(130,120,100,30);
        speedySpeichern.setEnabled(false);
        speedySpeichern.addActionListener(buttonListener);
        panel4.add(speedySpeichern);

        masseSpeichern=new JButton("Speichern");
        masseSpeichern.setBounds(130,150,100,30);
        masseSpeichern.setEnabled(false);
        masseSpeichern.addActionListener(buttonListener);
        panel4.add(masseSpeichern);



        panel1.add(new JScrollPane(liste));
        c.add(panel1);
        c.add(panel2);
        c.add(newBall);
        c.add(deleteBall);
        c.add(ubdate);
        c.add(pause);
        c.add(grav);
        c.add(gravitation);
        c.add(anzeigeframe);
        c.add(draw);


        setVisible(true);


    }
    public class Screen extends JLabel
    {
        public void paintComponent(Graphics g)
        {
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(0,0,1050,600);
            g.setColor(Color.WHITE);
            g.fillRect(0,0,600,600);
            g.fillRect(600,300,450,300);

            for (int i = 0; i < r; i++)
            {
                Ball h=(Ball)allBalls[i];
                g.drawImage(Ball.getBild(),(int)h.getPos_x(),(int)h.getPos_y(),null);

            }
            for (int i = 0; i < r; i++)
            {
                g.setColor(Color.BLACK);
                anzeige=Anzeige[listListener.getT()];
                anzeigeframe.setSelected(anzeige);
                if(anzeige==true)
                {
                    Ball z= (Ball) allBalls[listListener.getT()];
                    g.drawRect((int)z.getPos_x(),(int)z.getPos_y(),Ball.getBild().getWidth(),Ball.getBild().getHeight());
                }
            }

        }
    }



    public class ButtonListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {


            if (e.getSource() == newBall)
            {
                if (r < 50)
                {
                    Ball z = (Ball) allBalls[r];
                    z.setSpeed_x(Speed.nextInt(150 + 50));
                    z.setSpeed_y(Speed.nextInt(150 + 50));

                    BallName[r] = "Ball " + (r + 1);
                    txtname.setText(BallName[r]);
                    txtpos_x.setText(df.format(z.getPos_x() * u));
                    txtpos_y.setText(df.format(z.getPos_y() * u));
                    txtspeed_x.setText(String.valueOf(z.getSpeed_x()));
                    txtspeed_y.setText(String.valueOf(z.getSpeed_y()));
                    txtmasse.setText(String.valueOf(z.getMass()));

                    liste.setSelectedIndex(r);
                    listListener.setT(r);

                    txtname.setEnabled(true);
                    txtpos_x.setEnabled(true);
                    txtpos_y.setEnabled(true);
                    txtspeed_x.setEnabled(true);
                    txtspeed_y.setEnabled(true);
                    anzeigeframe.setEnabled(true);
                    gravitation.setEnabled(true);
                    txtmasse.setEnabled(true);


                    r += 1;
                    txtname.repaint();
                    liste.repaint();
                }
            }
            if (e.getSource() == deleteBall)
            {
                if (r >= 1)
                {
                    r -= 1;
                    Ball h = (Ball) allBalls[r];
                    h.setPos_x(0);
                    h.setPos_y(0);
                    h.setMass(1);
                    BallName[r] = "Leer";
                    txtname.setText("Leer");
                    txtpos_x.setText(null);
                    txtpos_y.setText(null);
                    txtspeed_x.setText(null);
                    txtspeed_y.setText(null);
                    txtmasse.setText(null);


                    liste.setSelectedIndex(r - 1);
                    listListener.setT(r - 1);

                    if (r > 0)
                    {
                        Ball d = (Ball) allBalls[r - 1];
                        txtname.setText(BallName[r - 1]);
                        txtpos_x.setText(df.format(d.getPos_x() * u));
                        txtpos_y.setText(df.format(d.getPos_y() * u));
                        txtspeed_x.setText(String.valueOf(d.speed_x));
                        txtspeed_y.setText(String.valueOf(d.speed_y));
                        txtmasse.setText(String.valueOf(d.getMass()));
                    }

                    if (r == 0)
                    {
                        txtname.setEnabled(false);
                        nameSpeichern.setEnabled(false);
                        txtpos_x.setEnabled(false);
                        txtpos_y.setEnabled(false);
                        txtspeed_x.setEnabled(false);
                        txtspeed_y.setEnabled(false);
                        anzeigeframe.setEnabled(false);
                        gravitation.setEnabled(false);
                        txtmasse.setEnabled(false);
                    }

                    liste.repaint();
                }
            }
            if (e.getSource() == ubdate)
            {
                if (listListener.getT() < r)
                {
                    Ball r = (Ball) allBalls[listListener.getT()];

                    txtpos_x.setText(df.format(r.getPos_x()));
                    txtpos_y.setText(df.format(r.getPos_y()));
                    txtspeed_y.setText(String.valueOf(r.getSpeed_x() * u));
                    txtspeed_y.setText(String.valueOf(r.getSpeed_y() * u));
                    txtmasse.setText(String.valueOf(r.getMass()));


                    liste.repaint();
                }
            }


            if (e.getSource() == nameSpeichern)
            {
                String name = txtname.getText();
                BallName[listListener.getT()] = name;
                liste.repaint();
            }

            if (e.getSource() == posxSpeichern)
            {
                Ball u = (Ball) allBalls[listListener.getT()];
                try
                {
                    u.setPos_x(Float.parseFloat(txtpos_x.getText()));
                }catch (Exception w)
                {
                    JOptionPane.showConfirmDialog(null,"Geben Sie eine Zahl ein","Keine Zahl",JOptionPane.CLOSED_OPTION,JOptionPane.ERROR_MESSAGE);
                }
            }
            if (e.getSource() == posySpeichern)
            {
                Ball u = (Ball) allBalls[listListener.getT()];
                try
                {
                    u.setPos_y(Float.parseFloat(txtpos_y.getText()));
                }catch (Exception w)
                {
                    JOptionPane.showConfirmDialog(null,"Geben Sie eine Zahl ein","Keine Zahl",JOptionPane.CLOSED_OPTION,JOptionPane.ERROR_MESSAGE);
                }
            }
            if (e.getSource() == speedxSpeichern)
            {
                Ball u = (Ball) allBalls[listListener.getT()];
               try
               {
                   u.setSpeed_x(Integer.parseInt(txtspeed_x.getText()));
               }catch (Exception w)
               {
                   JOptionPane.showConfirmDialog(null,"Geben Sie eine Zahl ein","Keine Zahl",JOptionPane.CLOSED_OPTION,JOptionPane.ERROR_MESSAGE);
               }
            }
            if (e.getSource() == speedySpeichern)
            {
                Ball u = (Ball) allBalls[listListener.getT()];
                try
                {
                    u.setSpeed_y(Integer.parseInt(txtspeed_y.getText()));
                }catch (Exception w)
                {
                    JOptionPane.showConfirmDialog(null,"Geben Sie eine Zahl ein","Keine Zahl",JOptionPane.CLOSED_OPTION,JOptionPane.ERROR_MESSAGE);
                }
            }
            if (e.getSource() == masseSpeichern)
            {
                Ball u = (Ball) allBalls[listListener.getT()];
                try
                {
                    float zahl=Integer.parseInt(txtmasse.getText());
                    if (zahl>0)
                    {
                        u.setMass(zahl);
                    }else
                        JOptionPane.showConfirmDialog(null,"Geben Sie eine Zahl größer Null ein","Zu kleine Zahl",JOptionPane.CLOSED_OPTION,JOptionPane.ERROR_MESSAGE);
                }catch (Exception w)
                {
                    JOptionPane.showConfirmDialog(null,"Geben Sie eine Zahl ein","Keine Zahl",JOptionPane.CLOSED_OPTION,JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public class ListListener implements ListSelectionListener
    {
        int t;

        @Override
        public void valueChanged(ListSelectionEvent e)
        {
           if(e.getValueIsAdjusting())
           {
               t = liste.getSelectedIndex();
               if (t < r)
               {
                   Ball h = (Ball) allBalls[t];
                   txtname.setText(BallName[t]);

                   txtpos_x.setText(String.valueOf(df.format(h.getPos_x() * u)));
                   txtpos_y.setText(String.valueOf(df.format(h.getPos_y() * u)));

                   txtspeed_x.setText(String.valueOf(h.getSpeed_x()));
                   txtspeed_y.setText(String.valueOf(h.getSpeed_y()));
                   txtmasse.setText(String.valueOf(h.getMass()));

                   txtname.setEnabled(true);
                   txtpos_x.setEnabled(true);
                   txtpos_y.setEnabled(true);
                   txtspeed_x.setEnabled(true);
                   txtspeed_y.setEnabled(true);
                   anzeigeframe.setEnabled(true);
                   gravitation.setEnabled(true);
                   txtmasse.setEnabled(true);
               } else
               {
                   txtname.setText("Leer");
                   txtpos_x.setText(null);
                   txtpos_y.setText(null);

                   txtspeed_x.setText(null);
                   txtspeed_y.setText(null);
                   txtmasse.setText(null);

                   txtname.setEnabled(false);
                   txtpos_x.setEnabled(false);
                   txtpos_y.setEnabled(false);
                   txtspeed_x.setEnabled(false);
                   txtspeed_y.setEnabled(false);
                   txtmasse.setEnabled(false);

                   nameSpeichern.setEnabled(false);
                   anzeigeframe.setEnabled(false);
                   gravitation.setEnabled(false);
                   masseSpeichern.setEnabled(false);
               }
               liste.repaint();
           }
        }

        public void setT(int t)
        {
            this.t = t;
        }

        public int getT()
        {
            return t;
        }
    }

    public class ItemListener implements java.awt.event.ItemListener
    {

        @Override
        public void itemStateChanged(ItemEvent e)
        {
            Ball i= (Ball) allBalls[listListener.getT()];

            if(pause.isSelected())
            {
                Ball.pause=false;
            }else Ball.pause=true;

            if(anzeigeframe.isSelected())
            {
                Anzeige[listListener.getT()]=true;
            }else Anzeige[listListener.getT()]=false;

            if(gravitation.getSelectedIndex()==0)
            {
               i.setSpeed_y(i.getSpeed_y0());
            }
            if(gravitation.getSelectedIndex()==1)
            {
                i.setSpeed_y(i.getSpeed_y0());
                i.setGravitation1(true);
            }else i.setGravitation1(false);

            if(gravitation.getSelectedIndex()==2)
            {
                i.setSpeed_y(i.getSpeed_y0());
                i.setGravitation2(true);
            }else i.setGravitation2(false);
            if(gravitation.getSelectedIndex()==3)
            {
                i.setSpeed_y(i.getSpeed_y0());
                i.setGravitation3(true);
            }else i.setGravitation3(false);
            if(gravitation.getSelectedIndex()==4)
            {
                i.setSpeed_y(i.getSpeed_y0());
                i.setGravitation4(true);
            }else i.setGravitation4(false);

        }
    }

    public class caretListener implements CaretListener
    {

        @Override
        public void caretUpdate(CaretEvent e)
        {
            if(txtname.isEnabled())
            {
                String name = txtname.getText();
                if (name.isEmpty())
                {
                    nameSpeichern.setEnabled(false);
                } else nameSpeichern.setEnabled(true);
            }

            String posx=txtpos_x.getText();
            if(posx.isEmpty())
            {
                posxSpeichern.setEnabled(false);
            }else posxSpeichern.setEnabled(true);

            String posy=txtpos_y.getText();
            if(posy.isEmpty())
            {
                posySpeichern.setEnabled(false);
            }else posySpeichern.setEnabled(true);

            String speedx=txtspeed_x.getText();
            if(speedx.isEmpty())
            {
                speedxSpeichern.setEnabled(false);
            }else speedxSpeichern.setEnabled(true);

            String speedy=txtspeed_y.getText();
            if(speedy.isEmpty())
            {
                speedySpeichern.setEnabled(false);
            }else speedySpeichern.setEnabled(true);

            String masse=txtmasse.getText();
            if(masse.isEmpty())
            {
                masseSpeichern.setEnabled(false);
            }else masseSpeichern.setEnabled(true);
        }
    }

    public static int getR()
    {
        return r;
    }
}


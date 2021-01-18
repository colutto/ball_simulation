package sample;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Main
{


    public static void main(String[] args)
    {


        BufferedImage ball_look=null;
        try {
            ball_look= ImageIO.read(Main.class.getClassLoader().getResourceAsStream("kugel.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Ball.setBild(ball_look);

        Ball[] allBalls=new Ball[50];
        for (int i = 0; i <50 ; i++)
        {
            allBalls[i]=new Ball();
        }
        String[] BallName=new String[50];
        for (int i = 0; i < 50; i++)
        {
            BallName[i]="Leer";
        }
        boolean[] Anzeige=new boolean[50];
        for (int i = 0; i <50 ; i++)
        {
            Anzeige[i]=false;
        }
        Frame fenster=new Frame(allBalls,BallName,Anzeige);

        long firstFrame=System.currentTimeMillis();

        while(true)
        {
            long nextFrame=System.currentTimeMillis();
            float currentFrame=(float)(nextFrame-firstFrame)/1000;
            firstFrame=nextFrame;

            fenster.draw.repaint();
            for (int i = 0; i <Frame.r; i++)
            {
                Ball h=allBalls[i];
                h.Motion(currentFrame,allBalls);
            }


            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }




    }
}

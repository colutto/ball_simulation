package sample;



import java.awt.image.BufferedImage;
import java.util.*;

/**
 * Created by Stefan on 31.05.15.
 */
public class Ball {

    float speed_x;
    float speed_y;

    float pos_x;
    float pos_y;

    boolean gravitation0;
    boolean gravitation1;
    boolean gravitation2;
    boolean gravitation3;
    boolean gravitation4;

    float mass;
    int speed_y0;
    int speed;
    float radius=(ball_look.getHeight())/2;


    static boolean pause=true;

    static BufferedImage ball_look;
    Random r=new Random();

    public Ball()
    {
        mass=1;
        pos_x=0;
        pos_y=0;

        speed_x=50+r.nextInt(150);
        speed=50+r.nextInt(150);
        speed_y0=speed;
        speed_y=speed;

        gravitation1=false;
        gravitation2=false;
        gravitation3=false;
        gravitation4=false;
    }

    public static void setBild(BufferedImage bild)
    {
        ball_look=bild;
    }

    public static BufferedImage getBild(){return ball_look;}


    public int getSpeed_y0()
    {
        return speed_y0;
    }

    public void setGravitation1(boolean gravitation1)
    {
        this.gravitation1 = gravitation1;
    }

    public void setGravitation2(boolean gravitation2)
    {
        this.gravitation2 = gravitation2;
    }

    public void setGravitation3(boolean gravitation3)
    {
        this.gravitation3 = gravitation3;
    }

    public void setGravitation4(boolean gravitation4)
    {
        this.gravitation4 = gravitation4;
    }

    public float getPos_x(){return pos_x;}

    public float getPos_y(){return pos_y;}

    public void setPos_x(float pos_x)
    {
        this.pos_x = pos_x;
    }

    public void setPos_y(float pos_y)
    {
        this.pos_y = pos_y;
    }

    public void setSpeed_x(float speed_x)
    {
        this.speed_x = speed_x;
    }

    public void setSpeed_y(float speed_y)
    {
        this.speed_y = speed_y;
    }

    public float getSpeed_x()
    {
        return speed_x;
    }

    public float getSpeed_y()
    {
        return speed_y;
    }

    public float getMass()
    {
        return mass;
    }

    public void setMass(float mass)
    {
        this.mass = mass;
    }

    public static void setPause(boolean pause)
    {
        Ball.pause = pause;
    }

    private boolean intersects(Ball ball)
    {
        float absx=pos_x-ball.getPos_x();
        float absy=pos_y-ball.getPos_y();
        double abs=absx*absx+absy*absy;
        return(abs<=(this.radius+ball.radius)*(this.radius+ball.radius));
    }

    public void Motion(float currentFrame,Ball[] balls)
    {
        if(Ball.pause==true)
        {
            pos_x += speed_x * currentFrame;
            pos_y += speed_y * currentFrame;

            if (pos_x < 0)
            {
                speed_x *= -1;
                pos_x = 0;
            }
            if ((pos_x > (600 - ball_look.getWidth())) && (pos_y < 300-(ball_look.getHeight()/2)))
            {
                speed_x *= -1;
                pos_x = 600 - ball_look.getWidth();
            }else if((pos_x>1050-ball_look.getWidth())&&(pos_y>300))
            {
                speed_x*=-1;
                pos_x=1050-ball_look.getWidth();
            }
            if ((pos_y < 0)&&(pos_x<600-ball_look.getWidth()))
            {
                speed_y *= -1;
                pos_y = 0;
            }else if((pos_y<300)&&(pos_x>600-(ball_look.getWidth()/2)))
            {
                speed_y*=-1;
                pos_y=300;
            }
            if (pos_y > 580 - ball_look.getHeight())
            {
                speed_y *= -1;
                pos_y = 580 - ball_look.getHeight();
            }
            if(gravitation1==true)
            {
                speed_y += 2;
            }
            if(gravitation2==true)
            {
                speed_y+=4;
            }
            if(gravitation3==true)
            {
                speed_y+=6;
            }
            if(gravitation4==true)
            {
                speed_y+=8;
            }
            for (int i = 0; i < Frame.getR(); i++)
            {
                if (balls[i]!=this)
                {
                    if(balls[i].intersects(this))
                    {
                        Ball b1=this;
                        Ball b2=balls[i];

                        float normalx=b2.getPos_x()-b1.getPos_x();
                        float normaly=b2.getPos_y()-b1.getPos_y();
                        float length=(float)Math.sqrt((normalx*normalx)+(normaly*normaly));

                        normalx/=length;
                        normaly/=length;

                        float masses=b1.mass+b2.mass;

                        float overlapplength=b2.radius+b1.radius-length;
                        b1.setPos_x(b1.getPos_x()+(-normalx*overlapplength)*b2.mass/masses);
                        b1.setPos_y(b1.getPos_y()+(-normaly*overlapplength)*b2.mass/masses);
                        b2.setPos_x(b2.getPos_x()+(normalx*overlapplength)*b1.mass/masses);
                        b2.setPos_y(b2.getPos_y()+(normaly*overlapplength)*b1.mass/masses);

                        float f=(-(2)*((b2.getSpeed_x()-b1.getSpeed_x())*normalx+(b2.getSpeed_y()-b1.getSpeed_y())*normaly))/(1/b1.mass+1/b2.mass);

                        float oldspeed_xb1=b1.getSpeed_x();
                        float oldspeed_yb1=b1.getSpeed_y();
                        float oldspeed_xb2=b2.getSpeed_x();
                        float oldspeed_yb2=b2.getSpeed_y();

                        b1.setSpeed_x(oldspeed_xb1-f/b1.mass*normalx);
                        b1.setSpeed_y(oldspeed_yb1-f/b1.mass*normaly);

                        b2.setSpeed_x(oldspeed_xb2+f/b2.mass*normalx);
                        b2.setSpeed_y(oldspeed_yb2 + f / b2.mass*normaly);
                    }
                }
            }
        }
    }
}


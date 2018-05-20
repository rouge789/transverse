package transverse;

import java.awt.Color;

public class Point
{
    private Color color;
    private final int size;
    private final int x;
    private final int y;


    public Point(int x, int y, int size, Color color)
    {
        this.size = size;
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public Color getColor()
    {
        return color;
    }

    public int getSize()
    {
        return size;
    }

    public int getX()
    {
        return x;
    }
 
    public int getY()
    {
        return y;
    }

    public void setColor(Color color)
    {
        this.color = color;
    }
}
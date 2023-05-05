

import java.lang.annotation.Retention;

public class Point {
    int x;
    int y;

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    public static Point getInstance(int x, int y){
        return new Point(x, y);
    }
    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void setX(int newX){
        x = newX;
    }

    public void setY(int newY){
        y = newY;
    }

    public boolean equals(Point comparedPoint){
        return ((x == comparedPoint.getX()) && (y == comparedPoint.getY()));
    }

    public boolean equals(int x, int y){
        return ((this.x == x) && (this.y == y));
    }

    public String displayPoint(){
        return String.format("X: %d, Y: %d", getX(), getY());
    }

    public int getDistance(Point p){
        return (int) Math.round(Math.sqrt( (x - p.getX())*(x - p.getX()) + (y - p.getY())*(y - p.getY())  ) );
    }
}

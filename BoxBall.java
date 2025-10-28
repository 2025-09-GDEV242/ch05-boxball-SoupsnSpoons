import java.awt.*;
import java.awt.geom.*;
import java.util.Random;
/**
 * Class BoxBall - a graphical ball that moves similar to the ball in PONG.
 * It bounces around the confines of a box as defined by the Box class.
 * Details of movement are determined by the ball itself. It will move to
 * the right when xspeed is positive and to the left when xspeed is negative.
 * Due to the way computer graphics are drawn using awt, the ball moves
 * DOWN when yspeed is positive and UP when yspeed is negative.
 * 
 * It is the ball's responsibility to determine if it has hit a wall and 
 * to reverse direction
 *
 * This movement can be initiated by repeated calls to the "move" method.
 * 
 * @author Michael Kölling (mik)
 * @author David J. Barnes
 * @author Bruce Quig
 * @author William Crosbie
 *
 * @version 2025.10.06
 */

public class BoxBall
{
    private Box myBox;
    
    private Ellipse2D.Double circle;    // represents the ball
    private Color color;        // color of the ball (can be rgb value)
    private int diameter;       // width of ball in number of pixels
    private int xPosition;      // horizontal position of bounding square
    private int yPosition;      // vertical position of bounding square
    private Canvas canvas;
    private int ySpeed;         // vertical speed
    private int xSpeed;         // horizontal speed

    /**
     * Constructor for objects of class BoxBall
     *
     * @param xPos  the horizontal coordinate of the ball
     * @param yPos  the vertical coordinate of the ball
     * @param ballDiameter  the diameter (in pixels) of the ball
     * @param ballColor  the color of the ball
     * @param box  the bounding box (where the ball will bounce)
     * @param drawingCanvas  the canvas to draw this ball on
     */
    public BoxBall(int xPos, int yPos, int ballPosition, int ballDiameter, Color ballColor,
                        Box box, Canvas drawingCanvas)
    {
        xPosition = xPos;
        yPosition = yPos;
        ballPosition = xPos + yPos;
        color = ballColor;
        diameter = ballDiameter;
        myBox = box;
        
        xSpeed = 3;
        ySpeed = 3;

        canvas = drawingCanvas;
    }
    /**
     * The point here is to make a ball that:
     *  moves inside the box
     *  bounces off the walls so the ball always stays inside the box
     *  change the initial speed of the ball so it's random and
     *      not strictly horizontal or vertical
     *  change the initial speed of the ball to random and 
     *      inside the box
     *  include a parameter determining how many balls are in the box
     */
    
    /**
     * Draw this ball at its current position onto the canvas.
     **/
    public void draw()
    {
        canvas.setForegroundColor(color);
        canvas.fillCircle(xPosition, yPosition, diameter);
    }

    /**
     * Erase this ball at its current position.
     **/
    public void erase()
    {
        canvas.eraseCircle(xPosition, yPosition, diameter);
        //this means that the canvas is being erased because the canvas is told to erase the ball loaction, regardless of position
        //the way to ensure the canvas doesn't visibly chip away is to have the canvas redraw itself after every iteration of the erase method
    }    

    /**
     * Move this ball according to its position and speed and redraw.
     **/
    public void move()
    {
        // remove from canvas at the current position
        erase();
            
        // compute new position
        xPosition = xPosition + xSpeed;
        yPosition = yPosition + ySpeed;
  
        // figure out if it has hit the left or right wall
        if(xPosition <= myBox.getRightWall() - diameter){
            xSpeed = xSpeed * -1;
        }
        if(xPosition >= myBox.getLeftWall()){
            xSpeed = xSpeed * -1;
        }
        // figure out if it has hit the top or bottom wall
        if(yPosition >= myBox.getBottomWall() - diameter){
            ySpeed = ySpeed * -1;
        }
        if(yPosition <= myBox.getTopWall()){
            ySpeed = ySpeed * -1;
        }
        /*
         * These statements don't seem incorrect, but when we run the code, the ball seems to freeze and bounce back and forth
         * over the course of a very small section. We don't know why. We changed the sarting position of the ball to make sure
         * it started inside the canvas, to avoid it hitting the outer corner and bouncing away, but now we have a new problem.
         */
        draw();
    }  

    /**
     * return the horizontal position of this ball
     */
    public int getXPosition()
    {
        return xPosition;
    }

    /**
     * return the vertical position of this ball
     */
    public int getYPosition()
    {
        return yPosition;
    }
}

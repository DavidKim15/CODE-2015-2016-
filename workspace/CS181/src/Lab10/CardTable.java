/*I pledge my honor that I have abided by the Stevens Honor System.
-David Kim
*/

//File   : GUI-lowlevel/cards1/cards/CardTable.java
//Purpose: This is just a JComponent for drawing the cards that are
//       showing on the table.
//
//Author : Fred Swartz - February 19, 2007 - Placed in public domain.
//
//Enhancements:
//     * Use model. Currently, it is initialized with a whole deck of cards,
//       but instead it should be intialized with a "model" which
//       it should interrogate (calling model methods) to find out what
//       should be displayed.
//     * Similarly, actions by the mouse might be used to set things in the
//       model, Perhaps by where it's dragged to, or double-clicked, or
//       with pop-up menu, or ...

package Lab10;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//////////////////////////////////////////////////////////////////////CardTable
public class CardTable extends JComponent implements MouseListener,
     MouseMotionListener {
	 public static final String NAME = "David Kim";

 //================================================================ constants
 private static final Color BACKGROUND_COLOR = Color.GREEN;
 private static final int   TABLE_WIDTH       = 800;    // Pixels.
 private static final int   TABLE_HEIGHT      = 400;    // Pixels.
 
 //=================================================================== fields
 //... Initial image coords.
 private int _initX     = 0;   // x coord - set from drag
 private int _initY     = 0;   // y coord - set from drag
 
 //... Position in image of mouse press to make dragging look better.
 private int _dragFromX = 0;  // Displacement inside image of mouse press.
 private int _dragFromY = 0;
 
 private Card[] _deck;        // Should really be in a model, but ...

 private Card   _currentCard = null;  // Current selected card.
 
 private ImageIcon[] faceCard = new ImageIcon[52]; 		//array of ImageIcons that will hold the card images
 private int INDEX = -1;								//this will keep track of the index of the cards in _deck
 
 //============================================================== constructor
 public CardTable(Card[] deck) {
     
	 _deck = deck;                // Should be passed a model.
     
     //... Initialize graphics
     setPreferredSize(new Dimension(TABLE_WIDTH, TABLE_HEIGHT));
     setBackground(Color.blue);
     
     //... Add mouse listeners.
     addMouseListener(this);
     addMouseMotionListener(this);
     
     //------------------------------------------------------  moving the images of the cards into the array faceCard[]
     int i = 0;
     String suits = "shdc";
     String faces = "a23456789tjqk";
     for (int suit=0; suit < suits.length(); suit++) {
         for (int face=0; face < faces.length(); face++) {
             //... Get the image from the images subdirectory.
             String imagePath = "./src/Lab10/images/" + faces.charAt(face) +
                     suits.charAt(suit) + ".gif";
             ImageIcon img = new ImageIcon(imagePath);
             faceCard[i] = img;
             i++;
         }
     }
     //------------------------------------------------------
 }
 
 //=========================================================== paintComponent
 @Override
 public void paintComponent(Graphics g) {
     //... Paint background
     int width = getWidth();
     int height = getHeight();
     g.setColor(BACKGROUND_COLOR);
     g.fillRect(0, 0, width, height);
     
     //... Display the cards, starting with the first array element.
     //    The array order defines the z-axis depth.
     for (Card c : _deck) {
         if (null != c)   c.draw(g, this);
     }
 }
 
 //====================================================== method mousePressed
 // Check to see if press is within any card.
 // If it is,
 // * Set _currentCard so mouseDragged knows what to drag.
 // * Record where in the image (relative to the upper left coordinates)
 //   the mouse was clicked, because it looks best if we drag from there.
 // TODO: Move the card to the last position so that it displays on top.
 public void mousePressed(MouseEvent e) {
     int x = e.getX();   // Save the x coord of the click
     int y = e.getY();   // Save the y coord of the click
     int index = 0;
     
     //... Find card image this is in.  Check from top down.
     _currentCard = null;  // Assume not in any image.
     for (int crd=_deck.length-1; crd>=0; crd--) {
         Card testCard = _deck[crd];
         index = crd;
         if ((null != testCard) && (testCard.contains(x, y))) {
             //... Found, remember this card for dragging.
             _dragFromX = x - testCard.getX();  // how far from left
             _dragFromY = y - testCard.getY();  // how far from top
             _currentCard = testCard;  // Remember what we're dragging.
             break;        // Stop when we find the first match.
         }
     }
     
     if (_currentCard != null) {				//this boolean condition checks if the mouse actually clicked on a card
         INDEX = index;							//INDEX will keep track of the index of the current card
    	 
	     Card holder = _currentCard;			//holder keeps the data of the currentCard while the loop moves elements of _deck over
	     ImageIcon image = faceCard[index];		//image keeps the image of the card while the loop moves elements of faceCard over
	     for (int i = index; i < 51; i++){
	    	 _deck[i] = _deck[i+1];
	    	 faceCard[i] = faceCard[i+1];
	     }
	     _deck[51] = holder;					//sets the last element of both arrays to holder and image respectively
	     faceCard[51] = image;					//overall effect: the card will be moved to the most positive position in the z-axis
     }

 }
 
 //============================================================= mouseDragged
 // Set x,y to mouse position and repaint.
 public void mouseDragged(MouseEvent e) {
     if (_currentCard != null) {   // Non-null if pressed inside card image.
         
         int newX = e.getX() - _dragFromX;
         int newY = e.getY() - _dragFromY;
         
         //--- Don't move the image off the screen sides
         newX = Math.max(newX, 0);
         newX = Math.min(newX, getWidth() - _currentCard.getWidth());
         
         //--- Don't move the image off top or bottom
         newY = Math.max(newY, 0);
         newY = Math.min(newY, getHeight() - _currentCard.getHeight());
         
         _currentCard.moveTo(newX, newY);
         
         this.repaint(); // Repaint because position changed.
     }
 }
//======================================================= method mouseClicked
 public void mouseClicked(MouseEvent e) {
	 if (e.getClickCount() == 2){
		 if (INDEX!=-1){							//boolean condition checks if a card has even been clicked
	     if (_deck[INDEX].getFlipped() == false) {
	    	 _deck[INDEX].replaceImg(new ImageIcon ("./src/Lab10/images/b.gif"));	//replaces image with the card back image that was given
	    	 _deck[INDEX].setFlipped(true);											//change the boolean to true because the card has been flipped
	     }
	     else {
	    	//gets the original image of the current card by matching the index of the current card with the index of its picture
	    	 _deck[INDEX].replaceImg(faceCard[INDEX]);		
	    	 _deck[INDEX].setFlipped(false);		//change the boolean to false because the card is upright
	     }
		 }
         this.repaint(); // Repaint because image changed.

	 }
 } 
 
 //======================================================= method mouseExited
 // Turn off dragging if mouse exits panel.
 public void mouseExited(MouseEvent e) {
	_currentCard = null;
 }

 public void mouseReleased(MouseEvent e) {
	_currentCard = null;
 }
 
 //=============================================== Ignore other mouse events.
 public void mouseMoved   (MouseEvent e) {}  // ignore these events
 public void mouseEntered(MouseEvent e) {}  // ignore these events
 



}

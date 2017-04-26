//==============================================================================
// Program  : P03GUITeamMove.java
// Author   : David Fielder
// Date     : 09/12/2012
// Abstract : Puts a gui user interface on project 2.
//==============================================================================
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
public class P03GUITeamMove
{
  public static void main( String args[] )
  {
    SolutionP03topTenGuiWindow myWindow = new SolutionP03topTenGuiWindow();
    myWindow.setSize(550,550);
    myWindow.setLocation(300,200);
    myWindow.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    myWindow.setVisible(true);
  }
}
//==============================================================================
class SolutionP03topTenGuiWindow extends JFrame
{
  JRadioButton teamRBtn[]  = new JRadioButton[20];
  JButton      teamBtn[]   = new JButton[20];
  String       team[]      = {"A","BB","CCC","DDDD","EEEEE","FFFFFF","GGGGGGG",
                              "HHHHHHHH","IIIIIIIII","JJJJJJJJJJ","KKKKKKKKKKK","LLLLLLLLLLLL",
                              "MMMMMMMMMMMMM","NNNNNNNNNNNNNN","OOOOOOOOOOOOOOO","PPPPPPPPPPPPPPPP",
                              "QQQQQQQQQQQQQQQQQ","RRRRRRRRRRRRRRRRRR","SSSSSSSSSSSSSSSSSSS",
                              "TTTTTTTTTTTTTTTTTTTT"};

  public SolutionP03topTenGuiWindow()
  {
    super("Top Twenty Teams");
    int i;
    MoveHandler moveHdlr = new MoveHandler();
    ButtonGroup teamGrp  = new ButtonGroup();
    JPanel      pnlWest  = new JPanel();
    JPanel      pnlEast  = new JPanel();
    pnlWest.setLayout(new GridLayout(20,1));
    pnlEast.setLayout(new GridLayout(20,1));

    for(i=0;i<20;i++)
      {
        teamRBtn[i]  = new JRadioButton(""+(i+1));
        teamRBtn[i].setFont(new Font("Courier", Font.PLAIN, 10));
        pnlWest.add(teamRBtn[i]);
        teamGrp.add(teamRBtn[i]);

        teamBtn[i] = new JButton(team[i]);
        teamBtn[i].setFont(new Font("Courier", Font.PLAIN, 10));
        teamBtn[i].setHorizontalAlignment(SwingConstants.LEFT);
        teamBtn[i].addActionListener(moveHdlr);
        pnlEast.add(teamBtn[i]);
      }

    setLayout(new BorderLayout());
    add(pnlWest,BorderLayout.WEST);
    add(pnlEast,BorderLayout.CENTER);
  }
  //----------------------------------------------------------------------------
  private class MoveHandler implements ActionListener
  {
    public void actionPerformed(ActionEvent event)
    {
      int    i;
      int    from = 0;
      int    to;
      String nameAtToSlot;

      nameAtToSlot = event.getActionCommand();  // to button

      i = 0;
      while(!nameAtToSlot.equals(teamBtn[i].getText()))
        i++;

      to = i;

      for(i=0;i<20;i++)
        if(teamRBtn[i].isSelected())  //from radiobutton
          from = i;

      moveTeam(teamBtn,from,to);

      teamRBtn[to].setSelected(true);
    }
  }//end inner class

 //------------------------------------------------------------------------move
  public static void moveTeam(JButton teamBtn[],int from,int to)
  {
    String names[] = new String[20];
    int i;
    for(i=0;i<20;i++)
      names[i] = teamBtn[i].getText();

    if(from>to)                           
      moveUp(names,from,to);
    else
      moveDown(names,from,to);

    for(i=0;i<20;i++)
      teamBtn[i].setText(names[i]);
  }

  //---------------------------------------------------------------------move up
  public static void moveUp(String names[],int from,int to)
  {
    String temp;
    int    row;

    temp = names[from];            

    for(row=from;row>to;row--)            
      names[row] = names[row-1];

    names[row] = temp;            
  }
  //-------------------------------------------------------------------move down
  public static void moveDown(String names[],int from,int to)
  {
    String temp;
    int    row;

    temp = names[from];           

    for(row=from;row<to;row++)            
      names[row] = names[row+1];

    names[row] = temp;            
  }
  //---------------------------------------------------------------------display
}//end outer class

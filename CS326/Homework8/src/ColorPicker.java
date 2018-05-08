import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.*;
import java.awt.*;
import java.io.*;


public class ColorPicker extends JFrame
{
    // Variables
    protected MakeRectangle rect;
    protected JList colorList;
    protected JButton RedPlus;
    protected JButton RedMinus;
    protected JButton GreenPlus;
    protected JButton GreenMinus;
    protected JButton BluePlus;
    protected JButton BlueMinus;
    protected JTextField RedField;
    protected JTextField GreenField;
    protected JTextField BlueField;
    protected JLabel RedLabel;
    protected JLabel GreenLabel;
    protected JLabel BlueLabel;
    protected JButton Save;
    protected JButton Reset;

    static protected int selection;
    static protected String[] colors;
    static protected int[][] RGB;

    private static ColorPicker myColorPicker;

    // Main Method
    public static void main(String argv[]) throws IOException
    {
       myColorPicker = new ColorPicker("Color Picker");
    }

    // Constructor for Color Picker
    public ColorPicker(String title) throws IOException
    {
        colors = new String[11];
        RGB = new int[11][3];
        ReadFromFile();
        // Create Window
        this.setTitle(title);
        setSize(400, 300);
        addWindowListener(new WindowEliminator());

        // Create Objects and Labels
        rect = new MakeRectangle();
        Save = new JButton();
        Save.setText("Save");
        Save.addActionListener(new ActionHandler());
        Reset = new JButton();
        Reset.setText("Reset");
        Reset.addActionListener(new ActionHandler());

        //Create List
        colorList = new JList();
        colorList.setListData(colors);
        colorList.addListSelectionListener(new ListHandler());

        //Create Red Objects
        RedLabel = new JLabel();
        RedField = new JTextField();
        RedPlus = new JButton();
        RedMinus = new JButton();

        //Initialize Red Objects
        RedLabel.setText("Red: ");
        RedField.addActionListener(new ActionHandler());
        RedPlus.setText("+");
        RedPlus.addActionListener(new ActionHandler());
        RedMinus.setText("-");
        RedMinus.addActionListener(new ActionHandler());

        //Create Green Objects
        GreenLabel = new JLabel();
        GreenField = new JTextField();
        GreenPlus = new JButton();
        GreenMinus = new JButton();

        //Initialize Green Objects
        GreenLabel.setText("Green: ");
        GreenField.addActionListener(new ActionHandler());
        GreenPlus.setText("+");
        GreenPlus.addActionListener(new ActionHandler());
        GreenMinus.setText("-");
        GreenMinus.addActionListener(new ActionHandler());

        //Create Blue Objects
        BlueLabel = new JLabel();
        BlueField = new JTextField();
        BluePlus = new JButton();
        BlueMinus = new JButton();

        //Initialize Blue Objects
        BlueLabel.setText("Blue: ");
        BlueField.addActionListener(new ActionHandler());
        BluePlus.setText("+");
        BluePlus.addActionListener(new ActionHandler());
        BlueMinus.setText("-");
        BlueMinus.addActionListener(new ActionHandler());

        // Add Objects to the Layout
        getContentPane().setLayout(null);
        getContentPane().add(rect);
        getContentPane().add(colorList);
        getContentPane().add(Save);
        getContentPane().add(Reset);

        //Add Red Objects to the Layout
        getContentPane().add(RedLabel);
        getContentPane().add(RedField);
        getContentPane().add(RedPlus);
        getContentPane().add(RedMinus);

        //Add Green Objects to the Layout
        getContentPane().add(GreenLabel);
        getContentPane().add(GreenField);
        getContentPane().add(GreenPlus);
        getContentPane().add(GreenMinus);

        //Add Blue Objects to the Layout
        getContentPane().add(BlueLabel);
        getContentPane().add(BlueField);
        getContentPane().add(BluePlus);
        getContentPane().add(BlueMinus);

        //Set the Bounds of each Object
        rect.setBounds(10,10,215,150);
        colorList.setBounds(230,10,150, 300);
        Save.setBounds(40,250,80,30);
        Reset.setBounds(110,250,80,30);

        //Set Red Object Bounds
        RedLabel.setBounds(10,165,50,30);
        RedField.setBounds(50,165,50,30);
        RedMinus.setBounds(95,165,50,30);
        RedPlus.setBounds(135,165,50,30);

        //Set Green Object Bounds
        GreenLabel.setBounds(10,190,50,30);
        GreenField.setBounds(50,190,50,30);
        GreenMinus.setBounds(95,190,50,30);
        GreenPlus.setBounds(135,190,50,30);

        //Set Blue Object Bounds
        BlueLabel.setBounds(10,215,50,30);
        BlueField.setBounds(50,215,50,30);
        BlueMinus.setBounds(95,215,50,30);
        BluePlus.setBounds(135, 215,50,30);

        setVisible(true);
    }

    // Data Structure to hold information about Colors


    public void ReadFromFile()
    {

        FileInputStream istream = null;
        InputStreamReader ireader = null;
        StreamTokenizer itoken = null;
        try
        {
            istream = new FileInputStream("colors.txt");
            ireader = new InputStreamReader(istream);
            itoken = new StreamTokenizer(ireader);

            itoken.nextToken();


            for(int i = 0; i < 11; ++i)
            {
                colors[i]= itoken.sval;
                itoken.nextToken();
                for (int j = 0; j < 3; ++j)
                {
                    RGB[i][j] = (int) itoken.nval;
                    itoken.nextToken();
                }

            }
            istream.close();
        }
        catch (IOException e)
        {
            System.out.println("Error in File Input");
        }


    }

    //Write the current values
    public void WriteToFile()
    {
        FileOutputStream ostream = null;
        PrintWriter owriter = null;

        try
        {
            ostream = new FileOutputStream("colors.txt");
            owriter = new PrintWriter(ostream);
            for (int i = 0; i < 11; ++i)
            {
                owriter.println(colors[i] + " " + RGB[i][0] + " " + RGB[i][1] + " " + RGB[i][2] );
            }

            owriter.flush();
            ostream.close();
        }
        catch(IOException e)
        {
            System.out.println("Error in File Output");
        }
    }

    private class ListHandler implements ListSelectionListener
    {

        @Override
        public void valueChanged(ListSelectionEvent listSelectionEvent)
        {
            if (listSelectionEvent.getSource() == colorList)
            {
                selection = colorList.getSelectedIndex();

                // Set the values in the text fields
                RedField.setText(Integer.toString(RGB[selection][0]));
                GreenField.setText(Integer.toString(RGB[selection][1]));
                BlueField.setText(Integer.toString(RGB[selection][2]));

                repaint();
            }
        }
    }

    private class ActionHandler implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            // Increment the red value by 5
            if (e.getSource() == RedPlus)
            {
                RGB[selection][0] += 5;

                if (RGB[selection][0] > 255)
                {
                    RGB[selection][0] = 255;
                }

                //Set the text
                RedField.setText(Integer.toString(RGB[selection][0]));
                myColorPicker.setTitle("Color Picker*");
                repaint();
            }

            //Decrement the red value by 5
            if (e.getSource() == RedMinus)
            {
                RGB[selection][0] -= 5;

                if (RGB[selection][0] < 0)
                {
                    RGB[selection][0] = 0;
                }

                RedField.setText(Integer.toString(RGB[selection][0]));
                myColorPicker.setTitle("Color Picker*");
                repaint();
            }

            //Increment the green value by 5
            if (e.getSource() == GreenPlus)
            {
                RGB[selection][1] += 5;

                if (RGB[selection][1] > 255)
                {
                    RGB[selection][1] = 255;
                }

                GreenField.setText(Integer.toString(RGB[selection][1]));
                myColorPicker.setTitle("Color Picker*");
                repaint();
            }

            //Decrement the green value by 5
            if (e.getSource() == GreenMinus)
            {
                RGB[selection][1] -= 5;

                if (RGB[selection][1] < 0)
                {
                    RGB[selection][1] = 0;
                }

                GreenField.setText(Integer.toString(RGB[selection][1]));
                myColorPicker.setTitle("Color Picker*");
                repaint();
            }

            //Increment the blue value by 5
            if (e.getSource() == BluePlus)
            {
                RGB[selection][2] += 5;

                if (RGB[selection][2] > 255)
                {
                    RGB[selection][2] = 255;
                }

                BlueField.setText(Integer.toString(RGB[selection][2]));
                myColorPicker.setTitle("Color Picker*");
                repaint();
            }

            //Decrement the value of Blue by 5
            if (e.getSource() == BlueMinus)
            {
                RGB[selection][2] -= 5;

                if (RGB[selection][2] < 0)
                {
                    RGB[selection][2] = 0;
                }

                BlueField.setText(Integer.toString(RGB[selection][2]));
                myColorPicker.setTitle("Color Picker*");
                repaint();
            }

            // Save the current values to the file
            if (e.getSource() == Save)
            {
                WriteToFile();
                myColorPicker.setTitle("Color Picker");
            }

            // Reset the Values from the File
            if(e.getSource() == Reset)
            {
                ReadFromFile();
                RedField.setText(Integer.toString(RGB[selection][0]));
                GreenField.setText(Integer.toString(RGB[selection][1]));
                BlueField.setText(Integer.toString(RGB[selection][2]));
                myColorPicker.setTitle("Color Picker");
                repaint();
            }
        }
    }

    public class MakeRectangle extends JComponent
    {
        //draw the rectangle
        public void paint(Graphics g)
        {
            float [] HSBValues = new float [3];
            // Change the values to Hue Saturation
            Color.RGBtoHSB(RGB[selection][0],RGB[selection][1],RGB[selection][2],HSBValues);
            g.setColor(Color.getHSBColor(HSBValues[0],HSBValues[1],HSBValues[2]));
            g.fillRect(1 ,1,215,150);
        }
    }

    //Close the window when the X is pressed
    private class WindowEliminator extends WindowAdapter
    {
        public void WindowCloser(WindowEvent e)
        {
            WriteToFile();
            System.exit(0);
        }
    }
}


import javax.swing.*; //containers and components
import javax.swing.border.LineBorder;
import java.awt.*; //parameters
import java.awt.event.*; //events

public class Calculator implements ActionListener{

    JFrame jFrame; //top level of swing
    JTextField jTextField;

    JButton[] numButtons = new JButton[10];
    JButton[] funcButtons = new JButton[8];

    JButton addButton;
    JButton subButton;
    JButton mulButton;
    JButton divButton;

    JButton dotButton;
    JButton eqButton;
    JButton negButton;
    JButton clearButton;

    //logic
    double accumulator = 0.0;
    String selectedOperation = " "; //+, -, *, /

    //constructor
    public Calculator(){
        //Main calculator frame
        jFrame = new JFrame("Java Calculator | For Practicing");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(460,460);
        jFrame.setLayout(null);
        jFrame.setResizable(false);

        //Main text field
        Font textFieldFont = new Font("Tahoma", Font.BOLD, 35);
        jTextField = new JTextField();
        jTextField.setBounds(40, 40, 370, 60);
        jTextField.setFont(textFieldFont);
        jTextField.setHorizontalAlignment(SwingConstants.RIGHT);
        jTextField.setEditable(false);
        LineBorder border = new LineBorder( new Color(157, 159,170), 2);
        jTextField.setBorder(border);
        jTextField.setText("");
        //add to JFrame
        jFrame.add(jTextField);

        //buttons
        Font buttonsFont = new Font("Cambria Math", Font.PLAIN, 25);
        for(int i = 0; i < numButtons.length; i++){
            String buttonName = String.valueOf(i);
            JButton newButton = new JButton(buttonName);
            newButton.setFont(buttonsFont);
            newButton.addActionListener(this);
            newButton.setFocusable(false);

            numButtons[i] = newButton;
        }

        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");

         dotButton = new JButton(".");
         eqButton = new JButton("=");
         negButton = new JButton("-");
         clearButton = new JButton("C");

         //functions to functions array
        funcButtons[0] = addButton;
        funcButtons[1] = subButton;
        funcButtons[2] =  mulButton;
        funcButtons[3] = divButton;

        funcButtons[4] = dotButton;
        funcButtons[5] = eqButton ;
        funcButtons[6] = negButton;
        funcButtons[7] = clearButton;

        for(int i = 0; i < funcButtons.length; i++){
            funcButtons[i].setFont(buttonsFont);
            funcButtons[i].addActionListener(this);
            funcButtons[i].setFocusable(false);
        }

        //place, show buttons
        JPanel jPanel = new JPanel();
        jPanel.setBounds(40, 120, 370, 210);
        jPanel.setLayout(new GridLayout(4, 4, 10, 10));
        //jPanel.setBackground(Color.gray);

        jPanel.add(numButtons[7]);
        jPanel.add(numButtons[8]);
        jPanel.add(numButtons[9]);
        jPanel.add(clearButton);
        jPanel.add(numButtons[4]);
        jPanel.add(numButtons[5]);
        jPanel.add(numButtons[6]);
        jPanel.add(divButton);
        jPanel.add(numButtons[1]);
        jPanel.add(numButtons[2]);
        jPanel.add(numButtons[3]);
        jPanel.add(mulButton);
        jPanel.add(negButton);
        jPanel.add(numButtons[0]);
        jPanel.add(dotButton);
        jPanel.add(subButton);

        jFrame.add(jPanel);

        eqButton.setBounds(40, 340, 275, 50);
        jFrame.add(eqButton);

        addButton.setBounds(325, 340, 85, 50);
        jFrame.add(addButton);

        //show calculator interface
        jFrame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e){
        JButton source = (JButton) e.getSource();

        //for numeric buttons
        for(int i = 0; i < numButtons.length; i++){
            if(source == numButtons[i]){
                String number = source.getText();

                jTextField.setText(jTextField.getText() + number);
            }
        }
        //for the dot button
        if(source == dotButton && !jTextField.getText().contains(".")){
            jTextField.setText(jTextField.getText()+".");
        }

        //for operations + - * /

        //addition
        if(source == addButton && !jTextField.getText().equals("")){
            accumulator = Double.parseDouble(jTextField.getText());
            selectedOperation = "+";
            jTextField.setText("");
        }
        //subtraction
        if(source == subButton && !jTextField.getText().equals("")){
            accumulator = Double.parseDouble(jTextField.getText());
            selectedOperation = "-";
            jTextField.setText("");
        }
        //division
        if(source == divButton && !jTextField.getText().equals("")){
            accumulator = Double.parseDouble(jTextField.getText());
            selectedOperation = "/";
            jTextField.setText("");
        }

        //multiplication
        if(source == mulButton && !jTextField.getText().equals("")){
            accumulator = Double.parseDouble(jTextField.getText());
            selectedOperation = "*";
            jTextField.setText("");
        }

        //negative value button
        if(source == negButton && !jTextField.getText().equals("")){
            double newValue = Double.parseDouble(jTextField.getText());
            String newText = String.valueOf(newValue);
            jTextField.setText(newText);
        }

        //clear button
        if(source == clearButton){
            jTextField.setText("");
            accumulator = 0.0;
            selectedOperation = "";
        }

        //equate button
        if(source == eqButton && !selectedOperation.equals("") && !jTextField.getText().equals("")){
            double textFieldValue = Double.parseDouble(jTextField.getText());

            double newValue = 0.0;
            switch (selectedOperation){
                case "+":
                    newValue = accumulator + textFieldValue;
                    break;
                case "-":
                    newValue = accumulator - textFieldValue;
                    break;
                case "*":
                    newValue = accumulator * textFieldValue;
                    break;
                case "/":
                    newValue = accumulator / textFieldValue;
                    break;
            }
            accumulator = 0.0;
            selectedOperation = "";
            jTextField.setText(String.valueOf(newValue));
        }
    }
}

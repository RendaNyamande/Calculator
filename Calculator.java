import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import java.util.ArrayList;

public class Calculator extends JFrame implements ActionListener{

    private static final long serialVersionUID = 1L;
    public static final int WIDTH = 600;
    public static final int HEIGHT = 400;
    public static final int NUMBER_OF_CHAR = 10;
    public static String operand = "";

    private JTextField operandField;
    private JTextField resultField;
    private double result = 0.0;
    private ArrayList<Double> array = new ArrayList<Double>();
    private ArrayList<String> arr = new ArrayList<String>();
    private String number = "";
    private String prevAns = "0.0";

    public Calculator(){
        setTitle("Calculator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLayout(new GridLayout(3, 1));

        //panel1
        JPanel fieldsPanel = new JPanel();
        fieldsPanel.setBackground(Color.LIGHT_GRAY);
        fieldsPanel.setLayout(new GridLayout(1,2));

        operandField = new JTextField(NUMBER_OF_CHAR);
        operandField.setBackground(Color.GRAY);
        operandField.setEditable(false);
        fieldsPanel.add(operandField);
        resultField = new JTextField(NUMBER_OF_CHAR);
        resultField.setBackground(Color.GRAY);
        resultField.setEditable(false);
        fieldsPanel.add(resultField);
        add(fieldsPanel);

        //panel2

        JPanel panel2 = new JPanel(); 
        panel2.setBackground(Color.LIGHT_GRAY);
        panel2.setLayout(new FlowLayout());
        
        JButton clearButton = new JButton("Clear");        
        clearButton.addActionListener(this);
        panel2.add(clearButton);
        JButton resetButton = new JButton("Reset");        
        resetButton.addActionListener(this);
        panel2.add(resetButton);
        add(panel2);

        //panel3
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.LIGHT_GRAY);
        buttonPanel.setLayout(new GridLayout(4,5));
        
        //row1
        JButton seven = new JButton("7");
        seven.addActionListener(this);
        buttonPanel.add(seven);
        JButton eight= new JButton("8");
        eight.addActionListener(this);
        buttonPanel.add(eight);
        JButton nine = new JButton("9");
        nine.addActionListener(this);
        buttonPanel.add(nine);
        JButton multiplicationButton = new JButton("*");
        multiplicationButton.addActionListener(this);
        buttonPanel.add(multiplicationButton);
        JButton divisionButton = new JButton("/");
        divisionButton.addActionListener(this);
        buttonPanel.add(divisionButton);

        //row2
        JButton four = new JButton("4");
        four.addActionListener(this);
        buttonPanel.add(four);
        JButton five = new JButton("5");
        five.addActionListener(this);
        buttonPanel.add(five);
        JButton six = new JButton("6");
        six.addActionListener(this);
        buttonPanel.add(six);
        JButton additionButton = new JButton("+");
        additionButton.addActionListener(this);
        buttonPanel.add(additionButton);
        JButton subtractionButton = new JButton("-");
        subtractionButton.addActionListener(this);
        buttonPanel.add(subtractionButton);

        //row3
        JButton one = new JButton("1");
        one.addActionListener(this);
        buttonPanel.add(one);
        JButton two = new JButton("2");
        two.addActionListener(this);
        buttonPanel.add(two);
        JButton three = new JButton("3");
        three.addActionListener(this);
        buttonPanel.add(three);
        JButton ans = new JButton("Ans");
        ans.addActionListener(this);
        buttonPanel.add(ans);
        JButton equalsButton = new JButton("=");
        equalsButton.addActionListener(this);
        buttonPanel.add(equalsButton);

        //row4
        JButton zero = new JButton("0");
        zero.addActionListener(this);
        buttonPanel.add(zero);
        JButton dotButton = new JButton(".");
        dotButton.addActionListener(this);
        buttonPanel.add(dotButton);
        JButton deleteCharButton = new JButton("<-");
        deleteCharButton.addActionListener(this);
        buttonPanel.add(deleteCharButton);
        
        add(buttonPanel);
    }
    public void initialize(double n, double result){
        if(result == 0.0){
            result = n;
        }
    }
    public String deleteChar(String s){
        String ans = "";
        String str = s.substring(s.length()-1);
        if(str.equals("s")){
            ans = s.substring(0, s.length()-3);
        }
        else{
            ans = s.substring(0, s.length()-1);
        }
        return ans;
    }
    public boolean numCheck(String s){
        //
        try{
            if(checker(s)){
                double j = Double.parseDouble(s);
                return true;
            }
            else {
                int i = Integer.parseInt(s);
                return true;
            }
        }
        catch(Exception e){
            //
            return false;
        }
    }
    public void comp() throws DivisionByZeroException{
        int count = 0;
        double num = 0.0;
        for(int i = 0; i<arr.size(); i++){
            if(arr.get(i).equals("*")){
                num = array.get(i) * array.get(i+1);
                array.remove(i);
                array.remove(i);
                array.add(i, num);
                array.add(0,0.0);
                count++;
            }
        }
        for(int i = 0; i<arr.size(); i++){
            if(arr.get(i).equals("/")){
                if(array.get(i+1)==0.0){
                    throw new DivisionByZeroException();
                }
                num = array.get(i) / array.get(i+1);
                array.remove(i);
                array.remove(i);
                array.add(i, num);
                array.add(0,0.0);
                count++;
            }
        }
        for(int i = 0; i<arr.size(); i++){
            if(arr.get(i).equals("+")){
                num = array.get(i) + array.get(i+1);
                array.remove(i);
                array.remove(i);
                array.add(i, num);
                array.add(0,0.0);
                count++;
            }
            else if(arr.get(i).equals("-")){
                num = array.get(i) - array.get(i+1);
                array.remove(i);
                array.remove(i);
                array.add(i, num);
                array.add(0,0.0);
                count++;
            }
        }
        result = array.get(count);
        prevAns = Double.toString(result);
    }
    public String cutter(String s){
        String[] arr1 = s.split("");
        String[] arr2 = new String[(arr1.length)-2];
        for(int i = 0; i<arr1.length-2; i++){
            arr2[i] = arr1[i]; 
        }
        String ans = "";
        for(int i = 0; i<arr2.length; i++){
            ans += arr2[i];
        }
        return ans;
    }
    
    public boolean checker(String s) {
        String[] arr1 = s.split("");
        boolean bool = false;
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i].equals(".")) {
                bool = true;
            }
        }
        return bool;
    }
    private static double stringToDouble(String s){
        return Double.parseDouble(s.trim());
    }
    public static void main(String[] args){
        Calculator c = new Calculator();
        c.setVisible(true);
    }
    public void actionPerformed(ActionEvent e){
        try{
            assumingCorrectNumberFormats(e);
        }
        catch(NumberFormatException e2){
            operandField.setText("Error: Reenter Number.");
        }
    }
    public void assumingCorrectNumberFormats(ActionEvent e){
        String actionCommand = e.getActionCommand();

        if (actionCommand.equals("0")) {
            if(number.equals("=")){
                operandField.setText("0");
                initialize(0.0, result);
                number="0";
                arr.clear();
                array.clear();
            }
            else{
                operandField.setText(operandField.getText()+"0");
                initialize(0.0, result);
                number+="0";
            }
        } 
        else if (actionCommand.equals("1")) {
            if(number.equals("=")){
                operandField.setText("1");
                initialize(1.0, result);
                number="1";
                arr.clear();
                array.clear();
            }
            else{
                operandField.setText(operandField.getText()+"1");
                initialize(1.0, result);
                number+="1";
            }
        } 
        else if (actionCommand.equals("2")) {
            if(number.equals("=")){
                operandField.setText("2");
                initialize(2.0, result);
                number="2";
                arr.clear();
                array.clear();
            }
            else{
                operandField.setText(operandField.getText()+"2");
                initialize(2.0, result);
                number += "2";
            }
        } 
        else if (actionCommand.equals("3")) {
            if(number.equals("=")){
                operandField.setText("3");
                initialize(3.0, result);
                number="3";
                arr.clear();
                array.clear();
            }
            else{
                operandField.setText(operandField.getText()+"3");
                initialize(3.0, result);
                number+="3";
            }
        } 
        else if (actionCommand.equals("4")) {
            if(number.equals("=")){
                operandField.setText("4");
                initialize(3.0, result);
                number="4";
                arr.clear();
                array.clear();
            }
            else{
                operandField.setText(operandField.getText()+"4");
                initialize(4.0, result);
                number+="4";
            }
        } 
        else if (actionCommand.equals("5")) {
            if(number.equals("=")){
                operandField.setText("5");
                initialize(5.0, result);
                number="5";
                arr.clear();
                array.clear();
            }
            else{
                operandField.setText(operandField.getText()+"5");
                initialize(5.0, result);
                number+="5";
            }
        } 
        else if (actionCommand.equals("6")) {
            if(number.equals("=")){
                operandField.setText("6");
                initialize(6.0, result);
                number="6";
                arr.clear();
                array.clear();
            }
            else{
                operandField.setText(operandField.getText()+"6");
                initialize(6.0, result);
                number+="6";
            }
        } 
        else if (actionCommand.equals("7")) {
            if(number.equals("=")){
                operandField.setText("7");
                initialize(7.0, result);
                number="7";
                arr.clear();
                array.clear();
            }
            else{
                operandField.setText(operandField.getText()+"7");
                initialize(7.0, result);
                number+="7";
            }
        } 
        else if (actionCommand.equals("8")) {
            if(number.equals("=")){
                operandField.setText("8");
                initialize(8.0, result);
                number="8";
                arr.clear();
                array.clear();
            }
            else{
                operandField.setText(operandField.getText()+"8");
                initialize(8.0, result);
                number+="8";
            }
        } 
        else if (actionCommand.equals("9")) {
            if(number.equals("=")){
                operandField.setText("9");
                initialize(9.0, result);
                number="9";
                arr.clear();
                array.clear();
            }
            else{
                operandField.setText(operandField.getText()+"9");
                initialize(9.0, result);
                number+="9";
            }
        } 
        else if (actionCommand.equals("*")) {
            if(number.equals("=")){
                operandField.setText("Ans*");
                initialize(stringToDouble(prevAns), result);
                number = cutter(prevAns);
                arr.clear();
                array.clear();

                arr.add("*");
                if(checker(number) == true){
                    array.add(stringToDouble(number));
                }
                else{
                    array.add(stringToDouble(number+".0"));
                }
                number = "";
            }
            else{
                operandField.setText(operandField.getText()+"*");
                arr.add("*");
                if(checker(number) == true){
                    array.add(stringToDouble(number));
                }
                else{
                    array.add(stringToDouble(number+".0"));
                }
                number = "";
            }
        } 
        else if (actionCommand.equals("/")) {
            if(number.equals("=")){
                operandField.setText("Ans/");
                initialize(stringToDouble(prevAns), result);
                number = cutter(prevAns);
                arr.clear();
                array.clear();

                arr.add("/");
                if(checker(number) == true){
                    array.add(stringToDouble(number));
                }
                else{
                    array.add(stringToDouble(number+".0"));
                }
                number = "";
            }
            else{
                operandField.setText(operandField.getText()+"/");
                arr.add("/");
                if(checker(number) == true){
                    array.add(stringToDouble(number));
                }
                else{
                    array.add(stringToDouble(number+".0"));
                }
                number = "";
            }
        } 
        else if (actionCommand.equals("+")) {
            if(number.equals("=")){
                operandField.setText("Ans+");
                initialize(stringToDouble(prevAns), result);
                number = cutter(prevAns);
                arr.clear();
                array.clear();

                arr.add("+");
                if(checker(number) == true){
                    array.add(stringToDouble(number));
                }
                else{
                    array.add(stringToDouble(number+".0"));
                }
                number = "";
            }
            else{
                operandField.setText(operandField.getText()+"+");
                arr.add("+");
                if(checker(number) == true){
                    array.add(stringToDouble(number));
                }
                else{
                    array.add(stringToDouble(number+".0"));
                }
                number = "";
            }
        } 
        else if (actionCommand.equals("-")) {
            if(number.equals("=")){
                operandField.setText("Ans-");
                initialize(stringToDouble(prevAns), result);
                number = cutter(prevAns);
                arr.clear();
                array.clear();

                arr.add("-");
                if(checker(number) == true){
                    array.add(stringToDouble(number));
                }
                else{
                    array.add(stringToDouble(number+".0"));
                }
                number = "";
            }
            else{
                operandField.setText(operandField.getText()+"-");
                arr.add("-");
                if(checker(number) == true){
                    array.add(stringToDouble(number));
                }
                else{
                    array.add(stringToDouble(number+".0"));
                }
                number = "";
            }
        } 
        else if (actionCommand.equals("Ans")) {
            if(number.equals("=")){
                operandField.setText("Ans");
                initialize(stringToDouble(prevAns), result);
                number = cutter(prevAns);
                arr.clear();
                array.clear();
            }
            else{
                operandField.setText(operandField.getText()+"Ans");
                initialize(stringToDouble(prevAns), result);
                number+= cutter(prevAns);
            }
        } 
        else if (actionCommand.equals("=")) {
            if(checker(number) == true){
                array.add(stringToDouble(number));
            }
            else{
                array.add(stringToDouble(number+".0"));
            }
            try{
                comp();
            }
            catch(DivisionByZeroException e2){
                operandField.setText(e2.getMessage());
            }
            resultField.setText(Double.toString(result));
            number = "=";
        } 
        else if (actionCommand.equals(".")) {
            if(number.equals("=")){
                operandField.setText(".");
                number = "0.";
                arr.clear();
                array.clear();
            }
            else{
                operandField.setText(operandField.getText()+".");
                number += "0.";
            }
        } 
        else if (actionCommand.equals("<-")) {
            String s = operandField.getText();
            int len = s.length();
            String last;
            if(!s.equals("")){
                last = s.substring(len-1);
                operandField.setText(deleteChar(s));
                if(numCheck(last)){
                    if(!number.equals("")){
                        number = deleteChar(number);
                    }
                    else{
                        array.remove(array.size()-1);
                    }
                }
                else{
                    arr.remove(arr.size()-1);
                }
            }
        } 
        else if (actionCommand.equals("Reset")) {
            number = "0.0";
            array.clear();
            arr.clear();
            resultField.setText(number);
            number = "";
        } 
        else if (actionCommand.equals("Clear")) {
            operandField.setText("");
        } 
        else{
            operandField.setText("Unexpected error");
        }
    }
}
class DivisionByZeroException extends Exception{
    public DivisionByZeroException(){
        super("Error: You divided by zero! Reset and try again");
    }
    public DivisionByZeroException(String message){
        super(message);
    }
}

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    private JTextField inputField1, inputField2;
    private JTextArea resultArea;
    private JButton primeButton, oddEvenButton, lcmButton, hcfButton, addButton, subtractButton, multiplyButton, divideButton;
    private JButton decimalButton, factorialButton, squareButton, sqrtButton, quizButton, clearButton;

    public Main() {
        setTitle("Math Game for Kids");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Input fields
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 2));
        inputPanel.add(new JLabel("Number 1:"));
        inputField1 = new JTextField();
        inputPanel.add(inputField1);
        inputPanel.add(new JLabel("Number 2:"));
        inputField2 = new JTextField();
        inputPanel.add(inputField2);

        // Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6, 2));
        primeButton = new JButton("Check Prime");
        oddEvenButton = new JButton("Check Odd/Even");
        lcmButton = new JButton("Calculate LCM");
        hcfButton = new JButton("Calculate HCF");
        addButton = new JButton("Add");
        subtractButton = new JButton("Subtract");
        multiplyButton = new JButton("Multiply");
        divideButton = new JButton("Divide");
        decimalButton = new JButton("Check Decimal");
        factorialButton = new JButton("Calculate Factorial");
        squareButton = new JButton("Calculate Square");
        sqrtButton = new JButton("Calculate Square Root");
        quizButton = new JButton("Random Math Quiz");
        clearButton = new JButton("Clear");

        buttonPanel.add(primeButton);
        buttonPanel.add(oddEvenButton);
        buttonPanel.add(lcmButton);
        buttonPanel.add(hcfButton);
        buttonPanel.add(addButton);
        buttonPanel.add(subtractButton);
        buttonPanel.add(multiplyButton);
        buttonPanel.add(divideButton);
        buttonPanel.add(decimalButton);
        buttonPanel.add(factorialButton);
        buttonPanel.add(squareButton);
        buttonPanel.add(sqrtButton);
        buttonPanel.add(quizButton);
        buttonPanel.add(clearButton);

        // Result area
        resultArea = new JTextArea();
        resultArea.setEditable(false);

        // Add components to the frame
        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(new JScrollPane(resultArea), BorderLayout.SOUTH);

        // Add action listeners
        primeButton.addActionListener(e -> checkPrime());
        oddEvenButton.addActionListener(e -> checkOddEven());
        lcmButton.addActionListener(e -> calculateLCM());
        hcfButton.addActionListener(e -> calculateHCF());
        addButton.addActionListener(e -> performOperation("+"));
        subtractButton.addActionListener(e -> performOperation("-"));
        multiplyButton.addActionListener(e -> performOperation("*"));
        divideButton.addActionListener(e -> performOperation("/"));
        decimalButton.addActionListener(e -> checkDecimal());
        factorialButton.addActionListener(e -> calculateFactorial());
        squareButton.addActionListener(e -> calculateSquare());
        sqrtButton.addActionListener(e -> calculateSquareRoot());
        quizButton.addActionListener(e -> generateMathQuiz());
        clearButton.addActionListener(e -> clearFields());
    }

    private void checkPrime() {
        try {
            int num = Integer.parseInt(inputField1.getText());
            boolean isPrime = num > 1;
            for (int i = 2; i <= Math.sqrt(num); i++) {
                if (num % i == 0) {
                    isPrime = false;
                    break;
                }
            }
            resultArea.setText(num + (isPrime ? " is a prime number." : " is not a prime number."));
        } catch (NumberFormatException e) {
            resultArea.setText("Invalid input. Please enter a valid number.");
        }
    }

    private void checkOddEven() {
        try {
            int num = Integer.parseInt(inputField1.getText());
            resultArea.setText(num + " is " + (num % 2 == 0 ? "even." : "odd."));
        } catch (NumberFormatException e) {
            resultArea.setText("Invalid input. Please enter a valid number.");
        }
    }

    private void calculateLCM() {
        try {
            int num1 = Integer.parseInt(inputField1.getText());
            int num2 = Integer.parseInt(inputField2.getText());
            int lcm = Math.max(num1, num2);
            while (lcm % num1 != 0 || lcm % num2 != 0) {
                lcm++;
            }
            resultArea.setText("LCM of " + num1 + " and " + num2 + " is " + lcm);
        } catch (NumberFormatException e) {
            resultArea.setText("Invalid input. Please enter valid numbers.");
        }
    }

    private void calculateHCF() {
        try {
            int num1 = Integer.parseInt(inputField1.getText());
            int num2 = Integer.parseInt(inputField2.getText());
            int hcf = 1;
            for (int i = 1; i <= num1 && i <= num2; i++) {
                if (num1 % i == 0 && num2 % i == 0) {
                    hcf = i;
                }
            }
            resultArea.setText("HCF of " + num1 + " and " + num2 + " is " + hcf);
        } catch (NumberFormatException e) {
            resultArea.setText("Invalid input. Please enter valid numbers.");
        }
    }

    private void performOperation(String operator) {
        try {
            double num1 = Double.parseDouble(inputField1.getText());
            double num2 = Double.parseDouble(inputField2.getText());
            double result = 0;
            String workings = num1 + " " + operator + " " + num2 + " = ";
            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 == 0) {
                        resultArea.setText("Cannot divide by zero.");
                        return;
                    }
                    result = num1 / num2;
                    break;
            }
            resultArea.setText(workings + result);
        } catch (NumberFormatException e) {
            resultArea.setText("Invalid input. Please enter valid numbers.");
        }
    }

    private void checkDecimal() {
        try {
            double num = Double.parseDouble(inputField1.getText());
            resultArea.setText(num + (num % 1 == 0 ? " is not a decimal." : " is a decimal."));
        } catch (NumberFormatException e) {
            resultArea.setText("Invalid input. Please enter a valid number.");
        }
    }

    private void calculateFactorial() {
        try {
            int num = Integer.parseInt(inputField1.getText());
            if (num < 0) {
                resultArea.setText("Factorial is not defined for negative numbers.");
                return;
            }
            long factorial = 1;
            for (int i = 1; i <= num; i++) {
                factorial *= i;
            }
            resultArea.setText("Factorial of " + num + " is " + factorial);
        } catch (NumberFormatException e) {
            resultArea.setText("Invalid input. Please enter a valid number.");
        }
    }

    private void calculateSquare() {
        try {
            double num = Double.parseDouble(inputField1.getText());
            resultArea.setText("Square of " + num + " is " + (num * num));
        } catch (NumberFormatException e) {
            resultArea.setText("Invalid input. Please enter a valid number.");
        }
    }

    private void calculateSquareRoot() {
        try {
            double num = Double.parseDouble(inputField1.getText());
            if (num < 0) {
                resultArea.setText("Square root is not defined for negative numbers.");
                return;
            }
            resultArea.setText("Square root of " + num + " is " + Math.sqrt(num));
        } catch (NumberFormatException e) {
            resultArea.setText("Invalid input. Please enter a valid number.");
        }
    }

    private void generateMathQuiz() {
        int num1 = (int) (Math.random() * 100);
        int num2 = (int) (Math.random() * 100);
        String[] operators = {"+", "-", "*", "/"};
        String operator = operators[(int) (Math.random() * operators.length)];
        String question = "What is " + num1 + " " + operator + " " + num2 + "?";
        resultArea.setText(question);
    }

    private void clearFields() {
        inputField1.setText("");
        inputField2.setText("");
        resultArea.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}

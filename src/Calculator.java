import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener {

    JTextField inputField;
    double num1, num2;
    char operator;
    boolean isOperatorClicked = false;
    boolean isDarkMode = false;

    JButton toggleModeButton;
    JPanel buttonPanel;
    JButton[] allButtons;

    // Constructor: sets up JowieCalculator 💜
    Calculator() {
        setTitle("JowieCalculator 💜 ");
        setSize(400, 560);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Input field
        inputField = new JTextField();
        inputField.setBounds(30, 30, 320, 50);
        inputField.setEditable(false);
        inputField.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        inputField.setHorizontalAlignment(JTextField.RIGHT);
        add(inputField);

        // Button labels
        String[] buttonLabels = {
                "7", "8", "9", "÷",
                "4", "5", "6", "×",
                "1", "2", "3", "−",
                "0", ".", "=", "+"
        };

        // Panel for buttons
        buttonPanel = new JPanel();
        buttonPanel.setBounds(30, 100, 320, 300);
        buttonPanel.setLayout(new GridLayout(4, 4, 10, 10));
        add(buttonPanel);

        allButtons = new JButton[buttonLabels.length];

        for (int i = 0; i < buttonLabels.length; i++) {
            allButtons[i] = new JButton(buttonLabels[i]);
            allButtons[i].setFont(new Font("Comic Sans MS", Font.BOLD, 20));
            allButtons[i].setFocusPainted(false);
            allButtons[i].setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
            allButtons[i].addActionListener(this);
            buttonPanel.add(allButtons[i]);
        }

        // Clear Button ✨
        JButton clearButton = new JButton("Clear ");
        clearButton.setBounds(30, 420, 150, 40);
        clearButton.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        clearButton.addActionListener(e -> {
            inputField.setText("");
            isOperatorClicked = false;
        });
        add(clearButton);

        // Toggle mode button 🌙
        toggleModeButton = new JButton("Dark Mode ");
        toggleModeButton.setBounds(200, 420, 150, 40);
        toggleModeButton.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        toggleModeButton.addActionListener(e -> toggleDarkMode());
        add(toggleModeButton);

        // Start in light mode
        applyLightMode();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        // Number or dot
        if ((command.charAt(0) >= '0' && command.charAt(0) <= '9') || command.equals(".")) {
            inputField.setText(inputField.getText() + command);
        }
        // Equals
        else if (command.equals("=")) {
            try {
                String[] parts = inputField.getText().split(" ");
                if (parts.length != 3) {
                    inputField.setText("Oops 😅");
                    return;
                }

                num1 = Double.parseDouble(parts[0]);
                operator = parts[1].charAt(0);
                num2 = Double.parseDouble(parts[2]);

                double result = 0;

                switch (operator) {
                    case '+': result = num1 + num2; break;
                    case '−': result = num1 - num2; break;
                    case '×': result = num1 * num2; break;
                    case '÷':
                        if (num2 == 0) {
                            inputField.setText("Nope 🙅‍♀️");
                            return;
                        }
                        result = num1 / num2;
                        break;
                }

                // Show as whole number if no decimal needed
                if (result == (int) result) {
                    inputField.setText(String.valueOf((int) result));
                } else {
                    inputField.setText(String.valueOf(result));
                }

            } catch (Exception ex) {
                inputField.setText("Error 💔");
            }
        }
        // Operator

        else {
            if (!isOperatorClicked) {
                inputField.setText(inputField.getText() + " " + command + " ");
                isOperatorClicked = true;
            }
        }
    }

    // Dark mode toggle 🌙
    //Light mode toggle ☀️
    private void toggleDarkMode() {
        isDarkMode = !isDarkMode;

        if (isDarkMode) {
            applyDarkMode();
            toggleModeButton.setText("Light Mode ");
        } else {
            applyLightMode();
            toggleModeButton.setText("Dark Mode ");
        }
    }

    // 🌞 Light mode with purple accents
    private void applyLightMode() {
        getContentPane().setBackground(new Color(255, 240, 250)); // soft background
        inputField.setBackground(Color.WHITE);
        inputField.setForeground(new Color(138, 43, 226)); // purple
        inputField.setCaretColor(new Color(138, 43, 226));
        buttonPanel.setBackground(new Color(255, 240, 250));

        for (JButton btn : allButtons) {
            btn.setBackground(Color.WHITE);
            btn.setForeground(new Color(138, 43, 226)); // bold purple
        }
    }

    // 🌚 Dark mode
    private void applyDarkMode() {
        getContentPane().setBackground(new Color(30, 30, 60));
        inputField.setBackground(new Color(50, 50, 70));
        inputField.setForeground(Color.WHITE);
        inputField.setCaretColor(Color.WHITE);
        buttonPanel.setBackground(new Color(30, 30, 60));

        for (JButton btn : allButtons) {
            btn.setBackground(new Color(70, 60, 100));
            btn.setForeground(Color.WHITE);
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LengthConverterGUI2 extends JFrame {
    private JTextField inputField;
    private JComboBox<String> fromUnitComboBox, toUnitComboBox;
    private JButton convertButton;
    private JLabel resultLabel;

    public LengthConverterGUI2() {
        setTitle("Length Converter");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initializeComponents();
        createLayout();

        setVisible(true);
    }

    private void initializeComponents() {
        inputField = new JTextField(10);

        fromUnitComboBox = new JComboBox<>(new String[]{"mm", "cm", "m", "km", "in", "ft", "nautical miles", "mils"});
        toUnitComboBox = new JComboBox<>(new String[]{"mm", "cm", "m", "km", "in", "ft", "nautical miles", "mils"});

        convertButton = new JButton("Convert");
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convert();
            }
        });

        resultLabel = new JLabel("Result: ");
    }

    private void createLayout() {
        setLayout(new FlowLayout());

        add(new JLabel("Enter Length: "));
        add(inputField);
        add(fromUnitComboBox);
        add(new JLabel(" to "));
        add(toUnitComboBox);
        add(convertButton);
        add(resultLabel);
    }

    private void convert() {
        try {
            double inputValue = Double.parseDouble(inputField.getText());
            String fromUnit = (String) fromUnitComboBox.getSelectedItem();
            String toUnit = (String) toUnitComboBox.getSelectedItem();
            double result = performConversion(inputValue, fromUnit, toUnit);
            resultLabel.setText("Result: " + result + " " + toUnit);
        } catch (NumberFormatException ex) {
            resultLabel.setText("Invalid input. Please enter a valid number.");
        }
    }

    private double performConversion(double inputValue, String fromUnit, String toUnit) {
        double result = 0;

        // Conversion logic
        switch (fromUnit) {
            case "mm":
                result = inputValue / getConversionFactor(toUnit);
                break;
            case "cm":
                result = inputValue / getConversionFactor(toUnit) * 10;
                break;
            case "m":
                result = inputValue / getConversionFactor(toUnit) * 1000;
                break;
            case "km":
                result = inputValue / getConversionFactor(toUnit) * 1000000;
                break;
            case "in":
                result = inputValue / getConversionFactor(toUnit) * 25.4;
                break;
            case "ft":
                result = inputValue / getConversionFactor(toUnit) * 304.8;
                break;
            case "nautical miles":
                result = inputValue / getConversionFactor(toUnit) * 1852000;
                break;
            case "mils":
                result = inputValue / getConversionFactor(toUnit) * 0.0254;
                break;
        }

        return result;
    }

    private double getConversionFactor(String unit) {
        // Conversion factors relative to millimeters
        switch (unit) {
            case "mm":
                return 1;
            case "cm":
                return 10;
            case "m":
                return 1000;
            case "km":
                return 1000000;
            case "in":
                return 25.4;
            case "ft":
                return 304.8;
            case "nautical miles":
                return 1852000;
            case "mils":
                return 0.0254;
            default:
                return 1;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LengthConverterGUI2());
    }
}
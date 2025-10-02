import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Main extends JFrame {

    private static final double COMMISSION_PERCENT = 33.0;

    private final JTextField rateField;
    private final JTextField robuxField;
    private final JLabel resultLabel;

    public Main() {
        setTitle("Income Calculator");
        setSize(350, 220);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());

        rateField = new JTextField("0.58", 10);
        robuxField = new JTextField(10);
        resultLabel = new JLabel("Result: ");

        addComponent(panel, new JLabel("Rate per unit:"), 0, 0, 1, GridBagConstraints.LINE_END, GridBagConstraints.NONE);
        addComponent(panel, rateField, 1, 0, 1, GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL);

        addComponent(panel, new JLabel("Robux:"), 0, 1, 1, GridBagConstraints.LINE_END, GridBagConstraints.NONE);
        addComponent(panel, robuxField, 1, 1, 1, GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL);

        addComponent(panel, new JLabel("-30% Roblox tax"), 0, 2, 1, GridBagConstraints.LINE_START, GridBagConstraints.NONE);
        addComponent(panel, new JLabel("-3% Funpay fee"), 1, 2, 1, GridBagConstraints.LINE_START, GridBagConstraints.NONE);

        addComponent(panel, resultLabel, 0, 3, 2, GridBagConstraints.CENTER, GridBagConstraints.NONE);

        JButton btn = new JButton("Calculate");
        addComponent(panel, btn, 0, 4, 2, GridBagConstraints.CENTER, GridBagConstraints.NONE);

        add(panel);

        btn.addActionListener(e -> calculate());
    }

    private void addComponent(JPanel panel, Component comp, int x, int y, int width, int anchor, int fill) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = x;
        c.gridy = y;
        c.gridwidth = width;
        c.gridheight = 1;
        c.anchor = anchor;
        c.fill = fill;
        c.insets = new Insets(5, 5, 5, 5);
        panel.add(comp, c);
    }

    private void calculate() {
        try {
            double rate = Double.parseDouble(rateField.getText());
            int units = Integer.parseInt(robuxField.getText());

            if (rate <= 0 || units <= 0) {
                JOptionPane.showMessageDialog(this, "Please enter positive numbers", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double total = rate * units;
            double commission = total * (COMMISSION_PERCENT / 100);
            int result = (int) Math.round(total - commission);

            DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
            symbols.setGroupingSeparator('.');

            DecimalFormat formatter = new DecimalFormat("#,###", symbols);
            String formattedResult = formatter.format(result);

            resultLabel.setText("Result: " + formattedResult + " R$");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter numbers correctly", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class DrawingUI extends JFrame {

    private DrawingPanel panel;

    private JTextField x1Field, y1Field, x2Field, y2Field;
    private JTextField xcField, ycField, radiusField;

    public DrawingUI() {

        setTitle("Scan Conversion Algorithms - Step by Step");
        setSize(1000, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        panel = new DrawingPanel();
        add(panel, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(3, 1));

        // ===== LINE PANEL =====
        JPanel linePanel = new JPanel();
        linePanel.setBorder(BorderFactory.createTitledBorder("Line (Bresenham)"));

        x1Field = new JTextField(5);
        y1Field = new JTextField(5);
        x2Field = new JTextField(5);
        y2Field = new JTextField(5);

        linePanel.add(new JLabel("x1:"));
        linePanel.add(x1Field);
        linePanel.add(new JLabel("y1:"));
        linePanel.add(y1Field);
        linePanel.add(new JLabel("x2:"));
        linePanel.add(x2Field);
        linePanel.add(new JLabel("y2:"));
        linePanel.add(y2Field);

        JButton drawLineBtn = new JButton("Draw Line");
        linePanel.add(drawLineBtn);

        // ===== CIRCLE PANEL =====
        JPanel circlePanel = new JPanel();
        circlePanel.setBorder(BorderFactory.createTitledBorder("Circle (Midpoint)"));

        xcField = new JTextField(5);
        ycField = new JTextField(5);
        radiusField = new JTextField(5);

        circlePanel.add(new JLabel("Center X:"));
        circlePanel.add(xcField);
        circlePanel.add(new JLabel("Center Y:"));
        circlePanel.add(ycField);
        circlePanel.add(new JLabel("Radius:"));
        circlePanel.add(radiusField);

        JButton drawCircleBtn = new JButton("Draw Circle");
        circlePanel.add(drawCircleBtn);

        // ===== CLEAR PANEL =====
        JPanel clearPanel = new JPanel();
        JButton clearBtn = new JButton("Clear");
        clearPanel.add(clearBtn);

        controlPanel.add(linePanel);
        controlPanel.add(circlePanel);
        controlPanel.add(clearPanel);

        add(controlPanel, BorderLayout.SOUTH);

        // ===== BUTTON ACTIONS =====

        drawLineBtn.addActionListener(e -> {
            try {
                int x1 = Integer.parseInt(x1Field.getText());
                int y1 = Integer.parseInt(y1Field.getText());
                int x2 = Integer.parseInt(x2Field.getText());
                int y2 = Integer.parseInt(y2Field.getText());

                panel.animateLine(x1, y1, x2, y2);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid Line Input!");
            }
        });

        drawCircleBtn.addActionListener(e -> {
            try {
                int xc = Integer.parseInt(xcField.getText());
                int yc = Integer.parseInt(ycField.getText());
                int r = Integer.parseInt(radiusField.getText());

                panel.animateCircle(xc, yc, r);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid Circle Input!");
            }
        });

        clearBtn.addActionListener(e -> panel.clearDrawing());

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DrawingUI());
    }
}


// ================= DRAWING PANEL =================

class DrawingPanel extends JPanel {

    private ArrayList<Point> pixels = new ArrayList<>();
    private Timer timer;
    private int stepIndex = 0;

    public DrawingPanel() {
        setBackground(Color.WHITE);
    }

    // ================= BRESENHAM LINE =================
    public void animateLine(int x1, int y1, int x2, int y2) {

        pixels.clear();
        stepIndex = 0;

        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);

        int sx = (x1 < x2) ? 1 : -1;
        int sy = (y1 < y2) ? 1 : -1;

        int err = dx - dy;

        while (true) {

            pixels.add(new Point(x1, y1));

            if (x1 == x2 && y1 == y2)
                break;

            int e2 = 2 * err;

            if (e2 > -dy) {
                err -= dy;
                x1 += sx;
            }

            if (e2 < dx) {
                err += dx;
                y1 += sy;
            }
        }

        startAnimation();
    }

    // ================= MIDPOINT CIRCLE =================
    public void animateCircle(int xc, int yc, int radius) {

        pixels.clear();
        stepIndex = 0;

        int x = 0;
        int y = radius;
        int p = 1 - radius;

        while (x <= y) {

            addCirclePoints(xc, yc, x, y);

            x++;

            if (p < 0) {
                p += 2 * x + 1;
            } else {
                y--;
                p += 2 * (x - y) + 1;
            }
        }

        startAnimation();
    }

    private void addCirclePoints(int xc, int yc, int x, int y) {

        pixels.add(new Point(xc + x, yc + y));
        pixels.add(new Point(xc - x, yc + y));
        pixels.add(new Point(xc + x, yc - y));
        pixels.add(new Point(xc - x, yc - y));

        pixels.add(new Point(xc + y, yc + x));
        pixels.add(new Point(xc - y, yc + x));
        pixels.add(new Point(xc + y, yc - x));
        pixels.add(new Point(xc - y, yc - x));
    }

    private void startAnimation() {

        if (timer != null)
            timer.stop();

        timer = new Timer(20, e -> {

            if (stepIndex < pixels.size()) {
                stepIndex++;
                repaint();
            } else {
                timer.stop();
            }
        });

        timer.start();
    }

    public void clearDrawing() {
        if (timer != null)
            timer.stop();
        pixels.clear();
        repaint();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);

        for (int i = 0; i < stepIndex; i++) {
            Point p = pixels.get(i);
            g.fillRect(p.x, p.y, 2, 2); // bigger pixel for visibility
        }
    }
}

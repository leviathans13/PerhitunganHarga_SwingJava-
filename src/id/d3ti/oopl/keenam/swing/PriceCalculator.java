package id.d3ti.oopl.keenam.swing;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PriceCalculator extends JFrame implements ActionListener, ChangeListener {
    private ButtonGroup bg;
    private JRadioButton rbMember, rbNonMember;
    private JComboBox<String> cbProducts;
    private JButton bCalculate;
    private JTextField tfQuantity;
    private JLabel lblQuantity, lblTotal;
    private JMenuBar mb;
    private JMenu m;
    private final String[] products = {"PC", "Laptop", "Monitor"};
    
    public PriceCalculator() {
        super("Program Perhitungan Harga");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);
        
        // Container utama
        Container cp = getContentPane();
        cp.setLayout(new FlowLayout());
        
        // Menu Bar
        mb = new JMenuBar();
        m = new JMenu("Exit");
        m.addChangeListener(this);
        mb.add(m);
        setJMenuBar(mb);
        
        // Radio Buttons
        rbMember = new JRadioButton("Member (Diskon 10%)");
        rbNonMember = new JRadioButton("Non Member (Tanpa Diskon)");
        bg = new ButtonGroup();
        bg.add(rbMember);
        bg.add(rbNonMember);
        rbNonMember.setSelected(true);
        cp.add(rbMember);
        cp.add(rbNonMember);
        
        // Combo Box Produk
        cp.add(new JLabel("Pilih Produk: "));
        cbProducts = new JComboBox<>(products);
        cp.add(cbProducts);
        
        // Input Jumlah
        lblQuantity = new JLabel("Jumlah: ");
        tfQuantity = new JTextField(10);
        cp.add(lblQuantity);
        cp.add(tfQuantity);
        
        // Tombol Hitung
        bCalculate = new JButton("Hitung Total");
        bCalculate.addActionListener(this);
        cp.add(bCalculate);
        
        // Label Total
        lblTotal = new JLabel("Total: Rp. 0");
        cp.add(lblTotal);
        
        setVisible(true);
    }
    
    private int getProductPrice(String product) {
        switch(product) {
            case "PC": return 2000000;
            case "Laptop": return 5000000;
            case "Monitor": return 900000;
            default: return 0;
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == bCalculate) {
            try {
                String selectedProduct = cbProducts.getSelectedItem().toString();
                int quantity = Integer.parseInt(tfQuantity.getText());
                
                int basePrice = getProductPrice(selectedProduct);
                double total = basePrice * quantity;
                
                if(rbMember.isSelected()) {
                    total = total * 0.9; // Diskon 10%
                }
                
                lblTotal.setText(String.format("Total: Rp. %,.0f", total));
                
            } catch(NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, 
                    "Masukkan jumlah yang valid!", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    @Override
    public void stateChanged(ChangeEvent e) {
        if(e.getSource() == m) {
            System.exit(0);
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PriceCalculator());
    }
}
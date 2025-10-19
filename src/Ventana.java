import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Ventana {
    private JPanel Principal;
    private JLabel lblCedula;
    private JLabel lblEntradas;
    private JLabel lblPelicula;
    private JTextField txtCedula;
    private JComboBox cbmPeliculas;
    private JTextField txtEntradas;
    private JButton btnComprar;
    private JTextArea txtResumen;
    private JLabel lblPiratas;
    private JLabel lblNaruto;
    private JLabel lblAntman;
    private ColaCompra colaCompra = new ColaCompra();

    public Ventana() {
        btnComprar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // --- 1. OBTENER DATOS ---
                    String pelicula = cbmPeliculas.getSelectedItem().toString();
                    String cedulaStr = txtCedula.getText().trim(); //trim = metodo clase string, elimina espacios en blanco.
                    int entradas = Integer.parseInt(txtEntradas.getText());
                    float valor = entradas * 5;

                    List<Compra> compras = colaCompra.listaCompras();

                    if (entradas > 5 || entradas <= 0) {
                        JOptionPane.showMessageDialog(null, "No se puede comprar más de 5 entradas (ni 0 o menos).");
                        return;
                    }

                    for (int i = 0; i < cedulaStr.length(); i++) {
                        if (!Character.isDigit(cedulaStr.charAt(i))) {
                            JOptionPane.showMessageDialog(null, "La cédula solo puede tener números.");
                            return;
                        }
                    }
                    if (cedulaStr.length()<6 || cedulaStr.length()>10) {
                        JOptionPane.showMessageDialog(null, "Formato de cédula incorrecto.");
                        return;
                    }

                    long cedula = Long.parseLong(cedulaStr); //Para no tener NumberFormatException (desborde límite int), pasamos a long.
                    if (cedula <0 || cedula>2000000000) {
                        JOptionPane.showMessageDialog(null, "La cédula debe estar entre 0 y 2 000 000 000.");
                        return;
                    }

                    for (Compra c : compras) {
                        if (c.getCedula() == cedula) {
                            JOptionPane.showMessageDialog(null, "No se puede ingresar el mismo cliente (cédula ya registrada).");
                            return;
                        }
                    }

                    int totalEntradasVendidas = 0;
                    for (Compra p : compras) {
                        totalEntradasVendidas += p.getEntradas();
                    }

                    if ((totalEntradasVendidas + entradas) > 17) {
                        int disponibles = 17 - totalEntradasVendidas;
                        JOptionPane.showMessageDialog(null, "Ya no hay suficientes entradas. Solo quedan " + disponibles + " asientos.");
                        return;
                    }

                    colaCompra.encolar(new Compra(entradas, pelicula, valor, (int)cedula));
                    JOptionPane.showMessageDialog(null, "Se realizó la compra. ¡Felicidades!");
                    txtResumen.setText(colaCompra.toString());
                    compras = colaCompra.listaCompras();
                    int total=0;
                    int total1=0;
                    int total2=0;
                    for (Compra compra:compras){
                        if (compra.getNombrep().equals("Piratas")){
                            total += compra.getEntradas()*5;
                            lblPiratas.setText("Piratas: $"+ total);
                        } else if (compra.getNombrep().equals("Naruto")) {
                            total1 += compra.getEntradas()*5;
                            lblNaruto.setText("Naruto: $"+ total1);
                        } else if (compra.getNombrep().equals("Antman")) {
                            total2 += compra.getEntradas()*5;
                            lblAntman.setText("Antman: $"+ total2);
                        }
                    }


                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(null, "Error: La cédula y el número de entradas deben ser números válidos.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Ocurrió un error inesperado: " + ex.getMessage());
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Ventana");
        frame.setContentPane(new Ventana().Principal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

package Vista;

import Modelo.Encoder.ArithCoder;

import javax.swing.*;
import Controlador.Controlador;

public class MainWindow extends JFrame{
    private JPanel panel1;
    private JTextField elementInput;
    private JButton encodeButton;
    private JButton decodeButton;
    private JTextField probInput;

    public MainWindow() {
        setTitle("ArithCoder");
        setSize(500, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra la ventana en la pantalla
        setContentPane(panel1);

        // Agregar componentes, paneles, etc., según sea necesario

        setVisible(true); // Hacer visible la ventana
        encodeButton.addActionListener(e -> {
            //Get String inputs
            String elements = elementInput.getText();
            String probs = probInput.getText();
            System.out.println("Elements input: "+elements);
            System.out.println("Probs input: "+elements);
            //Send the input to Controlador to prepare an ArithCoder
            Controlador controlador = Controlador.getInstance();
            ArithCoder arithCoder = controlador.prepareCoder(elements, probs);
            System.out.println(arithCoder.toString());

            //Ask the user for the message to encode and show the result
            String texto = JOptionPane.showInputDialog(null, "Enter the message to be encoded:");
            JOptionPane.showMessageDialog(null, "encoded value: " + controlador.encodeToString(arithCoder,texto));
        });
        decodeButton.addActionListener(e -> {
            //Get String inputs
            String elements = elementInput.getText();
            String probs = probInput.getText();

            //Send the input to Controlador to prepare an ArithCoder
            Controlador controlador = Controlador.getInstance();
            ArithCoder arithCoder = controlador.prepareCoder(elements, probs);

            //Ask the user for the coded message
            String texto = JOptionPane.showInputDialog(null, "Enter the message to be encoded:");

            //Ask the expected message length
            String numeroStr = JOptionPane.showInputDialog(null, "Enter the message length:");
            int numero = Integer.parseInt(numeroStr);

            //Show the decoded message
            JOptionPane.showMessageDialog(null, "The decoded value is: "+ controlador.decode(arithCoder, Double.parseDouble(texto), numero));

        });
    }

}

package Vista;

import Modelo.Encoder.ArithCoder;

import javax.swing.*;
import Controlador.Controlador;

import java.math.BigDecimal;

public class MainWindow extends JFrame{
    private JPanel panel1;
    private JTextField elementInput;
    private JButton encodeButton;
    private JButton decodeButton;
    private JTextField probInput;
    private JComboBox comboBox1;

    public MainWindow() {
        setUpPanel();
        addListeners();
    }
    private void setUpPanel(){
        setTitle("ArithCoder");
        setSize(500, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(panel1);
        setVisible(true);
    }
    private void addListeners(){
        //------------------------------------------------------------------

        //ENCODE BUTTON
        encodeButton.addActionListener(e -> {
            //INPUTS FROM GUI
            String elements = elementInput.getText();
            String probs = probInput.getText();
            String mode = comboBox1.getSelectedItem().toString();

            //Send the input to Controlador to prepare an ArithCoder
            Controlador controlador = Controlador.getInstance();
            try{
                //PREPARE CODER
                ArithCoder arithCoder = controlador.prepareCoder(elements, probs);
                System.out.println(arithCoder.toString());

                //GET MSG AND EXECUTE APP

                String texto = JOptionPane.showInputDialog(null, "Enter the message to be encoded:");
                JOptionPane.showMessageDialog(null, "encoded value: " + controlador.encodeToString(arithCoder,texto, mode));



            }catch(Exception ex){
                JOptionPane.showMessageDialog(this, "Error, check input fields");
            }
        });

        //------------------------------------------------------------------
        //DECODE BUTTON
        decodeButton.addActionListener(e -> {
            //INPUTS FROM GUI
            String elements = elementInput.getText();
            String probs = probInput.getText();
            String mode = comboBox1.getSelectedItem().toString();

            //Send the input to Controlador to prepare an ArithCoder
            Controlador controlador = Controlador.getInstance();
            try{
                //PREPARE CODER
                ArithCoder arithCoder = controlador.prepareCoder(elements, probs);
                System.out.println(arithCoder.toString());

                //GET MSG AND EXECUTE APP
                String texto = JOptionPane.showInputDialog(null, "Enter the message to be decoded:");
                String numeroStr = JOptionPane.showInputDialog(null, "Enter the message length:");
                int numero = Integer.parseInt(numeroStr);
                JOptionPane.showMessageDialog(null, "The decoded value is: "+ controlador.decode(arithCoder, texto, numero, mode));

            }catch(Exception ex){
                JOptionPane.showMessageDialog(this, "Error, check input fields");
            }
        });
        //------------------------------------------------------------------
    }

}

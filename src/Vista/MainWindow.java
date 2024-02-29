package Vista;

import Alphabet.Alphabet;
import Encoder.ArithCoder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
        encodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String elements = elementInput.getText();
                String probs = probInput.getText();
                ArithCoder arithCoder = prepareCoder(elements, probs);

                String texto = JOptionPane.showInputDialog(null, "Enter the message to be encoded:");
                String result = ""+arithCoder.encode(texto);
                JOptionPane.showMessageDialog(null, "encoded value: " + result);
            }
        });
        decodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String elements = elementInput.getText();
                String probs = probInput.getText();
                ArithCoder arithCoder = prepareCoder(elements, probs);

                String texto = JOptionPane.showInputDialog(null, "Enter the message to be encoded:");

                // Solicitar al usuario un número
                String numeroStr = JOptionPane.showInputDialog(null, "Ingrese un número:");

                // Convertir el número de texto a un entero
                int numero = Integer.parseInt(numeroStr);
                JOptionPane.showMessageDialog(null, "Cadena de texto ingresada: " + texto + "\nNúmero ingresado: " + numero);

            }
        });
    }

    private ArithCoder prepareCoder(String elements, String probs) {
        if(elements.charAt(0)!='['||elements.charAt(elements.length()-1)!=']'||probs.charAt(0)!='['||probs.charAt(probs.length()-1)!=']'){
            //Not correct format
            JOptionPane.showMessageDialog(null,"Not correct input format, check steps 1 and 2");
        }

        elements= elements.substring(1, elements.length()-2);
        probs= probs.substring(1, probs.length()-2);
        try{
            Alphabet alphabet = new Alphabet(elements.length());
            ArrayList<String> elemList = new ArrayList<>();
            ArrayList<Long> propList = new ArrayList<>();

            for(int i = 0;i<elements.length();i++){
                elemList.add(elements.charAt(i)+"");
            }
            String[] probArray = probs.split(" ");
            // Convertir las partes en valores numéricos y mostrarlos
            for (String tempProb : probArray) {
                propList.add((long)Long.valueOf(tempProb));
            }
            alphabet.setAlphabetElements(elemList);
            alphabet.setAlphabetProportion(propList);
            return new ArithCoder(alphabet);
        }catch (IllegalArgumentException ex){
            JOptionPane.showMessageDialog(null,"Alphabet and probs number of elements are different, check steps 1 and 2");
            ex.printStackTrace();
        }
        return null;
    }
}

package myProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is used for ...
 * @autor F
 * @version v.1.0.0 date:21/11/2021
 */
public class GUIGridBagLayout extends JFrame {
    private static final String MENSAJE_INICIO = "Bienvenido a Geek Out Masters \n"
            + "Oprime el boton activar para iniciar el juego y luego de que inicie, Con el podras activar el dado seleccionado"
            + "Oprime el boton cambiar para cambiar el dado seleccionado"
            + "\nEl Meeple permite relanzar otro dado en juego, es decir, de la sección dados activos."
            + "\nLa Nave Espacial envía un dado no usado (de la sección dados activos) a la sección de dados\n" +
            "inactivos."
            + "\nEl Superhéroe permite que cualquier dado no usado (sección dados activos) sea volteado y\n" +
            "colocado en su cara opuesta."
            + "\nEl Corazón permite tomar un dado de la sección de dados inactivos y lanzarlo para que sea un\n" +
            "nuevo dado activo."
            + "\nEl Dragón es la cara que se quiere evitar, ya que si al final de la ronda es el último dado activo que\n" +
            "queda se habrán perdido todos los puntos ganados y acumulados."
            + "\n42 es cara que permite sumar puntos al final de la ronda."
            + "\nEste juego lo jugará un único jugador y ganará si logra sumar 30 puntos en 5 rondas consecutivas de juego.";

    private Header headerProject;
    private JLabel dado1, dado2, dado3, dado4, dado5, dado6, dado7,dado8, dado9, dado10;
    private JButton activar, cambiar, ayuda;
    private JPanel panelRondas, panelActivos, panelInactivos, panelUtilizados, panelPuntuacion, panelSeleccion, panelInteraccion;
    private ImageIcon imageDados;
    private JTextArea numeroDado, tarjetaPuntuacion;
    private Escucha escucha;
    //private ModelGame modelGame;

    /**
     * Constructor of GUI class
     */
    public GUIGridBagLayout(){
        initGUI();
        setIconImage(new ImageIcon(getClass().getResource("/resources/logo.png")).getImage());
        //Default JFrame configuration
        this.setTitle("Geek Out Masters");
        this.pack();
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * This method is used to set up the default JComponent Configuration,
     * create Listener and control Objects used for the GUI class
     */
    private void initGUI() {
        //Set up JFrame Container's Layout
        this.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints constrains = new GridBagConstraints();
        GridBagConstraints GBCInterno = new GridBagConstraints();

        //Create Listener Object and Control Object
        escucha = new Escucha();
        //modelGame = new ModelGame;
        //Set up JComponents
        headerProject = new Header("Mesa Juego Geek Out Masters", Color.BLACK);
        constrains.gridx = 0;
        constrains.gridy = 0;
        constrains.gridwidth = 2;
        constrains.fill = GridBagConstraints.HORIZONTAL;
        this.add(headerProject,constrains);

        ayuda = new JButton(" ? ");
        ayuda.addActionListener(escucha);
        constrains.gridx = 0;
        constrains.gridy = 1;
        constrains.gridwidth = 1;
        constrains.fill = GridBagConstraints.NONE;
        constrains.anchor = GridBagConstraints.LINE_START;
        this.add(ayuda, constrains);

        panelRondas = new JPanel();
        panelRondas.setPreferredSize(new Dimension(200, 26));
        panelRondas.setBackground(Color.BLACK);
        constrains.gridx = 0;
        constrains.gridy = 1;
        constrains.gridwidth = 1;
        constrains.fill = GridBagConstraints.NONE;
        constrains.anchor = GridBagConstraints.CENTER;
        this.add(panelRondas, constrains);

        panelActivos = new JPanel();
        panelActivos.setPreferredSize(new Dimension(560, 400));
        panelActivos.setBorder(BorderFactory.createTitledBorder(" Tus Dados "));
        constrains.gridx = 0;
        constrains.gridy = 2;
        constrains.gridheight = 4;
        constrains.fill = GridBagConstraints.BOTH;
        constrains.anchor = GridBagConstraints.CENTER;
        add(panelActivos,constrains);

        panelInactivos = new JPanel();
        panelInactivos.setPreferredSize(new Dimension(330, 170));
        panelInactivos.setBorder(BorderFactory.createTitledBorder(" Dados Inactivos "));
        constrains.gridx = 1;
        constrains.gridy = 1;
        constrains.gridheight = 2;
        constrains.fill = GridBagConstraints.NONE;
        constrains.anchor = GridBagConstraints.CENTER;
        add(panelInactivos,constrains);

        panelUtilizados = new JPanel();
        panelUtilizados.setPreferredSize(new Dimension(330, 170));
        panelUtilizados.setBorder(BorderFactory.createTitledBorder(" Dados Utilizados "));
        constrains.gridx = 1;
        constrains.gridy = 3;
        constrains.gridheight = 2;
        constrains.fill = GridBagConstraints.NONE;
        constrains.anchor = GridBagConstraints.CENTER;
        add(panelUtilizados,constrains);


        panelPuntuacion = new JPanel();
        panelPuntuacion.setPreferredSize(new Dimension(330, 170));
        panelPuntuacion.setBorder(BorderFactory.createTitledBorder(" Puntuacion "));
        constrains.gridx = 1;
        constrains.gridy = 5;
        constrains.gridheight = 2;
        constrains.fill = GridBagConstraints.BOTH;
        constrains.anchor = GridBagConstraints.CENTER;
        add(panelPuntuacion,constrains);

        panelInteraccion = new JPanel(new GridBagLayout());
        panelInteraccion.setPreferredSize(new Dimension(300, 106));
        panelInteraccion.setBackground(Color.CYAN);
        constrains.gridx = 0;
        constrains.gridy = 6;
        constrains.gridheight = 1;
        constrains.fill = GridBagConstraints.NONE;
        constrains.anchor = GridBagConstraints.CENTER;
        add(panelInteraccion,constrains);



        panelSeleccion = new JPanel();
        panelSeleccion.setPreferredSize(new Dimension(200, 100));
        panelSeleccion.setBackground(Color.BLUE);
        GBCInterno.gridx =0;
        GBCInterno.gridy = 0;
        GBCInterno.gridheight = 2;
        GBCInterno.gridwidth = 1;
        GBCInterno.fill = GridBagConstraints.NONE;
        GBCInterno.anchor = GridBagConstraints.CENTER;
        panelInteraccion.add(panelSeleccion,GBCInterno);

        cambiar = new JButton("cambiar");
        cambiar.addActionListener(escucha);
        GBCInterno.gridx = 1;
        GBCInterno.gridy = 0;
        GBCInterno.gridheight = 1;
        GBCInterno.gridwidth = 1;
        GBCInterno.weighty = 50.0;
        GBCInterno.fill = GridBagConstraints.NONE;
        GBCInterno.anchor = GridBagConstraints.LAST_LINE_END;
        panelInteraccion.add(cambiar, GBCInterno);

        activar = new JButton("activar");
        activar.addActionListener(escucha);
        GBCInterno.gridx = 1;
        GBCInterno.gridy = 1;
        GBCInterno.ipadx = 8;
        GBCInterno.gridheight = 1;
        GBCInterno.gridwidth = 1;
        //GBCInterno.ipadx = 8;
        GBCInterno.fill = GridBagConstraints.NONE;
        GBCInterno.anchor = GridBagConstraints.FIRST_LINE_END;
        panelInteraccion.add(activar, GBCInterno);

    }

    /**
     * Main process of the Java program
     * @param args Object used in order to send input data from command line when
     *             the program is execute by console.
     */
    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            GUIGridBagLayout miProjectGUI = new GUIGridBagLayout();
        });
    }

    /**
     * inner class that extends an Adapter Class or implements Listeners used by GUI class
     */

    private class Escucha implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == activar) {

            } else {
                if (e.getSource() == ayuda) {
                    JOptionPane.showMessageDialog(null, MENSAJE_INICIO);
                } else {
                    System.exit(0);
                }

            }
        }
    }
}

package myProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is used for ...
 * @autor F
 * @version V.2.0.0 date 26/01/2022
 */
public class GUIGridBagLayout extends JFrame {
    private static final String MENSAJE_INICIO =  "Inicia el juego, con el boton 'Activar' podras activar el dado seleccionado"
            + "Oprime el boton 'Cambiar' para cambiar el dado seleccionado"
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
    private JLabel dado1, dado2, dado3, dado4, dado5, dado6, dado7,dado8, dado9, dado10, puntos;
    private JButton activar, cambiar, ayuda, escoger;
    private JPanel panelRondas, panelActivos, panelInactivos, panelUtilizados, panelPuntuacion, panelSeleccion, panelInteraccion, panelPuntos;
    private ImageIcon imageDados, imagePuntuacion;
    private JTextArea seleccionDado, tarjetaPuntuacion, tarjetaRonda, mensajeFinal;
    private Escucha escucha;
    private ModelGame modelGame;
    private int dadoSeleccionado,flag,controlLabel,dadoSecundario,dadoPrincipal;


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
        dadoSeleccionado=0;
        dadoSecundario=10;
        flag=0;
        controlLabel=1;
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
        modelGame = new ModelGame();
        //Set up JComponents
        seleccionDado = new JTextArea(2, 6);
        tarjetaPuntuacion = new JTextArea( 2, 5 );
        mensajeFinal = new JTextArea( 2, 5 );
        tarjetaRonda = new JTextArea(1, 5);


        headerProject = new Header("Mesa de Juego Geek Out Masters", Color.BLACK);
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

        imageDados = new ImageIcon(getClass().getResource("/resources/descargaE.png"));
        dado1 = new JLabel(imageDados);
        dado2 = new JLabel(imageDados);
        dado3 = new JLabel(imageDados);
        dado4 = new JLabel(imageDados);
        dado5 = new JLabel(imageDados);
        dado6 = new JLabel(imageDados);
        dado7 = new JLabel(imageDados);
        dado8 = new JLabel(imageDados);
        dado9 = new JLabel(imageDados);
        dado10 = new JLabel(imageDados);

        imagePuntuacion = new ImageIcon(getClass().getResource("/resources/welcome.png"));
        puntos = new JLabel(imagePuntuacion);


        panelRondas = new JPanel();
        panelRondas.setPreferredSize(new Dimension(200, 26));
        panelRondas.setBackground(Color.lightGray);
        constrains.gridx = 0;
        constrains.gridy = 1;
        constrains.gridwidth = 1;
        constrains.fill = GridBagConstraints.HORIZONTAL;
        constrains.anchor = GridBagConstraints.PAGE_END;
        this.add(panelRondas, constrains);
        panelRondas.add(tarjetaRonda);
        tarjetaRonda.setBackground(null);

        panelActivos = new JPanel();
        panelActivos.setPreferredSize(new Dimension(400, 450));
        panelActivos.setBorder(BorderFactory.createTitledBorder(" Dados Activos "));
        constrains.gridx = 0;
        constrains.gridy = 2;
        constrains.gridheight = 4;
        constrains.fill = GridBagConstraints.NONE;
        constrains.anchor = GridBagConstraints.CENTER;
        add(panelActivos,constrains);

        panelActivos.add(dado1);
        panelActivos.add(dado2);
        panelActivos.add(dado3);
        panelActivos.add(dado4);
        panelActivos.add(dado5);
        panelActivos.add(dado6);
        panelActivos.add(dado7);
        panelActivos.add(dado8);
        panelActivos.add(dado9);
        panelActivos.add(dado10);


        panelInactivos = new JPanel();
        panelInactivos.setPreferredSize(new Dimension(330, 230));
        panelInactivos.setBorder(BorderFactory.createTitledBorder(" Dados Inactivos "));
        constrains.gridx = 1;
        constrains.gridy = 1;
        constrains.gridheight = 2;
        constrains.fill = GridBagConstraints.NONE;
        constrains.anchor = GridBagConstraints.CENTER;
        add(panelInactivos,constrains);

        panelUtilizados = new JPanel();
        panelUtilizados.setPreferredSize(new Dimension(330, 230));
        panelUtilizados.setBorder(BorderFactory.createTitledBorder(" Dados Utilizados "));
        constrains.gridx = 1;
        constrains.gridy = 3;
        constrains.gridheight = 2;
        constrains.fill = GridBagConstraints.NONE;
        constrains.anchor = GridBagConstraints.CENTER;
        add(panelUtilizados,constrains);

        panelPuntuacion = new JPanel();
        panelPuntuacion.setPreferredSize(new Dimension(330, 242));
        panelPuntuacion.setBorder(BorderFactory.createTitledBorder(" Puntuacion "));
        constrains.gridx = 1;
        constrains.gridy = 5;
        constrains.gridheight = 2;
        constrains.fill = GridBagConstraints.BOTH;
        constrains.anchor = GridBagConstraints.CENTER;
        add(panelPuntuacion,constrains);

        panelPuntos = new JPanel();
        panelPuntos.setPreferredSize(new Dimension(330, 26));
        panelPuntos.setBackground(Color.lightGray);
        panelPuntuacion.add(panelPuntos, BorderLayout.NORTH);
        tarjetaPuntuacion.setBackground(null);
        tarjetaPuntuacion.setEditable(false);
        mensajeFinal.setBackground(null);
        mensajeFinal.setEditable(false);
        panelPuntos.add(tarjetaPuntuacion);

        panelPuntuacion.add(puntos, BorderLayout.SOUTH);


        panelInteraccion = new JPanel(new GridBagLayout());
        panelInteraccion.setPreferredSize(new Dimension(300, 110));
        panelInteraccion.setBackground(Color.CYAN);
        constrains.gridx = 0;
        constrains.gridy = 6;
        constrains.gridheight = 1;
        constrains.fill = GridBagConstraints.BOTH;
        constrains.anchor = GridBagConstraints.CENTER;
        add(panelInteraccion,constrains);


        panelSeleccion = new JPanel();
        panelSeleccion.setPreferredSize(new Dimension(260, 52));
        panelSeleccion.setBackground(Color.LIGHT_GRAY);
        GBCInterno.gridx =0;
        GBCInterno.gridy = 0;
        GBCInterno.gridheight = 2;
        GBCInterno.gridwidth = 1;
        GBCInterno.fill = GridBagConstraints.NONE;
        GBCInterno.anchor = GridBagConstraints.CENTER;
        panelSeleccion.setVisible(false);
        panelInteraccion.add(panelSeleccion,GBCInterno);






        cambiar = new JButton("Iniciar Juego");
        cambiar.addActionListener(escucha);
        panelInteraccion.add(cambiar, GBCInterno);

        activar = new JButton("activar");
        activar.addActionListener(escucha);
        GBCInterno.gridx = 1;
        GBCInterno.gridy = 1;
        GBCInterno.ipadx = 6;
        GBCInterno.gridheight = 1;
        GBCInterno.gridwidth = 1;
        GBCInterno.weighty = 50.0;
        GBCInterno.fill = GridBagConstraints.NONE;
        GBCInterno.anchor = GridBagConstraints.FIRST_LINE_END;
        activar.setVisible(false);
        panelInteraccion.add(activar, GBCInterno);

        escoger = new JButton("escoger");
        escoger.addActionListener(escucha);
        GBCInterno.gridx = 1;
        GBCInterno.gridy = 1;
        GBCInterno.ipadx = 8;
        //GBCInterno.gridheight = 1;
        GBCInterno.gridwidth = 1;
        GBCInterno.weighty = 50.0;
        GBCInterno.fill = GridBagConstraints.NONE;
        GBCInterno.anchor = GridBagConstraints.FIRST_LINE_END;
        escoger.setVisible(false);
        panelInteraccion.add(escoger, GBCInterno);

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
        String[] zonaActivos, zonaUtilizados,zonaInactivos;

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == cambiar) {
                if(flag!=1) {
                    cambiar.setText("cambiar");
                    GridBagConstraints GBCInterno = new GridBagConstraints();
                    GBCInterno.gridx = 1;
                    GBCInterno.gridy = 0;
                    GBCInterno.gridheight = 1;
                    GBCInterno.gridwidth = 1;
                    GBCInterno.weighty = 50.0;
                    GBCInterno.fill = GridBagConstraints.NONE;
                    GBCInterno.anchor = GridBagConstraints.LAST_LINE_END;
                    panelInteraccion.add(cambiar, GBCInterno);

                    if (flag == 2) {
                        panelPuntos.remove(mensajeFinal);
                        panelPuntos.add(tarjetaPuntuacion);
                    }else{
                        seleccionDado.setBackground(null);
                        seleccionDado.setEditable(false);
                        seleccionDado.setFont(new Font(Font.DIALOG,Font.BOLD+Font.ITALIC,12));
                        panelSeleccion.add(seleccionDado);
                    }

                    panelSeleccion.setVisible(true);
                    activar.setVisible(true);
                    flag=1;



                    imagePuntuacion = new ImageIcon(getClass().getResource("/resources/ImagePuntuacion.jpeg"));
                    puntos.setIcon(imagePuntuacion);
                    actualizarInterfaz();
                }
                cambiarDado(true,"");
            }
            else if (e.getSource() == ayuda) {
                JOptionPane.showMessageDialog(null, MENSAJE_INICIO);
            }
            else if (e.getSource() == activar){
                dadoPrincipal=dadoSeleccionado-1;
                if (dadoPrincipal==-1){
                    dadoPrincipal=modelGame.contarDadosActivos()-1;
                }else if(zonaActivos[dadoPrincipal]==null){
                    cambiarDado(true,"activar");
                }

                if (zonaActivos[dadoPrincipal].equals("meeple")) {
                        escogerDadoSecundario();

                } else if (zonaActivos[dadoPrincipal].equals("cohete")) {
                        escogerDadoSecundario();

                } else if (zonaActivos[dadoPrincipal].equals("superheroe")) {
                        escogerDadoSecundario();

                }
                else if (zonaActivos[dadoPrincipal].equals("corazon")){
                        modelGame.activarDado(dadoPrincipal, 10);
                        cambiarDado(true,"activar");
                        actualizarInterfaz();
                }
                else if (zonaActivos[dadoPrincipal].equals("dragon")){
                    if (modelGame.hayMasDadosAccion()){
                        cambiarDado(false,"activar");
                    }else {
                        modelGame.activarDado(dadoPrincipal, 10);
                        cambiarDado(true,"activar");
                        actualizarInterfaz();
                    }
                }
                else if (zonaActivos[dadoPrincipal].equals("42")){
                    cambiarDado(false,"activar");
                }
            }
            else if (e.getSource()== escoger){
                actualizarInformacion();
                if(dadoPrincipal!=dadoSeleccionado-1){
                    dadoSecundario=dadoSeleccionado-1;

                    activar.setVisible(true);
                    escoger.setVisible(false);


                    GridBagConstraints GBCInterno = new GridBagConstraints();
                    GBCInterno.gridx = 1;
                    GBCInterno.gridy = 0;
                    GBCInterno.weighty = 50.0;
                    GBCInterno.anchor = GridBagConstraints.LAST_LINE_END;
                    panelInteraccion.add(cambiar, GBCInterno);

                    modelGame.activarDado(dadoPrincipal,dadoSecundario);
                    cambiarDado(true,"escoger");
                    actualizarInterfaz();
                }
                else{
                    cambiarDado(false,"escoger");
                }
                actualizarInformacion();
            }
            if (modelGame.contarDadosActivos() == 0 || modelGame.soloHay("42")){
                modelGame.actualizarRonda();
                nuevoJuego();
                actualizarInterfaz();
            }
            revalidate();
            repaint();
        }
        private String getJLabel(int dadoN) {
            switch (dadoN) {
                case 1 :dado1.setIcon(imageDados);
                    break;
                case 2 : dado2.setIcon(imageDados);
                    break;
                case 3 : dado3.setIcon(imageDados);
                    break;
                case 4 : dado4.setIcon(imageDados);
                    break;
                case 5 : dado5.setIcon(imageDados);
                    break;
                case 6 : dado6.setIcon(imageDados);
                    break;
                case 7 : dado7.setIcon(imageDados);
                    break;
                case 8 : dado8.setIcon(imageDados);
                    break;
                case 9 : dado9.setIcon(imageDados);
                    break;
                case 10 : dado10.setIcon(imageDados);
                    break;
                }
        return "dado"+dadoN;
        }
        private void addLabel(String dadoNu,String zona){

            if(dadoNu.equals("dado1")){
                if(zona.equals("dadosActivos")){
                  panelActivos.add(dado1);
                }
                else if(zona.equals("dadosInactivos")){
                   panelInactivos.add(dado1);
                }
                else if(zona.equals("dadosUtilizados")){
                    panelUtilizados.add(dado1);
                }
            }
            else if(dadoNu.equals("dado2")){
                if(zona.equals("dadosActivos")){
                    panelActivos.add(dado2);
                }else if(zona.equals("dadosInactivos")){
                    panelInactivos.add(dado2);
                }else if(zona.equals("dadosUtilizados")){
                    panelUtilizados.add(dado2);
                }
            }
            else if(dadoNu.equals("dado3")){
                if(zona.equals("dadosActivos")){
                    panelActivos.add(dado3);
                }
                else if(zona.equals("dadosInactivos")){
                    panelInactivos.add(dado3);
                }
                else if(zona.equals("dadosUtilizados")){
                    panelUtilizados.add(dado3);
                }
            }
            else if(dadoNu.equals("dado4")){
                if(zona.equals("dadosActivos")){
                    panelActivos.add(dado4);
                }else if(zona.equals("dadosInactivos")){
                    panelInactivos.add(dado4);
                }else if(zona.equals("dadosUtilizados")){
                    panelUtilizados.add(dado4);
                }
            }
            else if(dadoNu.equals("dado5")){
                if(zona.equals("dadosActivos")){
                    panelActivos.add(dado5);
                }else if(zona.equals("dadosInactivos")){
                    panelInactivos.add(dado5);
                }else if(zona.equals("dadosUtilizados")){
                    panelUtilizados.add(dado5);
                }
            }
            else if(dadoNu.equals("dado6")){
                if(zona.equals("dadosActivos")){
                    panelActivos.add(dado6);
                }else if(zona.equals("dadosInactivos")){
                    panelInactivos.add(dado6);
                }else if(zona.equals("dadosUtilizados")){
                    panelUtilizados.add(dado6);
                }
            }
            else if(dadoNu.equals("dado7")){
                if(zona.equals("dadosActivos")){
                    panelActivos.add(dado7);
                }else if(zona.equals("dadosInactivos")){
                    panelInactivos.add(dado7);
                }else if(zona.equals("dadosUtilizados")){
                    panelUtilizados.add(dado7);
                }
            }
            else if(dadoNu.equals("dado8")){
                if(zona.equals("dadosActivos")){
                    panelActivos.add(dado8);
                }else if(zona.equals("dadosInactivos")){
                    panelInactivos.add(dado8);
                }else if(zona.equals("dadosUtilizados")){
                    panelUtilizados.add(dado8);
                }
            }
            else if(dadoNu.equals("dado9")){
                if(zona.equals("dadosActivos")){
                    panelActivos.add(dado9);
                }else if(zona.equals("dadosInactivos")){
                    panelInactivos.add(dado9);
                }else if(zona.equals("dadosUtilizados")){
                    panelUtilizados.add(dado9);
                }
            }
            else if(dadoNu.equals("dado10")){
                if(zona.equals("dadosActivos")){
                    panelActivos.add(dado10);
                }else if(zona.equals("dadosInactivos")){
                    panelInactivos.add(dado10);
                }else if(zona.equals("dadosUtilizados")){
                    panelUtilizados.add(dado10);
                }
            }
       }
        private void actualizarInterfaz(){
           panelActivos.removeAll();
           panelInactivos.removeAll();
           panelUtilizados.removeAll();
           actualizarInformacion ();
           tarjetaRonda.setText("Ronda: "+ modelGame.getNumeroRonda());
           for (int i=0;i<10;i++){
               if(zonaActivos[i]!=null){
                   if(zonaActivos[i]=="meeple"){
                       imageDados = new ImageIcon(getClass().getResource("/resources/meeple.png"));
                       addLabel(getJLabel(controlLabel++),"dadosActivos") ;
                   }else if(zonaActivos[i]=="cohete"){
                       imageDados = new ImageIcon(getClass().getResource("/resources/cohete.png"));
                       addLabel(getJLabel(controlLabel++),"dadosActivos") ;
                   }else if(zonaActivos[i]=="superheroe"){
                       imageDados = new ImageIcon(getClass().getResource("/resources/superheroe.png"));
                       addLabel(getJLabel(controlLabel++),"dadosActivos") ;
                   }else if(zonaActivos[i]=="corazon"){
                       imageDados = new ImageIcon(getClass().getResource("/resources/corazon.png"));
                       addLabel(getJLabel(controlLabel++),"dadosActivos") ;
                   }else if(zonaActivos[i]=="dragon"){
                       imageDados = new ImageIcon(getClass().getResource("/resources/dragon.png"));
                       addLabel(getJLabel(controlLabel++),"dadosActivos") ;
                   }else if(zonaActivos[i]=="42"){
                       imageDados = new ImageIcon(getClass().getResource("/resources/42.png"));
                       addLabel(getJLabel(controlLabel++),"dadosActivos") ;
                   }
               }
               if(zonaInactivos[i]!=null){

                   if(zonaInactivos[i]=="meeple"){
                       imageDados = new ImageIcon(getClass().getResource("/resources/meeple.png"));
                       addLabel(getJLabel(controlLabel++),"dadosInactivos") ;
                   }else if(zonaInactivos[i]=="cohete"){
                       imageDados = new ImageIcon(getClass().getResource("/resources/cohete.png"));
                       addLabel(getJLabel(controlLabel++),"dadosInactivos") ;
                   }else if(zonaInactivos[i]=="superheroe"){
                       imageDados = new ImageIcon(getClass().getResource("/resources/superheroe.png"));
                       addLabel(getJLabel(controlLabel++),"dadosInactivos") ;
                   }else if(zonaInactivos[i]=="corazon"){
                       imageDados = new ImageIcon(getClass().getResource("/resources/corazon.png"));
                       addLabel(getJLabel(controlLabel++),"dadosInactivos") ;
                   }else if(zonaInactivos[i]=="dragon"){
                       imageDados = new ImageIcon(getClass().getResource("/resources/dragon.png"));
                       addLabel(getJLabel(controlLabel++),"dadosInactivos") ;
                   }else if(zonaInactivos[i]=="42"){
                       imageDados = new ImageIcon(getClass().getResource("/resources/42.png"));
                       addLabel(getJLabel(controlLabel++),"dadosInactivos") ;
                   }
               }
               if(zonaUtilizados[i]!=null){
                   if(zonaUtilizados[i]=="meeple"){
                       imageDados = new ImageIcon(getClass().getResource("/resources/meeple.png"));
                       addLabel(getJLabel(controlLabel++),"dadosUtilizados") ;
                   }else if(zonaUtilizados[i]=="cohete"){
                       imageDados = new ImageIcon(getClass().getResource("/resources/cohete.png"));
                       addLabel(getJLabel(controlLabel++),"dadosUtilizados") ;
                   }else if(zonaUtilizados[i]=="superheroe"){
                       imageDados = new ImageIcon(getClass().getResource("/resources/superheroe.png"));
                       addLabel(getJLabel(controlLabel++),"dadosUtilizados") ;
                   }else if(zonaUtilizados[i]=="corazon"){
                       imageDados = new ImageIcon(getClass().getResource("/resources/corazon.png"));
                       addLabel(getJLabel(controlLabel++),"dadosUtilizados") ;
                   }else if(zonaUtilizados[i]=="dragon"){
                       imageDados = new ImageIcon(getClass().getResource("/resources/dragon.png"));
                       addLabel(getJLabel(controlLabel++),"dadosUtilizados") ;
                   }else if(zonaUtilizados[i]=="42"){
                       imageDados = new ImageIcon(getClass().getResource("/resources/42.png"));
                       addLabel(getJLabel(controlLabel++),"dadosUtilizados") ;
                   }
               }
               revalidate();
               repaint();
           }
           if (modelGame.contarDadosIU("inactivos")>3&&modelGame.contarDadosIU("utilizados")<4){
               panelUtilizados.setPreferredSize(new Dimension(330, 130));
               panelInactivos.setPreferredSize(new Dimension(330, 330));
           }
           else if (modelGame.contarDadosIU("utilizados")>3&&modelGame.contarDadosIU("inactivos")<4){
                panelUtilizados.setPreferredSize(new Dimension(330, 330));
                panelInactivos.setPreferredSize(new Dimension(330, 130));
           }
           else if(modelGame.contarDadosIU("inactivos")==0&&modelGame.contarDadosIU("utilizados")>6) {
               panelUtilizados.setPreferredSize(new Dimension(330, 430));
               panelInactivos.setPreferredSize(new Dimension(330, 30));
           }
           else {
               panelUtilizados.setPreferredSize(new Dimension(330, 230));
               panelInactivos.setPreferredSize(new Dimension(330, 230));
           }
           controlLabel=1;
           modelGame.actualizarRonda();
           nuevoJuego();
           tarjetaPuntuacion.setText("Puntuacion de la ronda: "+ modelGame.getPuntuacionRonda() +"            Puntuacion acumulada: "+ modelGame.getPuntuacionJuego());
           revalidate();
           repaint();
       }
        private void escogerDadoSecundario(){
            cambiarDado(true,"activar");

           activar.setVisible(false);
           escoger.setVisible(true);

           GridBagConstraints GBCInterno = new GridBagConstraints();
           GBCInterno.gridx = 1;
           GBCInterno.gridy = 0;
           GBCInterno.ipadx = 10;
           GBCInterno.weighty = 50.0;
           GBCInterno.anchor = GridBagConstraints.LAST_LINE_END;
           panelInteraccion.add(cambiar, GBCInterno);
           actualizarInterfaz();
       }
        private void actualizarInformacion (){
           zonaActivos= modelGame.getCaraDado("dadosActivos");
           zonaUtilizados= modelGame.getCaraDado("dadosUtilizados");
           zonaInactivos= modelGame.getCaraDado("dadosInactivos");
       }
        private void cambiarDado(boolean dadoAceptado, String boton) {
            if (dadoAceptado) {
                actualizarInformacion();
                if (dadoSeleccionado < modelGame.contarDadosActivos()) {
                    seleccionDado.setText("El dado seleccionado es" + "\nel dado numero (" + (dadoSeleccionado + 1) + ") " + zonaActivos[dadoSeleccionado]);
                    dadoSeleccionado = dadoSeleccionado + 1;
                } else if (dadoSeleccionado >= modelGame.contarDadosActivos()) {
                    seleccionDado.setText("El dado seleccionado es" + "\nel dado numero (" + 1 + ") " + zonaActivos[0]);
                    dadoSeleccionado = 1;
                }
            }else {
                actualizarInformacion();

                if (dadoSeleccionado < modelGame.contarDadosActivos()) {
                    if (boton.equals("activar")){
                        seleccionDado.setText("No puedes activar este dado, Ahora"+"\nEl dado seleccionado es" + "\nel dado numero (" + (dadoSeleccionado + 1) + ") " + zonaActivos[dadoSeleccionado]);
                    }else if(boton.equals("escoger")){
                        seleccionDado.setText("No puedes escoger este dado, Ahora"+"\nEl dado seleccionado es" + "\nel dado numero (" + (dadoSeleccionado + 1) + ") " + zonaActivos[dadoSeleccionado]);
                    }
                    dadoSeleccionado += 1;
                } else if (dadoSeleccionado >= modelGame.contarDadosActivos()) {
                    if (boton.equals("activar")){
                        seleccionDado.setText("No puedes activar este dado, Ahora"+"\nEl dado seleccionado es" + "\nel dado numero (" + 1 + ") " + zonaActivos[0]);
                    }else if(boton.equals("escoger")){
                        seleccionDado.setText("No puedes escoger este dado, Ahora"+"\nEl dado seleccionado es" + "\nel dado numero (" + 1 + ") " + zonaActivos[0]);
                    }
                    dadoSeleccionado = 1;
                }
            }
        }
        private void nuevoJuego(){
            if(modelGame.isEndGame()){
                panelActivos.removeAll();
                panelInactivos.removeAll();
                panelUtilizados.removeAll();

                cambiar.setText("Reiniciar Juego");
                GridBagConstraints GBCInterno = new GridBagConstraints();
                GBCInterno.anchor = GridBagConstraints.CENTER;
                panelInteraccion.add(cambiar, GBCInterno);

                panelSeleccion.setVisible(false);
                activar.setVisible(false);

                tarjetaRonda.setText("");

                panelPuntos.remove(tarjetaPuntuacion);

                imageDados = new ImageIcon(getClass().getResource("/resources/descargaE.png"));
                dado1.setIcon(imageDados);
                dado2.setIcon(imageDados);
                dado3.setIcon(imageDados);
                dado4.setIcon(imageDados);
                dado5.setIcon(imageDados);
                dado6.setIcon(imageDados);
                dado7.setIcon(imageDados);
                dado8.setIcon(imageDados);
                dado9.setIcon(imageDados);
                dado10.setIcon(imageDados);

                panelActivos.add(dado1);
                panelActivos.add(dado2);
                panelActivos.add(dado3);
                panelActivos.add(dado4);
                panelActivos.add(dado5);
                panelActivos.add(dado6);
                panelActivos.add(dado7);
                panelActivos.add(dado8);
                panelActivos.add(dado9);
                panelActivos.add(dado10);

                if(modelGame.getPuntuacionJuego()>=30){
                    mensajeFinal.setText("Felicidades, lograste acumular "+ modelGame.getPuntuacionJuego()+" puntos.");
                    imagePuntuacion = new ImageIcon(getClass().getResource("/resources/win.png"));
                    puntos.setIcon(imagePuntuacion);
                }else{
                    mensajeFinal.setText("Lo sentimos, lograste acumular "+ modelGame.getPuntuacionJuego()+" puntos.");
                    imagePuntuacion = new ImageIcon(getClass().getResource("/resources/gameOver.png"));
                    puntos.setIcon(imagePuntuacion);
                }
                panelPuntos.add(mensajeFinal);
                modelGame.reIniciarJuego();
                flag=2;
            }
       }
    }

}



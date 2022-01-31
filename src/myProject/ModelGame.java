package myProject;

import javax.swing.*;

/**
 * ModelGame apply Geek Out Masters rules, actions as well as the dynamics of the game..
 * The game will be won if 30 points are scored in 5 consecutive rounds of play.
 * @author Fabian Lopez
 * @author Juan Jose Viafara
 * @version V.2.0.0 date 26/01/2022
 */

public class ModelGame {
    private Dado[] dadosActivos, dadosInactivos, dadosUtilizados;
    private int puntuacionRonda ,puntuacionJuego, cantidadDadosA, numeroRonda;
    private boolean endGame,dragonActivado;
    /**
     * Class Constructor
     */
    public ModelGame(){
        dadosActivos = new Dado[10];
        dadosInactivos= new Dado[10];
        dadosUtilizados= new Dado[10];
        cantidadDadosA=0;
        numeroRonda=1;
        endGame=false;
        dragonActivado=false;
        iniciarRonda();
    }

    /**
     *count the dice
     * @param vectorDados, dice to count
     * @return dice number in the vector
     */
    private int contarDados(Dado[] vectorDados){
        int cantidadDadosA=0;
        for (int i=0;i<10;i++){
            if(vectorDados[i]!=null){
                cantidadDadosA++;
            }
        }
        return cantidadDadosA;
    }
    /**
     *count the dice for other class, in the vector 'dadosInactivos' or 'dadosUtilizados'
     * @param dados, dice to count
     * @return dice number in the vector
     */
    public int contarDadosIU(String dados){
        int nDadosVector=0;
        if (dados.equals("inactivos")){
            nDadosVector= contarDados(dadosInactivos);
        }else if (dados.equals("utilizados")){
            nDadosVector= contarDados(dadosUtilizados);
        }
        return nDadosVector;
    }
    /**
     *count the dice in 'dadosActivos' vector
     * @return dice number in the vector
     */
    public int contarDadosActivos(){
        return contarDados(dadosActivos);
    }

    /**
     * rearranges a vector so that the dice are always in order
     * @param vectorDados, organized
     */
    public void reOrganizarVector(Dado[] vectorDados){
        int dadosEnVector=contarDados(vectorDados);
        for (int i=0;i<10;i++){
            if(vectorDados[i]==null&&i<9){
                if (vectorDados[i+1] != null){
                    vectorDados[i] = new Dado();
                    vectorDados[i].setCara(vectorDados[i+1].getCara());
                    vectorDados[i+1] = null;
                }
            }else if(i==9){
                vectorDados[i]=null;
            }
        }
        vectorDados[dadosEnVector]=null;
    }

    /**
     * responsible for performing the actions depending on the die that has been activated
     * @param dadoActivar, the main die, which was activated (the position of the die in the "zonaActivos")
     * @param dadoEscogido,some dice require another die on which to execute some action(the position of the die in the "zonaActivos"), if not required, expect a 10
     */
    public void activarDado(int dadoActivar,int dadoEscogido){
        int dadoAC = contarDados(dadosUtilizados);
        dadosUtilizados[dadoAC]= new Dado();
        dadosUtilizados[dadoAC].setCara(dadosActivos[dadoActivar].getCara());
       if (dadoEscogido<10){
           if(dadosActivos[dadoActivar].getCara()=="meeple"){
               dadosActivos[dadoEscogido] = new Dado();
           }
           else if(dadosActivos[dadoActivar].getCara()=="cohete"){
               int dadoAC2 = contarDados(dadosInactivos);
               dadosInactivos[dadoAC2]= new Dado();
               dadosInactivos[dadoAC2].setCara(dadosActivos[dadoEscogido].getCara());
               dadosActivos[dadoEscogido]=null;
               reOrganizarVector(dadosActivos);
               if (dadoActivar > dadoEscogido){
                   dadoActivar = dadoActivar-1;
                   if (dadoActivar == -1){
                       dadoActivar = contarDadosActivos()-1;
                   }
               }
           }
           else if(dadosActivos[dadoActivar].getCara()=="superheroe"){
               dadosActivos[dadoEscogido].setCara(dadosActivos[dadoEscogido].getCaraContraria());
           }
       }
        else if(dadosActivos[dadoActivar].getCara()=="corazon"){
            if (dadosInactivos[0]!=null){
                dadosActivos[contarDados(dadosActivos)]=new Dado();
                dadosInactivos[contarDados(dadosInactivos)-1]=null;
            }
        }else if(dadosActivos[dadoActivar].getCara()=="dragon"){
            puntuacionRonda = 0;
            puntuacionJuego = 0;
            dragonActivado=true;
        }
        dadosActivos[dadoActivar]=null;
        reOrganizarVector(dadosActivos);
        actualizarRonda();
    }

    /**
     *
     * @param nombreVector, the vector, which wants to know the face of its dice
     * @return responsible for reporting the face of the dice in the vector
     */
    public String[] getCaraDado(String nombreVector){
        String vectorRetornar[]= new String[10];
        for(int i=0; i<10;i++){
            if (nombreVector=="dadosActivos"&&dadosActivos[i]!=null){
               vectorRetornar[i] = dadosActivos[i].getCara();
            }else if (nombreVector=="dadosInactivos"&&dadosInactivos[i]!=null){
                vectorRetornar[i] = dadosInactivos[i].getCara();
            }else if(nombreVector=="dadosUtilizados"&&dadosUtilizados[i]!=null){
                vectorRetornar[i] = dadosUtilizados[i].getCara();
            }else{
                vectorRetornar[i] = null;
            }
        }
        return vectorRetornar;
    }

    /**
     *  Method that generates the score of the round you are in.
     * @return score according to the number of "42" you have in dadosActivos.
     */
    public int getPuntuacionRonda(){
        int numeroDe42 = 0;
        puntuacionRonda = 0;
        for(int i=0; i<10;i++){
            if (dadosActivos[i] != null){
                if (dadosActivos[i].getCara() == "42"){
                    numeroDe42++;
                }
            }
        }
        switch (numeroDe42){
            case 1 -> puntuacionRonda = 1;
            case 2 -> puntuacionRonda = 3;
            case 3 -> puntuacionRonda = 6;
            case 4 -> puntuacionRonda = 10;
            case 5 -> puntuacionRonda = 15;
            case 6 -> puntuacionRonda = 21;
            case 7 -> puntuacionRonda = 28;
            case 8 -> puntuacionRonda = 36;
            case 9 -> puntuacionRonda = 45;
            case 10 -> puntuacionRonda = 55;
        }
        return puntuacionRonda;
    }

    /**
     * @param caraDado,face of the die you want to know if there is only one of that type.
     * @return boolean yes or no
     */
    public boolean soloHay(String caraDado){
        boolean soloHay = true;
        for(int i=0; i<10;i++){
            if (dadosActivos[i] != null){
                if (dadosActivos[i].getCara() != caraDado){
                    soloHay = false;
                    break;
                }
            }
        }
        return soloHay;
    }

    /**
     *responsible for deciding if a round is over
     */
    public void actualizarRonda(){
        if (soloHay("42")){
            cambiarRonda();
        }
        else if (soloHay("dragon")){
            cambiarRonda();
            puntuacionJuego=0;
            dragonActivado=true;
        }
        else if (contarDadosActivos() == 0){
            cambiarRonda();
        }
        else if (contarDadosActivos() == 1){
         if (dadosActivos[0].getCara().equals("meeple")){
         cambiarRonda();
         }
         else if(dadosActivos[0].getCara().equals("superheroe")){
         cambiarRonda();
         }
         else if(dadosActivos[0].getCara().equals("cohete")){
         cambiarRonda();
         }
        }
    }

    /**
     *responsible to change the round
     */
    private void cambiarRonda(){
        int puntuacionRondaActual= getPuntuacionRonda();
        puntuacionJuego+=puntuacionRondaActual;

        if(dragonActivado){
            puntuacionJuego=0;
            /**
             * error intencional de la fase de prueba, olvide quitarlo si se revisa versiones anteriores al commit 'final version' se puede constatar
             */
            //dragonActivado=false;
        }
        puntuacionRonda = 0;
        if (numeroRonda < 5){
            numeroRonda++;
            iniciarRonda();
        }
        else if(numeroRonda == 5 ){
            endGame=true;
        }

    }

    /**
     *
     * @return points accumulated so far in previous rounds
     */
    public int getPuntuacionJuego(){
            return puntuacionJuego;
    }

    /**
     *
     * @return points accumulated in the actual round
     */
    public int getNumeroRonda(){
        return numeroRonda;
    }

    /**
     * responsible start a new round
     */
    private void iniciarRonda(){
        dragonActivado=false;
        for (int i=0; i<10;i++){
            dadosActivos[i] = null;
            dadosInactivos[i] = null;
            dadosUtilizados[i] = null;
            if(i<7){
                dadosActivos[i]= new Dado();
                if (i<3){
                    dadosInactivos[i]= new Dado();
                }else{
                    dadosInactivos[i]=null;
                }
            }else{
                dadosActivos[i]= null;
                dadosInactivos[i]=null;
            }
        }
    }

    /**
     * determines if there are still dice in the "dadosActivos" vector that can be activated.
     * @return boolean, yes or not
     */
    public boolean hayMasDadosAccion(){
        boolean dadosAccion = false;
        for (int i=0; i<10;i++) {
            if (dadosActivos[i] != null) {
                if (dadosActivos[i].getCara() != "42" && dadosActivos[i].getCara() != "dragon") {
                    dadosAccion = true;
                    break;
                }
            }
        }
        return  dadosAccion;
    }

    /**
     * @return bolean,  whether or not it's game over
     */
    public boolean isEndGame() {
        return endGame;
    }

    /**
     * restart the game
     */
    public void reIniciarJuego(){
        numeroRonda = 1;
        puntuacionJuego = 0;
        iniciarRonda();
        endGame=false;
    }
}

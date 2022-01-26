package myProject;

import javax.swing.*;

/**
 * ModelGame apply Geek Out Masters rules.
 *
 * @author Fabian Lopez
 * @author Juan Jose Viafara
 * @version V.1.3.0 date 23/01/2022
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

    private int contarDados(Dado[] vectorDados){
        int cantidadDadosA=0;
        for (int i=0;i<10;i++){
            if(vectorDados[i]!=null){
                cantidadDadosA++;
            }
        }
        return cantidadDadosA;
    }
    public int contarDadosIU(String dados){
        int nDadosVector=0;
        if (dados.equals("inactivos")){
            nDadosVector= contarDados(dadosInactivos);
        }else if (dados.equals("utilizados")){
            nDadosVector= contarDados(dadosUtilizados);
        }
        return nDadosVector;
    }
    public int contarDadosActivos(){
        return contarDados(dadosActivos);
    }
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
    private void cambiarRonda(){
        int puntuacionRondaActual= getPuntuacionRonda();
        puntuacionJuego+=puntuacionRondaActual;

        if(dragonActivado){
            puntuacionJuego=0;
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
    public int getPuntuacionJuego(){
            return puntuacionJuego;
    }
    public int getNumeroRonda(){
        return numeroRonda;
    }
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
    public boolean isEndGame() {
        return endGame;
    }
    public void reIniciarJuego(){
        numeroRonda = 1;
        puntuacionJuego = 0;
        iniciarRonda();
        endGame=false;
    }
}

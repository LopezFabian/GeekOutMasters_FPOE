package myProject;

import javax.swing.*;

/**
 * ModelGame apply Geek Out Masters rules.
 *
 * @author Fabian Lopez
 * @author Juan Jose Viafara
 * @version V.1.0.0 date 03/01/2022
 */

public class ModelGame {
    private Dado[] dadosActivos, dadosInactivos, dadosUtilizados;
    private int puntuacionRonda ,puntuacionJuego, cantidadDadosA, numeroRonda;


    /**
     * Class Constructor
     */
    public ModelGame(){
        dadosActivos = new Dado[10];
        dadosInactivos= new Dado[10];
        dadosUtilizados= new Dado[10];
        cantidadDadosA=0;
        numeroRonda=1;
         for (int i=0;i<10;i++){
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

    public int contarDados(Dado[] vectorDados){
        int cantidadDadosA=0;
        for (int i=0;i<10;i++){
            if(vectorDados[i]!=null){
                cantidadDadosA= cantidadDadosA + 1;
            }
        }
        return cantidadDadosA;
    }
    public int contarDadosActivos(){
        return contarDados(dadosActivos);
    }
    public void activarDado(Dado dadoActivado,Dado dadoSeleccionado){
        if(dadoActivado.getCara()=="meeple"){
            dadoSeleccionado= new Dado();
        }
        else if(dadoActivado.getCara()=="cohete"){
            dadosInactivos[contarDados(dadosInactivos)]=dadoSeleccionado;
            dadoSeleccionado=null;
        }
        else if(dadoActivado.getCara()=="superheroe"){
            //dadoSeleccionado.getCara() = dadoSeleccionado.getCaraContraria();
        }
        else if(dadoActivado.getCara()=="corazon"){
            if (dadosInactivos[0]!=null){
                dadosActivos[contarDados(dadosActivos)]=new Dado();
                dadosInactivos[contarDados(dadosInactivos)-1]=null;
            }

        }else if(dadoActivado.getCara()=="dragon"){
            if (contarDados(dadosActivos)==1){
                puntuacionJuego=0;
                puntuacionRonda=0;
            }
        }
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

}

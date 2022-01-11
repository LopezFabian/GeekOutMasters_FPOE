package myProject;

import javax.swing.*;

/**
 * ModelGame apply Geek Out Masters rules.
 *
 * @author Fabian Lopez
 * @author Juan Jose Viafara
 * @version V.1.1.2 date 07/01/2022
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
    public void reOrganizarVector(Dado[] vectorDados){
        int dadosEnVector=contarDados(vectorDados);
        for (int i=0;i<10;i++){
            if(vectorDados[i]==null&&i<9){
                vectorDados[i]=vectorDados[i+1];
            }else if(i==9){
                vectorDados[i]=null;
            }
        }
        vectorDados[dadosEnVector]=null;
    }
    public void activarDado(int dadoActivar,int dadoEscogido){
        Dado dadoT=new Dado();
        dadoT.setCara(dadosActivos[dadoActivar].getCara());
        dadosUtilizados[contarDados(dadosUtilizados)]= dadoT;

       if (dadoEscogido<10){
           if(dadosActivos[dadoActivar].getCara()=="meeple"){
               dadosActivos[dadoEscogido] = new Dado();
           }
           else if(dadosActivos[dadoActivar].getCara()=="cohete"){
               Dado dadoR=new Dado();
               dadoR.setCara(dadosActivos[dadoEscogido].getCara());
               dadosInactivos[contarDados(dadosInactivos)]= dadoR;
               dadosActivos[dadoEscogido]=null;
               reOrganizarVector(dadosActivos);

               revisar(dadosActivos);
               revisar(dadosInactivos);
           }
           else if(dadosActivos[dadoActivar].getCara()=="superheroe"){
               dadosActivos[dadoEscogido].setCara(dadosActivos[dadoEscogido].getCaraContraria());
           }
       }
        if(dadosActivos[dadoActivar].getCara()=="corazon"){
            if (dadosInactivos[0]!=null){
                dadosActivos[contarDados(dadosActivos)]=new Dado();
                dadosInactivos[contarDados(dadosInactivos)-1]=null;
            }

        }else if(dadosActivos[dadoActivar].getCara()=="dragon"){
            if (contarDados(dadosActivos)==1){
                puntuacionJuego=0;
                puntuacionRonda=0;
            }
        }
        dadosActivos[dadoActivar]=null;
        reOrganizarVector(dadosActivos);
    }
    public void revisar(Dado vectorRe[]){
        for (int i=0;i<10;i++) {
            if (vectorRe[i] != null) {
                System.out.println("posicion "+i + ","+ vectorRe[i].getCara() );
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
    public int getPuntuacionRonda(){
        int numeroDe42 = 0;
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

}

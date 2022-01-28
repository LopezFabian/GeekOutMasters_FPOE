package myProject;

import java.util.Random;

/**
 *
 * @author Fabian Lopez
 * @author Juan Jose Viafara
 * @version V.2.0.0 date 16/01/2022
 */

public class Dado {
    private String cara, caraContraria;
    private int numberCara;
    //private String caraContraria;

    /**
     * Class
     * Constructor
     */
    public Dado(){
        Random numeroAleatorio= new Random();
        numberCara =numeroAleatorio.nextInt(6)+1;
    };

    /**
     * Method that generate a random value to cara.
     * @return meeple, cohete, superheroe, corazon, dragon or 42
     */
    public String getCara() {
        switch (numberCara) {
            case 1 -> cara = "meeple";
            case 2 -> cara = "cohete";
            case 3 -> cara = "superheroe";
            case 4 -> cara = "corazon";
            case 5 -> cara = "dragon";
            case 6 -> cara = "42";
        }
        return cara;
    }
    /**
     * Method that generate a random value to caraContraria.
     * @return meeple, cohete, superheroe, corazon, dragon or 42
     */
    public String getCaraContraria(){
        switch (numberCara) {
            case 1 -> caraContraria = "cohete";
            case 2 -> caraContraria = "meeple";
            case 3 -> caraContraria = "dragon";
            case 4 -> caraContraria = "42";
            case 5 -> caraContraria = "superheroe";
            case 6 -> caraContraria = "corazon";
        }
        return caraContraria;
    }

    /**
     * change the face of a dice
     * @param caraS the face you want to change to
     **/
    public void setCara(String caraS){
        switch (caraS) {
            case "meeple" -> numberCara = 1;
            case "cohete" -> numberCara = 2;
            case "superheroe" -> numberCara = 3;
            case "corazon" -> numberCara = 4;
            case "dragon" -> numberCara = 5;
            case "42" -> numberCara = 6;
        }
    }
}

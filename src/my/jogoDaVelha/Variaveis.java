package my.jogoDaVelha;

import static my.jogoDaVelha.telaJogo.Mresultado;

public class Variaveis {
    static int auxm;
    static int aux;
    static String nJ1 = new String("");
    static String nJ2 = new String("");
    static int rodadas;
    static String simboloJ1;
    static String simboloJ2;
    
//STRING DAS MATRIZES
    static String matA1 = new String("");
    static String matA2 = new String("");

    static String matB1 = new String("");
    static String matB2 = new String("");

    static String matC1 = new String("");
    static String matC2 = new String("");
    
    static String matD1 = new String("");
    static String matD2 = new String("");
    
    static String matE1 = new String("");
    static String matE2 = new String("");
    
    static String matF1 = new String("");
    static String matF2 = new String("");

    //SOMATORIO
    static String resultadoBin = new String("");
    static String resultadoOctal = new String("");
    static String resultadoHex = new String("");
    static String B1;  //OK
    static String O1;  //OK
    static String H1;  //OK
    static int Msoma = 0;

    public static String convertaBin(int x){
        if (x>0) {
            convertaBin(x/2);
            resultadoBin +=x%2;
        }
        
        
        return resultadoBin;
    }
    
    public static String convertaOct(int x){
        while (x!=0) {            
            resultadoOctal += x%8;
            x/=8;
        }
        return resultadoOctal;
    }
    
    public static String convertaHex(int x){
      
        while (x!=0) {            
            if (x%16 == 10) {
            resultadoHex += "A";
        } else if (x%16 == 11) {
            resultadoHex += "B";
        }else if (x%16 == 12) {
            resultadoHex += "C";
        }else if (x%16 == 13) {
            resultadoHex += "D";
        }else if (x%16 == 14) {
            resultadoHex += "E";
        }else if (x%16 == 15) {
            resultadoHex += "F";
        }else{
            resultadoHex += x%16;
        }
            x = x/16;
        }
        return resultadoHex;
        
    }
    
    public static void LimparMatriz() {
        nJ1 = ("");
        nJ2 = ("");
        matA1 = ("");
        matA2 = ("");
        matB1 = ("");
        matB2 = ("");
        matC1 = ("");
        matC2 = ("");
        matD1 = ("");
        matD2 = ("");
        matE1 = ("");
        matE2 = ("");
        matF1 = ("");
        matF2 = ("");
        Msoma = 0;
        resultadoBin = ("");
        resultadoOctal = ("");
        resultadoHex = ("");
        simboloJ1 = ("");
        simboloJ2 = ("");

    }

}

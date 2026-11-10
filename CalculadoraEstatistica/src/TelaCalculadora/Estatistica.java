package TelaCalculadora;
import java.util.Arrays;


public class Estatistica {
	
	 double soma = 0;


    public double media(double[] numero){
        for(int i = 0; i<numero.length; i++){
            soma = soma + numero[i];
        }
        double media = soma/numero.length;
        return media;
    }
    

    public double moda(double[]numero){
        if(numero.length == 0 || numero == null){
            return 0;
        }

        Arrays.sort(numero);

        double moda = numero[0];
        int frequenciaMax = 1;

        double numeroAtual = numero[0];
        int frequenciaAtual = 1;

        for(int i = 1; i<numero.length; i++){
            if(numero[i] == numeroAtual){
                frequenciaAtual++;
            }else{
                numeroAtual = numero[i];
                frequenciaAtual = 1;
            }

            if(frequenciaAtual > frequenciaMax){
                frequenciaMax = frequenciaAtual;
                moda = numeroAtual;
            }
        }

        if (frequenciaMax <=1){
            return 00;
        }
        return moda;
    }
    

    public double mediana(double[] numero){
        Arrays.sort(numero);

        int meio = numero.length/2;

        if (numero.length % 2 == 0){

            return (numero[meio-1] + numero[meio])/2;

        }else{
            return numero[((numero.length+1)/2)-1];
        }
    }
    

    public double q1 (double[]numero){
        Arrays.sort(numero);

        double pos = (numero.length + 1)/4.0;

        int inteiro = (int) pos;
        double decimal = pos - inteiro;

        if(decimal == 0){
            return numero[inteiro-1];
        }else{
            double valorInferior = numero[inteiro - 1];
            double valorSuperior = numero[inteiro];

            return valorInferior + decimal * (valorSuperior - valorInferior);
        }
    }
    

    public double q3(double[]numero){
        Arrays.sort(numero);

        double pos = 3* (numero.length + 1)/4.0;

        int inteiro = (int) pos;
        double decimal = pos - inteiro;

        if(decimal == 0){
            return numero[inteiro-1];
        }else{
            double valorInferior = numero[inteiro - 1];
            double valorSuperior = numero[inteiro];

            return valorInferior + decimal * (valorSuperior - valorInferior);
        }
    }
    

    public double percentil(int percentil, double[]numero){
        Arrays.sort(numero);

        double p = (percentil * (numero.length +1))/100;

        if(percentil <1){
            return numero[0];
        }

        if(percentil >= numero.length){
            return numero[numero.length -1];
        }

        return numero[(int)p -1];
    }
    

    public double variancia(double[] numero){
        for(int i = 0; i<numero.length; i++){
            soma = soma + numero[i];
        }
        double media = soma/numero.length;

        double somatorio = 0;

        for (int j = 0; j<numero.length; j++){
            somatorio = somatorio + Math.pow((numero[j]-media),2);
        }
        double variancia = somatorio/numero.length;

        return variancia;
    }
    

    public double desvioPadrao(double variancia){
        return Math.sqrt(variancia);
    }
    

    public double coeficienteV(double desvio, double media){
        double coeficienteV = (desvio/media)*100;

        return coeficienteV;

    }

}

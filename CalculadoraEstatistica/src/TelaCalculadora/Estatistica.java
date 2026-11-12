package TelaCalculadora;
import java.util.Arrays;

public class Estatistica {

    public double media(double[] numero) {
        if (numero == null || numero.length == 0) {
        	return 0;
        }
        double soma = 0;
        for (int i = 0; i < numero.length; i++) {
            soma += numero[i];
        }
        return soma / numero.length;
    }
    
    public double moda(double[] numero) {
        if (numero == null || numero.length == 0) {
            return 0;
        }

        double[] copia = numero.clone();
        Arrays.sort(copia);

        double moda = copia[0];
        int frequenciaMax = 1;

        double numeroAtual = copia[0];
        int frequenciaAtual = 1;

        for (int i = 1; i < copia.length; i++) {
            if (copia[i] == numeroAtual) {
                frequenciaAtual++;
            } else {
                numeroAtual = copia[i];
                frequenciaAtual = 1;
            }

            if (frequenciaAtual > frequenciaMax) {
                frequenciaMax = frequenciaAtual;
                moda = numeroAtual;
            }
        }

        if (frequenciaMax <= 1) {
            return 0;
        }
        
        return moda;
    }
    
    public double mediana(double[] numero) {
        if (numero == null || numero.length == 0) {
        	return 0;
        }
        
        double[] copia = numero.clone();
        Arrays.sort(copia);

        int meio = copia.length / 2;

        if (copia.length % 2 == 0) {
            return (copia[meio - 1] + copia[meio]) / 2;
        } else {
            return copia[meio];
        }
    }
    
    public double q1(double[] numero) {
        if (numero == null || numero.length == 0) {
        	return 0;
        }
        
        double[] copia = numero.clone();
        Arrays.sort(copia);

        double pos = (copia.length + 1) / 4;
        int inteiro = (int) pos;
        double decimal = pos - inteiro;

        if (inteiro <= 0) {
            return copia[0];
        }
        if (inteiro >= copia.length) {
            return copia[copia.length - 1];
        }

        if (decimal == 0) {
            return copia[inteiro - 1];
        } else {
            double valorInferior = copia[inteiro - 1];
            double valorSuperior = copia[inteiro];
            return valorInferior + decimal * (valorSuperior - valorInferior);
        }
    }
    
    public double q3(double[] numero) {
        if (numero == null || numero.length == 0) {
        	return 0;
        }
        
        double[] copia = numero.clone();
        Arrays.sort(copia);

        double pos = 3 * (copia.length + 1) / 4;
        int inteiro = (int) pos;
        double decimal = pos - inteiro;

        if (inteiro <= 0) {
            return copia[0];
        }
        if (inteiro >= copia.length) {
            return copia[copia.length - 1];
        }

        if (decimal == 0) {
            return copia[inteiro - 1];
        } else {
            double valorInferior = copia[inteiro - 1];
            double valorSuperior = copia[inteiro];
            return valorInferior + decimal * (valorSuperior - valorInferior);
        }
    }
    
    public double percentil(int percentil, double[] numero) {
        if (numero == null || numero.length == 0) {
        	return 0;
        }
        
        double[] copia = numero.clone();
        Arrays.sort(copia);

        if (percentil <= 0) {
            return copia[0];
        }
        if (percentil >= 100) {
            return copia[copia.length - 1];
        }

        double p = (percentil * (copia.length + 1)) / 100.0;
        int indice = (int) p - 1;
        
        if (indice < 0) return copia[0];
        if (indice >= copia.length) return copia[copia.length - 1];

        return copia[indice];
    }
    
    public double variancia(double[] numero) {
        if (numero == null || numero.length == 0) {
        	return 0;
        }
        
        double soma = 0;
        
        for(int i = 0; i<numero.length; i++){
            soma = soma + numero[i];
        }

        
        double media = soma/numero.length;

        double somatorio = 0;

        for (int j = 0; j < numero.length; j++) {
            somatorio += Math.pow((numero[j] - media), 2);
        }
        return somatorio / numero.length;
    }
    
    public double desvioPadrao(double variancia) {
        return Math.sqrt(variancia);
    }
    
    public double coeficienteV(double desvio, double media) {
        if (media == 0) {
        	return 0;
        }
        return (desvio / media) * 100;
    }
}
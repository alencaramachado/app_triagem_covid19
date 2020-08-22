package br.ufsm.csi.service;

import br.ufsm.csi.model.CheckSintomas;

public class CheckSintomasService {

 /*
        covid >= 4

        Febre > 37,8 = 2 pontos
        tosse (com ou sem catarro) = 1 pontos
        Rouquidao = 1 ponto;
        Dor de garganta = 1 ponto;
        Nariz entupido = 1 ponto


        qtdDias > 1
        qtdDias > 5
        qtdDias > 7 (suspeito)
        qtdDias > 10 (suspeito)

    * */

    public String suspeitoCOVID(CheckSintomas sintomas){

        int pontos = calculaPontos(sintomas);

        if(pontos >=4 && sintomas.getQtdDiasSintomas() >=10){
            return "CASO URGENTE: PACIENTE DEVE SER INTERNADO IMEDIATAMENTE";
        }else  if(pontos >=4 && sintomas.getQtdDiasSintomas() >=7){
            return "ATENÇÃO: ENCAMINHAR PACIENTE PARA AVALIAÇÃO MÉDICA";
        }else if(pontos >=4 && sintomas.getQtdDiasSintomas() >=5){
            return "CASO SUSPEITO: PACIENTE DEVE SER ENCAMINHADO PARA EXAMES E AVALIAÇÃO MÉDICA";
        }else if(pontos >=4 && sintomas.getQtdDiasSintomas() < 5){
            return "CASO SUSPEITO: PACIENTE DEVE SER ENCAMINHADO PARA EXAMES";
        }else{
            return "";
        }


    }

    public boolean suspeito(CheckSintomas sintomas){
        int pontos = calculaPontos(sintomas);
        if(pontos >=4 && sintomas.getQtdDiasSintomas() >=10){
            return true;
        }else  if(pontos >=4 && sintomas.getQtdDiasSintomas() >=7){
            return true;
        }else if(pontos >=4 && sintomas.getQtdDiasSintomas() >=5){
            return true;
        }else if(pontos >=4 && sintomas.getQtdDiasSintomas() < 5){
            return false;
        }else{
            return false;
        }

    }


    private int calculaPontos(CheckSintomas sintomas){
        int pontos = 0;

        if(sintomas.getTemperatura() > 37.8f){
            pontos = pontos + 2;
        }
        if(sintomas.isTosse()){
            pontos = pontos + 1;
        }
        if(sintomas.isRouquidao()){
            pontos = pontos + 1;
        }
        if(sintomas.isDorGarganta()){
            pontos = pontos + 1;
        }
        if(sintomas.isNarizEntupido()){
            pontos = pontos + 1;
        }
        return pontos;
    }


}

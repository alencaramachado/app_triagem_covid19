package br.ufsm.csi.model;

import java.sql.Date;

public class CheckSintomas {

    private int id;
    private Paciente paciente;
    private AgenteSaude agenteSaude;
    private float temperatura;
    private boolean tosse;
    private boolean catarro;
    private boolean rouquidao;
    private boolean dorGarganta;
    private boolean narizEntupido;
    private Date dataAvaliacao;
    private int qtdDiasSintomas;
    private boolean casoSuspeito;


    public CheckSintomas(Paciente paciente, AgenteSaude agenteSaude, float temperatura, boolean tosse, boolean catarro,
                         boolean rouquidao, boolean dorGarganta, boolean narizEntupido, int qtdDiasSintomas) {
        this.paciente = paciente;
        this.agenteSaude = agenteSaude;
        this.temperatura = temperatura;
        this.tosse = tosse;
        this.catarro = catarro;
        this.rouquidao = rouquidao;
        this.dorGarganta = dorGarganta;
        this.narizEntupido = narizEntupido;
        this.qtdDiasSintomas = qtdDiasSintomas;
    }

    public CheckSintomas(Paciente paciente, AgenteSaude agenteSaude, float temperatura, boolean tosse, boolean catarro,
                         boolean rouquidao, boolean dorGarganta, boolean narizEntupido, int qtdDiasSintomas, Date dataAvaliacao) {
        this.paciente = paciente;
        this.agenteSaude = agenteSaude;
        this.temperatura = temperatura;
        this.tosse = tosse;
        this.catarro = catarro;
        this.rouquidao = rouquidao;
        this.dorGarganta = dorGarganta;
        this.narizEntupido = narizEntupido;
        this.qtdDiasSintomas = qtdDiasSintomas;
        this.setDataAvaliacao(dataAvaliacao);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public AgenteSaude getAgenteSaude() {
        return agenteSaude;
    }

    public void setAgenteSaude(AgenteSaude agenteSaude) {
        this.agenteSaude = agenteSaude;
    }

    public float getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(float temperatura) {
        this.temperatura = temperatura;
    }

    public boolean isTosse() {
        return tosse;
    }

    public void setTosse(boolean tosse) {
        this.tosse = tosse;
    }

    public boolean isCatarro() {
        return catarro;
    }

    public void setCatarro(boolean catarro) {
        this.catarro = catarro;
    }

    public boolean isRouquidao() {
        return rouquidao;
    }

    public void setRouquidao(boolean rouquidao) {
        this.rouquidao = rouquidao;
    }

    public boolean isDorGarganta() {
        return dorGarganta;
    }

    public void setDorGarganta(boolean dorGarganta) {
        this.dorGarganta = dorGarganta;
    }

    public boolean isNarizEntupido() {
        return narizEntupido;
    }

    public void setNarizEntupido(boolean narizEntupido) {
        this.narizEntupido = narizEntupido;
    }

    public Date getDataAvaliacao() {
        return dataAvaliacao;
    }

    public void setDataAvaliacao(Date dataAvaliacao) {
        this.dataAvaliacao = dataAvaliacao;
    }

    public int getQtdDiasSintomas() {
        return qtdDiasSintomas;
    }

    public void setQtdDiasSintomas(int qtdDiasSintomas) {
        this.qtdDiasSintomas = qtdDiasSintomas;
    }

    public boolean isCasoSuspeito() {
        return casoSuspeito;
    }

    public void setCasoSuspeito(boolean casoSuspeito) {
        this.casoSuspeito = casoSuspeito;
    }
}

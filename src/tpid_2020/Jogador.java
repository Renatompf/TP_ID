package tpid_2020;

import java.io.IOException;
import java.util.ArrayList;

public class Jogador {

    String nomeCompleto, alcunha, linkFoto, dataNascimento, nacionalidade, prefPe, posicao, clubeAtual, selecaoNacional, estadoAtual, empresario;
    double peso, valorMercado, altura;
    int nrCamisola, idade;
    ArrayList<String> premiosGanhos, clubesAnteriores;

    public Jogador(String nomeCompleto, String alcunha, String linkFoto, String dataNascimento, String nacionalidade, String prefPe, String posicao, String clubeAtual, String selecaoNacional, String estadoAtual, String empresario, double peso, double valorMercado, double altura, int nrCamisola, int idade, ArrayList<String> premiosGanhos, ArrayList<String> clubesAnteriores) {
        this.nomeCompleto = nomeCompleto;
        this.alcunha = alcunha;
        this.linkFoto = linkFoto;
        this.dataNascimento = dataNascimento;
        this.nacionalidade = nacionalidade;
        this.prefPe = prefPe;
        this.posicao = posicao;
        this.clubeAtual = clubeAtual;
        this.selecaoNacional = selecaoNacional;
        this.estadoAtual = estadoAtual;
        this.empresario = empresario;
        this.peso = peso;
        this.valorMercado = valorMercado;
        this.altura = altura;
        this.nrCamisola = nrCamisola;
        this.idade = idade;
        this.premiosGanhos = premiosGanhos;
        this.clubesAnteriores = clubesAnteriores;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getAlcunha() {
        return alcunha;
    }

    public void setAlcunha(String alcunha) {
        this.alcunha = alcunha;
    }

    public String getLinkFoto() {
        return linkFoto;
    }

    public void setLinkFoto(String linkFoto) {
        this.linkFoto = linkFoto;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getPrefPe() {
        return prefPe;
    }

    public void setPrefPe(String prefPe) {
        this.prefPe = prefPe;
    }

    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    public String getClubeAtual() {
        return clubeAtual;
    }

    public void setClubeAtual(String clubeAtual) {
        this.clubeAtual = clubeAtual;
    }

    public String getSelecaoNacional() {
        return selecaoNacional;
    }

    public void setSelecaoNacional(String selecaoNacional) {
        this.selecaoNacional = selecaoNacional;
    }

    public String getEstadoAtual() {
        return estadoAtual;
    }

    public void setEstadoAtual(String estadoAtual) {
        this.estadoAtual = estadoAtual;
    }

    public String getEmpresario() {
        return empresario;
    }

    public void setEmpresario(String empresario) {
        this.empresario = empresario;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getValorMercado() {
        return valorMercado;
    }

    public void setValorMercado(double valorMercado) {
        this.valorMercado = valorMercado;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public int getNrCamisola() {
        return nrCamisola;
    }

    public void setNrCamisola(int nrCamisola) {
        this.nrCamisola = nrCamisola;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public ArrayList<String> getPremiosGanhos() {
        return premiosGanhos;
    }

    public void setPremiosGanhos(ArrayList<String> premiosGanhos) {
        this.premiosGanhos = premiosGanhos;
    }

    public ArrayList<String> getClubesAnteriores() {
        return clubesAnteriores;
    }

    public void setClubesAnteriores(ArrayList<String> clubesAnteriores) {
        this.clubesAnteriores = clubesAnteriores;
    }

    
    public static Jogador cria_Jogador(String nome) throws IOException {
        Jogador player;
        String n = Wrappers.obtemNomeCompleto(nome);
        String a = Wrappers.obtemNomeMaisConhecido(nome);
        String l = Wrappers.obtemLinkFoto(nome);
        String d = Wrappers.obtemDataNascimento(nome);
        String na = Wrappers.obtemNacionalidade(nome);
        String pp = Wrappers.obtemPePref(nome);
        String p = Wrappers.obtemPosicao(nome);
        String ca = Wrappers.obtemClubeAtual(nome);
        String s = Wrappers.obtemSelecao(nome);
        String e = Wrappers.obtemEstadoAtual(nome);
        String emp = Wrappers.obtemAgente(nome);
        double peso = Wrappers.obtemPeso(nome);
        double altura = Wrappers.obtemAltura(nome);
        double vdm = Wrappers.obtemVDM(nome);
        int nr = Wrappers.obtemNumero(nome);
        int idade = Wrappers.obtemIdade(nome);
        ArrayList<String> premios = Wrappers.obtemPremios(nome);
        ArrayList<String> clubesA = Wrappers.obtemClubesAnteriores(nome);

        player = new Jogador(n, a, l, d, na, pp, p, ca, s, e, emp, peso, vdm, altura, nr, idade, premios, clubesA);
        return player;
    }

}

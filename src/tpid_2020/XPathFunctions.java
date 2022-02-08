/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpid_2020;

import java.io.File;
import java.util.List;
import java.util.Scanner;
import net.sf.saxon.s9api.DocumentBuilder;
import net.sf.saxon.s9api.Processor;
import net.sf.saxon.s9api.SaxonApiException;
import net.sf.saxon.s9api.XPathCompiler;
import net.sf.saxon.s9api.XPathSelector;
import net.sf.saxon.s9api.XdmItem;
import net.sf.saxon.s9api.XdmNode;
import net.sf.saxon.s9api.XdmValue;

public class XPathFunctions {

    static XdmValue executaXpath(String xp, String xmlFile) throws SaxonApiException {
        XdmValue resultado = null;
        File f = new File(xmlFile);
        if (f.exists()) {
            Processor proc = new Processor(false);
            XPathCompiler xpath = proc.newXPathCompiler();

            DocumentBuilder builder = proc.newDocumentBuilder();

            XdmNode xml = builder.build(new File(xmlFile));
            XPathSelector selector = xpath.compile(xp).load();

            selector.setContextItem(xml);
            resultado = selector.evaluate();
        }
        return resultado;
    }

    public static String mostraInformaçãoRelevante(String XMLFile, String nome) throws SaxonApiException {

        //Scanner scan = new Scanner(System.in);
        //System.out.println("Nome do Jogador:");
        //String nome = scan.next();
        String xp = "//Jogador[contains(Nome, '" + nome + "')]/Nome | //Jogador[contains(Nome, '" + nome + "')]/Alcunha | //Jogador[contains(Nome, '" + nome + "')]/Nacionalidade | //Jogador[contains(Nome, '" + nome + "')]/Clubes/ClubeAtual |"
                + "//Jogador[contains(Nome, '" + nome + "')]/Idade | //Jogador[contains(Nome, '" + nome + "')]/ValorContrato | //Jogador[contains(Nome, '" + nome + "')]/Premios";
        XdmValue res = XPathFunctions.executaXpath(xp, XMLFile);
        String s = XPathFunctions.listaResultado(res);
        if (res == null) {
            System.out.println("Ficheiro XML não existe");
        } else if (res.size() == 0) {
            System.out.println("Sem resultados");
        }else {
            System.out.println(s);
        }
        return s;
    }

    public static String mostraJogadoresPClube(String XMLFile, String nome) throws SaxonApiException {
        //Scanner scan = new Scanner(System.in);
        //System.out.println("Nome do Clube:");
        //String nome = scan.next();
        String xp = "//Jogador[Clubes[contains(ClubeAtual, '" + nome + "')]]/Nome/text()";
        System.out.println(xp);
        XdmValue res = XPathFunctions.executaXpath(xp, XMLFile);
        String s = XPathFunctions.listaResultado(res);
        if (res == null) {
            System.out.println("Ficheiro XML não existe");
        } else if (res.size() == 0) {
            System.out.println("Sem resultados");
        } else {
            System.out.println(s);
        }
        return s;
    }

    public static String mostraJogadoresNacionalidade(String XMLFile, String nome) throws SaxonApiException {
        //Scanner scan = new Scanner(System.in);
        //System.out.println("Nome do Clube:");
        //String nome = scan.next();
        String xp = "//Jogador[Nacionalidade = '" + nome + "']/Nome/text()";
        System.out.println(xp);
        XdmValue res = XPathFunctions.executaXpath(xp, XMLFile);
        String s = XPathFunctions.listaResultado(res);
        if (res == null) {
            System.out.println("Ficheiro XML não existe");
        } else if (res.size() == 0) {
            System.out.println("Sem resultados");
        } //else {
        //System.out.println(s);
        //}
        return s;
    }

    public static String mostraJogadorPosicao(String XMLFile, String nome) throws SaxonApiException {
        //Scanner scan = new Scanner(System.in);
        //System.out.println("Nome do Clube:");
        //String nome = scan.next();
        String xp = "//Jogador[contains(Posicao, '" + nome + "')]/Nome/text()";
        System.out.println(xp);
        XdmValue res = XPathFunctions.executaXpath(xp, XMLFile);
        String s = XPathFunctions.listaResultado(res);
        if (res == null) {
            System.out.println("Ficheiro XML não existe");
        } else if (res.size() == 0) {
            System.out.println("Sem resultados");
        } //else {
        //System.out.println(s);
        //}
        return s;
    }

    public static String mostraJogadorEstado(String XMLFile, String nome) throws SaxonApiException {
        //Scanner scan = new Scanner(System.in);
        //System.out.println("Nome do Clube:");
        //String nome = scan.next();
        String xp = "//Jogador[contains(@estadoAtual, '" + nome + "')]/Nome/text()";
        System.out.println(xp);
        XdmValue res = XPathFunctions.executaXpath(xp, XMLFile);
        String s = XPathFunctions.listaResultado(res);
        if (res == null) {
            System.out.println("Ficheiro XML não existe");
        } else if (res.size() == 0) {
            System.out.println("Sem resultados");
        } //else {
        //System.out.println(s);
        //}
        return s;
    }

    public static String mostraJogadorIdades(String XMLFile, int idadeMin, int idadeMax) throws SaxonApiException {
        String xp = "//Jogador[Idade >= " + idadeMin + " and Idade <= " + idadeMax + "]/Nome/text()";
        System.out.println(xp);
        XdmValue res = XPathFunctions.executaXpath(xp, XMLFile);
        String s = XPathFunctions.listaResultado(res);
        if (res == null) {
            System.out.println("Ficheiro XML não existe");
        } else if (res.size() == 0) {
            System.out.println("Sem resultados");
        }
        return s;
    }
    
        public static String mostraJogadorVDM(String XMLFile, int vdcMin, int vdcMax) throws SaxonApiException {
        String xp = "//Jogador[ValorContrato >= " + vdcMin + " and ValorContrato <= " + vdcMax + "]/Nome/text()";
        System.out.println(xp);
        XdmValue res = XPathFunctions.executaXpath(xp, XMLFile);
        String s = XPathFunctions.listaResultado(res);
        if (res == null) {
            System.out.println("Ficheiro XML não existe");
        } else if (res.size() == 0) {
            System.out.println("Sem resultados");
        }
        return s;
    }

    static String listaResultado(XdmValue lista) {
        StringBuilder texto = new StringBuilder();
        System.out.println("RESULTADO DA PESQUISA XPATH:");
        for (XdmItem item : lista) {
            texto = texto.append(item.getStringValue()).append("\n");
        }
        return texto.toString();
    }

}

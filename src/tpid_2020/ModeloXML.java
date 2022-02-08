package tpid_2020;

import java.util.ArrayList;
import java.util.List;
import net.sf.saxon.s9api.SaxonApiException;
import net.sf.saxon.s9api.XdmValue;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;

public class ModeloXML {

    public static Document adicionaJogador(Jogador player, Document doc) throws SaxonApiException {
        Element raiz;
        if (doc == null) {
            raiz = new Element("Jogadores");
            doc = new Document(raiz);
            raiz = doc.getRootElement();

            Element jogador = new Element("Jogador");
            Attribute estado = new Attribute("estadoAtual", player.getEstadoAtual());
            jogador.setAttribute(estado);

            Element nome = new Element("Nome").addContent(player.getNomeCompleto());
            jogador.addContent(nome);
            Element alcunha = new Element("Alcunha").addContent(player.getAlcunha());
            jogador.addContent(alcunha);
            Element dataNascimento = new Element("DataNascimento").addContent(player.getDataNascimento());
            jogador.addContent(dataNascimento);
            Element idade = new Element("Idade").addContent(Integer.toString(player.getIdade()));
            jogador.addContent(idade);
            Element nacionalidade = new Element("Nacionalidade").addContent(player.getNacionalidade());
            jogador.addContent(nacionalidade);
            Element altura = new Element("Altura").addContent(Double.toString(player.getAltura()));
            jogador.addContent(altura);
            Element peso = new Element("Peso").addContent(Double.toString(player.getPeso()));
            jogador.addContent(peso);
            Element PePref = new Element("PePreferencial").addContent(player.getPrefPe());
            jogador.addContent(PePref);
            Element posicao = new Element("Posicao").addContent(player.getPosicao());
            jogador.addContent(posicao);
            Element selecao = new Element("Selecao").addContent(player.getSelecaoNacional());
            jogador.addContent(selecao);
            if (player.getNrCamisola() != -1) {
                Element nrcamisola = new Element("NrCamisola").addContent(Integer.toString(player.getNrCamisola()));
                jogador.addContent(nrcamisola);
            }
            if (player.getValorMercado() > 0) {
                Element vdm = new Element("ValorContrato").addContent(Double.toString(player.getValorMercado()));
                jogador.addContent(vdm);
            }
            Element empresario = new Element("Empresario").addContent(player.getEmpresario());
            jogador.addContent(empresario);
            Element foto = new Element("Foto").addContent(player.getLinkFoto());
            jogador.addContent(foto);
            Element premios = new Element("Premios");
            ArrayList<String> awards = player.getPremiosGanhos();
            for (int i = 0; i < awards.size(); i++) {
                Element premio = new Element("Premio").addContent(awards.get(i));
                premios.addContent(premio);
            }
            jogador.addContent(premios);
            Element clubes = new Element("Clubes");
            Element clubeAtual = new Element("ClubeAtual").addContent(player.getClubeAtual());
            clubes.addContent(clubeAtual);
            Element clubesAnteriores = new Element("ClubesAnteriores");
            clubes.addContent(clubesAnteriores);
            ArrayList<String> anteriores = player.getClubesAnteriores();
            for (int i = 0; i < anteriores.size(); i++) {
                Element clubeAnterior = new Element("ClubeAnterior").addContent(anteriores.get(i));
                clubesAnteriores.addContent(clubeAnterior);
            }
            jogador.addContent(clubes);
            raiz.addContent(jogador);

            System.out.println("Documento criado com sucesso e jogador inserido com sucesso");
        } else {
            raiz = doc.getRootElement();
            String xp = "//Jogador[Nome = '" + player.getNomeCompleto() + "']";
            XdmValue res = XPathFunctions.executaXpath(xp, "ID_XMLFile.xml");
            if (res != null && res.size() > 0) {
                System.out.println("O jogador já existe");
                return null;
            } else {
                Element jogador = new Element("Jogador");
                Attribute estado = new Attribute("estadoAtual", player.getEstadoAtual());
                jogador.setAttribute(estado);

                Element nome = new Element("Nome").addContent(player.getNomeCompleto());
                jogador.addContent(nome);
                Element alcunha = new Element("Alcunha").addContent(player.getAlcunha());
                jogador.addContent(alcunha);
                Element dataNascimento = new Element("DataNascimento").addContent(player.getDataNascimento());
                jogador.addContent(dataNascimento);
                Element idade = new Element("Idade").addContent(Integer.toString(player.getIdade()));
                jogador.addContent(idade);
                Element nacionalidade = new Element("Nacionalidade").addContent(player.getNacionalidade());
                jogador.addContent(nacionalidade);
                Element altura = new Element("Altura").addContent(Double.toString(player.getAltura()));
                jogador.addContent(altura);
                Element peso = new Element("Peso").addContent(Double.toString(player.getPeso()));
                jogador.addContent(peso);
                Element PePref = new Element("PePreferencial").addContent(player.getPrefPe());
                jogador.addContent(PePref);
                Element posicao = new Element("Posicao").addContent(player.getPosicao());
                jogador.addContent(posicao);
                Element selecao = new Element("Selecao").addContent(player.getSelecaoNacional());
                jogador.addContent(selecao);
                if (player.getNrCamisola() != -1) {
                    Element nrcamisola = new Element("NrCamisola").addContent(Integer.toString(player.getNrCamisola()));
                    jogador.addContent(nrcamisola);
                }
                if (player.getValorMercado() > 0) {
                    Element vdm = new Element("ValorContrato").addContent(Double.toString(player.getValorMercado()));
                    jogador.addContent(vdm);
                }
                Element empresario = new Element("Empresario").addContent(player.getEmpresario());
                jogador.addContent(empresario);
                Element foto = new Element("Foto").addContent(player.getLinkFoto());
                jogador.addContent(foto);
                Element premios = new Element("Premios");
                ArrayList<String> awards = player.getPremiosGanhos();
                for (int i = 0; i < awards.size(); i++) {
                    Element premio = new Element("Premio").addContent(awards.get(i));
                    premios.addContent(premio);
                }
                jogador.addContent(premios);
                Element clubes = new Element("Clubes");
                Element clubeAtual = new Element("ClubeAtual").addContent(player.getClubeAtual());
                clubes.addContent(clubeAtual);
                Element clubesAnteriores = new Element("ClubesAnteriores");
                clubes.addContent(clubesAnteriores);
                ArrayList<String> anteriores = player.getClubesAnteriores();
                for (int i = 0; i < anteriores.size(); i++) {
                    Element clubeAnterior = new Element("ClubeAnterior").addContent(anteriores.get(i));
                    clubesAnteriores.addContent(clubeAnterior);
                }
                jogador.addContent(clubes);
                raiz.addContent(jogador);

                System.out.println("Jogador inserido com sucesso");
            }
        }
        return doc;
    }

    public static Document removeJogador(String procura, Document doc) {
        Element raiz;
        if (doc == null) {
            System.out.println("Ficheiro não existe, nada a remover");
            return null;
        } else {
            raiz = doc.getRootElement();
        }
        List todosJogadores = raiz.getChildren("Jogador");
        boolean found = false;
        for (int i = 0; i < todosJogadores.size(); i++) {
            Element jogador = (Element) todosJogadores.get(i); //obtem livro i da Lista
            if (jogador.getChild("Nome").getText().contains(procura)) {
                jogador.getParent().removeContent(jogador);
                System.out.println("Jogador removido com sucesso!");
                found = true;
            }
        }
        if (!found) {
            System.out.println("Jogador " + procura + " não foi encontrado");
            return null;
        }
        return doc;
    }

    public static Document editaIdade(String procura, int novaIdade, Document doc) {
        Element raiz;
        if (doc == null) {
            System.out.println("Ficheiro não existe, nada a remover");
            return null;
        } else {
            raiz = doc.getRootElement();
        }
        List todosJogadores = raiz.getChildren("Jogador");
        boolean found = false;
        for (int i = 0; i < todosJogadores.size(); i++) {
            Element jogador = (Element) todosJogadores.get(i); //obtem livro i da Lista
            if (jogador.getChild("Nome").getText().contains(procura)) {
                jogador.getChild("Idade").setText(Integer.toString(novaIdade));
                System.out.println("Idade alterada com sucesso!");
                found = true;
            }
        }
        if (!found) {
            System.out.println("Jogador " + procura + " não foi encontrado");
            return null;
        }
        return doc;
    }

    public static Document editaNacionalidade(String procura, String novaNacionalidade, Document doc) {
        Element raiz;
        if (doc == null) {
            System.out.println("Ficheiro não existe, nada a remover");
            return null;
        } else {
            raiz = doc.getRootElement();
        }
        List todosJogadores = raiz.getChildren("Jogador");
        boolean found = false;
        for (int i = 0; i < todosJogadores.size(); i++) {
            Element jogador = (Element) todosJogadores.get(i); //obtem livro i da Lista
            if (jogador.getChild("Nome").getText().contains(procura)) {
                jogador.getChild("Nacionalidade").setText(novaNacionalidade);
                System.out.println("Nacionalidade alterada com sucesso!");
                found = true;
            }
        }
        if (!found) {
            System.out.println("Jogador " + procura + " não foi encontrado");
            return null;
        }
        return doc;
    }

    public static Document editaClubeAtual(String procura, String novoClube, Document doc) {
        Element raiz;
        if (doc == null) {
            System.out.println("Ficheiro não existe, nada a remover");
            return null;
        } else {
            raiz = doc.getRootElement();
        }
        List todosJogadores = raiz.getChildren("Jogador");
        boolean found = false;
        for (int i = 0; i < todosJogadores.size(); i++) {
            Element jogador = (Element) todosJogadores.get(i); //obtem livro i da Lista
            if (jogador.getChild("Nome").getText().contains(procura)) {
                jogador.getChild("Clubes").getChild("ClubeAtual").setText(novoClube);
                System.out.println("Clube atual alterado com sucesso!");
                found = true;
            }
        }
        if (!found) {
            System.out.println("Jogador " + procura + " não foi encontrado");
            return null;
        }
        return doc;
    }

    public static Document editaPeso(String procura, double novoPeso, Document doc) {
        Element raiz;
        if (doc == null) {
            System.out.println("Ficheiro não existe, nada a remover");
            return null;
        } else {
            raiz = doc.getRootElement();
        }
        List todosJogadores = raiz.getChildren("Jogador");
        boolean found = false;
        for (int i = 0; i < todosJogadores.size(); i++) {
            Element jogador = (Element) todosJogadores.get(i); //obtem livro i da Lista
            if (jogador.getChild("Nome").getText().contains(procura)) {
                jogador.getChild("Peso").setText(Double.toString(novoPeso));
                System.out.println("Peso alterado com sucesso!");
                found = true;
            }
        }
        if (!found) {
            System.out.println("Jogador " + procura + " não foi encontrado");
            return null;
        }
        return doc;
    }

    public static Document editaPrefPe(String procura, String novoPrefPe, Document doc) {
        Element raiz;
        if (doc == null) {
            System.out.println("Ficheiro não existe, nada a remover");
            return null;
        } else {
            raiz = doc.getRootElement();
        }
        List todosJogadores = raiz.getChildren("Jogador");
        boolean found = false;
        for (int i = 0; i < todosJogadores.size(); i++) {
            Element jogador = (Element) todosJogadores.get(i); //obtem livro i da Lista
            if (jogador.getChild("Nome").getText().contains(procura)) {
                jogador.getChild("PePreferencial").setText(novoPrefPe);
                System.out.println("Pe Preferencial alterado com sucesso!");
                found = true;
            }
        }
        if (!found) {
            System.out.println("Jogador " + procura + " não foi encontrado");
            return null;
        }
        return doc;
    }

    public static Document editaPosicao(String procura, String novaPos, Document doc) {
        Element raiz;
        if (doc == null) {
            System.out.println("Ficheiro não existe, nada a remover");
            return null;
        } else {
            raiz = doc.getRootElement();
        }
        List todosJogadores = raiz.getChildren("Jogador");
        boolean found = false;
        for (int i = 0; i < todosJogadores.size(); i++) {
            Element jogador = (Element) todosJogadores.get(i); //obtem livro i da Lista
            if (jogador.getChild("Nome").getText().contains(procura)) {
                jogador.getChild("Posicao").setText(novaPos);
                System.out.println("Posição alterado com sucesso!");
                found = true;
            }
        }
        if (!found) {
            System.out.println("Jogador " + procura + " não foi encontrado");
            return null;
        }
        return doc;
    }

    public static Document editaVDC(String procura, Double novoVDC, Document doc) {
        if (novoVDC < 0) {
            System.out.println("Valor de contrato inserido com valor negativo");
            return doc;
        }
        Element raiz;
        if (doc == null) {
            System.out.println("Ficheiro não existe, nada a remover");
            return null;
        } else {
            raiz = doc.getRootElement();
        }
        List todosJogadores = raiz.getChildren("Jogador");
        boolean found = false;
        for (int i = 0; i < todosJogadores.size(); i++) {
            Element jogador = (Element) todosJogadores.get(i); //obtem livro i da Lista
            if (jogador.getChild("Nome").getText().contains(procura)) {
                jogador.getChild("ValorContrato").setText(Double.toString(novoVDC));
                System.out.println("Valor de contrato alterado com sucesso!");
                found = true;
            }
        }
        if (!found) {
            System.out.println("Jogador " + procura + " não foi encontrado");
            return null;
        }
        return doc;
    }

    public static Document editaEmpresario(String procura, String novoEmp, Document doc) {
        Element raiz;
        if (doc == null) {
            System.out.println("Ficheiro não existe, nada a remover");
            return null;
        } else {
            raiz = doc.getRootElement();
        }
        List todosJogadores = raiz.getChildren("Jogador");
        boolean found = false;
        for (int i = 0; i < todosJogadores.size(); i++) {
            Element jogador = (Element) todosJogadores.get(i); //obtem livro i da Lista
            if (jogador.getChild("Nome").getText().contains(procura)) {
                jogador.getChild("Empresario").setText(novoEmp);
                System.out.println("Empresário alterado com sucesso!");
                found = true;
            }
        }
        if (!found) {
            System.out.println("Jogador " + procura + " não foi encontrado");
            return null;
        }
        return doc;
    }

    public static Document editaEstadoAtual(String procura, String novoEstado, Document doc) {
        Element raiz;
        if (doc == null) {
            System.out.println("Ficheiro não existe, nada a remover");
            return null;
        } else {
            raiz = doc.getRootElement();
        }
        List todosJogadores = raiz.getChildren("Jogador");
        boolean found = false;
        for (int i = 0; i < todosJogadores.size(); i++) {
            Element jogador = (Element) todosJogadores.get(i); //obtem livro i da Lista
            if (jogador.getChild("Nome").getText().contains(procura)) {
                jogador.getAttribute("estadoAtual").setValue(novoEstado);
                System.out.println("Estado alterado com sucesso!");
                found = true;
            }
        }
        if (!found) {
            System.out.println("Jogador " + procura + " não foi encontrado");
            return null;
        }
        return doc;
    }
}

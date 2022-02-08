package tpid_2020;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Wrappers {

    public static ArrayList<String> leFichPlayers() throws FileNotFoundException {
        ArrayList<String> Jogadores = new ArrayList();
        File Filejogadores = new File("players.txt");
        Scanner scan = new Scanner(Filejogadores);

        while (scan.hasNextLine()) {
            Jogadores.add(scan.nextLine());
        }

        return Jogadores;
    }

    //----------------------------------------------------------
    public static String LinkWiki(String nome) throws IOException {
        String link = "https://www.google.com/seatch?q=";
        HttpRequestFunctions.httpRequest1(link, nome + " wikipedia", "wiki.txt");

        String ER1 = "<a href=\"https://pt.wikipedia.org/wiki/[^\"]+\"";
        Pattern p1 = Pattern.compile(ER1);

        Matcher m1;
        var input = new Scanner(new FileInputStream("wiki.txt"));
        while (input.hasNextLine()) {
            String linha = input.nextLine();
            m1 = p1.matcher(linha);
            if (m1.find()) {
                input.close();
                return m1.group(1);
            }
        }
        input.close();
        return null;
    }

    public static String LinkZeroZero(String nome) throws IOException {
        String link = "https://www.zerozero.pt/search.php?inputString=" + nome.replace(" ", "+") + "&op=all";
        HttpRequestFunctions.httpRequest2(link, "", "zero.txt");

        String ER1 = "<a href=\"(jogador\\.php\\?id=[0-9]+&search=1)\">";
        Pattern p1 = Pattern.compile(ER1);

        Matcher m1;
        Scanner input = new Scanner(new FileInputStream("zero.txt"));
        while (input.hasNextLine()) {
            String linha = input.nextLine();
            m1 = p1.matcher(linha);

            if (m1.find()) {
                input.close();
                return "https://www.zerozero.pt/" + m1.group(1);
            }
        }
        input.close();
        return null;
    }

    public static String LinkTransfermarkt(String nome) throws IOException {
        String link = "https://www.transfermarkt.pt/schnellsuche/ergebnis/schnellsuche?query=";
        HttpRequestFunctions.httpRequest1(link, nome, "transfer.txt");
        String ER1 = "href=\"/([a-z-]+/profil/spieler/[0-9]+)\">";
        Pattern p1 = Pattern.compile(ER1);

        Matcher m1;
        Scanner input = new Scanner(new FileInputStream("transfer.txt"));
        while (input.hasNextLine()) {
            String linha = input.nextLine();
            m1 = p1.matcher(linha);

            if (m1.find()) {
                input.close();
                return "https://www.transfermarkt.pt/" + m1.group(1);
            }
        }
        input.close();
        return null;
    }

    //----------------------------------------------------------
    /*public static String obtemPosicao(String nome) throws IOException { //Obtem a posicao do jogador
        String link = LinkZeroZero(nome);
        HttpRequestFunctions.httpRequest2(link, "", "zero_details.txt");
        String ER1 = "<span>Posição</span><tr><td class=\"label\"></td><td>([^<]+)</td>";
        Pattern p = Pattern.compile(ER1);
        Matcher m;

        Scanner scan = new Scanner(new FileInputStream("zero_details.txt"), "windows-1252");
        while (scan.hasNextLine()) {
            String linha = scan.nextLine();
            m = p.matcher(linha);
            if (m.find()) {
                scan.close();
                return m.group(1);
            }
        }
        scan.close();
        return null;
    }*/
    public static String obtemPosicao(String nome) throws IOException { //Obtem a posicao do jogador
        String link = LinkTransfermarkt(nome);
        HttpRequestFunctions.httpRequest1(link, "", "transf_details.txt");
        String ER1 = "<th>Posição:</th>";
        String ER2 = "<td>";
        String ER3 = "([^<]+)                        </td>";

        Pattern p = Pattern.compile(ER1);
        Pattern p2 = Pattern.compile(ER2);
        Pattern p3 = Pattern.compile(ER3);
        Matcher m, m2, m3;

        Scanner scan = new Scanner(new FileInputStream("transf_details.txt"));
        while (scan.hasNextLine()) {
            String linha = scan.nextLine();
            m = p.matcher(linha);
            if (m.find()) {
                linha = scan.nextLine();
                m2 = p2.matcher(linha);
                if (m2.find()) {
                    linha = scan.nextLine();
                    m3 = p3.matcher(linha);
                    if (m3.find()) {
                        scan.close();
                        return m3.group(1);
                    }
                }
            }
        }
        scan.close();
        return "Este jogador não joga pela seleção";
    }

    /*public static Integer obtemAltura(String nome) throws IOException { //Obtem a altura do jogador
        String link = LinkZeroZero(nome);
        HttpRequestFunctions.httpRequest2(link, "", "zero_details.txt");
        String ER1 = "<span>Altura</span>([0-9]+) cm</div>";
        Pattern p = Pattern.compile(ER1);
        Matcher m;

        Scanner scan = new Scanner(new FileInputStream("zero_details.txt"), "windows-1252");
        while (scan.hasNextLine()) {
            String linha = scan.nextLine();
            m = p.matcher(linha);
            if (m.find()) {
                scan.close();
                return Integer.parseInt(m.group(1));
            }
        }
        scan.close();
        return null;
    }*/
    public static Double obtemAltura(String nome) throws IOException { //Obtem a posicao do jogador
        String link = LinkTransfermarkt(nome);
        HttpRequestFunctions.httpRequest1(link, "", "transf_details.txt");
        String ER1 = "<span class=\"dataItem\">Altura:</span>";
        String ER2 = "<span itemprop=\"height\" class=\"dataValue\">([^m]+) m</span>";

        Pattern p = Pattern.compile(ER1);
        Pattern p2 = Pattern.compile(ER2);
        Matcher m, m2;

        Scanner scan = new Scanner(new FileInputStream("transf_details.txt"));
        while (scan.hasNextLine()) {
            String linha = scan.nextLine();
            m = p.matcher(linha);
            if (m.find()) {
                linha = scan.nextLine();
                m2 = p2.matcher(linha);
                if (m2.find()) {
                    scan.close();
                    return Double.parseDouble(m2.group(1).replace(",", "."));
                }
            }
        }
        scan.close();
        return null;
    }

    /*public static String obtemNomeCompleto(String nome) throws IOException { //Obtem o nome completo do jogador
        String link = LinkZeroZero(nome);
        HttpRequestFunctions.httpRequest2(link, "", "zero_details.txt");
        String ER1 = "<span>Nome</span>([^<]+)</div>";
        Pattern p = Pattern.compile(ER1);
        Matcher m;

        Scanner scan = new Scanner(new FileInputStream("zero_details.txt"), "windows-1252");
        while (scan.hasNextLine()) {
            String linha = scan.nextLine();
            m = p.matcher(linha);
            if (m.find()) {
                scan.close();
                return m.group(1);
            }
        }
        scan.close();
        return null;
    }*/
    public static String obtemNomeCompleto(String nome) throws IOException { //Obtem a posicao do jogador
        HttpRequestFunctions.httpRequest1("https://pt.wikipedia.org/wiki/", nome.replace(" ", "_"), "wiki_details.txt");
        String ER1 = "<th [^>]*>Nome completo";
        String ER2 = "</th>";
        String ER3 = "<td([^>]*)>([^<]+)";

        Pattern p = Pattern.compile(ER1);
        Pattern p2 = Pattern.compile(ER2);
        Pattern p3 = Pattern.compile(ER3);
        Matcher m, m2, m3;

        Scanner scan = new Scanner(new FileInputStream("wiki_details.txt"));
        while (scan.hasNextLine()) {
            String linha = scan.nextLine();
            m = p.matcher(linha);
            if (m.find()) {
                linha = scan.nextLine();
                m2 = p2.matcher(linha);
                if (m2.find()) {
                    linha = scan.nextLine();
                    m3 = p3.matcher(linha);
                    if (m3.find()) {
                        scan.close();
                        return m3.group(2);
                    }
                }
            }
        }
        scan.close();
        return null;
    }

    public static Double obtemPeso(String nome) throws IOException { //Obtem o peso do jogador
        String link = LinkZeroZero(nome);
        HttpRequestFunctions.httpRequest2(link, "", "zero_details.txt");
        String ER1 = "<span>Peso</span>([0-9]+) kg</div>";
        Pattern p = Pattern.compile(ER1);
        Matcher m;

        Scanner scan = new Scanner(new FileInputStream("zero_details.txt"), "windows-1252");
        while (scan.hasNextLine()) {
            String linha = scan.nextLine();
            m = p.matcher(linha);
            if (m.find()) {
                scan.close();
                return Double.parseDouble(m.group(1));
            }
        }
        scan.close();
        return -1.0;
    }

    /*public static String obtemPePref(String nome) throws IOException { //Obtem o pé preferido
        String link = LinkZeroZero(nome);
        HttpRequestFunctions.httpRequest2(link, "", "zero_details.txt");
        String ER1 = "<span>Pé preferencial</span>([^<]+)</div>";
        Pattern p = Pattern.compile(ER1);
        Matcher m;

        Scanner scan = new Scanner(new FileInputStream("zero_details.txt"), "windows-1252");
        while (scan.hasNextLine()) {
            String linha = scan.nextLine();
            m = p.matcher(linha);
            if (m.find()) {
                scan.close();
                return m.group(1);
            }
        }
        scan.close();
        return null;
    }*/
    public static String obtemPePref(String nome) throws IOException { //Obtem o pé preferido
        String link = LinkTransfermarkt(nome);
        HttpRequestFunctions.httpRequest1(link, "", "transf_details.txt");

        String ER1 = "<th>Pé:</th>";
        String ER2 = "<td>([^<]+)</td>";

        Pattern p = Pattern.compile(ER1);
        Pattern p2 = Pattern.compile(ER2);

        Matcher m, m2;

        Scanner scan = new Scanner(new FileInputStream("transf_details.txt"));
        while (scan.hasNextLine()) {
            String linha = scan.nextLine();
            m = p.matcher(linha);
            if (m.find()) {
                linha = scan.nextLine();
                m2 = p2.matcher(linha);
                if (m2.find()) {
                    scan.close();
                    return m2.group(1);
                }
            }
        }
        scan.close();
        return null;
    }

    public static String obtemEstadoAtual(String nome) throws IOException { //Obtem o seu estado atual: Ativo, Reformado,..
        String link = LinkZeroZero(nome);
        HttpRequestFunctions.httpRequest2(link, "", "zero_details.txt");
        String ER1 = "<span>Situação</span>([^<]+)</div>";
        Pattern p = Pattern.compile(ER1);
        Matcher m;

        Scanner scan = new Scanner(new FileInputStream("zero_details.txt"), "windows-1252");
        while (scan.hasNextLine()) {
            String linha = scan.nextLine();
            m = p.matcher(linha);
            if (m.find()) {
                scan.close();
                return m.group(1);
            }
        }
        scan.close();
        return null;
    }

    /*public static String obtemAgente(String nome) throws IOException { //Obtem o agente que representa o jogador atualmente
        String link = LinkZeroZero(nome);
        HttpRequestFunctions.httpRequest2(link, "", "zero_details.txt");
        String ER1 = "<span>Agente</span><a href=\"agent\\.php\\?id=[0-9]+\">([^<]+)</a>";
        Pattern p = Pattern.compile(ER1);
        Matcher m;

        Scanner scan = new Scanner(new FileInputStream("zero_details.txt"), "windows-1252");
        while (scan.hasNextLine()) {
            String linha = scan.nextLine();
            m = p.matcher(linha);
            if (m.find()) {
                scan.close();
                return m.group(1);
            }
        }
        scan.close();
        return null;
    }*/
    public static String obtemAgente(String nome) throws IOException { //Obtem o agente que representa o jogador atualmente
        String link = LinkTransfermarkt(nome);
        HttpRequestFunctions.httpRequest1(link, "", "transf_details.txt");
        String ER1 = "<th>Empresários:</th>";
        String ER2 = "<td>";
        String ER3 = "<a href=\"([^\"]+)\">([^<]+)</a>";

        Pattern p = Pattern.compile(ER1);
        Pattern p2 = Pattern.compile(ER2);
        Pattern p3 = Pattern.compile(ER3);

        Matcher m, m2, m3;

        Scanner scan = new Scanner(new FileInputStream("transf_details.txt"));
        while (scan.hasNextLine()) {
            String linha = scan.nextLine();
            m = p.matcher(linha);
            if (m.find()) {
                linha = scan.nextLine();
                m2 = p2.matcher(linha);
                if (m2.find()) {
                    linha = scan.nextLine();
                    m3 = p3.matcher(linha);
                    if (m3.find()) {
                        scan.close();
                        return m3.group(2);
                    }
                }
            }
        }
        scan.close();
        return "Este jogador não se encontra a ser representado por nenhum agente";
    }

    public static Integer obtemNumero(String nome) throws IOException { //Obtem nr da camisola que representa atualmente
        String link = LinkTransfermarkt(nome);
        HttpRequestFunctions.httpRequest2(link, "", "transf_details.txt");
        String ER1 = "<span class=\"dataRN\">#([0-9]+)</span>";
        Pattern p = Pattern.compile(ER1);
        Matcher m;

        Scanner scan = new Scanner(new FileInputStream("transf_details.txt"));
        while (scan.hasNextLine()) {
            String linha = scan.nextLine();
            m = p.matcher(linha);
            if (m.find()) {
                scan.close();
                return Integer.parseInt(m.group(1));
            }
        }
        scan.close();
        return -1;
    }

    public static String obtemClubeAtual(String nome) throws IOException {
        String link = LinkTransfermarkt(nome);
        HttpRequestFunctions.httpRequest2(link, "", "transf_details.txt");

        String ER1 = "Clube atual:";
        String ER2 = "</th>";
        String ER3 = "<td>";
        String ER4 = "<a class=\"vereinprofil_tooltip\" id=\"([0-9]+)\" href=\"([^>]+)\">([^<]+)</a>";

        Pattern p1 = Pattern.compile(ER1);
        Pattern p2 = Pattern.compile(ER2);
        Pattern p3 = Pattern.compile(ER3);
        Pattern p4 = Pattern.compile(ER4);

        Scanner scan = new Scanner(new FileInputStream("transf_details.txt"));
        while (scan.hasNextLine()) {
            String linha = scan.nextLine();
            Matcher m1 = p1.matcher(linha);
            if (m1.find()) {
                linha = scan.nextLine();
                Matcher m2 = p2.matcher(linha);
                if (m2.find()) {
                    linha = scan.nextLine();
                    Matcher m3 = p3.matcher(linha);
                    if (m3.find()) {
                        linha = scan.nextLine();
                        Matcher m4 = p4.matcher(linha);
                        if (m4.find()) {
                            scan.close();
                            return m4.group(3);
                        }
                    }
                }
            }
        }
        scan.close();
        return "Este jogador não se apresenta em nenhum clube";
    }

    public static String obtemNacionalidade(String nome) throws IOException {
        String link = LinkTransfermarkt(nome);
        HttpRequestFunctions.httpRequest1(link, "", "transf_details.txt");

        String ER1 = "<span class=\"dataItem\">Nacionalidade:</span>";
        String ER2 = "<span class=\"dataValue\">";
        String ER3 = "<img src=\"([^\"]+)\" title=\"([^\"]+)\" alt=\"([^\"]+)\" class=\"flaggenrahmen\" /><span itemprop=\"nationality\">([^<]+)</span>";

        Pattern p1 = Pattern.compile(ER1);
        Pattern p2 = Pattern.compile(ER2);
        Pattern p3 = Pattern.compile(ER3);

        Scanner scan = new Scanner(new FileInputStream("transf_details.txt"));
        while (scan.hasNextLine()) {
            String linha = scan.nextLine();
            Matcher m1 = p1.matcher(linha);
            if (m1.find()) {
                linha = scan.nextLine();
                Matcher m2 = p2.matcher(linha);
                if (m2.find()) {
                    linha = scan.nextLine();
                    Matcher m3 = p3.matcher(linha);
                    if (m3.find()) {
                        scan.close();
                        return m3.group(4);
                    }

                }
            }
        }
        scan.close();
        return null;
    }

    public static String obtemLinkFoto(String nome) throws IOException {
        String link = LinkTransfermarkt(nome);
        HttpRequestFunctions.httpRequest1(link, "", "transf_details.txt");

        String ER1 = "<meta property=\"og:image\" content=\"([^\"]+)\" />";

        Pattern p1 = Pattern.compile(ER1);

        Scanner scan = new Scanner(new FileInputStream("transf_details.txt"));
        while (scan.hasNextLine()) {
            String linha = scan.nextLine();
            Matcher m1 = p1.matcher(linha);
            if (m1.find()) {
                scan.close();
                return m1.group(1);
            }
        }
        scan.close();
        return null;
    }

    public static String obtemNomeMaisConhecido(String nome) throws IOException {
        HttpRequestFunctions.httpRequest1("https://pt.wikipedia.org/wiki/", nome.replace(" ", "_"), "wiki_details.txt");
        String ER1 = "<th [^>]*>Apelido";
        String ER2 = "</th>";
        String ER3 = "<td[^>]*>[<i>]*([^<]+)[</i>]*";

        Pattern p1 = Pattern.compile(ER1);
        Pattern p2 = Pattern.compile(ER2);
        Pattern p3 = Pattern.compile(ER3);

        Scanner scan = new Scanner(new FileInputStream("wiki_details.txt"));
        while (scan.hasNextLine()) {
            String linha = scan.nextLine();
            Matcher m1 = p1.matcher(linha);
            if (m1.find()) {
                linha = scan.nextLine();
                Matcher m2 = p2.matcher(linha);
                if (m2.find()) {
                    linha = scan.nextLine();
                    Matcher m3 = p3.matcher(linha);
                    if (m3.find()) {
                        scan.close();
                        return m3.group(1);
                    }
                }
            }
        }
        scan.close();
        return "Este jogador não tem nenhum nome pelo qual seja mais conhecido";
    }

    public static Double obtemVDM(String nome) throws IOException {
        String link = LinkTransfermarkt(nome);
        HttpRequestFunctions.httpRequest1(link, "", "transf_details.txt");

        String ER1 = "<a href=\\\"/([^/]+)/marktwertverlauf/spieler/[^>]+>([0-9\\,]+)[^>]+>([^<]+)</span>";
        Pattern p1 = Pattern.compile(ER1);

        Scanner scan = new Scanner(new FileInputStream("transf_details.txt"));
        while (scan.hasNextLine()) {
            String linha = scan.nextLine();
            Matcher m1 = p1.matcher(linha);
            if (m1.find()) {
                scan.close();
                if (m1.group(3).contains("M") == true) {
                    Double vdc = Double.parseDouble(m1.group(2).replace(",", ".")) * 1000000;
                    return vdc;
                }
                if (m1.group(3).contains("m") == true) {
                    Double vdc = Double.parseDouble(m1.group(2).replace(",", ".")) * 1000;
                    return vdc;
                }
            }
        }
        scan.close();
        return -1.0;
    }

    public static ArrayList obtemPremios(String nome) throws IOException {
        String link = LinkTransfermarkt(nome);
        HttpRequestFunctions.httpRequest1(link, "", "transf_details.txt");
        ArrayList<String> premios = new ArrayList();
        int i = 0;

        String ER1 = "<img src=\"([^\"]+)\" title=\"([^\"]+)\" alt=\"([^\"]+)\" class=\"dataErfolgImage\" />";

        Pattern p1 = Pattern.compile(ER1);

        Scanner scan = new Scanner(new FileInputStream("transf_details.txt"));
        while (scan.hasNextLine() && i < 5) {
            String linha = scan.nextLine();
            Matcher m1 = p1.matcher(linha);
            if (m1.find()) {
                premios.add(m1.group(2));
                i++;
            }
        }
        scan.close();
        return premios;
    }

    public static ArrayList obtemClubesAnteriores(String nome) throws IOException {
        String link = LinkTransfermarkt(nome);
        HttpRequestFunctions.httpRequest1(link, "", "transf_details.txt");
        ArrayList<String> premios = new ArrayList();
        
        int flag = 0;
        int i = 0;

        String ER1 = "<td class=\"hauptlink no-border-links  vereinsname\"><a class=\"vereinprofil_tooltip\" id=\"([0-9]+)\" href=\"([^\"]+)\">([^<]+)</a></td>";

        Pattern p1 = Pattern.compile(ER1);

        Scanner scan = new Scanner(new FileInputStream("transf_details.txt"));
        while (scan.hasNextLine() && i < 5) {
            String linha = scan.nextLine();
            Matcher m1 = p1.matcher(linha);
            if (m1.find()) {
                if (flag != 0) {
                    premios.add(m1.group(3));
                    i++;
                }
                flag = 1;
            }
        }
        scan.close();
        return premios;
    }

    public static String obtemSelecao(String nome) throws IOException {
        String link = LinkTransfermarkt(nome);
        HttpRequestFunctions.httpRequest1(link, "", "transf_details.txt");

        String ERInicial = "<th colspan=\"[0-9]+\" >Seleção nacional</th>";
        String ER1 = "<a class=[^>]*>([^<]+)</a>";
        //String ER1 = "<a class=[^>]*>([^<]+)</a>";

        Pattern p1 = Pattern.compile(ERInicial);
        Pattern p2 = Pattern.compile(ER1);

        Scanner scan = new Scanner(new FileInputStream("transf_details.txt"));
        while (scan.hasNextLine()) {
            String linha = scan.nextLine();
            Matcher m1 = p1.matcher(linha);
            if (m1.find()) {
                while (scan.hasNextLine()) {
                    linha = scan.nextLine();
                    Matcher m2 = p2.matcher(linha);
                    if (m2.find()) {
                        scan.close();
                        return m2.group(1);
                    }
                }
            }
        }
        scan.close();
        return "Este jogador não joga pela seleção";
    }

    public static String obtemDataNascimento(String nome) throws IOException { //Obtem a data de nascimento do jogador
        String link = LinkTransfermarkt(nome);
        HttpRequestFunctions.httpRequest1(link, "", "transf_details.txt");

        String ER1 = "<th>Data de nascimento:</th>";
        String ER2 = "<td><a href=\"([^\"]+)\">([^<]+)</a>";

        Pattern p = Pattern.compile(ER1);
        Pattern p2 = Pattern.compile(ER2);

        Matcher m1, m2;

        Scanner scan = new Scanner(new FileInputStream("transf_details.txt"));
        while (scan.hasNextLine()) {
            String linha = scan.nextLine();
            m1 = p.matcher(linha);
            if (m1.find()) {
                linha = scan.nextLine();
                m2 = p2.matcher(linha);
                if (m2.find()) {
                    scan.close();
                    return m2.group(2);
                }
            }
        }
        scan.close();
        return null;
    }

    public static Integer obtemIdadeFalecido(String nome) throws IOException {
        String link = LinkTransfermarkt(nome);
        HttpRequestFunctions.httpRequest1(link, "", "transf_details.txt");

        String ER1 = "<th>Falecido:</th>";
        String ER2 = "<td>[0-9.]+ ([^<]+)</td>";

        Pattern p = Pattern.compile(ER1);
        Pattern p2 = Pattern.compile(ER2);

        Matcher m1, m2;

        Scanner scan = new Scanner(new FileInputStream("transf_details.txt"));
        while (scan.hasNextLine()) {
            String linha = scan.nextLine();
            m1 = p.matcher(linha);
            if (m1.find()) {
                linha = scan.nextLine();
                m2 = p2.matcher(linha);
                if (m2.find()) {
                    scan.close();
                    String replace = m2.group(1).replace(")", "");
                    replace = replace.replace("(", "");
                    return Integer.parseInt(replace);
                }
            }
        }
        scan.close();
        return -1;
    }

    public static Integer obtemIdade(String nome) throws IOException { //Obtem a data de nascimento do jogador
        String link = LinkTransfermarkt(nome);
        HttpRequestFunctions.httpRequest1(link, "", "transf_details.txt");

        String ER1 = "<th>Idade:</th>";
        String ER2 = "<td>([^<]+)</td>";

        Pattern p = Pattern.compile(ER1);
        Pattern p2 = Pattern.compile(ER2);

        Matcher m1, m2;

        String estado = obtemEstadoAtual(nome);
        if (estado.contains("Falecido")) {
            return obtemIdadeFalecido(nome);
        } else {
            Scanner scan = new Scanner(new FileInputStream("transf_details.txt"));
            while (scan.hasNextLine()) {
                String linha = scan.nextLine();
                m1 = p.matcher(linha);
                if (m1.find()) {
                    linha = scan.nextLine();
                    m2 = p2.matcher(linha);
                    if (m2.find()) {
                        scan.close();
                        return Integer.parseInt(m2.group(1));
                    }
                }
            }
            scan.close();
            return -1;
        }
    }

}

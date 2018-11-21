/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhoed;

import java.nio.file.FileVisitResult;
import java.util.Scanner;

/**
 *
 * @author gsmin
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Arquivo root = new Arquivo("root", null, Constantes.TIPO_DIRETORIO);

        Scanner scan = new Scanner(System.in);

        String comando = scan.nextLine();
        String[] splitComando = comando.split(" ");

        String nomeArquivo;
        String caminho = "";
        String recursivo = "";
        String[] splitCaminho;
        Arquivo atual = root;

        while (!splitComando[0].equals("sair")) {
           comando = scan.nextLine();
           splitComando = comando.split(" ");
           
            switch (splitComando[0]) {
                /*Criação de Diretórios*/
                case "mkdir":
                    
                    if(splitComando.length == 1){
                        System.out.println("Comando Invalido.");
                        break;
                    }
                    
                    /*SE O COMANDO TEM 3 PARTES (MKDIR CAMINHO NOME_DO_ARQUIVO*/
                    if (splitComando.length == 3) {
                        nomeArquivo = splitComando[2];
                        caminho = splitComando[1];
                    } else {
                        nomeArquivo = splitComando[1];
                    }
                    /*SE O COMANDO DÁ O CAMINHO ESPECÍFICO*/
                    if (!caminho.isEmpty()) {
                        splitCaminho = caminho.split("/");
                        int size = splitCaminho.length;

                        for (int i = 0; i < splitCaminho.length; i++) {
                            Arquivo arquivoEncontrado
                                    = busca(splitCaminho[i], atual);
                            if (arquivoEncontrado != null) {
                                atual = atual.getFilho();
                            } else {/*E SE O CAMINHO TODO NÃO EXISTIR?*/
                                System.out.println("Caminho de arquivo inválido.");
                            }
                        }
                        /*Saindo do laço, estamos na pasta na qual o arquivo será
                          criado.*/

                    }
                    insere(nomeArquivo, atual, Constantes.TIPO_DIRETORIO);
                    break;
                /*Criação de Arquivos*/
                case "touch":
                    
                    if(splitComando.length == 1){
                        System.out.println("Comando Invalido.\n");
                        break;
                    }

                    if (splitComando.length == 3) {
                        nomeArquivo = splitComando[2];
                        caminho = splitComando[1];
                    } else {
                        nomeArquivo = splitComando[1];
                    }

                    if (!caminho.isEmpty()) {
                        splitCaminho = caminho.split("/");
                        int size = splitCaminho.length;

                        for (int i = 0; i < splitCaminho.length; i++) {
                            Arquivo arquivoEncontrado
                                    = busca(splitCaminho[i], atual);
                            if (arquivoEncontrado != null) {
                                atual = atual.getFilho();
                            } else {
                                System.out.println("Caminho de arquivo inválido.");
                            }
                        }
                        /*Saindo do laço, estamos na pasta na qual o arquivo será
                          criado.*/

                    }
                    insere(nomeArquivo, atual, Constantes.TIPO_ARQUIVO);
                    break;

                /*Mostrando o Conteúdo do diretório*/
                case "ls":/*INCOMPLETO VERIFICAR*/
                    if(splitComando.length == 1){
                        System.out.println("Comando Invalido.\n");
                        break;
                    }
                    
                    if (splitComando.length == 3) {
                        caminho = splitComando[2];
                        recursivo = splitComando[1];
                    } else {
                        caminho = splitComando[1];
                    }
                    if (!caminho.isEmpty()) {
                        splitCaminho = caminho.split("/");
                        int size = splitCaminho.length;

                        for (int i = 0; i > 0; i++) {
                            Arquivo arquivoEncontrado
                                    = busca(splitCaminho[i], atual);
                            if (arquivoEncontrado != null) {
                                atual = atual.getFilho();
                            } else {
                                System.out.println("Caminho de Arquivo inválido.");
                            }
                        }
                        mostraConteudo(atual);
                    } else {
                        mostraConteudo(atual.getFilho());
                    }
                    break;

                case "search":
                    
                    if(splitComando.length == 1){
                        System.out.println("Comando Invalido.\n");
                        break;
                    }
                    
                    System.out.println("busca.");
                    break;
                    
                default:
                    System.out.println("Comando Invalido.\n");
                    break;

            }
            splitComando[0] = "";
        }

    }

    /*Mostra os nomes de arquivos no diretorio*/
    static void mostraConteudo(Arquivo atual) {

        while (atual != null) {
            System.out.print(atual.getNome() + "\t");
            if (atual.getTipo().equals(Constantes.TIPO_DIRETORIO)) {
                System.out.print("<DIR>");
            }
            System.out.println("");
            atual = atual.getProximo();
        }
    }

    static void mostraConteudoR(Arquivo atual) {
        if (atual.getFilho() != null) {
            mostraConteudo(atual);
            mostraConteudoR(atual.getFilho());
        } else {
            mostraConteudo(atual);
        }

    }

    static void insere(String nomeArquivo, Arquivo atual, String tipo) {
        Arquivo novo = new Arquivo(nomeArquivo, null, tipo);

        if (atual.getFilho() == null) {
            atual.setFilho(novo);
        } else {
            Arquivo filho = atual.getFilho();
            while (filho.getProximo() != null) {
                filho = filho.getProximo();
            }
            filho.setProximo(novo);
        }
    }

    /*Busca o arquivo com a chave indicada*/
    static Arquivo busca(String nome, Arquivo pai) {
        Arquivo atual = pai.getFilho();
        if (atual != null) {
            while (atual.getProximo() != null) {
                if (atual.getNome().equals(nome)) {
                    return atual;
                } else {
                    atual = atual.getProximo();
                }
            }
        }
        return null;
    }

}

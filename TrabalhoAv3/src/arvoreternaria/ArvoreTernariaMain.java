/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arvoreternaria;
import java.util.Scanner;
/**
 *
 * @author lucwa
 */
public class ArvoreTernariaMain {
    
    public static void main(String[]args){
        Scanner scan = new Scanner(System.in);
        
        
        ArvoreTernariaBusca tst = new ArvoreTernariaBusca();
        System.out.println("ESCOLHA OPÇÃO DE ARVORE TERNARIA\n");
        char ch;// char para aceitar tanto a palavra maiuscula quanto minuscula
        
        
        do{
            System.out.println("\n------Árvore Ternaria Operações------\n");
            System.out.println("1. Inserir Palavra ");
            System.out.println("2. Procura Palavra ");
            System.out.println("3. Deleta Palavra ");
            System.out. println("4. Checar se estar vazio Vazio ");
            System.out.println("5. Limpar Arvore(Ficar vazio) ");
            System.out.println("------------------------------------------");
            System.out.println("Escolha a opção desejada : " );
            int escolha = scan.nextInt();
            switch(escolha){
                case 1:
                    System.out.println("Digite a palavra para inserir: ");
                    tst.inserir(scan.next());
                    break;
                case 2: 
                    System.out.println("Digite a palavra para procurar");
                    String s = scan.next();
                    if (tst.procurar(s) == true) {
                        
                        System.out.println("Resultado: " + tst.procurar(s) + " (EXISTE!)");//procurando a palavra
                        
                    }else{
                        
                        System.out.println("Resultado: " + s + " Não está contido.");
                        try {
                            System.out.print("Sugestões: " + tst.autocompletar(s));
                        } catch (NullPointerException e) {
                            System.err.print("Erro: Não foi Possível executar esse código.");
                            System.out.print("Erro:" + e.getMessage() + "\nCódigo: " + e.getCause());
                        }
                        
                    }
                    break;
                case 3:
                    System.out.println("Digite a palavra para ser deletada: ");
                    tst.delete(scan.next());
                    break;
                case 4: 
                    System.out.println("Status Vazio: " + tst.eVazio());//se o status estiver vazio!
                    break;
                case 5:
                    System.out.println("Arvore Ternaria De Busca limpo ");
                    tst.ficarVazio();//Para limpar a arvore de busca
                    break;
                default:
                    System.out.println("Entrada Errada");//se o comando estiver errado!
                    break;
            }
            System.out.println(tst);//chamando a instancia
            System.out.println("\n Você deseja continuar? (digite s ou n) \n");
            ch = scan.next().charAt(0);
            
        }while(ch == 'S' || ch == 's');
        scan.close();// o programa fecha caso a resposta seja "n"
        System.err.println("Programa Encerrado...");
    }
    
}

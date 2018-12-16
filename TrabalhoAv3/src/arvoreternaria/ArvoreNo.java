package arvoreternaria;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Este é a Classe Nó (TSTNode) ~~ Lucas Alves
 */
class ArvoreNo {

    char data;
    boolean eFim;
    ArvoreNo esquerda;
    ArvoreNo meio, direita;

    public ArvoreNo(char data) {
        this.data = data; // data é um char logo ele recebe ele msm
        this.eFim = false;//Fim
        this.esquerda = null;//Esquerda
        this.meio = null;//nulos pois eles comeam sem nada!
        this.direita = null;//Direita
    }
}

/**
 * Está é a Classe da Arvore Ternária de Busca ~~ Lucas Alves
 */
class ArvoreTernariaBusca {//TernarySearchTree

    private ArvoreNo root;//serve como uma especie de atualização para que começe e va ate o fim recebendo informalçoes
    private ArrayList<String> al;//ArrayList de String da Arvore

    public ArvoreTernariaBusca() {//o root começa como nulo
        root = null;
    }

    public boolean eVazio() {//caso esteja vazio
        return root == null;
    }

    public void ficarVazio() {//caso queira que fique vazia
        root = null;
    }

    public void inserir(String word) {//inserir String
        root = inserir(root, word.toCharArray(), 0);
    }

    //Esse 'r' representa a classe TSTNode!! 
    public ArvoreNo inserir(ArvoreNo r, char[] palavra, int ptr) {//não entendi mt bem esse INT PTR
        if (r == null) {
            r = new ArvoreNo(palavra[ptr]);
        }
        if (palavra[ptr] < r.data) {
            r.esquerda = inserir(r.esquerda, palavra, ptr);
        } else if (palavra[ptr] > r.data) {
            r.direita = inserir(r.direita, palavra, ptr);
        } else {
            if (ptr + 1 < palavra.length) {
                r.meio = inserir(r.meio, palavra, ptr + 1);
            } else {
                r.eFim = true;//caso não seja, o isEnd recebe true como verdadeiro
            }
        }
        return r;
    }

    public void delete(String palavra) {//deletar a palavra
        delete(root, palavra.toCharArray(), 0);//usando o root para deletar a palavra
    }

    private void delete(ArvoreNo r, char[] palavra, int ptr) {//continuo sem entender direito esse ptr
        if (r == null) {
            return;
        }
        if (palavra[ptr] < r.data) {
            delete(r.esquerda, palavra, ptr);
        } else if (palavra[ptr] > r.data) {
            delete(r.direita, palavra, ptr);
        } else {
            if (r.eFim && ptr == palavra.length - 1) {
                r.eFim = false;
            } else if (ptr + 1 < palavra.length) {
                delete(r.meio, palavra, ptr + 1);
            }
        }
    }

    public boolean procurar(String palavra) {//Procurar palavra
        return procurar(root, palavra.toCharArray(), 0);
    }

    private boolean procurar(ArvoreNo r, char[] palavra, int ptr) {
        if (r == null) {
            return false;
        }
        if (palavra[ptr] < r.data) {
            return procurar(r.esquerda, palavra, ptr);
        } else if (palavra[ptr] > r.data) {
            return procurar(r.direita, palavra, ptr);
        } else {
            if (r.eFim && ptr == palavra.length - 1) {
                return true;
            } else if (ptr == palavra.length - 1) {
                return false;
            } else {
                return procurar(r.meio, palavra, ptr + 1);
            }
        }
    }

    public String autocompletar(String s) {

        return autocompletar(root, s, 0);

    }

    private String autocompletar(ArvoreNo r, String pArray, int ptr) {

        ArrayList<String> palavras = new ArrayList<>();
        r = this.acharNo(root, pArray.toCharArray(), 0);

        if (r.eFim == true) {

            palavras.add(pArray);
            return palavras.toString();

        } else {

            addPalavras(r, pArray, palavras);
            return palavras.toString();

        }
    }

    private ArrayList<String> addPalavras(ArvoreNo r, String palavraInit, ArrayList<String> array) {

        String esq = palavraInit;
        String med = palavraInit;
        String dir = palavraInit;
        ArvoreNo esqd, dirt, medt;

        if (r.esquerda != null) {
            esqd = r.esquerda;
            while (esqd.eFim != true) {

                esq = esq + esqd.data;
                esqd = esqd.meio;
                
                if (esqd.eFim)
                    esq += esqd.data;

            }
        }

        if (r.direita != null) {
            dirt = r.direita;
            while (dirt.eFim != true) {

                dir = dir + dirt.data;
                dirt = dirt.meio;
                
                if(dirt.eFim)
                    dir += dirt.data;

            }
        }

        if (r.meio != null) {
            medt = r.meio;
            while (medt.eFim != true) {

                med = med + medt.data;
                medt = medt.meio;
                
                if(medt.eFim)
                    med += medt.data;

            }
        }

        if (esq.equalsIgnoreCase(palavraInit) == false) {
            array.add(esq);
        }

        if (med.equalsIgnoreCase(palavraInit) == false) {
            array.add(med);
        }

        if (dir.equalsIgnoreCase(palavraInit) == false) {
            array.add(dir);
        }
        
        if (array.isEmpty())
            array.add("Sem Sugestões");
        
        return array;

    }

    private ArvoreNo acharNo(ArvoreNo r, char[] palavra, int ptr) {

        if (palavra[ptr] < r.data) {
            return acharNo(r.esquerda, palavra, ptr);
        } else if (palavra[ptr] > r.data) {
            return acharNo(r.direita, palavra, ptr);
        } else {
            if (r.eFim && ptr == palavra.length - 1) {
                return r;
            } else if (ptr == palavra.length - 1) {
                return r;
            } else {
                return acharNo(r.meio, palavra, ptr + 1);
            }
        }

    }

    public String toString() {//esse to string é pra dizer a palavra chave que ele tem ou que ele achou
        al = new ArrayList<String>();
        traverse(root, "");
        return "\nPalavras da Arvove Ternária Trie: " + al;
    }

    private void traverse(ArvoreNo r, String str) {//nao entendi mt bem essa parte mas sei como funciona
        if (r != null) {
            traverse(r.esquerda, str);
            str = str + r.data;
            if (r.eFim) {
                al.add(str);
            }
            traverse(r.meio, str);
            str = str.substring(0, str.length() - 1);
            traverse(r.direita, str);
        }
    }

}

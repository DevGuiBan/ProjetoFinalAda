import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class agenda {
    public static void main(String[] args) {

        List<String> nomes = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        int escolha = 1;

        while (escolha != 6) {
            System.out.println("-- ESCOLHA --");
            System.out.println("1 - Adicionar");
            System.out.println("2 - Buscar");
            System.out.println("3 - Atualizar");
            System.out.println("4 - Deletar");
            System.out.println("5 - Listar");
            System.out.println("6 - Sair");
            escolha = input.nextInt();
            input.nextLine();

            switch (escolha) {
                case 1:
                    System.out.println("Informe o nome: ");
                    String adicionar = input.nextLine();
                    nomes.add(adicionar);
                    System.out.println("O nome " + adicionar + " foi adicionado com sucesso!");
                    break;
                case 2:
                    System.out.println("Informe o nome que deseja buscar: ");
                    String busca = input.nextLine();
                    if (nomes.contains(busca)) {
                        System.out.println("O nome " + busca + " está incluso na lista.");
                    }else {
                        System.out.println("Não existe o nome " + busca);
                    }
                    break;
                case 3:
                    System.out.println("Informe o nome que deseja atualizar: ");
                    String atualiza = input.nextLine();
                    if (nomes.contains(atualiza)) {
                        System.out.println("Informe o novo nome que deseja colocar: ");
                        String novoNome = input.nextLine();
                        int index = nomes.indexOf(atualiza);
                        nomes.set(index, novoNome);
                        System.out.println("O nome " + atualiza + " foi atualizado com sucesso para o nome " + novoNome);
                    }else {
                        System.out.println("O nome que deseja atualizar não está na lista!");
                    }
                    break;
                case 4:
                    System.out.println("Informe o nome que deseja deletar: ");
                    String deletar = input.nextLine();
                    if (nomes.contains(deletar)) {
                        nomes.remove(deletar);
                        System.out.println("O nome " + deletar + " foi deletado com sucesso!");
                    }else {
                        System.out.println("O nome que deseja deletar não está na lista!");
                    }
                    break;
                case 5:
                    System.out.println("LISTA DE NOMES VIGENTES");
                    System.out.println(nomes);
                case 6:
                    System.out.println("Saindo....");
                    break;
                default:
                    System.out.println("Opção escolhida inválida, tente novamente!");
                    break;
            }
        }

    }
}

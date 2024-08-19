import java.util.Scanner;

public class agenda {
    public static void main(String[] args) {

        int[] agendaId = new int[5];
        String[] agendaNome = new String[5];
        String[] agendaTelefone = new String[5];
        String[] agendaEmail = new String[5];
        Scanner input = new Scanner(System.in);
        int escolha = 1;
        String telefone;

        while (escolha != 6) {
            menuAgenda();
            escolha = input.nextInt();
            input.nextLine();

            switch (escolha) {
                case 1:
                    telefone = solicitarTelefone(input);
                    int indiceLivre = encontrarIndiceLivre(agendaTelefone);

                    if (indiceLivre != -1) {
                        if (!telefoneExiste(telefone, agendaTelefone)) {
                            String nome = solicitarNome(input);
                            String email = solicitarEmail(input);
                            salvarContato(indiceLivre, telefone, nome, email, agendaId, agendaTelefone, agendaNome, agendaEmail);
                            System.out.println("\n\uD83D\uDDF8 Contato adicionado com sucesso!");
                            //detalharContato(agendaId[indiceLivre], agendaNome[indiceLivre], agendaTelefone[indiceLivre], agendaEmail[indiceLivre]);
                        } else {
                            System.out.println("\n  \uD83D\uDDD9 O telefone já existe na agenda!");
                        }
                    } else {
                        System.out.println("\n  \uD83D\uDDD9 Agenda cheia! Não é possível adicionar mais contatos.");
                    }
                    break;
                case 2:
                    detalharContato(input, agendaId, agendaNome, agendaTelefone, agendaEmail);
                    break;
                case 3:
                    System.out.println("\n\uD83D\uDE7E\uD83D\uDE7E\uD83D\uDE7E EDITAR CONTATO \uD83D\uDE7F\uD83D\uDE7F\uD83D\uDE7F");
                    System.out.print("\uD83D\uDF82 Digite o telefone do contato que deseja editar: ");
                    telefone = input.nextLine();
                    boolean contatoParaEdicaoExiste = false;
                    try{
                        for (int i = 0; i < agendaTelefone.length; i++) {
                            if (agendaTelefone[i].equals(telefone)) {
                                detalharContato(agendaId[i], agendaNome[i], agendaTelefone[i], agendaEmail[i]);
                                contatoParaEdicaoExiste = true;
                                escolha = 0;
                                while (escolha != 4) {
                                    menuEdicao();
                                    escolha = input.nextInt();
                                    input.nextLine();
                                    switch (escolha) {
                                        case 1:
                                            String nomeEditado = agendaNome[i];
                                            System.out.print(" \uD83D\uDF82 Digite o novo nome: ");
                                            agendaNome[i] = input.nextLine();
                                            System.out.println(" \uD83D\uDDF8 O nome " + nomeEditado + " foi alterado para " + agendaNome[i] + " com sucesso!");
                                            break;
                                        case 2:
                                            String telefoneEditado = agendaTelefone[i];
                                            System.out.print(" \uD83D\uDF82 Digite o novo telefone: ");
                                            agendaTelefone[i] = input.nextLine();
                                            System.out.println(" \uD83D\uDDF8 O telefone " + telefoneEditado + " foi alterado para " + agendaTelefone[i] + " com sucesso!");
                                            break;
                                        case 3:
                                            String emailEditado = agendaEmail[i];
                                            System.out.print(" \uD83D\uDF82 Digite o novo e-mail: ");
                                            agendaEmail[i] = input.nextLine();
                                            System.out.println(" \uD83D\uDDF8 O email " + emailEditado + " foi alterado para " + agendaEmail[i] + " com sucesso!");
                                            break;
                                        case 4:
                                            System.out.println(" \uD83D\uDF82 Saindo...");
                                            break;
                                        default:
                                            System.out.println(" \uD83D\uDDD9 Opção inválida. Tente novamente.");
                                    }
                                }
                                System.out.println(" \uD83D\uDDF8 Edição finalizada com sucesso!");
                                detalharContato(agendaId[i], agendaNome[i], agendaTelefone[i], agendaEmail[i]);
                                break;
                            }
                        }
                    }catch (NullPointerException e){
                        if (!contatoParaEdicaoExiste){
                            System.out.println(" \uD83D\uDDD9 Não existe o contato com o telefone: " + telefone + " que deseja editar!");
                        }
                        break;
                    }
                    if (!contatoParaEdicaoExiste){
                        System.out.println(" \uD83D\uDDD9 Não existe o contato com o telefone: " + telefone + " que deseja editar!");
                    }
                    break;
                case 4:
                    System.out.println("\n\uD83D\uDE7E\uD83D\uDE7E\uD83D\uDE7E REMOVER CONTATO \uD83D\uDE7F\uD83D\uDE7F\uD83D\uDE7F");
                    System.out.print("\uD83D\uDF82 Digite o telefone do contato que deseja remover: ");
                    telefone = input.nextLine();
                    boolean contatoParaDelExiste = false;

                    int indiceParaRemover = -1;
                    for (int i = 0; i < agendaTelefone.length; i++) {
                        if (agendaTelefone[i] != null && agendaTelefone[i].equals(telefone)) {
                            indiceParaRemover = i;
                            contatoParaDelExiste = true;
                            break;
                        }
                    }

                    if (contatoParaDelExiste) {
                        for (int i = indiceParaRemover; i < agendaTelefone.length - 1; i++) {
                            agendaId[i] = agendaId[i + 1];
                            agendaTelefone[i] = agendaTelefone[i + 1];
                            agendaNome[i] = agendaNome[i + 1];
                            agendaEmail[i] = agendaEmail[i + 1];
                        }

                        agendaId[agendaTelefone.length - 1] = 0;
                        agendaTelefone[agendaTelefone.length - 1] = null;
                        agendaNome[agendaTelefone.length - 1] = null;
                        agendaEmail[agendaTelefone.length - 1] = null;

                        for (int i = 0; i < agendaId.length; i++) {
                            if (agendaId[i] != 0) {
                                agendaId[i] = i + 1;
                            }
                        }

                        System.out.println(" \uD83D\uDDF8 Contato " + telefone + " foi deletado com sucesso!");
                    } else {
                        System.out.println(" \uD83D\uDDD9 Não existe o contato buscado com o telefone: " + telefone);
                    }
                    break;
                case 5:
                    if (agendaId[0] == 0){
                        System.out.println("Não existe nenhum contato na agenda!");
                    }else{
                        listarAgenda(agendaId, agendaNome, agendaTelefone, agendaEmail, agendaTelefone);
                    }
                    break;
                case 6:
                    System.out.println(" \uD83D\uDF82 Saindo...");
                    break;
                default:
                    System.out.println(" \uD83D\uDDD9 Opção inválida. Tente novamente.");
                    break;
            }
        }

    }

    public static void menuAgenda() {
        System.out.println("\n  \uD83D\uDE7C\uD83D\uDE7C\uD83D\uDE7C \uD835\uDE3C\uD835\uDE42\uD835\uDE40\uD835\uDE49\uD835\uDE3F\uD835\uDE3C \uD835\uDE3F\uD835\uDE40 \uD835\uDE3E\uD835\uDE4A\uD835\uDE49\uD835\uDE4F\uD835\uDE3C\uD835\uDE4F\uD835\uDE4A\uD835\uDE4E \uD83D\uDE7D\uD83D\uDE7D\uD83D\uDE7D");
        System.out.println("    [1] Adicionar Contato");
        System.out.println("    [2] Detalhar Contato");
        System.out.println("    [3] Editar Contato");
        System.out.println("    [4] Remover Contato");
        System.out.println("    [5] Listar Contatos");
        System.out.println("    [6] Sair");
    }

    public static void detalharContato(int id, String nome, String tel, String email) {
        //System.out.println("\n \uD83D\uDE7E\uD83D\uDE7E\uD83D\uDE7E CONTATO \uD83D\uDE7F\uD83D\uDE7F\uD83D\uDE7F");
        System.out.println("\nID | Nome            | Telefone          | E-mail");
        //System.out.println(id + "   |   " + nome + "   |   " + tel + "   |   " + email);
        System.out.printf("%-3d| %-16s| %-18s| %s\n", id, nome, tel, email);
    }

    public static void listarAgenda(int[] id, String[] nome, String[] tel, String[] email, String[] agenda) {
        System.out.println("\n\uD83D\uDE7E\uD83D\uDE7E\uD83D\uDE7E LISTA DE CONTATOS \uD83D\uDE7F\uD83D\uDE7F\uD83D\uDE7F");
        System.out.println("\nID | Nome            | Telefone          | E-mail");
        for (int i = 0; i < agenda.length; i++){
            if (id[i] >= 1){
                System.out.printf("%-3d| %-16s| %-18s| %s\n", id[i], nome[i], tel[i], email[i]);
               // System.out.println(id[i] + "   |   " + nome[i] + "   |   " + tel[i] + "   |   " + email[i]);
            }else {
                break;
            }

        }

    }

    public static void menuEdicao() {
        System.out.println("\n\uD83D\uDE7E\uD83D\uDE7E\uD83D\uDE7E EDITAR CONTATO \uD83D\uDE7F\uD83D\uDE7F\uD83D\uDE7F");
        System.out.println("[1] Editar nome");
        System.out.println("[2] Editar telefone");
        System.out.println("[3] Editar e-mail");
        System.out.println("[4] Sair");
    }
    //Codigo refatorado do case 1
    public static String solicitarTelefone(Scanner input) {
        System.out.print("\uD83D\uDE7E\uD83D\uDE7E\uD83D\uDE7E ADICIONANDO CONTATO \uD83D\uDE7F\uD83D\uDE7F\uD83D\uDE7F");
        System.out.print("\n\uD83D\uDF82\uD83D\uDF82 Digite o número do telefone: ");
        return input.nextLine();
    }

    public static String solicitarNome(Scanner input) {
        System.out.print("\uD83D\uDF82\uD83D\uDF82 Digite o nome do contato: ");
        return input.nextLine();
    }

    public static String solicitarEmail(Scanner input) {
        System.out.print("\uD83D\uDF82\uD83D\uDF82 Digite o e-mail do contato: ");
        return input.nextLine();
    }

    public static int encontrarIndiceLivre(String[] agendaTelefone) {
        for (int i = 0; i < agendaTelefone.length; i++) {
            if (agendaTelefone[i] == null) {
                return i;
            }
        }
        return -1;
    }

    public static boolean telefoneExiste(String telefone, String[] agendaTelefone) {
        for (String tel : agendaTelefone) {
            if (telefone.equals(tel)) {
                return true;
            }
        }
        return false;
    }

    public static void salvarContato(int indice, String telefone, String nome, String email, int[] agendaId, String[] agendaTelefone, String[] agendaNome, String[] agendaEmail) {
        agendaId[indice] = indice + 1;
        agendaTelefone[indice] = telefone;
        agendaNome[indice] = nome;
        agendaEmail[indice] = email;
    }
    //Codigo refatorado do case 2
    public static void detalharContato(Scanner input, int[] agendaId, String[] agendaNome, String[] agendaTelefone, String[] agendaEmail) {
        System.out.print("\uD83D\uDE7E\uD83D\uDE7E\uD83D\uDE7E DETALHAR CONTATO \uD83D\uDE7F\uD83D\uDE7F\uD83D\uDE7F");
        System.out.print("\n\uD83D\uDF82\uD83D\uDF82 Digite o número do telefone: ");
        String telefone = input.nextLine();

        int indiceContato = encontrarContato(telefone, agendaTelefone);
        if (indiceContato != -1) {
            exibirContato(agendaId[indiceContato], agendaNome[indiceContato], agendaTelefone[indiceContato], agendaEmail[indiceContato]);
        } else {
            System.out.println("\n  \uD83D\uDDD9 Contato não encontrado!");
        }
    }

    public static int encontrarContato(String telefone, String[] agendaTelefone) {
        for (int i = 0; i < agendaTelefone.length; i++) {
            if (telefone.equals(agendaTelefone[i])) {
                return i;
            }
        }
        return -1;
    }

    public static void exibirContato(int id, String nome, String telefone, String email) {
        System.out.println("\nID | Nome            | Telefone          | E-mail");
        System.out.printf("%-3d| %-16s| %-18s| %s\n", id, nome, telefone, email);
    }




}

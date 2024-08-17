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
                    System.out.println("Informe o telefone: ");
                    telefone = input.nextLine();
                    for (int i = 0; i < agendaTelefone.length; i++) {
                        if (agendaTelefone[i] == null){
                            System.out.println("Informe o nome: ");
                            String nome = input.nextLine();
                            System.out.println("Informe o e-mail: ");
                            String email = input.nextLine();
                            agendaId[i] = i + 1;
                            agendaTelefone[i] = telefone;
                            agendaNome[i] = nome;
                            agendaEmail[i] = email;
                            System.out.println("Contato adicionado na agenda com sucesso!");
                            detalharContato(agendaId[i], agendaNome[i], agendaTelefone[i], agendaEmail[i]);
                            break;
                        }else if (agendaTelefone[i].equals(telefone)) {
                            System.out.println("O telefone já existe na agenda!");
                            break;
                        }
                    }
                    break;
                case 2:
                    System.out.println("Informe o telefone que deseja buscar: ");
                    telefone = input.nextLine();
                    boolean contatoExiste = false;
                    for (int i = 0; i < agendaTelefone.length; i++) {
                        try{
                            if (agendaTelefone[i].equals(telefone)) {
                                detalharContato(agendaId[i], agendaNome[i], agendaTelefone[i], agendaEmail[i]);
                                contatoExiste = true;
                                break;
                            }
                        }catch (NullPointerException e){
                            break;
                        }

                    }
                    if (!contatoExiste) {
                        System.out.println("Não existe o contato buscado com o telefone: " + telefone);
                    }
                    break;
                case 3:
                    System.out.println("Informe o telefone que deseja editar: ");
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
                                            System.out.println("Informe o nome que deseja cadastrar: ");
                                            agendaNome[i] = input.nextLine();
                                            System.out.println("O nome " + nomeEditado + " foi alterado para " + agendaNome[i] + " com sucesso!");
                                            break;
                                        case 2:
                                            String telefoneEditado = agendaTelefone[i];
                                            System.out.println("Informe o telefone que deseja cadastrar: ");
                                            agendaTelefone[i] = input.nextLine();
                                            System.out.println("O telefone " + telefoneEditado + " foi alterado para " + agendaTelefone[i] + " com sucesso!");
                                            break;
                                        case 3:
                                            String emailEditado = agendaEmail[i];
                                            System.out.println("Informe o email que deseja cadastrar: ");
                                            agendaEmail[i] = input.nextLine();
                                            System.out.println("O email " + emailEditado + " foi alterado para " + agendaEmail[i] + " com sucesso!");
                                            break;
                                        case 4:
                                            System.out.println("Saindo....");
                                            break;
                                    }
                                }
                                System.out.println(">>>>  Alteração finalizada <<<<");
                                detalharContato(agendaId[i], agendaNome[i], agendaTelefone[i], agendaEmail[i]);
                                break;
                            }
                        }
                    }catch (NullPointerException e){
                        if (!contatoParaEdicaoExiste){
                            System.out.println("Não existe o contato com o telefone: " + telefone + " que deseja editar!");
                        }
                        break;
                    }
                    if (!contatoParaEdicaoExiste){
                        System.out.println("Não existe o contato com o telefone: " + telefone + " que deseja editar!");
                    }
                    break;
                case 4:
                    System.out.println("Informe o telefone do contato que deseja deletar: ");
                    telefone = input.nextLine();
                    boolean contatoParaDelExiste = false;
                    for (int i = 0, j = i + 1; i < agendaTelefone.length; i++) {
                        try {
                            if (agendaTelefone[i].equals(telefone))  {
                                if (agendaId[j] > 0) {
                                    agendaId[i] = agendaId[j - 1];
                                    agendaTelefone[i] = agendaTelefone[j];
                                    agendaEmail[i] = agendaEmail[j];
                                    agendaNome[i] = agendaNome[j];
                                }
                                contatoParaDelExiste = true;
                            }
                        }catch (NullPointerException e){
                            break;
                        }

                        if (agendaId[j] == 0) {
                            agendaId[i] = 0;
                            agendaTelefone[i] = null;
                            agendaEmail[i] = null;
                            agendaNome[i] = null;
                        }
                        if (j < agendaTelefone.length && j != 4) {
                            j++;
                        }
                    }
                    if (!contatoParaDelExiste) {
                        System.out.println("Não existe o contato buscado com o telefone: " + telefone);
                    }
                    if (contatoParaDelExiste) {
                        System.out.println("Foi deletado com sucesso o contato com o telefone: " + telefone);
                        listarAgenda(agendaId, agendaNome, agendaTelefone, agendaEmail, agendaTelefone);
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
                    System.out.println("Saindo....");
                    break;
                default:
                    System.out.println("Opção escolhida inválida, tente novamente!");
                    break;
            }
        }

    }

    public static void menuAgenda() {
        System.out.println(">>>>  Menu Contato <<<<");
        System.out.println("1 - Adicionar Contato");
        System.out.println("2 - Detalhar Contato");
        System.out.println("3 - Editar Contato");
        System.out.println("4 - Remover Contato");
        System.out.println("5 - Listar Agenda");
        System.out.println("6 - Sair");
    }

    public static void detalharContato(int id, String nome, String tel, String email) {
        System.out.println(">>>>  Contato <<<<");
        System.out.println("Id   |   Nome         |   Telefone      |   E-mail");
        System.out.println(id + "   |   " + nome + "   |   " + tel + "   |   " + email);
    }

    public static void listarAgenda(int[] id, String[] nome, String[] tel, String[] email, String[] agenda) {
        System.out.println(">>>>  Contato <<<<");
        System.out.println("Id   |   Nome         |   Telefone      |   E-mail");
        for (int i = 0; i < agenda.length; i++){
            if (id[i] >= 1){
                System.out.println(id[i] + "   |   " + nome[i] + "   |   " + tel[i] + "   |   " + email[i]);
            }else {
                break;
            }

        }
    }

    public static void menuEdicao() {
        System.out.println(">>>>  Editar Contato <<<<");
        System.out.println("1 - Editar nome");
        System.out.println("2 - Editar telefone");
        System.out.println("3 - Editar e-mail");
        System.out.println("4 - Sair");
    }

}

package view;

import java.util.Scanner;

import controller.TarefaController;

public class Main {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		TarefaController tarefaController = new TarefaController();
		int opc = 0;
		
		do {
			System.out.println("MENU\n1-Lista de processos\n2-Matar processo por PID\n3-Matar processo por nome");
			opc = scanner.nextInt();
			
			switch (opc) {
				case 1:
					System.out.println(tarefaController.listaProcessos());
					break;
				case 2:
					System.out.println("Insira o PID do processo:");
					String pid = scanner.next();
					tarefaController.mataPid(pid);	
					break;
				case 3:
					System.out.println("Insira o nome do processo:");
					String name = scanner.next();
					tarefaController.mataNome(name);
					break;
				case 9:
					System.out.println("FIM DO PROGRAMA");
					break;
				default:
					System.out.println("A opção informada é inválida");
			}
		} while (opc != 9);
		
		scanner.close();
	}
	
}

package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TarefaController {

	private String os() {
		return System.getProperty("os.name");
	}

	public String listaProcessos() {
		String nome = os();
		StringBuilder builder = new StringBuilder();

		if (nome.contains("Windows")) {
			try {
				Process p = Runtime.getRuntime().exec("TASKLIST /FO TABLE");
				InputStream stream = p.getInputStream();
				InputStreamReader reader = new InputStreamReader(stream);
				BufferedReader buffer = new BufferedReader(reader);

				String linha = buffer.readLine();
				while (linha != null) {
					builder.append(linha);
					builder.append("\n");

					linha = buffer.readLine();
				}

				buffer.close();
				reader.close();
				stream.close();
			} catch (IOException e) {
				String err = e.getMessage();
				System.err.println(err);
			}
		} else if (nome.contains("Linux")) {
			try {
				Process p = Runtime.getRuntime().exec("ps -ef");
				InputStream stream = p.getInputStream();
				InputStreamReader reader = new InputStreamReader(stream);
				BufferedReader buffer = new BufferedReader(reader);

				String linha = buffer.readLine();
				while (linha != null) {
					builder.append(linha);
					builder.append("\n");

					linha = buffer.readLine();
				}

				buffer.close();
				reader.close();
				stream.close();
			} catch (IOException e) {
				String err = e.getMessage();
				if (err.contains("740")) {
					StringBuffer buffer = new StringBuffer();
					buffer.append("cmd /c");
					buffer.append(" ");
					buffer.append("ping -4 -n 10 www.google.com.br");

					String[] newCommand = buffer.toString().split(" ");

					try {
						Runtime.getRuntime().exec(newCommand);
					} catch (Exception e1) {
						String err2 = e1.getMessage();
						System.err.println(err2);
					}
				} else {
					System.err.println(err);
				}
			}
		} else {
			return "SO não identificado";
		}

		return builder.toString();
	}

	public void mataNome(String name) {
		String nome = os();

		if (nome.contains("Windows")) {
			String command = "TASKKILL /IM " + name;

			try {
				String[] comm = command.split(" ");
				Runtime.getRuntime().exec(comm);
				System.out.println("Processo de nome \"" + name + "\" encerrado com sucesso!");
			} catch (IOException e) {
				String err = e.getMessage();

				if (err.contains("740")) {
					StringBuffer buffer = new StringBuffer();
					buffer.append("cmd /c");
					buffer.append(" ");
					buffer.append(command);

					String[] newCommand = buffer.toString().split(" ");

					System.out.println("Ocorreu um erro. Executando com privilégios de administrador...");

					try {
						Runtime.getRuntime().exec(newCommand);
						System.out.println("Processo de nome \"" + name + "\" encerrado com sucesso!");
					} catch (Exception e1) {
						String err2 = e1.getMessage();
						System.err.println(
								"Houve um erro ao finalizar o processo de nome \"" + name + "\"!\nErro: " + err2);
					}
				} else {
					System.err.println("Houve um erro ao finalizar o processo de nome \"" + name + "\"!\nErro: " + err);
				}
			}
		} else if (nome.contains("Linux")) {
			String command = "pkill -f " + name;

			try {
				String[] comm = command.split(" ");
				Runtime.getRuntime().exec(comm);
				System.out.println("Processo de nome \"" + name + "\" encerrado com sucesso!");
			} catch (IOException e) {
				String err = e.getMessage();
				System.err.println("Houve um erro ao finalizar o processo de nome \"" + name + "\"!\nErro: " + err);
			}
		} else {
			System.out.println("SO não identificado");
		}

	}

	public void mataPid(String pid) {
		String nome = os();

		if (nome.contains("Windows")) {
			String command = "TASKKILL /PID " + pid;

			try {
				String[] comm = command.split(" ");
				Runtime.getRuntime().exec(comm);
				System.out.println("Processo de PID \"" + pid + "\" encerrado com sucesso!");
			} catch (IOException e) {
				String err = e.getMessage();

				if (err.contains("740")) {
					StringBuffer buffer = new StringBuffer();
					buffer.append("cmd /c");
					buffer.append(" ");
					buffer.append(command);

					String[] newCommand = buffer.toString().split(" ");

					System.out.println("Ocorreu um erro. Executando com privilégios de administrador...");

					try {
						Runtime.getRuntime().exec(newCommand);
						System.out.println("Processo de PID \"" + pid + "\" encerrado com sucesso!");
					} catch (Exception e1) {
						String err2 = e1.getMessage();
						System.err.println(
								"Houve um erro ao finalizar o processo de PID \"" + pid + "\"!\nErro: " + err2);
					}
				} else {
					System.err.println("Houve um erro ao finalizar o processo de PID \"" + pid + "\"!\nErro: " + err);
				}
			}
		} else if (nome.contains("Linux")) {
			String command = "kill -9 " + pid;
			
			try {
				String[] comm = command.split(" ");
				Runtime.getRuntime().exec(comm);
				System.out.println("Processo de PID \"" + pid + "\" encerrado com sucesso!");
			} catch (IOException e) {
				String err = e.getMessage();
				System.err.println("Houve um erro ao finalizar o processo de PID \"" + pid + "\"!\nErro: " + err);
			}
		} else {
			System.out.println("SO não identificado");
		}

	}

}

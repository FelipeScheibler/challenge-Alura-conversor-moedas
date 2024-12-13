package br.com.alura.conversor.modelos;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Scanner;

public class ConversorMoedas {
    public static void main(String[] args) throws IOException, InterruptedException {

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();

        String apiKey = "1d78f1854db1883c25d0f162";
        String endereco = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/BRL";


        HttpClient client = HttpClient.newHttpClient();


        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .build();


        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());


        String json = response.body();


        Moeda moeda = gson.fromJson(
                gson.fromJson(json, JsonObject.class)
                        .getAsJsonObject("conversion_rates")
                        .toString(), Moeda.class);


        DecimalFormatSymbols dfs = new DecimalFormatSymbols(Locale.ENGLISH);
        DecimalFormat decimalFormat = new DecimalFormat("0.00000000000000", dfs);



        Scanner leitura = new Scanner(System.in);
        int opcao = 0;


        while (opcao != 7) {

            System.out.println("\n");
            System.out.println("***********************************************************");
            System.out.println("Seja bem vindo(a) ao Conversor de Moedas!");
            System.out.println("1) Real => Peso argentino");
            System.out.println("2) Peso argentino => Real");
            System.out.println("3) Real => Dólar");
            System.out.println("4) Dólar => Real");
            System.out.println("5) Real => Euro");
            System.out.println("6) Euro => Real");
            System.out.println("7) Sair");
            System.out.println("Escolha uma opção válida:");
            System.out.print("***********************************************************");
            System.out.print("\n");
            opcao = leitura.nextInt();

            if (opcao == 1) {
                System.out.println("Digite o valor que deseja converter:");
                double quantidade = leitura.nextDouble();
                double taxaConversao = moeda.ARS();
                double resultado = quantidade * taxaConversao;;

                System.out.print("\n");
                System.out.print("Valor: " + quantidade + " [BRL] corresponde ao valor de: " + decimalFormat.format(resultado) + " [ARS]");
            } else if (opcao == 2) {
                System.out.println("Digite o valor que deseja converter:");
                double quantidade = leitura.nextDouble();
                double taxaConversao = moeda.ARS();
                double resultado = quantidade / taxaConversao;

                System.out.print("\n");
                System.out.print("Valor: " + quantidade + " [ARS] corresponde ao valor de: " + decimalFormat.format(resultado) + " [BRL]");
            } else if (opcao == 3) {
                System.out.println("Digite o valor que deseja converter:");
                double quantidade = leitura.nextDouble();
                double taxaConversao = moeda.USD();
                double resultado = quantidade * taxaConversao;;

                System.out.print("\n");
                System.out.print("Valor: " + quantidade + " [BRL] ccorresponde ao valor de: " + decimalFormat.format(resultado) + " [USD]");
            } else if (opcao == 4) {
                System.out.println("Digite o valor que deseja converter:");
                double quantidade = leitura.nextDouble();
                double taxaConversao = moeda.USD();
                double resultado = quantidade / taxaConversao;

                System.out.print("\n");
                System.out.print("Valor: " + quantidade + " [USD] corresponde ao valor de: " + decimalFormat.format(resultado) + " [BRL]");
            } else if (opcao == 5) {
                System.out.println("Digite o valor que deseja converter:");
                double quantidade = leitura.nextDouble();
                double taxaConversao = moeda.EUR();
                double resultado = quantidade * taxaConversao;;

                System.out.print("\n");
                System.out.print("Valor: " + quantidade + " [BRL] corresponde ao valor de: " + decimalFormat.format(resultado) + " [EUR]");
            } else if (opcao == 6) {
                System.out.println("Digite o valor que deseja converter:");
                double quantidade = leitura.nextDouble();
                double taxaConversao = moeda.EUR();
                double resultado = quantidade / taxaConversao;

                System.out.print("\n");
                System.out.print("Valor " + quantidade + " [EUR] corresponde ao valor final de =>>> " + decimalFormat.format(resultado) + " [BRL]");
            } else if (opcao == 7) {
                System.out.print("\n");
                System.out.println("Programa encerrado!");
            } else {
                System.out.print("\n");
                System.out.println("Opção inválida, tente novamente!");
            }
        }
    }
}
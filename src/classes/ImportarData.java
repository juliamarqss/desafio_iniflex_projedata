package classes;

import comparator.ComparatorOrdemAlfabetica;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ImportarData {
    List<Funcionario> listaDeFuncionarios = new ArrayList<>();

    public List<Funcionario> preenchendoListaDeFuncionarios() {
        String caminhoArquivo = "src/funcionarios.csv";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d/MM/yyyy");

        try {
            FileReader leitorArquivo = new FileReader(caminhoArquivo);
            BufferedReader br = new BufferedReader(leitorArquivo);

            String linha = br.readLine();
            linha = br.readLine();

            while(linha != null) {
                String[] valor = linha.split(",");

                String nome = valor[0];
                String dataNascimento = valor[1];
                LocalDate localDate = LocalDate.parse(dataNascimento, dtf);
                BigDecimal salario = new BigDecimal(valor[2].replace(",", "."));
                String funcao = valor[3];

                Funcionario funcionario = new Funcionario(nome, localDate, salario, funcao);
                listaDeFuncionarios.add(funcionario);

                linha = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return listaDeFuncionarios;
    }

    public void imprimirLista() {
        for (Funcionario fun: listaDeFuncionarios) {
            System.out.println(fun.getNome() + " | " + fun.formatacaoData() + " | " + fun.formatacaoSalario() + " | " + fun.getFuncao());
        }
    }

    public void removerFuncionario(String nome) {
        for (int i = 0; i < listaDeFuncionarios.size(); i++) {
            if (listaDeFuncionarios.get(i).getNome().equals(nome)){
                listaDeFuncionarios.remove(i);
            }
        }
    }

    public void atualizarSalario(double porcentagem) {
        BigDecimal cem = new BigDecimal(100);
        BigDecimal porcentagemConvertida = new BigDecimal(porcentagem);
        BigDecimal porcentagemPorCem = porcentagemConvertida.divide(cem);

        for (Funcionario fun : listaDeFuncionarios) {
            BigDecimal salario = fun.getSalario();
            BigDecimal valor = salario.multiply(porcentagemPorCem);
            fun.setSalario(salario.add(valor));
        }

        System.out.println("Lista de funcionarios com salário atualizado (Aumento de " + porcentagem + "%):");
        imprimirLista();
    }

    public void imprimirOrdemAlfabetica() {
        System.out.println("Lista por ordem alfabética:");
        Collections.sort(listaDeFuncionarios, new ComparatorOrdemAlfabetica());
        for (Funcionario fun: listaDeFuncionarios) {
            System.out.println(fun.getNome() + " " + fun.formatacaoData() + " " + fun.formatacaoSalario() + " " + fun.getFuncao());
        }
    }

    public void totalDosSalarios() {
        BigDecimal soma = BigDecimal.valueOf(0);
        for (int i = 0; i < listaDeFuncionarios.size(); i++) {
            BigDecimal salario = listaDeFuncionarios.get(i).getSalario();
            soma = soma.add(salario);
            System.out.println(salario);
        }
        System.out.println("A soma do salário dos funcionários é: " + soma);
    }
}
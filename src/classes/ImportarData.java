package classes;

import comparator.ComparatorOrdemAlfabetica;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ImportarData {
    List<Funcionario> listaDeFuncionarios = new ArrayList<>();
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d/MM/yyyy");

    public List<Funcionario> preenchendoListaDeFuncionarios() {
        String caminhoArquivo = "src/funcionarios.csv";

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
//        System.out.println("\nLista de funcionários:");
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
        System.out.println("\nFuncionário(a) " + nome + " removido da lista!");
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

        System.out.println("\nLista de funcionarios com salário atualizado (Aumento de " + porcentagem + "%):");
        imprimirLista();
    }

    public Map<String, List<Funcionario>> listarPorFuncao(List<Funcionario> listaDeFuncionarios) {
        Map<String, List<Funcionario>> funcionariosPorFuncao = new HashMap<>();
        for (Funcionario fun : listaDeFuncionarios) {
            List<Funcionario> listaFuncao = funcionariosPorFuncao.get(fun.getFuncao());
            if (listaFuncao == null) {
                listaFuncao = new ArrayList<>();
                funcionariosPorFuncao.put(fun.getFuncao(), listaFuncao);
            }
            listaFuncao.add(fun);
        }
        return funcionariosPorFuncao;
    }

    public void imprimirPorFuncao() {
        Map<String, List<Funcionario>> funcionariosPorFuncao = listarPorFuncao(listaDeFuncionarios);
//        Set<Map.Entry<String, List<Funcionario>>> entry = funcionariosPorFuncao.entrySet();

        System.out.println("\nLista de funcionários separado por função:");
        for (String funcao: funcionariosPorFuncao.keySet()) {
            System.out.println(funcao + ": " + funcionariosPorFuncao.get(funcao));
        }
    }

//    public void aniversariantes() {
//        for (Funcionario fun: listaDeFuncionarios) {
//            if (fun.getDataNascimento() )
//        }
//    }

    public void imprimirMaiorIdade() {
        int idadeMax = 1;
        String nome = "";
        int anoAtual = Year.now().getValue();

        for (int i = 0; i < listaDeFuncionarios.size(); i++) {
            LocalDate dataDeNascimento = listaDeFuncionarios.get(i).getDataNascimento();
            int ano = dataDeNascimento.getYear();
            int resultado = anoAtual - ano;

            if (resultado > idadeMax) {
                idadeMax = resultado;
                nome = listaDeFuncionarios.get(i).getNome();
            }
        }

        System.out.println("Funcionário(a) com a maior idade é: " + nome + " com " + idadeMax + " anos.");
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
        for (Funcionario listaDeFuncionario : listaDeFuncionarios) {
            BigDecimal salario = listaDeFuncionario.getSalario();
            soma = soma.add(salario);
            System.out.println(salario);
        }
        System.out.println("A soma do salário dos funcionários é: " + soma);
    }

    public void salariosMinimos() {

    }
}
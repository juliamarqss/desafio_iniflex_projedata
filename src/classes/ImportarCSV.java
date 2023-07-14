package classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ImportarCSV {
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
                String conversaoData = dtf.format(localDate);
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
            System.out.println(fun);
        }
    }
}
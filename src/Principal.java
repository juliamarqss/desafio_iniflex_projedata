import classes.ImportarData;

public class Principal {
    public static void main(String[] args) {
        ImportarData importarData = new ImportarData();

        importarData.preenchendoListaDeFuncionarios();
        importarData.imprimirLista();

        // Remover o João
        importarData.removerFuncionario("João");

        // Imprimir todos os funcionários com todas suas informaçõe
        importarData.imprimirLista();

        // 3.4 Aumento de Salario (10%)

        // 3.5 Funcionários por função em um MAP, sendo a chave a “função” e o valor a “lista de funcionários”.

        // 3.6 Imprimir os funcionários, agrupados por função.

        // 3.8 Imprimir os funcionários que fazem aniversário no mês 10 e 12

        // 3.9 Imprimir o funcionário com a maior idade, exibir os atributos: nome e idade.

        // 3.10 Imprimir a lista de funcionários por ordem alfabética.
        importarData.imprimirOrdemAlfabetica();

        // 3.11 Imprimir o total dos salários dos funcionários.
        importarData.totalDosSalarios();
        // 3.12 Imprimir quantos salários mínimos ganha cada funcionário, considerando que o salário mínimo é R$1212.00.


    }
}
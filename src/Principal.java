import classes.ImportarCSV;

public class Principal {
    public static void main(String[] args) {
        ImportarCSV importarCSV = new ImportarCSV();

        System.out.println(importarCSV.preenchendoListaDeFuncionarios());
        importarCSV.imprimirLista();
    }
}
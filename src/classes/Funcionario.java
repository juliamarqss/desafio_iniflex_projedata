package classes;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;

public class Funcionario extends Pessoa {
    private String funcao;
    private BigDecimal salario;

    DecimalFormat df = new DecimalFormat("#,###.00");

    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

    public String toString() {
        return getNome() + " " + getDataNascimento() + " " + getSalario() + " " + getFuncao();
    }

    public String getFuncao() {
        return funcao;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String formatacaoSalario() {
        return df.format(getSalario());
    }
}

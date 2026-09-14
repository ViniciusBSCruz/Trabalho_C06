package modelo;

public class Cliente {

    private String nome;
    private String cpf;
    private Computador[] computadores;

    public Cliente(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setComputadores(Computador[] computadores) {
        this.computadores = computadores;
    }

    public float calculaTotalCompra() {
        float total = 0;

        for (int i = 0; i < computadores.length; i++) {
            total = total + computadores[i].getPreco();
        }

        return total;
    }
}

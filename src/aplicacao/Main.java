package aplicacao;

import modelo.Cliente;
import modelo.Computador;
import modelo.HardwareBasico;
import modelo.MemoriaUSB;
import modelo.SistemaOperacional;
import pedido.ProcessarPedido;

import java.util.Scanner;

public class Main {

    private static float matricula;

    // Tamanho máximo de PCs que um cliente pode comprar numa mesma compra
    private static final int CAPACIDADE_MAXIMA_CARRINHO = 50;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Bem-vindo(a) à PC Mania ===");
        System.out.print("Digite o nome do cliente: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o CPF do cliente: ");
        String cpf = scanner.nextLine();
        System.out.print("Digite a sua matrícula: ");
        matricula = scanner.nextFloat();
        scanner.nextLine();

        Cliente cliente = new Cliente(nome, cpf);

        Computador[] carrinho = new Computador[CAPACIDADE_MAXIMA_CARRINHO];
        int quantidade = 0;
        int codigo;

        do {
            System.out.println();
            System.out.println("Escolha o código da promoção que deseja comprar:");
            System.out.println("1 - Promoção 1 (Apple)");
            System.out.println("2 - Promoção 2 (Samsung)");
            System.out.println("3 - Promoção 3 (Dell)");
            System.out.println("0 - Finalizar compra");
            System.out.print("Código: ");
            codigo = scanner.nextInt();
            scanner.nextLine();

            if (codigo == 0) {
                if (quantidade < 2) {
                    System.out.println("É necessário comprar pelo menos 2 computadores antes de finalizar.");
                }
            } else if (codigo == 1) {
                carrinho[quantidade] = criaComputadorPromocao1();
                quantidade++;
            } else if (codigo == 2) {
                carrinho[quantidade] = criaComputadorPromocao2();
                quantidade++;
            } else if (codigo == 3) {
                carrinho[quantidade] = criaComputadorPromocao3();
                quantidade++;
            } else {
                System.out.println("Código inválido! Digite 0, 1, 2 ou 3.");
            }

        } while (codigo != 0 || quantidade < 2);

        Computador[] computadoresComprados = new Computador[quantidade];
        for (int i = 0; i < quantidade; i++) {
            computadoresComprados[i] = carrinho[i];
        }

        cliente.setComputadores(computadoresComprados);

        ProcessarPedido.processarPedido(computadoresComprados);

        System.out.println();
        System.out.println("=== Dados do Cliente ===");
        System.out.println("Nome: " + cliente.getNome());
        System.out.println("CPF: " + cliente.getCpf());

        System.out.println();
        System.out.println("=== Computadores adquiridos ===");
        for (int i = 0; i < computadoresComprados.length; i++) {
            computadoresComprados[i].mostraPCConfigs();
        }

        System.out.println("Total da compra: R$" + cliente.calculaTotalCompra());

        scanner.close();
    }

    private static Computador criaComputadorPromocao1() {
        HardwareBasico[] hardwares = new HardwareBasico[3];
        hardwares[0] = new HardwareBasico("Processador Core i5", 2200);
        hardwares[1] = new HardwareBasico("Memória RAM", 8);
        hardwares[2] = new HardwareBasico("HD", 500);

        SistemaOperacional sistemaOperacional = new SistemaOperacional("macOS Sequoia", 64);

        Computador computador = new Computador("Apple", matricula, hardwares, sistemaOperacional);
        computador.addMemoriaUSB(new MemoriaUSB("Pen-drive", 16));

        return computador;
    }

    private static Computador criaComputadorPromocao2() {
        HardwareBasico[] hardwares = new HardwareBasico[3];
        hardwares[0] = new HardwareBasico("Processador Core i7", 3370);
        hardwares[1] = new HardwareBasico("Memória RAM", 16);
        hardwares[2] = new HardwareBasico("HD", 1000); // 1Tb = 1000Gb

        SistemaOperacional sistemaOperacional = new SistemaOperacional("Windows 8", 64);

        Computador computador = new Computador("Samsung", matricula + 1, hardwares, sistemaOperacional);
        computador.addMemoriaUSB(new MemoriaUSB("Pen-drive", 32));

        return computador;
    }

    private static Computador criaComputadorPromocao3() {
        HardwareBasico[] hardwares = new HardwareBasico[3];
        hardwares[0] = new HardwareBasico("Processador Core i7", 4500);
        hardwares[1] = new HardwareBasico("Memória RAM", 32);
        hardwares[2] = new HardwareBasico("HD", 2000); // 2Tb = 2000Gb

        SistemaOperacional sistemaOperacional = new SistemaOperacional("Windows 10", 64);

        Computador computador = new Computador("Dell", matricula + 2, hardwares, sistemaOperacional);
        computador.addMemoriaUSB(new MemoriaUSB("HD Externo", 1000)); // 1Tb = 1000Gb

        return computador;
    }
}
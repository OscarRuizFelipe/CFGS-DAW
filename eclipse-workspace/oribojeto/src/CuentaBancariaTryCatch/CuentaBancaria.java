package CuentaBancariaTryCatch;

public class CuentaBancaria {

    private String titular;
    private double saldo;

    public CuentaBancaria(String titular, double saldoInicial) {

        if (saldoInicial < 0) {
            throw new IllegalArgumentException("El saldo inicial no puede ser negativo");
        }

        this.titular = titular;
        this.saldo = saldoInicial;
    }

    public void ingresar(double cantidad) {

        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad a ingresar debe ser mayor que 0");
        }

        this.saldo += cantidad;
    }

    public void retirar(double cantidad) throws SaldoInsuficienteException {

        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad a retirar debe ser mayor que 0");
        }

        if (cantidad > this.saldo) {
            throw new SaldoInsuficienteException("Saldo insuficiente para retirar " + cantidad);
        }

        this.saldo -= cantidad;
    }

    public double getSaldo() {
        return this.saldo;
    }

    public String toString() {
        return "Cuenta de " + titular + " con saldo: " + saldo;
    }
}
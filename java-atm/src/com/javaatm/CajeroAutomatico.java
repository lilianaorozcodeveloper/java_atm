package com.javaatm;

import java.util.ArrayList;
import java.util.Scanner;

public class CajeroAutomatico {
    static double saldoDisponible = 10_000.00;
    static int intentosInvalidos = 0;
    static ArrayList<String> ultimosMovimientos = new ArrayList<>();

    public static void mostrarMenu() {
        System.out.println("Bienvenido al cajero automático, por favor seleccione la opción deseada:");
        System.out.println("1) Retirar dinero");
        System.out.println("2) Hacer depósitos");
        System.out.println("3) Consultar saldo");
        System.out.println("4) Quejas");
        System.out.println("5) Ver últimos movimientos");
        System.out.println("9) Salir del cajero");
    }

    public static void retirarDinero() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Saldo disponible para retiro: " + saldoDisponible);
        System.out.println("Ingrese la cantidad a retirar (múltiplos de $50 y no más de $6000): ");
        double montoRetiro = scanner.nextDouble();
        if (montoRetiro % 50 == 0 && montoRetiro <= 6000 && montoRetiro <= saldoDisponible) {
            saldoDisponible -= montoRetiro;
            System.out.println("Retiró $" + montoRetiro);
            System.out.println("¿Desea donar $200 para la graduación de ch34? (Sí/No): ");
            String donar = scanner.next();
            if (donar.equalsIgnoreCase("sí")) {
                saldoDisponible -= 200;
                System.out.println("Gracias por su donación.");
            }
            ultimosMovimientos.add("Retiro de $" + montoRetiro);
        } else {
            System.out.println("Monto inválido o excede el límite de retiro");
        }
    }

    public static void hacerDepositos() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Seleccione una opción de depósito:");
        System.out.println("1) Depósito a cuenta de cheques");
        System.out.println("2) Depósito a tarjeta de crédito");
        String opcionDeposito = scanner.next();
        switch (opcionDeposito) {
            case "1":
                System.out.println("Ingrese la cantidad a depositar en cuenta de cheques (múltiplos de $50): ");
                double depositoCheques = scanner.nextDouble();
                if (depositoCheques % 50 == 0) {
                    saldoDisponible += depositoCheques;
                    System.out.println("Se depositaron $" + depositoCheques + " en cuenta de cheques.");
                    ultimosMovimientos.add("Depósito a cuenta de cheques de $" + depositoCheques);
                } else {
                    System.out.println("Monto inválido para depósito");
                }
                break;
            case "2":
                System.out.println("Ingrese la cantidad a depositar en tarjeta de crédito: ");
                double depositoTarjeta = scanner.nextDouble();
                saldoDisponible -= depositoTarjeta;
                System.out.println("Se realizó un depósito de $" + depositoTarjeta + " en la tarjeta de crédito.");
                ultimosMovimientos.add("Depósito a tarjeta de crédito de $" + depositoTarjeta);
                break;
            default:
                System.out.println("Opción inválida para depósito");
                break;
        }
    }

    public static void consultarSaldo() {
        System.out.println("Su saldo disponible es: $" + saldoDisponible);
    }

    public static void quejas() {
        System.out.println("No disponible por el momento, intente más tarde");
    }

    public static void verUltimosMovimientos() {
        System.out.println("Últimos movimientos:");
        for (String movimiento : ultimosMovimientos) {
            System.out.println(movimiento);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            mostrarMenu();
            System.out.println("Seleccione una opción: ");
            String opcion = scanner.next();

            switch (opcion) {
                case "1":
                    retirarDinero();
                    break;
                case "2":
                    hacerDepositos();
                    break;
                case "3":
                    consultarSaldo();
                    break;
                case "4":
                    quejas();
                    break;
                case "5":
                    verUltimosMovimientos();
                    break;
                case "9":
                    System.out.println("Gracias por usar el cajero automático. ¡Hasta luego!");
                    return;
                default:
                    System.out.println("Opción inválida, por favor, vuelva a intentar.");
                    intentosInvalidos++;
                    if (intentosInvalidos >= 3) {
                        System.out.println("Ha excedido el número de intentos inválidos. Saliendo del sistema.");
                        return;
                    }
                    break;
            }
        }
    }
}
package UI;

import UX.InteractCustomer;

import java.util.Scanner;

public class Customer {
    Scanner scan = new Scanner(System.in);
    InteractCustomer interactCustomer;

    public Customer(InteractCustomer interactCustomer) {
        this.interactCustomer = interactCustomer;
    }

    public void menu() {
        loop:
        while (true) {
            System.out.println("_________________Customer_________________\n" +
                    "|\t\t\t1.Thêm vào giỏ hàng\n" +
                    "|\t\t\t2.Thanh toán\n" +
                    "|\t\t\t3.Thoát chức năng\n" +
                    "|\t\t\t4.Thoát trương trình\n" +
                    "_______________________________________________\n");
            String a = scan.nextLine();
            int n = Integer.parseInt(a);
            switch (n) {
                case 1:
                    interactCustomer.addToCart();
                    break;
                case 2:
                    interactCustomer.purchase();
                    break;
                case 3:
                    break loop;
                case 4:
                    System.exit(0);
                default:
                    System.out.println("Bạn đã nhập sai cú pháp. Vui lòng nhập lại");
            }
        }
    }
}


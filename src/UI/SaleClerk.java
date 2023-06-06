package UI;

import UX.InteractSaleClerk;

import java.util.Scanner;

public class SaleClerk {
    Scanner scan = new Scanner(System.in);
    InteractSaleClerk interactSaleClerk;

    public SaleClerk(InteractSaleClerk interactSaleClerk) {
        this.interactSaleClerk = interactSaleClerk;
    }

    public void menu() {
        loop:
        while (true){
            System.out.println("_________________Sale Clerk_________________ \n" +
                    "|\t\t\t1.Thêm hàng hóa\n" +
                    "|\t\t\t2.Danh sách hàng hóa\n" +
                    "|\t\t\t3.Tăng thời gian\n" +
                    "|\t\t\t4.Thống kê\n" +
                    "|\t\t\t5.Thoát chức năng\n" +
                    "|\t\t\t6.Thoát trương trình\n" +
                    "_______________________________________________\n");
            String a = scan.nextLine();
            int n = Integer.parseInt(a);
            switch (n){
                case 1:
                    interactSaleClerk.addProduct();
                    break;
                case 2:
                    interactSaleClerk.showListProduct();
                    break;
                case 3:
                    interactSaleClerk.plusDays();
                    break;
                case 4:
                    interactSaleClerk.statistic();
                    break;
                case 5:
                    break loop;
                case 6:
                    System.exit(0);
                default:
                    System.out.println("Bạn đã nhập sai cú pháp. Vui lòng nhập lại");
            }
        }


    }
}

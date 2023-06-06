package UX;

import Model.*;
import UI.Customer;
import UI.SaleClerk;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Manager implements InteractCustomer, InteractSaleClerk {
    Scanner scan = new Scanner(System.in);
    List<Product> cart = new ArrayList<>();
    List<Product> listProduct = new ArrayList<>();
    List<Product> listSoldProduct=new ArrayList<>();
    List<Product> listProductExpired = new ArrayList<>();

    LocalDate dateOfSystem = LocalDate.now();



    InteractSaleClerk interactSaleClerk = new InteractSaleClerk() {
        @Override
        public void addProduct() {
            Manager.this.addProduct();
        }

        @Override
        public void showListProduct() {
            Manager.this.showListProduct();
        }

        @Override
        public void plusDays() {
            Manager.this.plusDays();
        }

        @Override
        public void statistic() {
            Manager.this.statistic();
        }
    };

    InteractCustomer interactCustomer = new InteractCustomer() {
        @Override
        public void addToCart() {
            Manager.this.addToCart();
        }

        @Override
        public void purchase() {
            Manager.this.purchase();
        }
    };

    public Manager() {
        addDefaultProduct();
    }

    public void addDefaultProduct() {
        listProduct.add(new Product("Bột giặt ô mô", "10001", "ô mô", 20, 30, 20));
        listProduct.add(new Product("Sữa tắm Lifebuoy", "10002", "LifeBuoy", 50, 80, 24));
        listProduct.add(new PackagedFood("Ba chỉ bò", "10003", "CP", 70, 90, 30, LocalDate.now(), 3, 500, "Gói"));
        listProduct.add(new PackagedFood("Ba chỉ bò", "10003", "CP", 70, 90, 5, LocalDate.now(), 3, 500, "Gói"));
        listProduct.add(new PackagedFood("Xúc xích", "10004", "CP", 30, 50, 40, LocalDate.now(), 30, 300, "Gói"));
        listProduct.add(new PackagedFood("Ngô cay", "10005", "SnacksFood", 10, 15, 15, LocalDate.now(), 3, 400, "Hộp"));
        listProduct.add(new UnpackagedFood("khoai tây", "10006", "Farm Food", 20, 25, 9, LocalDate.now(), 10, 1));
    }

    public void picType() {
        while (true) {
            System.out.println("_______Pic Type_______\n" +
                    "1.Sale Clerk \n" +
                    "2.Customer \n");
            String a = scan.nextLine();
            int n = Integer.parseInt(a);
            if (n == 1) {
                SaleClerk saleClerk = new SaleClerk(interactSaleClerk);
                saleClerk.menu();
            } else if (n == 2) {
                Customer customer = new Customer(interactCustomer);
                customer.menu();
            } else {
                System.out.println("Sai cú pháp, Xin vui lòng nhập lại");
            }
        }
    }

    //    ___________________________Feature Sale Clerk___________________________
    @Override
    public void addProduct() {
        loop:
        while (true) {
            System.out.println("1.Hàng hóa thông dụng\n" +
                    "2.Thực phẩm đóng gói\n" +
                    "3.Thực phẩm chưa đóng gói\n" +
                    "4.Quay lại");
            int n = Integer.parseInt(scan.nextLine());
            if (n == 4) {
                break loop;
            }
            System.out.println("Nhập mã hàng hóa:");
            String codeProduct = scan.nextLine();
            Product find = null;
            FactoryProduct factoryProduct = new FactoryProduct();
            for (Product a : listProduct) {
                if (codeProduct.equalsIgnoreCase(a.getCodeProduct())) {
                    find = a;
                    break;
                }
            }
            switch (n) {
                case 1:
                    if (find == null) {
                        Product newProduct = factoryProduct.getTypeProduct(1);
                        newProduct.setCodeProduct(codeProduct);
                        listProduct.add(newProduct);
                        System.out.println("Bạn đã thêm mặt hàng mới thành công");
                    } else {
                        if (find instanceof PackagedFood || find instanceof UnpackagedFood) {
                            System.out.println("Mã sản phẩm đã tồn tại, nhưng không cùng loại, Thêm hàng hóa thất bại");
                        } else {
                            System.out.println("Nhập số lượng muốn thêm vào (Cái hoặc Kg) : ");
                            int amount = Integer.parseInt(scan.nextLine());
                            find.setAmount(find.getAmount() + amount);
                            System.out.println("Bạn đã thêm hàng hóa thành công");
                        }
                    }
                    break;
                case 2:
                    if (find instanceof PackagedFood) {
                        PackagedFood packagedFood = (PackagedFood) find;
                        System.out.println("Nhập số lượng muốn thêm vào (Cái hoặc Kg) : ");
                        int amount = Integer.parseInt(scan.nextLine());
                        System.out.println("Ngày sản xuất (Ngày Tháng Năm):");
                        String stringPattern = scan.nextLine();
                        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("MM dd yyyy");
                        LocalDate dateOfManufacture = LocalDate.parse(stringPattern, pattern);
                        listProduct.add(new PackagedFood(packagedFood.getNameProduct(), packagedFood.getCodeProduct(), packagedFood.getDistributor(), packagedFood.getPriceImport(), packagedFood.getPriceSell(), amount, dateOfManufacture, packagedFood.getDateDue(), packagedFood.getWeightPackaged(), packagedFood.getTypePackaged()));
                        System.out.println("Bạn đã thêm hàng hóa thành công");
                    } else if (find == null) {
                        PackagedFood newPackagedFood = (PackagedFood) factoryProduct.getTypeProduct(2);
                        newPackagedFood.setCodeProduct(codeProduct);
                        listProduct.add(newPackagedFood);
                        System.out.println("Bạn đã thêm mặt hàng mới thành công");

                    }
                    break;
                case 3:
                    if (find instanceof UnpackagedFood) {
                        System.out.println("Nhập số lượng muốn thêm vào (Cái hoặc Kg) : ");
                        UnpackagedFood unpackagedFood = (UnpackagedFood) find;
                        int amount = Integer.parseInt(scan.nextLine());
                        unpackagedFood.setAmount(find.getAmount() + amount);
                        System.out.println("Ngày sản xuất (Ngày Tháng Năm):");
                        String stringPattern = scan.nextLine();
                        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("MM dd yyyy");
                        LocalDate dateOfManufacture = LocalDate.parse(stringPattern, pattern);
                        listProduct.add(new UnpackagedFood(unpackagedFood.getNameProduct(), unpackagedFood.getCodeProduct(), unpackagedFood.getDistributor(), unpackagedFood.getPriceImport(), unpackagedFood.getPriceSell(), amount, dateOfManufacture, unpackagedFood.getDateDue(), unpackagedFood.getWeightForPrice()));
                        System.out.println("Bạn đã thêm hàng hóa thành công");
                    } else if (find == null) {
                        UnpackagedFood newUnpackagedFood = (UnpackagedFood) factoryProduct.getTypeProduct(3);
                        newUnpackagedFood.setCodeProduct(codeProduct);
                        listProduct.add(newUnpackagedFood);
                        System.out.println("Bạn đã thêm mặt hàng mới thành công");
                    }
                    break;
                case 4:
                    break loop;
                default:
                    System.out.println("Lỗi cú pháp");
            }
        }
    }

    @Override
    public void showListProduct() {
        List<Product> listForShowProduct = new ArrayList<>();
        List<PackagedFood> listForShowPackagedFood = new ArrayList<>();
        List<UnpackagedFood> listForShowUnpackagedFood = new ArrayList<>();

        for (Product a : listProduct) {
            if (a instanceof PackagedFood) {
                listForShowPackagedFood.add((PackagedFood) a);
            } else if (a instanceof UnpackagedFood) {
                listForShowUnpackagedFood.add((UnpackagedFood) a);
            } else {
                listForShowProduct.add(a);
            }
        }
        System.out.println("____Product____");
        for (Product a : listForShowProduct) {
            System.out.println(a.toString());
        }
        System.out.println("____PackagedFood____");
        for (PackagedFood a : listForShowPackagedFood) {
            System.out.println(a.toString());
        }
        System.out.println("____UnpackagedFood____");
        for (UnpackagedFood a : listForShowUnpackagedFood) {
            System.out.println(a.toString());
        }
    }

    @Override
    public void plusDays() {
        List<Product> expiredToday = new ArrayList<>();
        System.out.println("Nhập số ngày bạn muốn tăng:");
        int n = Integer.parseInt(scan.nextLine());
        dateOfSystem = dateOfSystem.plusDays(n);
        System.out.println("Bạn đã tăng thời gian thành công");
        for (Product a : listProduct) {
            if (a instanceof Food) {
                Food food = (Food) a;
                LocalDate localDate = food.getDateOfManufacture().plusDays(food.getDateDue());
                if (localDate.isBefore(dateOfSystem)) {
                    listProductExpired.add(food);
                    expiredToday.add(food);
                }
            }
        }
        if (!expiredToday.isEmpty()) {
            System.out.println("_________________________________________________________số lượng hàng hóa đã hết hạn___________________________________________________");
            int TotalMoneyDamage=0;
            for (Product a : expiredToday) {
                int MoneyForEachConsignment=a.getAmount()*a.getPriceImport();
                TotalMoneyDamage+=MoneyForEachConsignment;
                listProduct.remove(a);
                Food foodExpired = (Food) a;
                System.out.println(a.showForPurchase()+ "Số tiền:  "+MoneyForEachConsignment+"000 VNĐ");
            }
            System.out.println("________________________________________________\n" +
                    "Số tiền thiệt hại :                    " + TotalMoneyDamage + ".000 VNĐ\n\n\n");

        }else {
            System.out.println("Không có mặt hàng nào hết hạn");
        }
    }
    public void showDamage(){
        if (!listProductExpired.isEmpty()) {
            System.out.println("_________________________________________________________số lượng hàng hóa đã hết hạn___________________________________________________");
            int TotalMoneyDamage=0;
            for (Product a : listProductExpired) {
                int MoneyForEachConsignment=a.getAmount()*a.getPriceImport();
                TotalMoneyDamage+=MoneyForEachConsignment;
                listProduct.remove(a);
                System.out.println(a.showForPurchase()+ "Số tiền:  "+MoneyForEachConsignment+"000 VNĐ");
            }
            System.out.println("________________________________________________\n" +
                    "Số tiền thiệt hại :                    " + TotalMoneyDamage + ".000 VNĐ\n\n\n");

        }else {
            System.out.println("Không có mặt hàng nào hết hạn");
        }
    }

    @Override
    public void statistic() {
        System.out.println("Các mặt hàng đã bán");
        if (!listSoldProduct.isEmpty()) {
            System.out.println("_________________________________________________________số lượng hàng hóa đã hết hạn___________________________________________________");
            int TotalRevenue=0;
            int TotalInterest=0;
            for (Product a : listSoldProduct) {
                int RevenueForEachConsignment=a.getAmount()*a.getPriceSell();
                int InterestForEachConsignment=a.getAmount()*(a.getPriceSell()-a.getPriceImport());
                TotalRevenue+=RevenueForEachConsignment;
                TotalInterest+=InterestForEachConsignment;
                System.out.println(a.showForPurchase()+ "Số tiền:  "+TotalRevenue+"000 VNĐ");
            }
            System.out.println("________________________________________________\n" +
                    "Tổng doanh thu :                    " + TotalRevenue + ".000 VNĐ" +
                    "Tổng lợi nhuận :                    " + TotalInterest  +  ".000 VNĐ\"\n\n\n");


        }
    }
    //____________________________________________________________________________


    //    ___________________________Feature Customer___________________________

    @Override
    public void addToCart() {
        System.out.println("Nhập mã hàng hóa:");
        String codeProduct = scan.nextLine();
        Product find = null;
        for (Product a : listProduct) {
            if (a.getCodeProduct().equalsIgnoreCase(codeProduct)) {
                find = a;
                break;
            }
        }
        if (find != null) {
            int totalAmount = 0;
            if (find instanceof UnpackagedFood) {
                System.out.println("Đây là hàng hóa chưa được đóng gói, bạn phải cân");
                System.out.println("Nếu đã cân xong mời bạn nhập số lượng vào đây (Kg):");
                int weight = Integer.parseInt(scan.nextLine());
                handleInStock(codeProduct, weight);
            } else {
                System.out.println("Nhập số lượng:");
                int amount = Integer.parseInt(scan.nextLine());
                for (Product a : listProduct) {
                    if (a.getCodeProduct().equalsIgnoreCase(codeProduct)) {
                        totalAmount += a.getAmount();
                    }
                }
                if (amount <= totalAmount) {
                    handleInStock(codeProduct, amount);
                } else {
                    System.out.println("Hàng hóa trong kho không đủ, thêm vào giỏ hàng thất bại");
                }
            }
        } else {
            System.out.println("Hàng hóa không tồn tại");
        }
    }

    public void handleInStock(String codeProduct, int amount) {

        for (int i = listProduct.size() - 1; i >= 0; i--) {
            Product product = listProduct.get(i);
            if (product.getCodeProduct().equalsIgnoreCase(codeProduct)) {
                if (product.getAmount() > amount) {
                    product.setAmount(product.getAmount() - amount);
                    Product cloneProduct = product.clone();
                    cloneProduct.setAmount(amount);
                    cart.add(cloneProduct);
                    System.out.println("bạn đã thêm vào giỏ hàng thành công");
                    listYourCart();
                    break;
                } else {
                    Product cloneProduct = product.clone();
                    cloneProduct.setAmount(product.getAmount());
                    cart.add(cloneProduct);
                    amount = amount - product.getAmount();
                    listProduct.remove(product);
                    if (amount == 0) {
                        System.out.println("Bạn đã thêm vào giỏ hàng thành công");
                        listYourCart();
                        break;
                    }
                }
            }
        }
    }

    public void listYourCart() {
        System.out.println("Giỏ hàng hiện tại của bạn:");
        for (Product a : cart) {
            System.out.println(a.ShowForCustomer());
        }
    }

    @Override
    public void purchase() {
        if (cart.isEmpty()) {
            System.out.println("Giỏ của bạn hiện không có gì.");
        } else {
            int totalMoney = 0;
            System.out.println("\n\n\n______________________Your Bill____________________");
            for (Product a : cart) {
                listSoldProduct.add(a);
                int MoneyOfEachConsignment = a.getAmount() * a.getPriceSell();
                System.out.println(a.showForPurchase() + MoneyOfEachConsignment + ".000 VNĐ");
                totalMoney += MoneyOfEachConsignment;
            }
            System.out.println("________________________________________________\n" +
                    "Thành tiền :                             " + totalMoney + ".000 VNĐ\n\n\n");
            cart.clear();
        }
    }
}



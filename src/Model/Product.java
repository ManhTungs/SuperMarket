package Model;

import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class Product implements Cloneable {
    Scanner scan = new Scanner(System.in);
    private String nameProduct;
    private String codeProduct;
    private String distributor;
    private int priceImport;
    private int priceSell;
    private int amount;

    public Product() {
    }

    public Product(String nameProduct, String codeProduct, String distributor, int priceImport, int priceSell, int amount) {
        this.nameProduct = nameProduct;
        this.codeProduct = codeProduct;
        this.distributor = distributor;
        this.priceImport = priceImport;
        this.priceSell = priceSell;
        this.amount = amount;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getCodeProduct() {
        return codeProduct;
    }

    public void setCodeProduct(String codeProduct) {
        this.codeProduct = codeProduct;
    }

    public String getDistributor() {
        return distributor;
    }

    public void setDistributor(String distributor) {
        this.distributor = distributor;
    }

    public int getPriceImport() {
        return priceImport;
    }

    public void setPriceImport(int priceImport) {
        this.priceImport = priceImport;
    }

    public int getPriceSell() {
        return priceSell;
    }

    public void setPriceSell(int priceSell) {
        this.priceSell = priceSell;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void addProduct() {
        System.out.println("Nhập vào Tên sản phẩm:");
        nameProduct=scan.nextLine();
        System.out.println("Nhà sản xuất:");
        distributor=scan.nextLine();
        System.out.println("Giá nhập:");
        priceImport = Integer.parseInt(scan.nextLine());
        System.out.println("Giá bán:");
        priceSell = Integer.parseInt(scan.nextLine());
        System.out.println("Số lượng:");
        amount = Integer.parseInt(scan.nextLine());
    }

    public Product clone(){
        try {
            Product product=getClass().getDeclaredConstructor().newInstance();
            product.nameProduct = nameProduct;
            product.codeProduct = codeProduct;
            product.distributor = distributor;
            product.priceImport = priceImport;
            product.priceSell = priceSell;
            return product;
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return " ||Name Product='" + nameProduct + '\'' +
                "    ||Code Product='" + codeProduct + '\'' +
                "    ||Distributor='" + distributor + '\'' +
                "    ||PriceImport=" + priceImport +
                "    ||PriceSell=" + priceSell +
                "    ||Amount=" + amount ;
    }
    public String ShowForCustomer(){
        return " ||Name Product='" + nameProduct + '\'' +
                " ||Price=" + priceSell +
                " ||Amount=" + amount ;
    }
    public String showForPurchase(){
        return "Name Product :" + nameProduct + "     Amount  :" + amount +"     ";
    }
}


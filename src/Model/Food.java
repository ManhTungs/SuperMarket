package Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Food extends Product implements Cloneable{
    private LocalDate dateOfManufacture;
    private int dateDue;

    public Food() {
    }

    public Food(String nameProduct, String codeProduct, String distributor, int priceImport, int priceSell, int amount, LocalDate dateOfManufacture, int dateDue) {
        super(nameProduct, codeProduct, distributor, priceImport, priceSell, amount);
        this.dateOfManufacture = dateOfManufacture;
        this.dateDue = dateDue;
    }

    public LocalDate getDateOfManufacture() {
        return dateOfManufacture;
    }

    public void setDateOfManufacture(LocalDate dateOfManufacture) {
        this.dateOfManufacture = dateOfManufacture;
    }

    public int getDateDue() {
        return dateDue;
    }

    public void setDateDue(int dateDue) {
        this.dateDue = dateDue;
    }

    public void addProduct(){
        super.addProduct();
        System.out.println("Ngày sản xuất (Ngày Tháng Năm):");
        String stringPattern = scan.nextLine();
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("MM dd yyyy");
        dateOfManufacture = LocalDate.parse(stringPattern, pattern);
        System.out.println("Hạn sử dụng (số ngày kể từ ngày sản xuất):");
        dateDue = Integer.parseInt(scan.nextLine());
    }

    public Food clone(){
        Food food=(Food) super.clone();
        food.dateOfManufacture = dateOfManufacture;
        food.dateDue = dateDue;
        return food;
    }
    @Override
    public String toString() {
        return super.toString() +
                "   ||dateOfManufacture=" + dateOfManufacture +
                "   ||dateDue=" + dateDue;
    }
}

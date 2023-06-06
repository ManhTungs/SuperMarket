package Model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UnpackagedFood extends Food implements Cloneable{
    private int weightForPrice;

    public UnpackagedFood() {
    }

    public UnpackagedFood(String nameProduct, String codeProduct, String distributor, int priceImport, int priceSell, int amount, LocalDate dateOfManufacture, int dateDue, int weightForPrice) {
        super(nameProduct, codeProduct, distributor, priceImport, priceSell, amount, dateOfManufacture, dateDue);
        this.weightForPrice = weightForPrice;
    }

    public int getWeightForPrice() {
        return weightForPrice;
    }

    public void setWeightForPrice(int weightForPrice) {
        this.weightForPrice = weightForPrice;
    }
    public void addProduct(UnpackagedFood unpackagedFood){
        super.addProduct();
        System.out.println("Khối lượng tính tiền (Kg):");
        weightForPrice=Integer.parseInt(scan.nextLine());
    }
    @Override
    public UnpackagedFood clone(){
        UnpackagedFood unpackagedFood= (UnpackagedFood) super.clone();
        unpackagedFood.weightForPrice = weightForPrice;
        return unpackagedFood;
    }
    @Override
    public String toString() {
        return super.toString() +
                "weightForPrice=" + weightForPrice +
                '}';
    }
}

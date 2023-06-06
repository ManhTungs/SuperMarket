package Model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PackagedFood extends Food implements Cloneable{
    private int weightPackaged;
    private String typePackaged;

    public PackagedFood() {
    }
    public PackagedFood(String nameProduct, String codeProduct, String distributor, int priceImport, int priceSell, int amount, LocalDate dateOfManufacture, int dateDue, int weightPackaged, String typePackaged) {
        super(nameProduct, codeProduct, distributor, priceImport, priceSell, amount, dateOfManufacture, dateDue);
        this.weightPackaged = weightPackaged;
        this.typePackaged = typePackaged;
    }

    public int getWeightPackaged() {
        return weightPackaged;
    }

    public void setWeightPackaged(int weightPackaged) {
        this.weightPackaged = weightPackaged;
    }

    public String getTypePackaged() {
        return typePackaged;
    }

    public void setTypePackaged(String typePackaged) {
        this.typePackaged = typePackaged;
    }
    public void addProduct(PackagedFood packagedFood){
        super.addProduct();
        System.out.println("Khối lượng đóng gói (gram):");
        weightPackaged=Integer.parseInt(scan.nextLine());
        System.out.println("Quy cách đóng gói (Gói,Hộp,...):");
        typePackaged=scan.nextLine();
    }
    @Override
    public PackagedFood clone(){
        PackagedFood packagedFood= (PackagedFood) super.clone();
        packagedFood.weightPackaged = weightPackaged;
        packagedFood.typePackaged = typePackaged;
        return packagedFood;
    }
    @Override
    public String toString() {
        return super.toString() +
                "   ||weightPackaged=" + weightPackaged +
                "   ||typePackaged='" + typePackaged ;
    }
}

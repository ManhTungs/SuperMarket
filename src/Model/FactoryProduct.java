package Model;

public class FactoryProduct {
    public Product getTypeProduct(int n){
        if(n==1){
            Product product=new Product();
            product.addProduct();
            return product;
        } else if (n==2) {
            PackagedFood packagedFood=new PackagedFood();
            packagedFood.addProduct();
            return packagedFood;
        } else if (n==3) {
            UnpackagedFood unpackagedFood=new UnpackagedFood();
            unpackagedFood.addProduct();
            return unpackagedFood;
        }
        return null;
    }
}

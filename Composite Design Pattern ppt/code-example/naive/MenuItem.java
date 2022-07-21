public class MenuItem {
    private String name;
    private double price;
    private boolean veg;

    public MenuItem(String name,double price,boolean veg){
        this.name = name;
        this.price = price;
        this.veg = veg;
    }

    public void printItem(){
        System.out.println(name+"-"+price);
    }

    public void isVeg(){
        System.out.print(veg);
    }
}

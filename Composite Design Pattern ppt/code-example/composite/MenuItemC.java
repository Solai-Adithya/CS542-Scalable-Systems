public class MenuItemC extends MenuComponent{
    String name;
    boolean veg;
    double price;

    public MenuItemC(String name, double price, boolean veg){
        this.name = name;
        this.veg = veg;
        this.price = price;
    }

    public String getName(){
        return name;
    }

    public double getPrice(){
        return price;
    }

    public boolean isveg(){
        return veg;
    }

    public void print(){
        System.out.print(" "+ getName());
        if(veg) System.out.print("(V)");
        System.out.println(" - "+getPrice());
    }
}

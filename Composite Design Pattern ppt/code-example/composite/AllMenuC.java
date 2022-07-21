public class AllMenuC {
    public static void main(String[] args) {
        MenuComponent BreakFast = new MenuC("BreakFast");
        MenuComponent DinnerStarters = new MenuC("Dinner Starters");
        MenuComponent Dinner = new MenuC("Dinner");
        MenuComponent DinnerDesserts = new MenuC("Dinner Desserts");

        MenuComponent allmenus = new MenuC("All Menus");
        

        BreakFast.add(new MenuItemC("Paratha", 40, true));
        BreakFast.add(new MenuItemC("Omlette", 20, false));
        BreakFast.add(new MenuItemC("Milk", 20, true));

        DinnerStarters.add(new MenuItemC("Soup", 50, true));
        DinnerStarters.add(new MenuItemC("Paneer tikka", 100, true));
    
        DinnerDesserts.add(new MenuItemC("Gulab Jamun", 50, true));
        DinnerDesserts.add(new MenuItemC("Barfi", 50, true));
        
        Dinner.add(DinnerStarters);
        Dinner.add(new MenuItemC("Naan", 40, true));
        Dinner.add(new MenuItemC("Butter Chicken", 200, false));
        Dinner.add(new MenuItemC("Pulao", 200, true));
        Dinner.add(DinnerDesserts);

        allmenus.add(BreakFast);
        allmenus.add(Dinner);
        allmenus.print();
    }
}

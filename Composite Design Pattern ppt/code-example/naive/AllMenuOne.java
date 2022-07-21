public class AllMenuOne {
    public static void main(String[] args){
        Menu BreakFastMenu = new Menu();
        Menu DinnerStartersMenu = new Menu();
        Menu DinnerMenu = new Menu();
        Menu DinnerDessertsMenu = new Menu();

        MenuItem item1 = new MenuItem("Paratha", 40, true);
        MenuItem item2 = new MenuItem("Omlette", 20, false);
        MenuItem item3 = new MenuItem("Milk", 20, true);

        MenuItem starter1 = new MenuItem("Soup", 50, true);
        MenuItem starter2 = new MenuItem("Paneer tikka", 100, true);

        MenuItem dinnerItem1 = new MenuItem("Naan", 40, true);
        MenuItem dinnerItem2 = new MenuItem("Butter Chicken", 200, false);
        MenuItem dinnerItem3 = new MenuItem("Pulao", 200, true);

        MenuItem dessertItem1 = new MenuItem("Gulab Jamun", 50, true);
        MenuItem dessertItem2 = new MenuItem("Barfi", 50, true);

        BreakFastMenu.addItem(item1);
        BreakFastMenu.addItem(item2);
        BreakFastMenu.addItem(item3);

        DinnerStartersMenu.addItem(starter1);
        DinnerStartersMenu.addItem(starter2);

        DinnerMenu.addItem(dinnerItem1);
        DinnerMenu.addItem(dinnerItem2);
        DinnerMenu.addItem(dinnerItem3);

        DinnerDessertsMenu.addItem(dessertItem1);
        DinnerDessertsMenu.addItem(dessertItem2);

        System.out.println("BreakFastMenu:");
        BreakFastMenu.printMenu();

        System.out.println("Dinner - Starters Menu:");
        DinnerStartersMenu.printMenu();

        System.out.println("Dinner - Maincourse:");
        DinnerMenu.printMenu();

        System.out.println("Dinner - Desserts Menu:");
        DinnerDessertsMenu.printMenu();
    }
}

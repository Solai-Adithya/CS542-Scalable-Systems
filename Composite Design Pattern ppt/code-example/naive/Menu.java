import java.util.ArrayList;
import java.util.Iterator;

public class Menu{
    private ArrayList<MenuItem> menu;

    public Menu(){
        this.menu = new ArrayList<MenuItem>();
    }

    public void addItem(MenuItem item){
        menu.add(item);
    }

    public void printMenu(){
        System.out.println("-------------------------------");
        Iterator<MenuItem> iterator = menu.iterator();

        while(iterator.hasNext()){
            MenuItem item = iterator.next();

            item.printItem();
        }
        System.out.println("-------------------------------");
    }

}

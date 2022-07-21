import java.util.ArrayList;
import java.util.Iterator;

public class MenuC extends MenuComponent {
    ArrayList<MenuComponent> menuComponents = new ArrayList<MenuComponent>();
    String name;

    public MenuC(String name){
        this.name = name;
    }

    public void add(MenuComponent menuComponent){
        menuComponents.add(menuComponent);
    }

    public void remove(MenuComponent menuComponent){
        menuComponents.remove(menuComponent);
    }

    public String getName(){
        return name;
    }

    public void print(){
        System.out.println("-------------------------------");
        System.out.println(getName());
        
        Iterator<MenuComponent> it = menuComponents.iterator();
        while(it.hasNext()){
            MenuComponent menuComponent = it.next();

            menuComponent.print();
        }
        System.out.println("-------------------------------");
    }
}

package Resteraunt;

import Adapter.MenuAdapter;
import InvokerItems.*;
import MealBuilder.EntreeBuilder;
import CommandClasses.Invoker;

import java.util.*;

public class SystemInterface {

    private static Invoker invoker;

    public static void init() {
        invoker = new Invoker(new Menu(), new Orders(), new Tab(), new Inventory(), new BurgerMenu(), new SandwichMenu(), new SaladMenu());
    }

    public static String[] printMenu() {
        Menu menu = invoker.getMenu();
        if(menu.totalMenuItems() == 0)
            menu.fillMenu();

        MenuItem item;
        Iterator<MenuItem> menuItr = menu.iterator();
        int i = 0;
        String[] lines = new String[menu.totalMenuItems()];
        while(menuItr.hasNext()) {
            item = menuItr.next();
            lines[i] = item.toString();
            i++;
        }
        return lines;
    }

    public static String[] printSubMenu(int x) {
        SubMenu subMenu;
        switch(x) {
            case 1:
                subMenu = invoker.getBurgerMenu();
                break;
            case 2:
                subMenu = invoker.getSandwichMenu();
                break;
            case 3:
                subMenu = invoker.getSaladMenu();
                break;
            default:
                subMenu = null;
                break;
        }

        if(subMenu.size() == 0)
            subMenu.fillMenu();

        String item;
        Iterator<SubMenuItem> subMenuItr = subMenu.iterator();
        int i = 0;
        String[] lines = new String[subMenu.size()];
        while(subMenuItr.hasNext()) {
            item = subMenuItr.next().toString();
            lines[i] = item;
            i++;
        }
        return lines;
    }

    public static String[] addOrder(int itemCode) {
        invoker.addOrders(itemCode);
        String[] lines = new String[2];
        lines[0] = "Order Successfully Added";
        lines[1] = "ItemNumber: " + itemCode;
        return lines;
    }

    public static String[] printTab() {
        Tab tab = invoker.getTab();
        String item;
        Iterator<String> tabItr = tab.iterator();
        int i = 0;
        String[] lines = new String[tab.getTotal()];
        while(tabItr.hasNext()) {
            item = tabItr.next();
            lines[i] = item;
            i++;
        }
        tab.clearTab();

        return lines;
    }

    public static String[] calcTotal() {
        Tab tab = invoker.getTab();
        Menu menu = invoker.getMenu();
        Orders order = invoker.getOrders();
        String[] lines = new String[1];
        lines[0] = tab.calcTotal(menu, order);

        return lines;
    }

    public static String[] invReport() {
        Inventory inventory = invoker.getInventory();
        InventoryItem item;
        int i = 0;
        if(inventory.getInventorySize() == 0)
            inventory.fillInventory();

        Iterator<InventoryItem> invItr = inventory.iterator();
        String[] lines = new String[inventory.getInventorySize()];

        while(invItr.hasNext()) {
            item = invItr.next();
            lines[i] = item.toString();
            i++;
        }
        return lines;
    }

    public static void adaptMenu(EntreeBuilder entree, int itemCode) {
        Menu menu = invoker.getMenu();
        Tab tab = invoker.getTab();
        MenuAdapter adapter = new MenuAdapter(entree, menu, tab);

        adapter.updateMenuItem(itemCode);
    }

    public static void updateInv(EntreeBuilder dish) {
        Inventory inventory = invoker.getInventory();

        if(inventory.getInventorySize() == 0)
            inventory.fillInventory();

        inventory.updateInv(dish);
    }

    public static void checkInv(EntreeBuilder dish, int x) {

        SubMenu subMenu;
        Inventory inventory = invoker.getInventory();

        switch(x) {
            case 1:
                subMenu = invoker.getBurgerMenu();
                break;
            case 2:
                subMenu = invoker.getSandwichMenu();
                break;
            case 3:
                subMenu = invoker.getSaladMenu();
                break;
            default:
                subMenu = null;
                break;
        }

        inventory.checkInventory(dish, subMenu);
    }
}

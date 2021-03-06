package InvokerItems;

import java.util.*;

public class SaladMenu implements SubMenu {
    private ArrayList<SubMenuItem> subMenu;

    public SaladMenu() {
        subMenu = new ArrayList<>();
    }

    public void fillMenu() {
        subMenu.add(new SubMenuItem(1,"Chicken Salad"));
        subMenu.add(new SubMenuItem(2,"Veggie Salad"));
    }

    public int size() {
        return subMenu.size();
    }

    public void removeItem(SubMenuItem subMenuItem) {
        subMenu.remove(subMenuItem);
    }

    public Iterator<SubMenuItem> iterator() {
        return new subMenuIterator();
    }

    public Iterator<SubMenuItem> itemIterator(String itemName) {
        return new itemIterator(itemName);
    }

    private class subMenuIterator implements Iterator<SubMenuItem> {
        private int index = 0;

        @Override
        public boolean hasNext() {
            return !(index >= subMenu.size() || subMenu.get(index) == null);
        }

        @Override
        public SubMenuItem next() {
            return subMenu.get(index++);
        }
    }

    private class itemIterator implements Iterator<SubMenuItem> {
        private int index = 0;
        private String itemName;

        public itemIterator(String itemName) {
            this.itemName = itemName;
        }

        public boolean checkArray() {
            if(subMenu.get(index).getItemName().equals(itemName)) {
                return true;
            }
            else {
                index++;
                return hasNext();
            }
        }

        public boolean hasNext() {
            return !(index >= subMenu.size() || subMenu.get(index) == null) && checkArray();
        }

        public SubMenuItem next() {
            return subMenu.get(index++);
        }
    }
}

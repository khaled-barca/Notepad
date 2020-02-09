package sample;

import java.util.HashMap;
import java.util.Map;

public enum EditMenu {
    UNDO, CUT, COPY, PASTE, DELETE, SELECT_ALL;

    private static final Map<String, EditMenu> map = new HashMap<>();
    static {
        for(EditMenu em : EditMenu.values()) {
            map.put(em.toString(), em);
        }
    }

    @Override
    public String toString() {
       String string = name();
       string = string.replaceAll("_", "");
       String firstLetter = string.substring(0,1).toUpperCase();
       return firstLetter + string.substring(1).toLowerCase();
    }

    public static EditMenu getEditMenu(String name) {
        return map.get(name);
    }
}

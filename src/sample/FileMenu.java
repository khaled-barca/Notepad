package sample;

import java.util.HashMap;
import java.util.Map;

enum FileMenu {
    NEW, OPEN, SAVE, EXIT;

    private static final Map<String, FileMenu> map = new HashMap<>();

    static {
        for (FileMenu fm : FileMenu.values()) {
            map.put(fm.toString(), fm);
        }
    }

    @Override
    public String toString() {
        String string = name();
        string = string.replaceAll("_", "");
        String firstLetter = string.substring(0, 1).toUpperCase();
        return firstLetter + string.substring(1).toLowerCase();
    }

    public static FileMenu getFileMenu(String name) {
        return map.get(name);
    }
}

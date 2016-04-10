package gl.mike.controller;

import java.util.Map;

public class View {
    private String name;
    private Map<String, Object> model;

    public View(String name) {
        this.name = name;
    }

    public View(String name, Map<String, Object> model) {
        this.name = name;
        this.model = model;
    }

    public String getName() {
        return name;
    }

    public Map<String, Object> getModel() {
        return model;
    }
}

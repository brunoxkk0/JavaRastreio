package br.com.brunoxkk0.jrastreio.core;

public class Event {

    private final String where;
    private final String action;

    public Event(String where, String action){
        this.where = where;
        this.action = action;
    }

    public String getAction() {
        return action;
    }

    public String getWhere() {
        return where;
    }

    @Override
    public String toString() {
        return "Event{" +
                "where='" + where + '\'' +
                ", action='" + action + '\'' +
                '}';
    }
}

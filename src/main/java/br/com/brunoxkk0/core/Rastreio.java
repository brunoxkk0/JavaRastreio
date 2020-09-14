package br.com.brunoxkk0.core;

import java.util.LinkedHashSet;

public class Rastreio {

    private final SRO sro;
    private final LinkedHashSet<Event> events;

    public Rastreio(SRO sro, LinkedHashSet<Event> events){
        this.sro = sro;
        this.events = events;
    }

    public LinkedHashSet<Event> getEvents() {
        return events;
    }

    public SRO getSro() {
        return sro;
    }

    public Event getLastEvent(){
        return events.iterator().next();
    }

    @Override
    public String toString() {
        return "Rastreio{" +
                "sro=" + sro +
                ", events=" + events +
                '}';
    }
}

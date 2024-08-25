package com.aklimets.pet.event;


public interface DomainEventAdapter {

    void send(DomainEvent event);
}

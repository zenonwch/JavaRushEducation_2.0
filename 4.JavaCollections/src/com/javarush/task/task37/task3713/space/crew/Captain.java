package com.javarush.task.task37.task3713.space.crew;

public class Captain extends AbstractCrewMember {
    public Captain (final AbstractCrewMember.CompetencyLevel competencyLevel) {
        this.competencyLevel = competencyLevel;
    }

    @Override
    protected void doTheJob(final String request) {
        System.out.println("Only the captain can handle request " + request + ". Let's do this!");
    }
}

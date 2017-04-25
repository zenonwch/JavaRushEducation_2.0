package com.javarush.task.task37.task3713.space.crew;

public class FirstMate extends AbstractCrewMember {
    public FirstMate (final AbstractCrewMember.CompetencyLevel competencyLevel) {
        this.competencyLevel = competencyLevel;
    }

    @Override
    protected void doTheJob(final String request) {
        System.out.println("The request " + request + " will be handled by first mate, let's not bother the captain with it.");
    }
}

package com.javarush.task.task37.task3713.space.crew;

public class CabinBoy extends AbstractCrewMember {
    public CabinBoy (final AbstractCrewMember.CompetencyLevel competencyLevel) {
        this.competencyLevel = competencyLevel;
    }

    @Override
    protected void doTheJob(final String request) {
        System.out.println("Even the cabin boy can handle the request " + request);
    }
}

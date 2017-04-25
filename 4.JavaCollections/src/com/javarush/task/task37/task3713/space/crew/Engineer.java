package com.javarush.task.task37.task3713.space.crew;

public class Engineer extends AbstractCrewMember {
    public Engineer (AbstractCrewMember.CompetencyLevel competencyLevel) {
        this.competencyLevel = competencyLevel;
    }

    @Override
    protected void doTheJob(final String request) {
        System.out.println(request + " is a common engineering task. To the work!");
    }
}

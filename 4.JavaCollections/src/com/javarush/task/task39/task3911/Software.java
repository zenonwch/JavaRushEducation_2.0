package com.javarush.task.task39.task3911;

import java.util.*;

public class Software {
    private int currentVersion;

    private final Map<Integer, String> versionHistoryMap = new LinkedHashMap<>();

    public void addNewVersion(final int version, final String description) {
        if (version > currentVersion) {
            versionHistoryMap.put(version, description);
            currentVersion = version;
        }
    }

    public int getCurrentVersion() {
        return currentVersion;
    }

    public Map<Integer, String> getVersionHistoryMap() {
        return Collections.unmodifiableMap(versionHistoryMap);
    }

    public boolean rollback(final int rollbackVersion) {
        if (versionHistoryMap.get(rollbackVersion) == null) return false;

        for (int i = currentVersion; i > rollbackVersion; i--) {
            versionHistoryMap.remove(i);
        }
        currentVersion = rollbackVersion;
        return true;
    }
}

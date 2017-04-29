package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.IPQuery;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class LogParser implements IPQuery {
    private final Path logDir;

    public LogParser(final Path logDir) {
        this.logDir = logDir;
    }

    @Override
    public int getNumberOfUniqueIPs(final Date after, final Date before) {
        return getUniqueIPs(after, before).size();
    }

    @Override
    public Set<String> getUniqueIPs(final Date after, final Date before) {
        return getLogsForPeriod(after, before).stream()
                .map(log -> log.ip)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getIPsForUser(final String user, final Date after, final Date before) {
        return getLogsForPeriod(after, before).stream()
                .filter(log -> log.user.equals(user))
                .map(log -> log.ip)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getIPsForEvent(final Event event, final Date after, final Date before) {
        return getLogsForPeriod(after, before).stream()
                .filter(log -> log.event == event)
                .map(log -> log.ip)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getIPsForStatus(final Status status, final Date after, final Date before) {
        return getLogsForPeriod(after, before).stream()
                .filter(log -> log.status == status)
                .map(log -> log.ip)
                .collect(Collectors.toSet());
    }

    private Set<Log> getLogsForPeriod(final Date after, final Date before) {
        return collectLogs().stream()
                .filter(log -> log.date.getTime() >= (after == null ? 0 : after.getTime())
                        && log.date.getTime() <= (before == null ? Long.MAX_VALUE : before.getTime()))
                .collect(Collectors.toSet());
    }

    private List<String> collectAllLogLines() {
        final List<String> logLines = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(logDir, "*.log")) {
            for (final Path path : stream) {
                logLines.addAll(Files.readAllLines(path));
            }
        } catch (final IOException ignored) {
        }
        return logLines;
    }

    private List<Log> collectLogs() {
        return collectAllLogLines().stream()
                .map(this::convertLineToLog)
                .collect(Collectors.toList());
    }

    private Log convertLineToLog(final String logLine) {
        final String[] logData = logLine.split("\\s");
        final int l = logData.length;

        final String ip = logData[0];
        final Status status = Status.valueOf(logData[l - 1]);

        final Event event;
        final String stringTime;
        final String stringDate;
        if (Character.isDigit(logData[l - 2].charAt(0))) {
            event = Event.valueOf(logData[l - 3]);
            stringTime = logData[l - 4];
            stringDate = logData[l - 5];
        } else {
            event = Event.valueOf(logData[l - 2]);
            stringTime = logData[l - 3];
            stringDate = logData[l - 4];
        }
        Date date = null;
        try {
            date = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse(stringDate + ' ' + stringTime);
        } catch (final ParseException ignore) {

        }

        final String user = logLine.substring(ip.length() + 1, logLine.indexOf(stringDate) - 1);
        return new Log(ip, user, date, event, status);
    }

    private static final class Log {
        private final String ip;
        private final String user;
        private final Date date;
        private final Event event;
        private final Status status;

        private Log(final String ip, final String user, final Date date,
                    final Event event, final Status status) {
            this.ip = ip;
            this.user = user;
            this.date = date;
            this.event = event;
            this.status = status;
        }

        public String getIp() {
            return ip;
        }

        public String getUser() {
            return user;
        }

        public Date getDate() {
            return date;
        }

        public Event getEvent() {
            return event;
        }

        public Status getStatus() {
            return status;
        }
    }
}
package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.IPQuery;
import com.javarush.task.task39.task3913.query.UserQuery;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class LogParser implements IPQuery, UserQuery {
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

    @Override
    public Set<String> getAllUsers() {
        return collectLogs().stream()
                .map(log -> log.user)
                .collect(Collectors.toSet());
    }

    @Override
    public int getNumberOfUsers(final Date after, final Date before) {
        return getLogsForPeriod(after, before).stream()
                .map(log -> log.user)
                .collect(Collectors.toSet())
                .size();
    }

    @Override
    public int getNumberOfUserEvents(final String user, final Date after, final Date before) {
        return getLogsForPeriod(after, before).stream()
                .filter(log -> log.user.equals(user))
                .map(log -> log.event)
                .collect(Collectors.toSet())
                .size();
    }

    @Override
    public Set<String> getUsersForIP(final String ip, final Date after, final Date before) {
        return getLogsForPeriod(after, before).stream()
                .filter(log -> log.ip.equals(ip))
                .map(log -> log.user)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getLoggedUsers(final Date after, final Date before) {
        return getUsersByEvent(after, before, Event.LOGIN);
    }

    @Override
    public Set<String> getDownloadedPluginUsers(final Date after, final Date before) {
        return getUsersByEvent(after, before, Event.DOWNLOAD_PLUGIN);
    }

    @Override
    public Set<String> getWroteMessageUsers(final Date after, final Date before) {
        return getUsersByEvent(after, before, Event.WRITE_MESSAGE);
    }

    @Override
    public Set<String> getSolvedTaskUsers(final Date after, final Date before) {
        return getUsersByEvent(after, before, Event.SOLVE_TASK);
    }

    @Override
    public Set<String> getSolvedTaskUsers(final Date after, final Date before, final int task) {
        return getLogsForPeriod(after, before).stream()
                .filter(log -> log.event == Event.SOLVE_TASK && log.task == task)
                .map(log -> log.user)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getDoneTaskUsers(final Date after, final Date before) {
        return getUsersByEvent(after, before, Event.DONE_TASK);
    }

    @Override
    public Set<String> getDoneTaskUsers(final Date after, final Date before, final int task) {
        return getLogsForPeriod(after, before).stream()
                .filter(log -> log.event == Event.DONE_TASK && log.task == task)
                .map(log -> log.user)
                .collect(Collectors.toSet());
    }

    private Set<String> getUsersByEvent(final Date after, final Date before, final Event event) {
        return getLogsForPeriod(after, before).stream()
                .filter(log -> log.event == event)
                .map(log -> log.user)
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
        final Integer task;
        final String stringTime;
        final String stringDate;
        if (Character.isDigit(logData[l - 2].charAt(0))) {
            task = Integer.parseInt(logData[l - 2]);
            event = Event.valueOf(logData[l - 3]);
            stringTime = logData[l - 4];
            stringDate = logData[l - 5];
        } else {
            task = null;
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
        return new Log(ip, user, date, event, task, status);
    }

    private static final class Log {
        private final String ip;
        private final String user;
        private final Date date;
        private final Event event;
        private final Integer task;
        private final Status status;

        private Log(final String ip, final String user, final Date date,
                    final Event event, final Integer task, final Status status) {
            this.ip = ip;
            this.user = user;
            this.date = date;
            this.event = event;
            this.task = task;
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

        public Integer getTask() {
            return task;
        }

        public Status getStatus() {
            return status;
        }
    }
}
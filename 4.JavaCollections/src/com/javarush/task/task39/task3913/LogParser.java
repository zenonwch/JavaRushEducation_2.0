package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.*;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class LogParser implements IPQuery, UserQuery, DateQuery, EventQuery, QLQuery {
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

    @Override
    public Set<Date> getDatesForUserAndEvent(final String user, final Event event, final Date after, final Date before) {
        return getLogsForPeriod(after, before).stream()
                .filter(log -> log.event == event && log.user.equals(user))
                .map(log -> log.date)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Date> getDatesWhenSomethingFailed(final Date after, final Date before) {
        return getLogsForPeriod(after, before).stream()
                .filter(log -> log.status == Status.FAILED)
                .map(log -> log.date)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Date> getDatesWhenErrorHappened(final Date after, final Date before) {
        return getLogsForPeriod(after, before).stream()
                .filter(log -> log.status == Status.ERROR)
                .map(log -> log.date)
                .collect(Collectors.toSet());
    }

    @Override
    public Date getDateWhenUserLoggedFirstTime(final String user, final Date after, final Date before) {
        return getDatesForUserAndEvent(user, Event.LOGIN, after, before).stream()
                .sorted()
                .findFirst()
                .orElse(null);
    }

    @Override
    public Date getDateWhenUserSolvedTask(final String user, final int task, final Date after, final Date before) {
        return getLogsForPeriod(after, before).stream()
                .filter(log -> log.event == Event.SOLVE_TASK && log.task == task && log.user.equals(user))
                .map(log -> log.date)
                .sorted()
                .findFirst()
                .orElse(null);
    }

    @Override
    public Date getDateWhenUserDoneTask(final String user, final int task, final Date after, final Date before) {
        return getLogsForPeriod(after, before).stream()
                .filter(log -> log.event == Event.DONE_TASK && log.task == task && log.user.equals(user))
                .map(log -> log.date)
                .sorted()
                .findFirst()
                .orElse(null);
    }

    @Override
    public Set<Date> getDatesWhenUserWroteMessage(final String user, final Date after, final Date before) {
        return getDatesForUserAndEvent(user, Event.WRITE_MESSAGE, after, before);
    }

    @Override
    public Set<Date> getDatesWhenUserDownloadedPlugin(final String user, final Date after, final Date before) {
        return getDatesForUserAndEvent(user, Event.DOWNLOAD_PLUGIN, after, before);
    }

    @Override
    public int getNumberOfAllEvents(final Date after, final Date before) {
        return getAllEvents(after, before).size();
    }

    @Override
    public Set<Event> getAllEvents(final Date after, final Date before) {
        return getLogsForPeriod(after, before).stream()
                .map(log -> log.event)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Event> getEventsForIP(final String ip, final Date after, final Date before) {
        return getLogsForPeriod(after, before).stream()
                .filter(log -> log.ip.equals(ip))
                .map(log -> log.event)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Event> getEventsForUser(final String user, final Date after, final Date before) {
        return getLogsForPeriod(after, before).stream()
                .filter(log -> log.user.equals(user))
                .map(log -> log.event)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Event> getFailedEvents(final Date after, final Date before) {
        return getLogsForPeriod(after, before).stream()
                .filter(log -> log.status == Status.FAILED)
                .map(log -> log.event)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Event> getErrorEvents(final Date after, final Date before) {
        return getLogsForPeriod(after, before).stream()
                .filter(log -> log.status == Status.ERROR)
                .map(log -> log.event)
                .collect(Collectors.toSet());
    }

    @Override
    public int getNumberOfAttemptToSolveTask(final int task, final Date after, final Date before) {
        return getLogsForPeriod(after, before).stream()
                .filter(log -> log.event == Event.SOLVE_TASK && log.task == task)
                .collect(Collectors.toSet())
                .size();
    }

    @Override
    public int getNumberOfSuccessfulAttemptToSolveTask(final int task, final Date after, final Date before) {
        return getLogsForPeriod(after, before).stream()
                .filter(log -> log.event == Event.DONE_TASK && log.task == task)
                .collect(Collectors.toSet())
                .size();
    }

    @Override
    public Map<Integer, Integer> getAllSolvedTasksAndTheirNumber(final Date after, final Date before) {
        return getLogsForPeriod(after, before).stream()
                .filter(log -> log.event == Event.SOLVE_TASK)
                .collect(Collectors.groupingBy(Log::getTask, Collectors.summingInt(i -> 1)));
    }

    @Override
    public Map<Integer, Integer> getAllDoneTasksAndTheirNumber(final Date after, final Date before) {
        return getLogsForPeriod(after, before).stream()
                .filter(log -> log.event == Event.DONE_TASK)
                .collect(Collectors.groupingBy(Log::getTask, Collectors.summingInt(i -> 1)));
    }

    @Override
    public Set<Object> execute(final String query) {
        if (query.contains("=")) {
            return executeQueryWithParam(query);
        }

        final Set<Object> result = new HashSet<>();
        switch (query) {
            case "get ip":
                result.addAll(getUniqueIPs(null, null));
                break;
            case "get user":
                result.addAll(getAllUsers());
                break;
            case "get date":
                result.addAll(getAllDates());
                break;
            case "get event":
                result.addAll(getAllEvents(null, null));
                break;
            case "get status":
                result.addAll(getAllStatuses());
                break;
            default:
                throw new UnsupportedOperationException();
        }
        return result;
    }

    private Set<Object> executeQueryWithParam(final String query) {
        final String param1Name = query.split("\\s")[1];
        final String param2Name = query.split("\\s")[3];

        try {
            final Field param1 = Log.class.getDeclaredField(param1Name);
            final Field param2 = Log.class.getDeclaredField(param2Name);
            param1.setAccessible(true);
            param2.setAccessible(true);

            final String valueBegin = query.substring(query.indexOf("\"") + 1);
            final String param2Value = valueBegin.substring(0, valueBegin.indexOf("\""));

            return collectLogs().stream()
                    .filter(log -> {
                        try {
                            return dataComparator(param2.get(log), param2Name, param2Value);
                        } catch (final IllegalAccessException ignored) {
                            return false;
                        }
                    })
                    .map(log -> {
                        try {
                            return param1.get(log);
                        } catch (final IllegalAccessException ignored) {
                            return false;
                        }
                    })
                    .collect(Collectors.toSet());
        } catch (final NoSuchFieldException ignored) {
            return null;
        }
    }

    private boolean dataComparator(final Object obj, final String paramName, final String paramValue) {
        Object value = null;
        switch (paramName) {
            case "date":
                try {
                    value = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse(paramValue);
                } catch (final ParseException ignored) {
                }
                break;
            case "event":
                value = Event.valueOf(paramValue);
                break;
            case "status":
                value = Status.valueOf(paramValue);
                break;
            default:
                value = paramValue;
                break;
        }

        return Objects.equals(obj, value);
    }

    private Set<Date> getAllDates() {
        return collectLogs().stream()
                .map(log -> log.date)
                .collect(Collectors.toSet());
    }

    private Set<Status> getAllStatuses() {
        return collectLogs().stream()
                .map(log -> log.status)
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
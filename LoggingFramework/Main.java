package LoggingFramework;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Log levels
enum LogLevel {
    INFO, DEBUG, ERROR
}

// Output target for logs (can add FileLogger, HttpLogger, etc.)
interface LoggerTarget {
    void write(String formattedMessage);
}

// Console logger implementation
class ConsoleLogger implements LoggerTarget {
    @Override
    public void write(String formattedMessage) {
        System.out.println(formattedMessage);
    }
}

// Base handler for chain of responsibility
abstract class AbstractLogProcessor {
    protected AbstractLogProcessor next;
    protected final LoggerTarget target;

    protected AbstractLogProcessor(LoggerTarget target) {
        this.target = target;
    }

    public AbstractLogProcessor setNext(AbstractLogProcessor next) {
        this.next = next;
        return next;
    }

    public final void log(LogLevel level, String message) {
        if (!handle(level, message) && next != null) {
            next.log(level, message);
        }
    }

    // Each concrete processor decides whether to handle
    protected abstract boolean handle(LogLevel level, String message);

    protected String prefix(LogLevel level) {
        String ts = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        return "[" + ts + "][" + level.name() + "] ";
    }
}

// Info logger
class InfoLog extends AbstractLogProcessor {
    public InfoLog(LoggerTarget target) {
        super(target);
    }

    @Override
    protected boolean handle(LogLevel level, String message) {
        if (level == LogLevel.INFO) {
            target.write(prefix(level) + message);
            return true;
        }
        return false;
    }
}

// Debug logger
class DebugLog extends AbstractLogProcessor {
    public DebugLog(LoggerTarget target) {
        super(target);
    }

    @Override
    protected boolean handle(LogLevel level, String message) {
        if (level == LogLevel.DEBUG) {
            target.write(prefix(level) + message);
            return true;
        }
        return false;
    }
}

// Error logger
class ErrorLog extends AbstractLogProcessor {
    public ErrorLog(LoggerTarget target) {
        super(target);
    }

    @Override
    protected boolean handle(LogLevel level, String message) {
        if (level == LogLevel.ERROR) {
            target.write(prefix(level) + message);
            return true;
        }
        return false;
    }
}

// Terminal no-op logger to avoid null checks (optional)
class NullLog extends AbstractLogProcessor {
    public NullLog(LoggerTarget target) {
        super(target);
    }

    @Override
    protected boolean handle(LogLevel level, String message) {
        // Could write a fallback or metrics here; no-op by default
        return true; // Treat as handled to stop chain silently
    }
}

// Facade for easy usage
class Logger {
    private final AbstractLogProcessor chain;

    private Logger(AbstractLogProcessor chain) {
        this.chain = chain;
    }

    public static Logger createDefault() {
        LoggerTarget console = new ConsoleLogger();
        // Chain: Error -> Debug -> Info -> Null
        AbstractLogProcessor error = new ErrorLog(console);
        AbstractLogProcessor debug = new DebugLog(console);
        AbstractLogProcessor info  = new InfoLog(console);
        AbstractLogProcessor terminal = new NullLog(console);

        error.setNext(debug).setNext(info).setNext(terminal);
        return new Logger(error);
    }

    public static Logger create(LogLevel... order) {
        LoggerTarget console = new ConsoleLogger();
        AbstractLogProcessor head = null, tail = null;

        for (LogLevel lvl : order) {
            AbstractLogProcessor node = switch (lvl) {
                case INFO -> new InfoLog(console);
                case DEBUG -> new DebugLog(console);
                case ERROR -> new ErrorLog(console);
            };
            if (head == null) {
                head = node;
                tail = node;
            } else {
                tail = tail.setNext(node);
            }
        }
        if (tail != null) tail.setNext(new NullLog(console));
        return new Logger(head != null ? head : new NullLog(console));
    }

    public void info(String msg)  { chain.log(LogLevel.INFO, msg); }
    public void debug(String msg) { chain.log(LogLevel.DEBUG, msg); }
    public void error(String msg) { chain.log(LogLevel.ERROR, msg); }

    // Generic method if needed
    public void log(LogLevel level, String msg) { chain.log(level, msg); }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("Enhanced logging framework");

        // Default: Error -> Debug -> Info -> Null
        Logger logger = Logger.createDefault();
        logger.info("Service started");
        logger.debug("Cache warmed");
        logger.error("DB connection failed");

        // Custom order example
        Logger custom = Logger.create(LogLevel.INFO, LogLevel.ERROR, LogLevel.DEBUG);
        custom.log(LogLevel.INFO,  "Custom: info");
        custom.log(LogLevel.ERROR, "Custom: error");
        custom.log(LogLevel.DEBUG, "Custom: debug");
    }
}

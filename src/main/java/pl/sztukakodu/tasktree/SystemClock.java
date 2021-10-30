package pl.sztukakodu.tasktree;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

//@Component -> registered by class with @Configuration
public class SystemClock implements Clock {

    @Override
    public LocalDateTime time() {
        return LocalDateTime.now();
    }
}

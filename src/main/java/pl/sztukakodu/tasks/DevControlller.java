package pl.sztukakodu.tasks;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Profile("dev")
@RestController
@RequestMapping("/dev")
public class DevControlller {
    @GetMapping
    public String devOnly() {
        return "Hello from dev profile";
    }
}

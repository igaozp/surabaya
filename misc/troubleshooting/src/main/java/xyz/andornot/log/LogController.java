package xyz.andornot.log;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("log")
public class LogController {

    @RequestMapping("print")
    public String print(@RequestParam(name = "info") String info) {
        return "Print some log and info:" + info;
    }

    @RequestMapping("printJson")
    public String printJson(@RequestBody String info) {
        return "Print some log and json info:" + info;
    }
}

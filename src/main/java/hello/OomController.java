package hello;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OomController {

    @RequestMapping("/begin")
    public String greeting(@RequestParam(value="increment", defaultValue="5") String increment, @RequestParam(value="sleep", defaultValue="1") String sleep) {
        (new Thread(new OomGenerator(getHostName(), "/logs/oom.log", Integer.valueOf(increment), Integer.valueOf(sleep)))).start();
        return "started";
    }

    private String getHostName() {
        InetAddress ip;
        String hostname;
        try {
            ip = InetAddress.getLocalHost();
            hostname = ip.getHostName();
        } catch (UnknownHostException e) {
            return "Unknown: " + e.getMessage();
        }

        return hostname;
    }
}

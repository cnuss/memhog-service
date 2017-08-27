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
    public String greeting(@RequestParam(value="bytes", defaultValue="1") String bytes, 
                           @RequestParam(value="increment", defaultValue="2") String increment, 
                           @RequestParam(value="sleep", defaultValue="1.0") String sleep) {
        (new Thread(new OomGenerator(getHostName(), Integer.valueOf(bytes), Integer.valueOf(increment), Double.valueOf(sleep)))).start();
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

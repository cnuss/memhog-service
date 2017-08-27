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
    public String begin(@RequestParam(value="bytes", defaultValue="1") String bytes, 
                           @RequestParam(value="increment", defaultValue="2") String increment, 
                           @RequestParam(value="sleep", defaultValue="1.0") String sleep,
                           @RequestParam(value="cpuHeavy", defaultValue="false") String cpuHeavy,
                           @RequestParam(value="numThreads", defaultValue="1") String numThreads) {
        String returnVal = "";

        OomGenerator oomGenerator = new OomGenerator(getHostName(), Integer.valueOf(bytes), Integer.valueOf(increment), Double.valueOf(sleep), Boolean.valueOf(cpuHeavy));

        returnVal += oomGenerator.toString() + "\n";

        for (int threadNumber = 0; threadNumber < Integer.valueOf(numThreads); threadNumber++) {
            Thread thread = new Thread(oomGenerator);
            thread.start();

            returnVal += "started: " + thread.toString() + "\n";
        }

        return returnVal;
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

package pro.sky.hogwarts3_6.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/info")
public class InfoController {

    final private int port;

    public InfoController(@Value("${server.port}")int port) {
        this.port= port;
    }

    @GetMapping("/getPort")
    public int getPort(){
        return port;
    }
}

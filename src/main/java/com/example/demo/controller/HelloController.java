package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@RestController
@CrossOrigin("http://localhost:3000")
public class HelloController {
    @RequestMapping(value="/hello/{id}/{name}",method= RequestMethod.GET)
    @ResponseBody
    public String sayHello(@PathVariable("id") Integer id, @PathVariable("name") String name){
        StringBuilder res = new StringBuilder();
        try {
            Process process = Runtime.getRuntime().exec("python3 /Users/liuqin/PycharmProjects/stockBot/speculator1.py");
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                res.append(line);
                System.out.println(line);
            }
            in.close();
            int re = process.waitFor();
            System.out.println(re);
        }catch (Exception e){
            e.printStackTrace();
        }
        return res.toString();
    }
}

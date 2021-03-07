package com.example.fs.controller.streaming;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/streaming")
@RequiredArgsConstructor
public class Streaming {

    /**
     * 参考:https://qiita.com/kazuki43zoo/items/53b79fe91c41cc5c2e59#streamingresponsebody%E3%81%AE%E5%88%A9%E7%94%A8
     */
    @GetMapping
    public StreamingResponseBody get(@RequestParam(defaultValue = "1") long eventNumber, @RequestParam(defaultValue = "0") long intervalSec) {
        System.out.println("Start get.");
        // StreamingResponseBodyのwriteToメソッドの中に非同期処理を実装する
        // StreamingResponseBodyは関数型インターフェースなのでJava SE 8+ならラムダ式が使えます。
        StreamingResponseBody responseBody = outputStream -> {
            System.out.println("Start Async processing.");

            if (intervalSec == 999) {
                throw new IllegalStateException("Special parameter for confirm error.");
            }

            for (long i = 1; i <= eventNumber; i++) {
                try {
                    TimeUnit.SECONDS.sleep(intervalSec);
                } catch (InterruptedException e) {
                    Thread.interrupted();
                }
                outputStream.write(("msg" + i + "\r\n").getBytes());
                outputStream.flush();
            }

            System.out.println("End Async processing.");
        };
        System.out.println("End get.");
        return responseBody;
    }
}

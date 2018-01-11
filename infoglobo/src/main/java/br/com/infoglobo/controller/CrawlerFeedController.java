package br.com.infoglobo.controller;


import br.com.infoglobo.entity.Feed;
import br.com.infoglobo.service.CrawlerService;
import com.sun.syndication.io.FeedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class CrawlerFeedController {


    private static final Logger log = LoggerFactory.getLogger(CrawlerFeedController.class);

    @Autowired
    private CrawlerService crawlerService;


    @RequestMapping("/capturarFeed")
    public ResponseEntity<Feed> capturarFeedsCrawler() throws IOException, FeedException {
        Feed feed = crawlerService.capturarFeedsCrawler();

        if(feed == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        log.debug("Feeds capturados com sucesso.");
        return new ResponseEntity<>(feed, HttpStatus.OK);
    }


    @ExceptionHandler({ IOException.class, FeedException.class })
    public void handleException() {
        log.debug("Erro ao tentar capturar os feeds.");
    }

}

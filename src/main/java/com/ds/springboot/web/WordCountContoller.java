package com.ds.springboot.web;


import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.ds.springboot.service.WordCountService;


@RestController
public class WordCountContoller {
	

	private WordCountService wordCountService;
	
	@RequestMapping(method=RequestMethod.POST,value="/wordcount",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String,Integer> fibSeries(HttpEntity<String> httpEntity)
	{   		
		wordCountService = new WordCountService(httpEntity.getBody());
		return wordCountService.getWordsToCount();
		
	}

}

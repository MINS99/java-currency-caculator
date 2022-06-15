package com.calculator.currency.application;

import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Component
public class CurrencyScheduler {

    private static final String BASE_URL = "https://api.apilayer.com/currency_data/live";
    @Value("${access_key}")
    private String ACCESS_KEY;
    private static final String SOURCE = "USD";
    private static final String CURRENCIES = "KRW,JPY,PHP";

    Logger logger = LoggerFactory.getLogger(CurrencyScheduler.class);

    private final CountryService countryService;

    public CurrencyScheduler(CountryService countryService) {
        this.countryService = countryService;
    }

    @Scheduled(fixedDelay = 3600000)
    @Transactional
    public void sendLiveRequest() throws ParseException {
        logger.info("sendLiveRequest start");
        String url = BASE_URL + "?source=" + SOURCE + "&currencies=" + CURRENCIES;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("apikey", ACCESS_KEY);

        HttpEntity<?> httpEntity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);

        String response = responseEntity.getBody();
        //String response = "{\"success\":true,\"source\":\"USD\",\"timestamp\":1655208663,\"quotes\":{\"USDJPY\":134.501964,\"USDKRW\":1288.509974,\"USDPHP\":53.262021}}";
        parsingData(response);
    }

    private void parsingData(String response) throws ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(response);

        String result = jsonObject.get("success").toString();
        if (result.equals("false")) {
            logger.info("sendLiveRequest ==> no data");
            return;
        }

        Map<String, Double> map = new HashMap<>();
        JSONObject data = (JSONObject) jsonObject.get("quotes");
        for (Object country : data.keySet()) {
            map.put(country.toString().substring(3, 6), Double.parseDouble(data.get(country).toString()));
        }

        countryService.updateCurrency(map);
        logger.info("sendLiveRequest ==> " + map.size() + " items change");
    }
}

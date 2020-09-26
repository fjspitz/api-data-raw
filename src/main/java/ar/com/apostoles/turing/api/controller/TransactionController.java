package ar.com.apostoles.turing.api.controller;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import ar.com.apostoles.turing.api.model.ResponseBySalesPerDateDto;
import ar.com.apostoles.turing.api.model.ResponseBySalesPerWeekDto;
import ar.com.apostoles.turing.api.model.Transaction;
import ar.com.apostoles.turing.api.model.TransactionPerWeek;
import ar.com.apostoles.turing.api.service.TransactionService;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin
@RestController
@Slf4j
public class TransactionController {

  @Autowired
  private TransactionService transactionService;

  @ResponseBody
  @GetMapping("/sales")
  public ResponseEntity<ResponseBySalesPerDateDto> findBySKUAndLocal(
      @RequestParam("sku") long sku, 
      @RequestParam("local") long local,
      @RequestParam("desde") String desde, 
      @RequestParam("hasta") String hasta) {
    
    Timestamp since = convertStringToTimestamp(desde);
    Timestamp to = convertStringToTimestamp(hasta);

    try {
      ResponseBySalesPerDateDto response = new ResponseBySalesPerDateDto();
      
      List<Transaction> sales = transactionService.findBySkuAndLocal(sku, local, since, to);
      
      response.setSales(sales);
      response.setRows(sales.size());
      
      return new ResponseEntity<>(response, HttpStatus.OK);
      
    } catch (EmptyResultDataAccessException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "SKU ID not found", e);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing parameters!", e);
    }
  }
  
  @ResponseBody
  @GetMapping("/sales/week")
  public ResponseEntity<ResponseBySalesPerWeekDto> findBySKUAndLocalGroupByWeek(
      @RequestParam("sku") long sku,
      @RequestParam("local") long local,
      @RequestParam(name = "desde", required = false) String desde, 
      @RequestParam(name = "hasta", required = false) String hasta) {
    
    Timestamp since = null;
    Timestamp to = null;
    
    if (desde != null && hasta != null) {
      since = convertStringToTimestamp(desde);
      to = convertStringToTimestamp(hasta);
    }
    
    List<TransactionPerWeek> sales = new ArrayList<>();
    
    try {
      ResponseBySalesPerWeekDto response = new ResponseBySalesPerWeekDto();
      
      if (since != null && to != null) {
        sales = transactionService.findBySkuAndLocalPerWeek(sku, local, since, to);
      } else {
        sales = transactionService.findAllBySkuAndLocalPerWeek(sku, local);
      }
      
      response.setSales(sales);
      response.setRows(sales.size());
      
      return new ResponseEntity<>(response, HttpStatus.OK);
      
    } catch (EmptyResultDataAccessException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "SKU ID not found", e);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing parameters!", e);
    }
  }
  
  public static Timestamp convertStringToTimestamp(String strDate) {
    try {
      DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
       // you can change format of date
      Date date = formatter.parse(strDate);

      return new Timestamp(date.getTime());
    } catch (ParseException e) {
      log.error("Exception: {}", e);
      return null;
    }
  }
}

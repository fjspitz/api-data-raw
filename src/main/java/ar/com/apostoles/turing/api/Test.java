package ar.com.apostoles.turing.api;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class Test {

  public static void main(String[] args) {
    int numero = 0;
    Random aleatorio;
    aleatorio = new Random();
    
    Calendar unaFecha;
    
    for (int i = 0; i < 100; i++) {
      unaFecha = Calendar.getInstance();
      unaFecha.set(aleatorio.nextInt(3)+2017, aleatorio.nextInt(12)+1, aleatorio.nextInt(30)+1);
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      //System.out.println("La fecha vale " + sdf.format(unaFecha.getTime()));
      System.out.println("'" + sdf.format(unaFecha.getTime()) + "'");
    }
  }
}

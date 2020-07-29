package br.com.jawsys.camel.twitter.demo;


public class LogMessage {

  public void log(String status) {
    System.out.println("--- new message ---");
    System.out.println(status);
  }

}

package com.jeremias.paddlechampion.mapper.exception;

public class RepeatedPlayer extends RuntimeException {

  public RepeatedPlayer(String error) {
    super(error);
  }
}
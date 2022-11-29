package com.jeremias.paddlechampion.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageDto <T>{
  private List<T> content;
  private Map<String,String> links = new HashMap<>();
}

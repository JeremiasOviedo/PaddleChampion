package com.jeremias.paddlechampion.service;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public interface IUtilService {
    String makePaginationLink(HttpServletRequest request, int page);
}

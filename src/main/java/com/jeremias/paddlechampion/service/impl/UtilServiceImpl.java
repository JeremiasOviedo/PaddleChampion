package com.jeremias.paddlechampion.service.impl;

import com.jeremias.paddlechampion.service.IUtilService;

import javax.servlet.http.HttpServletRequest;

public class UtilServiceImpl implements IUtilService {

    public String makePaginationLink(HttpServletRequest request, int page) {
        return String.format("%s?page=%d", request.getRequestURI(), page);
    }
}

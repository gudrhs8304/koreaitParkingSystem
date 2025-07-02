package com.koreait.koreaitparkingsystem.controller;

import com.koreait.koreaitparkingsystem.service.EntryService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/entry.do")
public class EntryController extends HttpServlet {

    private final EntryService entryService = new EntryService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}

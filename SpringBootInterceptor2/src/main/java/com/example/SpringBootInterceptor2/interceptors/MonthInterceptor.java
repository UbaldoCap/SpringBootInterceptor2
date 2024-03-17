package com.example.SpringBootInterceptor2.interceptors;

import com.example.SpringBootInterceptor2.entities.Month;
import com.sun.net.httpserver.Headers;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
public class MonthInterceptor implements HandlerInterceptor {
    static List<Month> monthList = new ArrayList<>(Arrays.asList(
            new Month(1, "aa", "bb", "cc"),
            new Month(2, "ba", "mm", "ww"),
            new Month(3, "vv", "ws", "po"),
            new Month(4, "qq", "sd", "ll"),
            new Month(5, "jj", "hh", "gg"),
            new Month(6, "ty", "nb", "ss")
    ));
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String headers = request.getHeader("month");
        if (headers == null || headers.isEmpty()) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return false;
        }
        Month month = find(Integer.valueOf(headers));
        request.setAttribute("month", Objects.requireNonNullElseGet(month, () -> new Month(null, "nope", "nope", "nope")));
        response.setStatus(HttpStatus.OK.value());
        return true;
    }

    private Month find(Integer integer) {
        for (Month month : monthList) {
            if (month.getMonthNumber().equals(integer)) {
                return month;
            }
        }
        return null;
    }
}

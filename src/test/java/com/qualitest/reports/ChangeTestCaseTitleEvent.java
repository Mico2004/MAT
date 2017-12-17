package com.qualitest.reports;

import ru.yandex.qatools.allure.events.TestCaseEvent;
import ru.yandex.qatools.allure.model.TestCaseResult;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ChangeTestCaseTitleEvent implements TestCaseEvent {
    private String titlePrefix;

    public ChangeTestCaseTitleEvent(String titlePrefix) {
        this.titlePrefix = titlePrefix;
    }

    @Override
    public void process(TestCaseResult context) {

        Date date = new Date();   // given date

        Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance

        context.setTitle(titlePrefix + context.getName());

        context.setStart(System.currentTimeMillis());

    }
}

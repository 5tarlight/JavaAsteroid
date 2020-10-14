package com.github.yeahx4.util;

import java.util.Calendar;

@SuppressWarnings("unused")
public class Logger {
    String serviceName;

    public String getServiceName() {
        return serviceName;
    }

    public Logger() {
        this.serviceName = "DefaultServiceName";
    }

    public Logger(String serviceName) {
        this.serviceName = serviceName;
    }

    protected String trim(int num) {
        return num < 10 ? "0" + num : Integer.toString(num);
    }

    protected String getTimeStamp() {
        return String.format("[%s:%s:%s]",
                this.trim(Calendar.HOUR_OF_DAY),
                this.trim(Calendar.MINUTE),
                this.trim(Calendar.SECOND)
        );
    }

    protected void printLog(Color color, String msg) {
        System.out.println(color + "[" + this.serviceName + "] " + this.getTimeStamp() + " " + msg + Color.RESET);
    }

    public void log(String msg) {
        printLog(Color.WHITE, msg);
    }

    public void err(String msg) {
        printLog(Color.RED, msg);
    }

    public void success(String msg) {
        printLog(Color.GREEN, msg);
    }
}

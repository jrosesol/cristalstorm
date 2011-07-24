package com.cristal.storm.prototype.shared;

public class TooManyResultsException extends Exception {

    public TooManyResultsException()
    {
            super();
    }

    public TooManyResultsException(Throwable t)
    {
            super(t);
    }

    public TooManyResultsException(String msg)
    {
            super(msg);
    }

    public TooManyResultsException(String msg, Throwable t)
    {
            super(msg, t);
    }
}

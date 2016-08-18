package com.example.administrator.pull;

import java.io.InputStream;
import java.util.List;

public interface BookParser {
    public List<Book> parse(InputStream is) throws Exception;
    public String serialize(List<Book> books) throws Exception;
}

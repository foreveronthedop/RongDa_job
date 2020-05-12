package com.llm.crawl.link;

public interface LinkFilter {
    public boolean accept(String url);
}

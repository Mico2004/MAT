package com.qualitest.pages;

public interface PageInterface {

    // [M.E] - initialize all page elements,  the function should be included as part of every "waitForPageToLoad()" function
    public void initializeElements();

    public  void waitForPageToLoad();

    
}

package com.mycompany.app;

import org.junit.Assert;
import org.junit.Test;

public class AppTest {
    @Test
    public void fancyAdd_shouldSumTwoNumbers() {
        Assert.assertEquals(2, App.fancyAdd(1, 1));
    }
}

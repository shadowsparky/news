package ru.shadowsparky.news;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.spy;

public class RequesterTest {
    Requester requester;
    @Before
    public void setUp() throws Exception {
        requester = spy(new Requester());
    }

    @Test
    public void getInstance() {
        assertNotNull(requester.getInstance());
    }

    @Test
    public void getApi() {
        assertNotNull(requester.getApi());
    }
}
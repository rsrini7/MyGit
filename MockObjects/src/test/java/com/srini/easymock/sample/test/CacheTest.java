package com.srini.easymock.sample.test;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import java.util.Map;

import junit.framework.TestCase;

import com.srini.easymock.sample.Cache;
public class CacheTest extends TestCase {
    public void testMethodWithReturnValue() {
        int expectedValue = 42;
        //Map mockStorage = EasyMock.createMockBuilder(Map.class).createMock();
        Map mockStorage = createMock(Map.class);
        expect(mockStorage.size()).andReturn(expectedValue);
        replay(mockStorage);
        Cache sut = new Cache(mockStorage);
        Object actualValue = sut.size();
        assertSame(expectedValue, actualValue);
        verify(mockStorage);
    }
}
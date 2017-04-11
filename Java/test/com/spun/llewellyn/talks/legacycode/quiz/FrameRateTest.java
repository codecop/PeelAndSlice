package com.spun.llewellyn.talks.legacycode.quiz;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.spun.llewellyn.talks.legacycode.required.VideoMaker;
import com.sun.net.httpserver.HttpContext;

public class FrameRateTest
{
  @Test
  public void countDownNtsc()
  {
    HttpContext web = mock(HttpContext.class);
    Map<String, Object> attributes = new HashMap<String, Object>();
    attributes.put("TvFormat", "ntsc");
    when(web.getAttributes()).thenReturn(attributes);
    VideoMaker maker = mock(VideoMaker.class);
    
    FrameRate frameRate = new FrameRate();
    frameRate.countdown(web, maker);
    
    verify(maker, times(30)).createFrame(anyString());
  }
}

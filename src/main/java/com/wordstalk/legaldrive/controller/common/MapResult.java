package com.wordstalk.legaldrive.controller.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by y on 2018/1/7.
 */
public class MapResult extends BaseResult<Map<String, Object>>{

    public MapResult(Head header) {
        super(header);
        data = new HashMap();
    }

    public MapResult(Head header, int size) {
        super(header);
        data = new HashMap(size);
    }

    public void putData(String key, Object value) {
        data.put(key, value);
    }

}

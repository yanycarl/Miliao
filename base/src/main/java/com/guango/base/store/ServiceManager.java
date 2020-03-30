package com.guango.base.store;

import com.guango.base.Interface.IService;

import java.util.HashMap;
import java.util.Map;

public class ServiceManager {

    private Map<String, IService> mServiceMap;

    public void addService(String name, IService service){
        if(mServiceMap == null){
            mServiceMap = new HashMap<>();
        }
        mServiceMap.put(name, service);
    }

    public IService getService(String name){
        return mServiceMap.get(name);
    }
}

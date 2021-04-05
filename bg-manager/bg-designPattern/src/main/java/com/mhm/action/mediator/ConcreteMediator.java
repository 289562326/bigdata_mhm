package com.mhm.action.mediator;

import java.util.ArrayList;
import java.util.List;

/**
 * 具体中介者
 * @author MHm
 * @date 2020-4-26 21:16
 */
public class ConcreteMediator implements Mediator{
    private List<Colleague> colleagues = new ArrayList<Colleague>();
    @Override
    public void register(Colleague colleague) {
        if(!colleagues.contains(colleague)){
            colleagues.add(colleague);
            colleague.setMediator(this);
        }
    }

    @Override
    public void dispath(Colleague colleague) {
        for(Colleague b:colleagues){
            if(!b.equals(colleague)){
                b.receive();
            }
        }
    }
}

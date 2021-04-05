package com.mhm.action.interpreter;


import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author MHm
 * @date 2020-4-20 18:57
 */
public class ExpressionContext {
    private Map valueMap = new HashMap();
    public void addValue(Variable x , int y)
    {
        Integer yi = new Integer(y);
        valueMap.put(x , yi);
    }

    public int lookupValue(Variable x)
    {
        int i = ((Integer)valueMap.get(x)).intValue();
        return i ;
    }
}

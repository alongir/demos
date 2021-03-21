package com.up9.generated;

import com.up9.up9lib.AuthenticationInterface;
import com.up9.up9lib.HttpTarget;

public class Authentication implements AuthenticationInterface
{
    public void authenticate(HttpTarget httpTarget)
    {
        if (httpTarget.targetKey == "TARGET_CARTS")
        {
        }
        else if (httpTarget.targetKey == "TARGET_CATALOGUE")
        {
        }
        else if (httpTarget.targetKey == "TARGET_FRONT_END")
        {
        }
        else if (httpTarget.targetKey == "TARGET_ORDERS")
        {
        }
        else if (httpTarget.targetKey == "TARGET_PAYMENT")
        {
        }
        else if (httpTarget.targetKey == "TARGET_SHIPPING")
        {
        }
        else if (httpTarget.targetKey == "TARGET_USER")
        {
        }
    }
}


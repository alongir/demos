package com.up9.generated;

import com.up9.up9lib.AuthenticationInterface;
import com.up9.up9lib.HttpTarget;

public class Authentication implements AuthenticationInterface
{
    public void authenticate(HttpTarget httpTarget)
    {
        if (httpTarget.baseURL == "http://carts.sock-shop")
        {
        }
        else if (httpTarget.baseURL == "http://catalogue.sock-shop")
        {
        }
        else if (httpTarget.baseURL == "http://front-end.sock-shop")
        {
        }
        else if (httpTarget.baseURL == "kafka://kafka")
        {
        }
        else if (httpTarget.baseURL == "http://orders.sock-shop")
        {
        }
        else if (httpTarget.baseURL == "http://payment.sock-shop")
        {
        }
        else if (httpTarget.baseURL == "http://shipping.sock-shop")
        {
        }
        else if (httpTarget.baseURL == "http://user.sock-shop")
        {
        }
    }
}


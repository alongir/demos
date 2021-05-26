package com.up9.generated;

import com.up9.up9lib.AuthenticationInterface;
import com.up9.up9lib.HttpTarget;

public class Authentication implements AuthenticationInterface
{
    public void authenticate(HttpTarget httpTarget)
    {
        if (httpTarget.baseURL == "http://carts")
        {
        }
        else if (httpTarget.baseURL == "http://catalogue")
        {
        }
        else if (httpTarget.baseURL == "http://front-end")
        {
        }
        else if (httpTarget.baseURL == "http://orders")
        {
        }
        else if (httpTarget.baseURL == "http://payment")
        {
        }
        else if (httpTarget.baseURL == "http://shipping")
        {
        }
        else if (httpTarget.baseURL == "http://user")
        {
        }
    }
}


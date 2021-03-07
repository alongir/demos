const authenticate = require("./authentication");
const {CSSselect, JSONPath, clearSession, dataset, getHttpTarget, urlencode} = require("./up9lib");

it("test_01_get_", () => {
    clearSession();

    // GET http://front-end/ (endp 1)
    const front_end = getHttpTarget("TARGET_FRONT_END", authenticate);
    return front_end.fetch("/")
    .then((response) => {
        expect(response.status).toEqual(200);
        return response.text();
    })
    .then((text) => {
        expect(CSSselect("div#hot div.box div.container div h2", text)).toContain("Hot this week");
    })
    .then((data) => {
    });
});

describe.each(dataset("data/dataset_26.json"))("test_26_get_param", (param) => {
    it("test_26_get_param", () => {
        clearSession();

        // GET http://front-end/{param} (endp 26)
        const front_end = getHttpTarget("TARGET_FRONT_END", authenticate);
        return front_end.fetch("/" + param, {
            headers: {
                "x-requested-with": "XMLHttpRequest"
            }
        })
        .then((response) => {
            expect(response.status).toEqual(200);
            return response.text();
        })
        .then((text) => {
        })
        .then((data) => {
        });
    });
});

it("test_02_get_cart", () => {
    clearSession();

    // GET http://front-end/cart (endp 2)
    const front_end = getHttpTarget("TARGET_FRONT_END", authenticate);
    return front_end.fetch("/cart", {
        headers: {
            "x-requested-with": "XMLHttpRequest"
        }
    })
    .then((response) => {
        expect(response.status).toEqual(200);
        return response.text();
    })
    .then((text) => {
    })
    .then((data) => {
    });
});

describe.each(dataset("data/dataset_4.json"))("test_04_get_catalogue", (size) => {
    it("test_04_get_catalogue", () => {
        clearSession();

        // GET http://front-end/catalogue (endp 4)
        const front_end = getHttpTarget("TARGET_FRONT_END", authenticate);
        return front_end.fetch("/catalogue" + urlencode([["page", "1"], ["size", size], ["tags", ""]]), {
            headers: {
                "x-requested-with": "XMLHttpRequest"
            }
        })
        .then((response) => {
            expect(response.status).toEqual(200);
            return response.text();
        })
        .then((text) => {
        })
        .then((data) => {
        });
    });
});

it("test_03_get_catalogue_size", () => {
    clearSession();

    // GET http://front-end/catalogue/size (endp 3)
    const front_end = getHttpTarget("TARGET_FRONT_END", authenticate);
    return front_end.fetch("/catalogue/size" + urlencode([["tags", ""]]), {
        headers: {
            "x-requested-with": "XMLHttpRequest"
        }
    })
    .then((response) => {
        expect(response.status).toEqual(200);
        return response.text();
    })
    .then((text) => {
    })
    .then((data) => {
    });
});

describe.each(dataset("data/dataset_5.json"))("test_05_get_customers_customerId", (customerId) => {
    it("test_05_get_customers_customerId", () => {
        clearSession();

        // GET http://front-end/customers/{customerId} (endp 5)
        const front_end = getHttpTarget("TARGET_FRONT_END", authenticate);
        return front_end.fetch("/customers/" + customerId, {
            headers: {
                "x-requested-with": "XMLHttpRequest"
            }
        })
        .then((response) => {
            expect(response.status).toEqual(200);
            return response.text();
        })
        .then((text) => {
            return JSON.parse(text);
        })
        .then((data) => {
            expect(JSONPath({
                path: "$.lastName",
                json: data
            })).toContain("Name");
        });
    });
});

describe.each(dataset("data/dataset_30.json"))("test_30_get_customers_customerId", (customerId) => {
    it("test_30_get_customers_customerId", () => {
        clearSession();

        // GET http://front-end/customers/{customerId} (endp 30)
        const front_end = getHttpTarget("TARGET_FRONT_END", authenticate);
        return front_end.fetch("/customers/" + customerId, {
            headers: {
                "x-requested-with": "XMLHttpRequest"
            }
        })
        .then((response) => {
            expect(response.status).toEqual(200);
            return response.text();
        })
        .then((text) => {
        })
        .then((data) => {
        });
    });
});

it("test_23_get_footer_html", () => {
    clearSession();

    // GET http://front-end/footer.html (endp 23)
    const front_end = getHttpTarget("TARGET_FRONT_END", authenticate);
    return front_end.fetch("/footer.html", {
        headers: {
            "x-requested-with": "XMLHttpRequest"
        }
    })
    .then((response) => {
        expect(response.status).toEqual(200);
        return response.text();
    })
    .then((text) => {
        expect(CSSselect("div#copyright div.container div p.pull-left a", text)).toContain("Weaveworks");
    })
    .then((data) => {
    });
});

it("test_06_get_index_html", () => {
    clearSession();

    // GET http://front-end/index.html (endp 6)
    const front_end = getHttpTarget("TARGET_FRONT_END", authenticate);
    return front_end.fetch("/index.html")
    .then((response) => {
        expect(response.status).toEqual(200);
        return response.text();
    })
    .then((text) => {
        expect(CSSselect("div#hot div.box div.container div h2", text)).toContain("Hot this week");
    })
    .then((data) => {
    });
});

// authentication-related test
it("test_25_get_login", () => {
    clearSession();

    // GET http://front-end/login (endp 25)
    const front_end = getHttpTarget("TARGET_FRONT_END");
    return front_end.fetch("/login", {
        headers: {
            "x-requested-with": "XMLHttpRequest"
        }
    })
    .then((response) => {
        expect(response.status).toEqual(200);
        return response.text();
    })
    .then((text) => {
        expect(CSSselect("p", text)).toContain("Cookie is set");
    })
    .then((data) => {
    });
});

it("test_07_get_orders", () => {
    clearSession();

    // GET http://front-end/orders (endp 7)
    const front_end = getHttpTarget("TARGET_FRONT_END", authenticate);
    return front_end.fetch("/orders", {
        headers: {
            "x-requested-with": "XMLHttpRequest"
        }
    })
    .then((response) => {
        expect(response.status).toEqual(201);
        return response.text();
    })
    .then((text) => {
    })
    .then((data) => {
    });
});

it("test_08_get_tags", () => {
    clearSession();

    // GET http://front-end/tags (endp 8)
    const front_end = getHttpTarget("TARGET_FRONT_END", authenticate);
    return front_end.fetch("/tags", {
        headers: {
            "x-requested-with": "XMLHttpRequest"
        }
    })
    .then((response) => {
        expect(response.status).toEqual(200);
        return response.text();
    })
    .then((text) => {
    })
    .then((data) => {
    });
});

it("test_27_get_topbar_html", () => {
    clearSession();

    // GET http://front-end/topbar.html (endp 27)
    const front_end = getHttpTarget("TARGET_FRONT_END", authenticate);
    return front_end.fetch("/topbar.html", {
        headers: {
            "x-requested-with": "XMLHttpRequest"
        }
    })
    .then((response) => {
        expect(response.status).toEqual(200);
        return response.text();
    })
    .then((text) => {
        expect(CSSselect("div#top div.container div.offer a.btn.btn-success.btn-sm", text)).toContain("Offer of the day");
    })
    .then((data) => {
    });
});

const authenticate = require("./authentication");
const {CSSselect, JSONPath, clearSession, getHttpTarget, urlencode} = require("./up9lib");

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

it("test_26_get_param", () => {
    clearSession();

    // GET http://front-end/{param} (endp 26)
    const param = "navbar.html";
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

it("test_43_delete_cart", () => {
    clearSession();

    // DELETE http://front-end/cart (endp 43)
    const front_end = getHttpTarget("TARGET_FRONT_END", authenticate);
    return front_end.fetch("/cart", {
        method: "DELETE"
    })
    .then((response) => {
        expect(response.status).toEqual(202);
        return response.text();
    })
    .then((text) => {
    })
    .then((data) => {
    });
});

it("test_37_get_catalogue", () => {
    clearSession();

    // GET http://front-end/catalogue (endp 37)
    const size = "6";
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

it("test_36_get_catalogue_size", () => {
    clearSession();

    // GET http://front-end/catalogue/size (endp 36)
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

it("test_34_get_category_html", () => {
    clearSession();

    // GET http://front-end/category.html (endp 34)
    const front_end = getHttpTarget("TARGET_FRONT_END", authenticate);
    return front_end.fetch("/category.html")
    .then((response) => {
        expect(response.status).toEqual(200);
        return response.text();
    })
    .then((text) => {
        expect(CSSselect("div#content div.container div div.panel.panel-default.sidebar-menu div.panel-heading h3.panel-title", text)).toContain("Filters ");
    })
    .then((data) => {
    });
});

it("test_05_get_customers_customerId", () => {
    clearSession();

    // GET http://front-end/customers/{customerId} (endp 5)
    const customerId = "7w4TGI0pnIxn5TEFoHX6O2spPdXa3dut";
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

it("test_30_get_customers_customerId", () => {
    clearSession();

    // GET http://front-end/customers/{customerId} (endp 30)
    const customerId = "7w4TGI0pnIxn5TEFoHX6O2spPdXa3dut";
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

it("test_45_get_detail_html", () => {
    clearSession();

    // GET http://front-end/catalogue (endp 4)
    const size = "5";
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
        return JSON.parse(text);
    })
    .then((data) => {
        const id = JSONPath({
            path: "$[*].id",
            json: data
        })[0];

        // GET http://front-end/detail.html (endp 45)
        return front_end.fetch("/detail.html" + urlencode([["id", id]]))
        .then((response) => {
            expect(response.status).toEqual(200);
            return response.text();
        })
        .then((text) => {
            expect(CSSselect("div#content div.container div div.row.same-height-row div div.box.same-height h3", text)).toContain("You may also like these products");
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

it("test_38_get_tags", () => {
    clearSession();

    // GET http://front-end/tags (endp 38)
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

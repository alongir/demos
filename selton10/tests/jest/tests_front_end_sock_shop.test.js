const authenticate = require("./authentication");
const {CSSselect, JSONBuild, JSONPath, clearSession, dataset, getHttpClient, urlencode} = require("./up9lib");

it("test_001_get_", () => {
    clearSession();

    // GET http://front-end.sock-shop/ (endp 1)
    const front_end_sock_shop = getHttpClient("http://front-end.sock-shop", authenticate);
    return front_end_sock_shop.fetch("/")
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

it("test_066_get_address", () => {
    clearSession();

    // GET http://front-end.sock-shop/address (endp 66)
    const front_end_sock_shop = getHttpClient("http://front-end.sock-shop", authenticate);
    return front_end_sock_shop.fetch("/address", {
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
            path: "$.city",
            json: data
        })).toContain("Glasgow");
    });
});

it("test_067_get_card", () => {
    clearSession();

    // GET http://front-end.sock-shop/card (endp 67)
    const front_end_sock_shop = getHttpClient("http://front-end.sock-shop", authenticate);
    return front_end_sock_shop.fetch("/card", {
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

it("test_069_post_cart", () => {
    clearSession();

    // GET http://front-end.sock-shop/cart (endp 22)
    const front_end_sock_shop = getHttpClient("http://front-end.sock-shop", authenticate);
    return front_end_sock_shop.fetch("/cart", {
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
            path: "$[*].itemId",
            json: data
        })[0];

        // POST http://front-end.sock-shop/cart (endp 69)
        return front_end_sock_shop.fetch("/cart", {
            method: "POST",
            headers: {
                "content-type": "application/json",
                "x-requested-with": "XMLHttpRequest"
            },
            body: JSONBuild("data/payload_for_endp_69.json", {
                "$.id": id
            })
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
});

it("test_083_delete_cart", () => {
    clearSession();

    // DELETE http://front-end.sock-shop/cart (endp 83)
    const front_end_sock_shop = getHttpClient("http://front-end.sock-shop", authenticate);
    return front_end_sock_shop.fetch("/cart", {
        method: "DELETE",
        headers: {
            "x-requested-with": "XMLHttpRequest"
        }
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

describe.each(dataset("data/dataset_70.json"))("test_070_delete_cart_id", (size, tags) => {
    it("test_070_delete_cart_id", () => {
        clearSession();

        // GET http://front-end.sock-shop/catalogue (endp 23)
        const front_end_sock_shop = getHttpClient("http://front-end.sock-shop", authenticate);
        return front_end_sock_shop.fetch("/catalogue" + urlencode([["page", "1"], ["size", size], ["sort", "id"], ["tags", tags]]), {
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

            // DELETE http://front-end.sock-shop/cart/{id} (endp 70)
            return front_end_sock_shop.fetch("/cart/" + id, {
                method: "DELETE",
                headers: {
                    "x-requested-with": "XMLHttpRequest"
                }
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
    });
});

describe.each(dataset("data/dataset_71.json"))("test_071_get_catalogue_id", (size, tags) => {
    it("test_071_get_catalogue_id", () => {
        clearSession();

        // GET http://front-end.sock-shop/catalogue (endp 23)
        const front_end_sock_shop = getHttpClient("http://front-end.sock-shop", authenticate);
        return front_end_sock_shop.fetch("/catalogue" + urlencode([["page", "1"], ["size", size], ["sort", "id"], ["tags", tags]]), {
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

            // GET http://front-end.sock-shop/catalogue/{id} (endp 71)
            return front_end_sock_shop.fetch("/catalogue/" + id, {
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
});

it("test_025_get_catalogue_size", () => {
    clearSession();

    // GET http://front-end.sock-shop/tags (endp 28)
    const front_end_sock_shop = getHttpClient("http://front-end.sock-shop", authenticate);
    return front_end_sock_shop.fetch("/tags", {
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
        const tags = JSONPath({
            path: "$.tags[*]",
            json: data
        })[0];

        // GET http://front-end.sock-shop/catalogue/size (endp 25)
        return front_end_sock_shop.fetch("/catalogue/size" + urlencode([["tags", tags]]), {
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

it("test_027_get_category_html", () => {
    clearSession();

    // GET http://front-end.sock-shop/tags (endp 28)
    const front_end_sock_shop = getHttpClient("http://front-end.sock-shop", authenticate);
    return front_end_sock_shop.fetch("/tags", {
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
        const tags = JSONPath({
            path: "$.tags[*]",
            json: data
        })[0];

        // GET http://front-end.sock-shop/category.html (endp 27)
        return front_end_sock_shop.fetch("/category.html" + urlencode([["tags", tags]]))
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
});

describe.each(dataset("data/dataset_87.json"))("test_087_get_customer_order_html", (order) => {
    it("test_087_get_customer_order_html", () => {
        clearSession();

        // GET http://front-end.sock-shop/customer-order.html (endp 87)
        const front_end_sock_shop = getHttpClient("http://front-end.sock-shop", authenticate);
        return front_end_sock_shop.fetch("/customer-order.html" + urlencode([["order", order]]))
        .then((response) => {
            expect(response.status).toEqual(200);
            return response.text();
        })
        .then((text) => {
            expect(CSSselect("div#customer-order div.box h2", text)).toContain("Order #");
        })
        .then((data) => {
        });
    });
});

describe.each(dataset("data/dataset_75.json"))("test_075_get_customers_customerId", (customerId) => {
    it("test_075_get_customers_customerId", () => {
        clearSession();

        // GET http://front-end.sock-shop/customers/{customerId} (endp 75)
        const front_end_sock_shop = getHttpClient("http://front-end.sock-shop", authenticate);
        return front_end_sock_shop.fetch("/customers/" + customerId, {
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

describe.each(dataset("data/dataset_118.json"))("test_118_get_detail_html", (size, tags) => {
    it("test_118_get_detail_html", () => {
        clearSession();

        // GET http://front-end.sock-shop/catalogue (endp 23)
        const front_end_sock_shop = getHttpClient("http://front-end.sock-shop", authenticate);
        return front_end_sock_shop.fetch("/catalogue" + urlencode([["page", "1"], ["size", size], ["sort", "id"], ["tags", tags]]), {
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

            // GET http://front-end.sock-shop/detail.html (endp 118)
            return front_end_sock_shop.fetch("/detail.html" + urlencode([["id", id]]))
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
});

it("test_032_get_footer_html", () => {
    clearSession();

    // GET http://front-end.sock-shop/footer.html (endp 32)
    const front_end_sock_shop = getHttpClient("http://front-end.sock-shop", authenticate);
    return front_end_sock_shop.fetch("/footer.html", {
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

it("test_089_get_index_html", () => {
    clearSession();

    // GET http://front-end.sock-shop/index.html (endp 89)
    const front_end_sock_shop = getHttpClient("http://front-end.sock-shop", authenticate);
    return front_end_sock_shop.fetch("/index.html")
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

it("test_033_get_navbar_html", () => {
    clearSession();

    // GET http://front-end.sock-shop/navbar.html (endp 33)
    const front_end_sock_shop = getHttpClient("http://front-end.sock-shop", authenticate);
    return front_end_sock_shop.fetch("/navbar.html", {
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

it("test_090_get_orders", () => {
    clearSession();

    // GET http://front-end.sock-shop/orders (endp 90)
    const front_end_sock_shop = getHttpClient("http://front-end.sock-shop", authenticate);
    return front_end_sock_shop.fetch("/orders", {
        headers: {
            "x-requested-with": "XMLHttpRequest"
        }
    })
    .then((response) => {
        expect(response.status).toEqual(201);
        return response.text();
    })
    .then((text) => {
        return JSON.parse(text);
    })
    .then((data) => {
        expect(JSONPath({
            path: "$[*].address.city",
            json: data
        })).toContain("Glasgow");
    });
});

it("test_091_post_orders", () => {
    clearSession();

    // POST http://front-end.sock-shop/orders (endp 91)
    const front_end_sock_shop = getHttpClient("http://front-end.sock-shop", authenticate);
    return front_end_sock_shop.fetch("/orders", {
        method: "POST",
        headers: {
            "x-requested-with": "XMLHttpRequest"
        }
    })
    .then((response) => {
        expect(response.status).toEqual(201);
        return response.text();
    })
    .then((text) => {
        return JSON.parse(text);
    })
    .then((data) => {
        expect(JSONPath({
            path: "$.address.city",
            json: data
        })).toContain("Glasgow");
    });
});

describe.each(dataset("data/dataset_103.json"))("test_103_get_orders_orderId", (orderId) => {
    it("test_103_get_orders_orderId", () => {
        clearSession();

        // GET http://front-end.sock-shop/orders/{orderId} (endp 103)
        const front_end_sock_shop = getHttpClient("http://front-end.sock-shop", authenticate);
        return front_end_sock_shop.fetch("/orders/" + orderId, {
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
                path: "$.address.city",
                json: data
            })).toContain("Glasgow");
        });
    });
});

it("test_034_get_topbar_html", () => {
    clearSession();

    // GET http://front-end.sock-shop/topbar.html (endp 34)
    const front_end_sock_shop = getHttpClient("http://front-end.sock-shop", authenticate);
    return front_end_sock_shop.fetch("/topbar.html", {
        headers: {
            "x-requested-with": "XMLHttpRequest"
        }
    })
    .then((response) => {
        expect(response.status).toEqual(200);
        return response.text();
    })
    .then((text) => {
        expect(CSSselect("div#top div.container div.offer a.btn.btn-success", text)).toContain("Offer of the day");
    })
    .then((data) => {
    });
});

const authenticate = require("./authentication");
const {CSSselect, JSONBuild, JSONPath, clearSession, dataset, getHttpClient, urlencode} = require("./up9lib");

it("test_01_get_", () => {
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

it("test_02_get_address", () => {
    clearSession();

    // GET http://front-end.sock-shop/address (endp 2)
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

it("test_03_get_basket_html", () => {
    clearSession();

    // GET http://front-end.sock-shop/basket.html (endp 3)
    const front_end_sock_shop = getHttpClient("http://front-end.sock-shop", authenticate);
    return front_end_sock_shop.fetch("/basket.html")
    .then((response) => {
        expect(response.status).toEqual(200);
        return response.text();
    })
    .then((text) => {
        expect(CSSselect("div#basket div.box form h1", text)).toContain("Shopping cart");
    })
    .then((data) => {
    });
});

it("test_04_get_card", () => {
    clearSession();

    // GET http://front-end.sock-shop/card (endp 4)
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

it("test_06_delete_cart", () => {
    clearSession();

    // DELETE http://front-end.sock-shop/cart (endp 6)
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

it("test_07_post_cart", () => {
    clearSession();

    // POST http://front-end.sock-shop/orders (endp 16)
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
        const id = JSONPath({
            path: "$.items[*].itemId",
            json: data
        })[0];
        const quantity = JSONPath({
            path: "$.items[*].quantity",
            json: data
        })[0];

        // POST http://front-end.sock-shop/cart (endp 7)
        return front_end_sock_shop.fetch("/cart", {
            method: "POST",
            headers: {
                "content-type": "application/json",
                "x-requested-with": "XMLHttpRequest"
            },
            body: JSONBuild("data/payload_for_endp_7.json", {
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

describe.each(dataset("data/dataset_8.json"))("test_08_delete_cart_itemId", (size) => {
    it("test_08_delete_cart_itemId", () => {
        clearSession();

        // GET http://front-end.sock-shop/catalogue (endp 11)
        const front_end_sock_shop = getHttpClient("http://front-end.sock-shop", authenticate);
        return front_end_sock_shop.fetch("/catalogue" + urlencode([["page", "1"], ["size", size], ["tags", ""]]), {
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
            // GET http://front-end.sock-shop/cart (endp 5)
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
                const itemId = JSONPath({
                    path: "$[*].itemId",
                    json: data
                })[0];

                // DELETE http://front-end.sock-shop/cart/{itemId} (endp 8)
                return front_end_sock_shop.fetch("/cart/" + itemId, {
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
});

describe.each(dataset("data/dataset_9.json"))("test_09_get_catalogue_id", (size) => {
    it("test_09_get_catalogue_id", () => {
        clearSession();

        // GET http://front-end.sock-shop/catalogue (endp 11)
        const front_end_sock_shop = getHttpClient("http://front-end.sock-shop", authenticate);
        return front_end_sock_shop.fetch("/catalogue" + urlencode([["page", "1"], ["size", size], ["tags", ""]]), {
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

            // GET http://front-end.sock-shop/catalogue/{id} (endp 9)
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

it("test_10_get_catalogue_size", () => {
    clearSession();

    // GET http://front-end.sock-shop/catalogue/size (endp 10)
    const front_end_sock_shop = getHttpClient("http://front-end.sock-shop", authenticate);
    return front_end_sock_shop.fetch("/catalogue/size" + urlencode([["tags", ""]]), {
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

it("test_12_get_category_html", () => {
    clearSession();

    // GET http://front-end.sock-shop/category.html (endp 12)
    const front_end_sock_shop = getHttpClient("http://front-end.sock-shop", authenticate);
    return front_end_sock_shop.fetch("/category.html")
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

it("test_13_get_customers_customerId", () => {
    clearSession();

    // GET http://front-end.sock-shop/login (endp 15)
    const front_end_sock_shop = getHttpClient("http://front-end.sock-shop", authenticate);
    return front_end_sock_shop.fetch("/login", {
        headers: {
            "x-requested-with": "XMLHttpRequest"
        }
    })
    .then((response) => {
        expect(response.status).toEqual(200);
        const customerId = response.headers.raw()["set-cookie"][logged_in];
        return response.text();
    })
    .then((text) => {
        expect(CSSselect("p", text)).toContain("Cookie is set");
    })
    .then((data) => {
        // GET http://front-end.sock-shop/customers/{customerId} (endp 13)
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

it("test_14_get_detail_html", () => {
    clearSession();

    // POST http://front-end.sock-shop/orders (endp 16)
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
        const id = JSONPath({
            path: "$.items[*].itemId",
            json: data
        })[0];

        // GET http://front-end.sock-shop/detail.html (endp 14)
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

it("test_54_get_orders", () => {
    clearSession();

    // GET http://front-end.sock-shop/orders (endp 54)
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
    })
    .then((data) => {
    });
});

it("test_17_get_tags", () => {
    clearSession();

    // GET http://front-end.sock-shop/tags (endp 17)
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
    })
    .then((data) => {
    });
});

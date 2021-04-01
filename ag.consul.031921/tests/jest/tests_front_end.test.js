const authenticate = require("./authentication");
const {CSSselect, JSONBuild, JSONPath, clearSession, dataset, getHttpClient, urlPart, urlencode} = require("./up9lib");

it("test_01_get_", () => {
    clearSession();

    // GET http://front-end/ (endp 1)
    const front_end = getHttpClient("http://front-end", authenticate);
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

it("test_12_get_cart", () => {
    clearSession();

    // GET http://front-end/tags (endp 21)
    const front_end = getHttpClient("http://front-end", authenticate);
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
        return JSON.parse(text);
    })
    .then((data) => {
        const tags = JSONPath({
            path: "$.tags[*]",
            json: data
        })[0];

        // GET http://front-end/catalogue/size (endp 14)
        return front_end.fetch("/catalogue/size" + urlencode([["tags", tags]]), {
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
                path: "$.name",
                json: data
            })).toContain("Holy");

            // GET http://front-end/cart (endp 12)
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
    });
});

describe.each(dataset("data/dataset_15.json"))("test_15_get_catalogue", (size) => {
    it("test_15_get_catalogue", () => {
        clearSession();

        // GET http://front-end/tags (endp 21)
        const front_end = getHttpClient("http://front-end", authenticate);
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
            return JSON.parse(text);
        })
        .then((data) => {
            const tags = JSONPath({
                path: "$.tags[*]",
                json: data
            })[0];

            // GET http://front-end/catalogue (endp 15)
            return front_end.fetch("/catalogue" + urlencode([["page", "1"], ["size", size], ["tags", tags]]), {
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

describe.each(dataset("data/dataset_6.json"))("test_06_get_category_html", (size, tags) => {
    it("test_06_get_category_html", () => {
        clearSession();

        // GET http://front-end/category.html (endp 6)
        const front_end = getHttpClient("http://front-end", authenticate);
        return front_end.fetch("/category.html" + urlencode([["page", "1"], ["size", size], ["tags", tags]]))
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

describe.each(dataset("data/dataset_16.json"))("test_16_get_customer_order_html", (order) => {
    it("test_16_get_customer_order_html", () => {
        clearSession();

        // GET http://front-end/customer-order.html (endp 16)
        const front_end = getHttpClient("http://front-end", authenticate);
        return front_end.fetch("/customer-order.html" + urlencode([["order", order]]))
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

it("test_18_get_customers_customerId", () => {
    clearSession();

    // GET http://front-end/login (endp 8)
    const front_end = getHttpClient("http://front-end", authenticate);
    return front_end.fetch("/login", {
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
        // GET http://front-end/customers/{customerId} (endp 18)
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

it("test_44_get_customers_customerId", () => {
    clearSession();

    // GET http://front-end/login (endp 8)
    const front_end = getHttpClient("http://front-end", authenticate);
    return front_end.fetch("/login", {
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
        // GET http://front-end/customers/{customerId} (endp 44)
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

describe.each(dataset("data/dataset_7.json"))("test_07_get_detail_html", (size, tags) => {
    it("test_07_get_detail_html", () => {
        clearSession();

        // GET http://front-end/catalogue (endp 5)
        const front_end = getHttpClient("http://front-end", authenticate);
        return front_end.fetch("/catalogue" + urlencode([["page", "1"], ["size", size], ["sort", "id"], ["tags", tags]]), {
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

            // POST http://front-end/cart (endp 4)
            return front_end.fetch("/cart", {
                method: "POST",
                headers: {
                    "content-type": "application/json"
                },
                body: JSONBuild("data/payload_for_endp_4.json", {
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
                // GET http://front-end/basket.html (endp 2)
                return front_end.fetch("/basket.html")
                .then((response) => {
                    expect(response.status).toEqual(200);
                    return response.text();
                })
                .then((text) => {
                    expect(CSSselect("div#basket div.box form h1", text)).toContain("Shopping cart");
                })
                .then((data) => {
                    // DELETE http://front-end/cart (endp 3)
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
                        // POST http://front-end/orders (endp 9)
                        return front_end.fetch("/orders", {
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
                            const id1 = JSONPath({
                                path: "$.items[*].itemId",
                                json: data
                            })[0];

                            // GET http://front-end/detail.html (endp 7)
                            return front_end.fetch("/detail.html" + urlencode([["id", id1]]))
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
            });
        });
    });
});

describe.each(dataset("data/dataset_20.json"))("test_20_get_orders_href", (size, tags) => {
    it("test_20_get_orders_href", () => {
        clearSession();

        // GET http://front-end/catalogue (endp 5)
        const front_end = getHttpClient("http://front-end", authenticate);
        return front_end.fetch("/catalogue" + urlencode([["page", "1"], ["size", size], ["sort", "id"], ["tags", tags]]), {
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

            // GET http://front-end/catalogue/{id} (endp 13)
            return front_end.fetch("/catalogue/" + id, {
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
                // GET http://front-end/address (endp 10)
                return front_end.fetch("/address", {
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

                    // GET http://front-end/card (endp 11)
                    return front_end.fetch("/card", {
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
                        // GET http://front-end/customer-orders.html (endp 17)
                        return front_end.fetch("/customer-orders.html")
                        .then((response) => {
                            expect(response.status).toEqual(200);
                            return response.text();
                        })
                        .then((text) => {
                            expect(CSSselect("div#customer-orders div.box h1", text)).toContain("My orders");
                        })
                        .then((data) => {
                            // GET http://front-end/orders (endp 19)
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
                                return JSON.parse(text);
                            })
                            .then((data) => {
                                expect(JSONPath({
                                    path: "$[*].address.city",
                                    json: data
                                })).toContain("Glasgow");
                                const href = urlPart("/2", JSONPath({
                                    path: "$[*]._links.self.href",
                                    json: data
                                })[0]);

                                // GET http://front-end/orders/{href} (endp 20)
                                return front_end.fetch("/orders/" + href, {
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
                    });
                });
            });
        });
    });
});

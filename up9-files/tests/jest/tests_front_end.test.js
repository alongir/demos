const authenticate = require("./authentication");
const {CSSselect, JSONPath, clearSession, dataset, getHttpTarget, urlencode} = require("./up9lib");

it("test_09_get_", () => {
    clearSession();

    // GET http://front-end/ (endp 9)
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

it("test_10_get_basket_html", () => {
    clearSession();

    // GET http://front-end/basket.html (endp 10)
    const front_end = getHttpTarget("TARGET_FRONT_END", authenticate);
    return front_end.fetch("/basket.html")
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

it("test_12_get_category_html", () => {
    clearSession();

    // GET http://front-end/category.html (endp 12)
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

describe.each(dataset("data/dataset_13.json"))("test_13_get_detail_html", (size) => {
    it("test_13_get_detail_html", () => {
        clearSession();

        // GET http://front-end/catalogue (endp 11)
        const front_end = getHttpTarget("TARGET_FRONT_END", authenticate);
        return front_end.fetch("/catalogue" + urlencode([["size", size], ["sort", "id"], ["tags", "brown"]]), {
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
                path: "$.[*].id",
                json: data
            })[0];

            // GET http://front-end/detail.html (endp 13)
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
});

it("test_22_get_cart", () => {
    clearSession();

    // GET http://front-end/cart (endp 22)
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

describe.each(dataset("data/dataset_23.json"))("test_23_get_catalogue_id", (size) => {
    it("test_23_get_catalogue_id", () => {
        clearSession();

        // GET http://front-end/catalogue (endp 11)
        const front_end = getHttpTarget("TARGET_FRONT_END", authenticate);
        return front_end.fetch("/catalogue" + urlencode([["size", size], ["sort", "id"], ["tags", "brown"]]), {
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
                path: "$.[*].id",
                json: data
            })[0];

            // GET http://front-end/catalogue/{id} (endp 23)
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
                return JSON.parse(text);
            })
            .then((data) => {
                expect(JSONPath({
                    path: "$.imageUrl.[*]",
                    json: data
                })).toContain("/catalogue/images/colourful_socks.jpg");
            });
        });
    });
});

describe.each(dataset("data/dataset_25.json"))("test_25_get_customers_customerId", (customerId) => {
    it("test_25_get_customers_customerId", () => {
        clearSession();

        // GET http://front-end/customers/{customerId} (endp 25)
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

it("test_26_get_index_html", () => {
    clearSession();

    // GET http://front-end/index.html (endp 26)
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

const authenticate = require("./authentication");
const {CSSselect, JSONPath, clearSession, dataset, getHttpTarget, urlencode} = require("./up9lib");

it("test_15_get_", () => {
    clearSession();

    // GET http://edge-router/ (endp 15)
    const edge_router = getHttpTarget("TARGET_EDGE_ROUTER", authenticate);
    return edge_router.fetch("/")
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

it("test_16_get_basket_html", () => {
    clearSession();

    // GET http://edge-router/basket.html (endp 16)
    const edge_router = getHttpTarget("TARGET_EDGE_ROUTER", authenticate);
    return edge_router.fetch("/basket.html")
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

it("test_18_get_category_html", () => {
    clearSession();

    // GET http://edge-router/category.html (endp 18)
    const edge_router = getHttpTarget("TARGET_EDGE_ROUTER", authenticate);
    return edge_router.fetch("/category.html")
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

describe.each(dataset("data/dataset_19.json"))("test_19_get_detail_html", (size) => {
    it("test_19_get_detail_html", () => {
        clearSession();

        // GET http://edge-router/catalogue (endp 17)
        const edge_router = getHttpTarget("TARGET_EDGE_ROUTER", authenticate);
        return edge_router.fetch("/catalogue" + urlencode([["size", size], ["sort", "id"], ["tags", "brown"]]), {
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

            // GET http://edge-router/detail.html (endp 19)
            return edge_router.fetch("/detail.html" + urlencode([["id", id]]))
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

it("test_27_get_cart", () => {
    clearSession();

    // GET http://edge-router/cart (endp 27)
    const edge_router = getHttpTarget("TARGET_EDGE_ROUTER", authenticate);
    return edge_router.fetch("/cart", {
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

describe.each(dataset("data/dataset_28.json"))("test_28_get_catalogue_id", (size) => {
    it("test_28_get_catalogue_id", () => {
        clearSession();

        // GET http://edge-router/catalogue (endp 17)
        const edge_router = getHttpTarget("TARGET_EDGE_ROUTER", authenticate);
        return edge_router.fetch("/catalogue" + urlencode([["size", size], ["sort", "id"], ["tags", "brown"]]), {
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

            // GET http://edge-router/catalogue/{id} (endp 28)
            return edge_router.fetch("/catalogue/" + id, {
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

describe.each(dataset("data/dataset_30.json"))("test_30_get_customers_customerId", (customerId) => {
    it("test_30_get_customers_customerId", () => {
        clearSession();

        // GET http://edge-router/customers/{customerId} (endp 30)
        const edge_router = getHttpTarget("TARGET_EDGE_ROUTER", authenticate);
        return edge_router.fetch("/customers/" + customerId, {
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

it("test_31_get_index_html", () => {
    clearSession();

    // GET http://edge-router/index.html (endp 31)
    const edge_router = getHttpTarget("TARGET_EDGE_ROUTER", authenticate);
    return edge_router.fetch("/index.html")
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

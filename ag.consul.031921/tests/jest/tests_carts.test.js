const authenticate = require("./authentication");
const {JSONBuild, JSONPath, clearSession, dataset, getHttpClient, urlencode} = require("./up9lib");

it("test_22_delete_carts_customerId", () => {
    clearSession();

    // GET http://user/login (endp 32)
    const user = getHttpClient("http://user", authenticate);
    return user.fetch("/login")
    .then((response) => {
        expect(response.status).toEqual(200);
        return response.text();
    })
    .then((text) => {
        return JSON.parse(text);
    })
    .then((data) => {
        expect(JSONPath({
            path: "$.user.lastName",
            json: data
        })).toContain("Name");
        const customerId = JSONPath({
            path: "$.user.id",
            json: data
        })[0];

        // DELETE http://carts/carts/{customerId} (endp 22)
        const carts = getHttpClient("http://carts", authenticate);
        return carts.fetch("/carts/" + customerId, {
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
});

describe.each(dataset("data/dataset_23.json"))("test_23_post_carts_customerId_items", (size) => {
    it("test_23_post_carts_customerId_items", () => {
        clearSession();

        // GET http://catalogue/tags (endp 30)
        const catalogue = getHttpClient("http://catalogue", authenticate);
        return catalogue.fetch("/tags")
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

            // GET http://catalogue/catalogue (endp 29)
            return catalogue.fetch("/catalogue" + urlencode([["page", "1"], ["size", size], ["sort", "id"], ["tags", tags]]))
            .then((response) => {
                expect(response.status).toEqual(200);
                return response.text();
            })
            .then((text) => {
                return JSON.parse(text);
            })
            .then((data) => {
                const itemId = JSONPath({
                    path: "$[*].id",
                    json: data
                })[0];
                const unitPrice = JSONPath({
                    path: "$[*].price",
                    json: data
                })[0];

                // GET http://user/login (endp 32)
                const user = getHttpClient("http://user", authenticate);
                return user.fetch("/login")
                .then((response) => {
                    expect(response.status).toEqual(200);
                    return response.text();
                })
                .then((text) => {
                    return JSON.parse(text);
                })
                .then((data) => {
                    expect(JSONPath({
                        path: "$.user.lastName",
                        json: data
                    })).toContain("Name");
                    const customerId = JSONPath({
                        path: "$.user.id",
                        json: data
                    })[0];

                    // POST http://carts/carts/{customerId}/items (endp 23)
                    const carts = getHttpClient("http://carts", authenticate);
                    return carts.fetch("/carts/" + customerId + "/items", {
                        method: "POST",
                        headers: {
                            "accept": "application/json",
                            "content-type": "application/json"
                        },
                        body: JSONBuild("data/payload_for_endp_23.json", {
                            "$.itemId": itemId,
                            "$.unitPrice": unitPrice
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
        });
    });
});

it("test_26_get_carts_customerId_items", () => {
    clearSession();

    // GET http://user/login (endp 32)
    const user = getHttpClient("http://user", authenticate);
    return user.fetch("/login")
    .then((response) => {
        expect(response.status).toEqual(200);
        return response.text();
    })
    .then((text) => {
        return JSON.parse(text);
    })
    .then((data) => {
        expect(JSONPath({
            path: "$.user.lastName",
            json: data
        })).toContain("Name");
        const customerId = JSONPath({
            path: "$.user.id",
            json: data
        })[0];

        // GET http://carts/carts/{customerId}/items (endp 26)
        const carts = getHttpClient("http://carts", authenticate);
        return carts.fetch("/carts/" + customerId + "/items", {
            headers: {
                "accept": "application/json"
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

describe.each(dataset("data/dataset_24.json"))("test_24_get_carts_customerId_merge", (sessionId) => {
    it("test_24_get_carts_customerId_merge", () => {
        clearSession();

        // GET http://user/login (endp 32)
        const user = getHttpClient("http://user", authenticate);
        return user.fetch("/login")
        .then((response) => {
            expect(response.status).toEqual(200);
            return response.text();
        })
        .then((text) => {
            return JSON.parse(text);
        })
        .then((data) => {
            expect(JSONPath({
                path: "$.user.lastName",
                json: data
            })).toContain("Name");
            const customerId = JSONPath({
                path: "$.user.id",
                json: data
            })[0];

            // GET http://carts/carts/{customerId}/merge (endp 24)
            const carts = getHttpClient("http://carts", authenticate);
            return carts.fetch("/carts/" + customerId + "/merge" + urlencode([["sessionId", sessionId]]))
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

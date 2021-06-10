const authenticate = require("./authentication");
const {JSONBuild, JSONPath, clearSession, dataset, getHttpClient, urlencode} = require("./up9lib");

describe.each(dataset("data/dataset_32.json"))("test_32_delete_carts_customerId", (size) => {
    it("test_32_delete_carts_customerId", () => {
        clearSession();

        // GET http://catalogue.sock-shop/tags (endp 27)
        const catalogue_sock_shop = getHttpClient("http://catalogue.sock-shop", authenticate);
        return catalogue_sock_shop.fetch("/tags")
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

            // GET http://catalogue.sock-shop/catalogue (endp 24)
            return catalogue_sock_shop.fetch("/catalogue" + urlencode([["page", "1"], ["size", size], ["sort", "id"], ["tags", tags]]))
            .then((response) => {
                expect(response.status).toEqual(200);
                return response.text();
            })
            .then((text) => {
            })
            .then((data) => {
                // GET http://user.sock-shop/login (endp 31)
                const user_sock_shop = getHttpClient("http://user.sock-shop", authenticate);
                return user_sock_shop.fetch("/login")
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

                    // DELETE http://carts.sock-shop/carts/{customerId} (endp 32)
                    const carts_sock_shop = getHttpClient("http://carts.sock-shop", authenticate);
                    return carts_sock_shop.fetch("/carts/" + customerId, {
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
        });
    });
});

describe.each(dataset("data/dataset_33.json"))("test_33_post_carts_customerId_items", (sessionId, size) => {
    it("test_33_post_carts_customerId_items", () => {
        clearSession();

        // GET http://catalogue.sock-shop/tags (endp 27)
        const catalogue_sock_shop = getHttpClient("http://catalogue.sock-shop", authenticate);
        return catalogue_sock_shop.fetch("/tags")
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

            // GET http://catalogue.sock-shop/catalogue (endp 24)
            return catalogue_sock_shop.fetch("/catalogue" + urlencode([["page", "1"], ["size", size], ["sort", "id"], ["tags", tags]]))
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
                const id = JSONPath({
                    path: "$[*].id",
                    json: data
                })[0];

                // GET http://user.sock-shop/login (endp 31)
                const user_sock_shop = getHttpClient("http://user.sock-shop", authenticate);
                return user_sock_shop.fetch("/login")
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

                    // GET http://carts.sock-shop/carts/{customerId}/merge (endp 35)
                    const carts_sock_shop = getHttpClient("http://carts.sock-shop", authenticate);
                    return carts_sock_shop.fetch("/carts/" + customerId + "/merge" + urlencode([["sessionId", sessionId]]))
                    .then((response) => {
                        expect(response.status).toEqual(202);
                        return response.text();
                    })
                    .then((text) => {
                    })
                    .then((data) => {
                        // GET http://catalogue.sock-shop/catalogue/{id} (endp 25)
                        return catalogue_sock_shop.fetch("/catalogue/" + id)
                        .then((response) => {
                            expect(response.status).toEqual(200);
                            return response.text();
                        })
                        .then((text) => {
                            return JSON.parse(text);
                        })
                        .then((data) => {
                            const unitPrice = JSONPath({
                                path: "$.price",
                                json: data
                            })[0];

                            // POST http://carts.sock-shop/carts/{customerId}/items (endp 33)
                            return carts_sock_shop.fetch("/carts/" + customerId + "/items", {
                                method: "POST",
                                headers: {
                                    "accept": "application/json",
                                    "content-type": "application/json"
                                },
                                body: JSONBuild("data/payload_for_endp_33.json", {
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
    });
});

it("test_34_delete_carts_customerId_items_itemId", () => {
    clearSession();

    // GET http://user.sock-shop/login (endp 31)
    const user_sock_shop = getHttpClient("http://user.sock-shop", authenticate);
    return user_sock_shop.fetch("/login")
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

        // GET http://carts.sock-shop/carts/{customerId}/items (endp 18)
        const carts_sock_shop = getHttpClient("http://carts.sock-shop", authenticate);
        return carts_sock_shop.fetch("/carts/" + customerId + "/items", {
            headers: {
                "accept": "application/json"
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

            // DELETE http://carts.sock-shop/carts/{customerId}/items/{itemId} (endp 34)
            return carts_sock_shop.fetch("/carts/" + customerId + "/items/" + itemId, {
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
});

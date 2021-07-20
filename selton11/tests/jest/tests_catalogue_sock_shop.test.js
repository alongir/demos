const authenticate = require("./authentication");
const {JSONPath, clearSession, dataset, getHttpClient, urlencode} = require("./up9lib");

describe.each(dataset("data/dataset_25.json"))("test_25_get_catalogue_id", (size) => {
    it("test_25_get_catalogue_id", () => {
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
                expect(JSONPath({
                    path: "$[*].tag[*]",
                    json: data
                })).not.toBeNull();
                const id = JSONPath({
                    path: "$[*].id",
                    json: data
                })[0];

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
                    expect(JSONPath({
                        path: "$.tag[*]",
                        json: data
                    })).not.toBeNull();
                });
            });
        });
    });
});

it("test_26_get_catalogue_size", () => {
    clearSession();

    // GET http://catalogue.sock-shop/catalogue/size (endp 26)
    const catalogue_sock_shop = getHttpClient("http://catalogue.sock-shop", authenticate);
    return catalogue_sock_shop.fetch("/catalogue/size" + urlencode([["tags", ""]]))
    .then((response) => {
        expect(response.status).toEqual(200);
        return response.text();
    })
    .then((text) => {
    })
    .then((data) => {
    });
});

const authenticate = require("./authentication");
const {JSONPath, clearSession, dataset, getHttpClient, urlencode} = require("./up9lib");

describe.each(dataset("data/dataset_35.json"))("test_035_get_catalogue_id", (size, tags) => {
    it("test_035_get_catalogue_id", () => {
        clearSession();

        // GET http://catalogue.sock-shop/catalogue (endp 2)
        const catalogue_sock_shop = getHttpClient("http://catalogue.sock-shop", authenticate);
        return catalogue_sock_shop.fetch("/catalogue" + urlencode([["page", "1"], ["size", size], ["sort", "id"], ["tags", tags]]))
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

            // GET http://catalogue.sock-shop/catalogue/{id} (endp 35)
            return catalogue_sock_shop.fetch("/catalogue/" + id)
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

describe.each(dataset("data/dataset_3.json"))("test_003_get_catalogue_size", (tags) => {
    it("test_003_get_catalogue_size", () => {
        clearSession();

        // GET http://catalogue.sock-shop/catalogue/size (endp 3)
        const catalogue_sock_shop = getHttpClient("http://catalogue.sock-shop", authenticate);
        return catalogue_sock_shop.fetch("/catalogue/size" + urlencode([["tags", tags]]))
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

it("test_005_get_tags", () => {
    clearSession();

    // GET http://catalogue.sock-shop/tags (endp 5)
    const catalogue_sock_shop = getHttpClient("http://catalogue.sock-shop", authenticate);
    return catalogue_sock_shop.fetch("/tags")
    .then((response) => {
        expect(response.status).toEqual(200);
        return response.text();
    })
    .then((text) => {
    })
    .then((data) => {
    });
});

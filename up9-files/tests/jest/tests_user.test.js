const authenticate = require("./authentication");
const {JSONPath, clearSession, dataset, getHttpTarget} = require("./up9lib");

describe.each(dataset("data/dataset_1.json"))("test_01_get_customers_id", (id) => {
    it("test_01_get_customers_id", () => {
        clearSession();

        // GET http://user/customers/{id} (endp 1)
        const user = getHttpTarget("TARGET_USER", authenticate);
        return user.fetch("/customers/" + id)
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

describe.each(dataset("data/dataset_2.json"))("test_02_get_customers_id_addresses", (id) => {
    it("test_02_get_customers_id_addresses", () => {
        clearSession();

        // GET http://user/customers/{id}/addresses (endp 2)
        const user = getHttpTarget("TARGET_USER", authenticate);
        return user.fetch("/customers/" + id + "/addresses")
        .then((response) => {
            expect(response.status).toEqual(200);
            return response.text();
        })
        .then((text) => {
            return JSON.parse(text);
        })
        .then((data) => {
            expect(JSONPath({
                path: "$._embedded.address.[*].city",
                json: data
            })).toContain("Glasgow");
        });
    });
});

describe.each(dataset("data/dataset_3.json"))("test_03_get_customers_id_cards", (id) => {
    it("test_03_get_customers_id_cards", () => {
        clearSession();

        // GET http://user/customers/{id}/cards (endp 3)
        const user = getHttpTarget("TARGET_USER", authenticate);
        return user.fetch("/customers/" + id + "/cards")
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

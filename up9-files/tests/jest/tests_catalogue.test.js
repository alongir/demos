const authenticate = require("./authentication");
const {JSONPath, clearSession, dataset, getHttpTarget, urlencode} = require("./up9lib");

describe.each(dataset("data/dataset_6.json"))("test_06_get_catalogue_id", (size, tags) => {
    it("test_06_get_catalogue_id", () => {
        clearSession();

        // GET http://catalogue/catalogue (endp 5)
        const catalogue = getHttpTarget("TARGET_CATALOGUE", authenticate);
        return catalogue.fetch("/catalogue" + urlencode([["size", size], ["sort", "id"], ["tags", tags]]))
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

            // GET http://catalogue/catalogue/{id} (endp 6)
            return catalogue.fetch("/catalogue/" + id)
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

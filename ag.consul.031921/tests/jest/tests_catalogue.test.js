const authenticate = require("./authentication");
const {JSONPath, clearSession, dataset, getHttpTarget, urlencode} = require("./up9lib");

describe.each(dataset("data/dataset_27.json"))("test_27_get_catalogue_id", (size) => {
    it("test_27_get_catalogue_id", () => {
        clearSession();

        // GET http://catalogue/tags (endp 30)
        const catalogue = getHttpTarget("TARGET_CATALOGUE", authenticate);
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
                const id = JSONPath({
                    path: "$[*].id",
                    json: data
                })[0];

                // GET http://catalogue/catalogue/{id} (endp 27)
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
});

describe.each(dataset("data/dataset_28.json"))("test_28_get_catalogue_size", (tags) => {
    it("test_28_get_catalogue_size", () => {
        clearSession();

        // GET http://catalogue/catalogue/size (endp 28)
        const catalogue = getHttpTarget("TARGET_CATALOGUE", authenticate);
        return catalogue.fetch("/catalogue/size" + urlencode([["tags", tags]]))
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

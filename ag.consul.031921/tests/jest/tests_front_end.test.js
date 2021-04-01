const authenticate = require("./authentication");
const {clearSession, dataset, getHttpClient} = require("./up9lib");

describe.each(dataset("data/dataset_13.json"))("test_13_get_catalogue_id", (id) => {
    it("test_13_get_catalogue_id", () => {
        clearSession();

        // GET http://front-end/catalogue/{id} (endp 13)
        const front_end = getHttpClient("http://front-end", authenticate);
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
        });
    });
});

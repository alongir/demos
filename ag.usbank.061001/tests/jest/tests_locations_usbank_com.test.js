const authenticate = require("./authentication");
const {CSSselect, clearSession, dataset, getHttpClient} = require("./up9lib");

describe.each(dataset("data/dataset_40.json"))("test_40_get_param", (param) => {
    it("test_40_get_param", () => {
        clearSession();

        // GET https://locations.usbank.com/{param} (endp 40)
        const locations_usbank_com = getHttpClient("https://locations.usbank.com", authenticate);
        return locations_usbank_com.fetch("/" + param)
        .then((response) => {
            expect(response.status).toEqual(200);
            return response.text();
        })
        .then((text) => {
            expect(CSSselect("html head title", text)).toContain("Location Search");
        })
        .then((data) => {
        });
    });
});

it("test_39_get_search_html", () => {
    clearSession();

    // GET https://locations.usbank.com/search.html (endp 39)
    const locations_usbank_com = getHttpClient("https://locations.usbank.com", authenticate);
    return locations_usbank_com.fetch("/search.html")
    .then((response) => {
        expect(response.status).toEqual(200);
        return response.text();
    })
    .then((text) => {
        expect(CSSselect("html head title", text)).toContain("Location Search");
    })
    .then((data) => {
    });
});

describe.each(dataset("data/dataset_41.json"))("test_41_get_search_param", (param) => {
    it("test_41_get_search_param", () => {
        clearSession();

        // GET https://locations.usbank.com/search/{param} (endp 41)
        const locations_usbank_com = getHttpClient("https://locations.usbank.com", authenticate);
        return locations_usbank_com.fetch("/search/" + param, {
            headers: {
                "x-requested-with": "XMLHttpRequest"
            }
        })
        .then((response) => {
            expect(response.status).toEqual(200);
            return response.text();
        })
        .then((text) => {
            expect(CSSselect("div#searchRest div.searchResultsLocations div.cq-dd-pages h1.hidden-sm.hidden-xs", text)).toContain("Search Results");
        })
        .then((data) => {
        });
    });
});

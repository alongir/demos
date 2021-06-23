const authenticate = require("./authentication");
const {CSSselect, clearSession, getHttpClient, urlencode} = require("./up9lib");

it("test_73_get_", () => {
    clearSession();

    // GET https://locator.chase.com/ (endp 73)
    const locator_chase_com = getHttpClient("https://locator.chase.com", authenticate);
    return locator_chase_com.fetch("/" + urlencode([["locale", "en_US"]]))
    .then((response) => {
        expect(response.status).toEqual(200);
        return response.text();
    })
    .then((text) => {
        expect(CSSselect("div#hero-label h1", text)).toContain("Find a Chase ATM or branch near you");
        expect(CSSselect("html head title", text)).toContain("Find a Chase ATM or branch near you | Chase Bank");
    })
    .then((data) => {
    });
});

it("test_74_get_adspace", () => {
    clearSession();

    // GET https://locator.chase.com/adspace (endp 74)
    const locator_chase_com = getHttpClient("https://locator.chase.com", authenticate);
    return locator_chase_com.fetch("/adspace")
    .then((response) => {
        expect(response.status).toEqual(200);
        return response.text();
    })
    .then((text) => {
    })
    .then((data) => {
    });
});

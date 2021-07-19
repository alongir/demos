const authenticate = require("./authentication");
const {CSSselect, clearSession, getHttpClient, urlPart, urlencode} = require("./up9lib");

it("test_066_get_cash_back_credit_cards", () => {
    clearSession();

    // GET https://www.chase.com/ (endp 1)
    const www_chase_com = getHttpClient("https://www.chase.com", authenticate);
    return www_chase_com.fetch("/")
    .then((response) => {
        expect(response.status).toEqual(200);
        return response.text();
    })
    .then((text) => {
        expect(CSSselect("main#main h1.accessible-text", text)).toContain("Chase.com home");
        expect(CSSselect("html head title", text)).toContain("Credit Card, Mortgage, Banking, Auto | Chase Online | Chase.com");
        const CELL = urlPart("?CELL", CSSselect("div div header.header-navigation section.desktop-header section.header-navigation__content.row section nav ul li div.header-navigation__dropdown.hide ul li.colored a.regular-link.chaseanalytics-track-link[href] @href", response).text().trim());
    })
    .then((data) => {
        // GET https://creditcards.chase.com/cash-back-credit-cards (endp 66)
        const creditcards_chase_com = getHttpClient("https://creditcards.chase.com", authenticate);
        return creditcards_chase_com.fetch("/cash-back-credit-cards" + urlencode([["CELL", CELL], ["jp_ltg", "chsecate_cashback"]]))
        .then((response) => {
            expect(response.status).toEqual(200);
            return response.text();
        })
        .then((text) => {
            expect(CSSselect("div#jsEnabled div.compatibility footer.footer div.row.content-section div.d-none.container div.section h3.placeholder-hidden", text)).toContain("Credit Cards");
            expect(CSSselect("html head title", text)).toContain("Compare Cash Back Credit Cards | Chase");
        })
        .then((data) => {
        });
    });
});

it("test_065_get_cash_back_credit_cards_freedom_flex", () => {
    clearSession();

    // GET https://www.chase.com/ (endp 1)
    const www_chase_com = getHttpClient("https://www.chase.com", authenticate);
    return www_chase_com.fetch("/")
    .then((response) => {
        expect(response.status).toEqual(200);
        return response.text();
    })
    .then((text) => {
        expect(CSSselect("main#main h1.accessible-text", text)).toContain("Chase.com home");
        expect(CSSselect("html head title", text)).toContain("Credit Card, Mortgage, Banking, Auto | Chase Online | Chase.com");
        const CELL = urlPart("?CELL", CSSselect("div div header.header-navigation section.desktop-header section.header-navigation__content.row section nav ul li div.header-navigation__dropdown.hide ul li.colored a.regular-link.chaseanalytics-track-link[href] @href", response).text().trim());
    })
    .then((data) => {
        // GET https://creditcards.chase.com/cash-back-credit-cards/freedom/flex (endp 65)
        const creditcards_chase_com = getHttpClient("https://creditcards.chase.com", authenticate);
        return creditcards_chase_com.fetch("/cash-back-credit-cards/freedom/flex" + urlencode([["CELL", CELL]]))
        .then((response) => {
            expect(response.status).toEqual(200);
            return response.text();
        })
        .then((text) => {
            expect(CSSselect("div#leftPanel div.left-rail-inner.clearfix div.no-padding.left-rail-header.freedomflex h1.card-title sup.sm-fix", text)).toContain("SM");
            expect(CSSselect("html head title", text)).toContain("Chase Freedom Flex Credit Card | Chase.com");
        })
        .then((data) => {
        });
    });
});

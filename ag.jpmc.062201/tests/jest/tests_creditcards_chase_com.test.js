const authenticate = require("./authentication");
const {CSSselect, clearSession, dataset, getHttpClient, urlencode} = require("./up9lib");

describe.each(dataset("data/dataset_66.json"))("test_66_get_cash_back_credit_cards", (CELL) => {
    it("test_66_get_cash_back_credit_cards", () => {
        clearSession();

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

describe.each(dataset("data/dataset_65.json"))("test_65_get_cash_back_credit_cards_freedom_flex", (CELL) => {
    it("test_65_get_cash_back_credit_cards_freedom_flex", () => {
        clearSession();

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

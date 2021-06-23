const authenticate = require("./authentication");
const {CSSselect, clearSession, dataset, getHttpClient, urlencode} = require("./up9lib");

describe.each(dataset("data/dataset_82.json"))("test_82_get_auto_finance_auto_loans", (offercode) => {
    it("test_82_get_auto_finance_auto_loans", () => {
        clearSession();

        // GET https://autofinance.chase.com/auto-finance/auto-loans (endp 82)
        const autofinance_chase_com = getHttpClient("https://autofinance.chase.com", authenticate);
        return autofinance_chase_com.fetch("/auto-finance/auto-loans" + urlencode([["offercode", offercode]]))
        .then((response) => {
            expect(response.status).toEqual(200);
            return response.text();
        })
        .then((text) => {
            expect(CSSselect("div#masthead div.finance-masthead.bkgd-image div.masthead-header.container div.row div.headline.text-left h1", text)).toContain("Finance a car");
        })
        .then((data) => {
        });
    });
});

describe.each(dataset("data/dataset_83.json"))("test_83_get_auto_finance_home", (offercode) => {
    it("test_83_get_auto_finance_home", () => {
        clearSession();

        // GET https://autofinance.chase.com/auto-finance/home (endp 83)
        const autofinance_chase_com = getHttpClient("https://autofinance.chase.com", authenticate);
        return autofinance_chase_com.fetch("/auto-finance/home" + urlencode([["offercode", offercode]]))
        .then((response) => {
            expect(response.status).toEqual(200);
            return response.text();
        })
        .then((text) => {
            expect(CSSselect("div#masthead div.home-masthead.bkgd-image div.masthead-header.container div.row div.headline.text-left h1", text)).toContain("It's your road, choose where to go.");
        })
        .then((data) => {
        });
    });
});

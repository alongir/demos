const authenticate = require("./authentication");
const {CSSselect, clearSession, dataset, getHttpClient} = require("./up9lib");

describe.each(dataset("data/dataset_69.json"))("test_69_get_connect_param", (param) => {
    it("test_69_get_connect_param", () => {
        clearSession();

        // GET https://cardrewards.usbank.com/connect/{param} (endp 69)
        const cardrewards_usbank_com = getHttpClient("https://cardrewards.usbank.com", authenticate);
        return cardrewards_usbank_com.fetch("/connect/" + param)
        .then((response) => {
            expect(response.status).toEqual(200);
            return response.text();
        })
        .then((text) => {
            expect(CSSselect("html head title", text)).toContain("Rewards Calculator");
        })
        .then((data) => {
        });
    });
});

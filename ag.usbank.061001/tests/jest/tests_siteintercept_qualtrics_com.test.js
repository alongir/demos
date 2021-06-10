const authenticate = require("./authentication");
const {clearSession, dataset, getHttpClient, urlencode} = require("./up9lib");

describe.each(dataset("data/dataset_20.json"))("test_20_post_WRSiteInterceptEngine_", (BrandDC, Q_ASID, Q_CID, Q_CLIENTVERSION, Q_SIID, SurveyID) => {
    it("test_20_post_WRSiteInterceptEngine_", () => {
        clearSession();

        // POST https://siteintercept.qualtrics.com/WRSiteInterceptEngine/ (endp 20)
        const siteintercept_qualtrics_com = getHttpClient("https://siteintercept.qualtrics.com", authenticate);
        return siteintercept_qualtrics_com.fetch("/WRSiteInterceptEngine/" + urlencode([["Q_ASID", Q_ASID], ["Q_CID", Q_CID], ["Q_CLIENTTYPE", "web"], ["Q_CLIENTVERSION", Q_CLIENTVERSION], ["Q_Impress", "1"], ["Q_SIID", Q_SIID], ["r", String(Date.now())]]), {
            method: "POST",
            headers: {
                "content-type": "application/x-www-form-urlencoded"
            },
            body: new URLSearchParams({
                "BrandDC": BrandDC,
                "BrandID": "usbank",
                "SurveyID": SurveyID
            })
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

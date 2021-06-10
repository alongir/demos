const authenticate = require("./authentication");
const {CSSselect, JSONBuild, JSONPath, clearSession, dataset, getHttpClient} = require("./up9lib");

it("test_02_get_bank_accounts_checking_accounts_gold_checking_account_html", () => {
    clearSession();

    // GET https://www.usbank.com/bank-accounts/checking-accounts/gold-checking-account.html (endp 2)
    const www_usbank_com = getHttpClient("https://www.usbank.com", authenticate);
    return www_usbank_com.fetch("/bank-accounts/checking-accounts/gold-checking-account.html")
    .then((response) => {
        expect(response.status).toEqual(200);
        return response.text();
    })
    .then((text) => {
        expect(CSSselect("section.pubIns.productDetailsPage div div.bodyContent.container-fluid div.row div.bannerResponsiveGrid div div.aem-Grid div.banner.parbase.aem-GridColumn div.USBDesignSystem--Shield.USBHero div div.USBHero__Container.clearfix div.clearfix div.text div div.textContainer h1", text)).toContain("U.S. BANK GOLD CHECKING PACKAGE");
        expect(CSSselect("html head title", text)).toContain("Gold Checking account | Personal Checking account | U.S. Bank");
    })
    .then((data) => {
    });
});

it("test_03_get_index_html", () => {
    clearSession();

    // GET https://www.usbank.com/index.html (endp 3)
    const www_usbank_com = getHttpClient("https://www.usbank.com", authenticate);
    return www_usbank_com.fetch("/index.html")
    .then((response) => {
        expect(response.status).toEqual(200);
        return response.text();
    })
    .then((text) => {
        expect(CSSselect("html head title", text)).toContain("Consumer banking | Personal banking | U.S. Bank");
    })
    .then((data) => {
    });
});

describe.each(dataset("data/dataset_4.json"))("test_04_post_plpXRb_YlO_param1_param2_param3_param4_aEs_aeId", (aeId, param, param1, param2, param3, sensor_data) => {
    it("test_04_post_plpXRb_YlO_param1_param2_param3_param4_aEs_aeId", () => {
        clearSession();

        // POST https://www.usbank.com/plpXRb/YlO/{param1}/{param2}/{param3}/{param4}/aEs/{aeId} (endp 4)
        const www_usbank_com = getHttpClient("https://www.usbank.com", authenticate);
        return www_usbank_com.fetch("/plpXRb/YlO/" + param + "/" + param1 + "/" + param2 + "/" + param3 + "/aEs/" + aeId, {
            method: "POST",
            headers: {
                "content-type": "application/json"
            },
            body: JSONBuild("data/payload_for_endp_4.json", {
                "$.sensor_data": sensor_data
            })
        })
        .then((response) => {
            expect(response.status).toEqual(201);
            return response.text();
        })
        .then((text) => {
        })
        .then((data) => {
        });
    });
});

describe.each(dataset("data/dataset_5.json"))("test_05_post_svt_usbank_shield_fetchDisclosureContent", (businessLines, disclosureTitles) => {
    it("test_05_post_svt_usbank_shield_fetchDisclosureContent", () => {
        clearSession();

        // POST https://www.usbank.com/svt/usbank/shield/fetchDisclosureContent (endp 5)
        const www_usbank_com = getHttpClient("https://www.usbank.com", authenticate);
        return www_usbank_com.fetch("/svt/usbank/shield/fetchDisclosureContent", {
            method: "POST",
            headers: {
                "content-type": "application/x-www-form-urlencoded",
                "x-requested-with": "XMLHttpRequest"
            },
            body: new URLSearchParams({
                "businessLines": businessLines,
                "disclosureTitles": disclosureTitles
            })
        })
        .then((response) => {
            expect(response.status).toEqual(200);
            return response.text();
        })
        .then((text) => {
            return JSON.parse(text);
        })
        .then((data) => {
            expect(JSONPath({
                path: "$[*].status",
                json: data
            })).toContain("success");
        });
    });
});

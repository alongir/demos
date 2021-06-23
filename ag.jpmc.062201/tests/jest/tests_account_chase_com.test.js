const authenticate = require("./authentication");
const {CSSselect, clearSession, dataset, getHttpClient, urlencode} = require("./up9lib");

describe.each(dataset("data/dataset_58.json"))("test_58_get_consumer_banking_seo", (ssv_creativeid) => {
    it("test_58_get_consumer_banking_seo", () => {
        clearSession();

        // GET https://account.chase.com/consumer/banking/seo (endp 58)
        const account_chase_com = getHttpClient("https://account.chase.com", authenticate);
        return account_chase_com.fetch("/consumer/banking/seo" + urlencode([["jp_aid_a", ssv_creativeid], ["jp_aid_p", "retail_checking_hp/tile"]]))
        .then((response) => {
            expect(response.status).toEqual(200);
            return response.text();
        })
        .then((text) => {
            expect(CSSselect("div#leaving-modal div.leaving-modal-center div.leaving-modal-title h1", text)).toContain("You're Now Leaving Chase");
        })
        .then((data) => {
        });
    });
});

describe.each(dataset("data/dataset_59.json"))("test_59_post_consumer_banking_seo", (Payload_ContactPoint, Payload_ProductSelection, form_build_id, referingURL, ssv_creativeid) => {
    it("test_59_post_consumer_banking_seo", () => {
        clearSession();

        // POST https://account.chase.com/consumer/banking/seo (endp 59)
        const account_chase_com = getHttpClient("https://account.chase.com", authenticate);
        return account_chase_com.fetch("/consumer/banking/seo" + urlencode([["jp_aid_a", ssv_creativeid], ["jp_aid_p", "retail_checking_hp/tile"]]), {
            method: "POST",
            headers: {
                "content-type": "application/x-www-form-urlencoded",
                "x-requested-with": "XMLHttpRequest"
            },
            body: new URLSearchParams({
                "Payload_ContactPoint": Payload_ContactPoint,
                "Payload_IsDaoWithEmailSubmission": "false",
                "Payload_IsEmailSubmission": "true",
                "Payload_ProductSelection": Payload_ProductSelection,
                "Payload_ValidationMsg": "",
                "device_type": "Desktop",
                "form_build_id": form_build_id,
                "form_id": "email_my_coupon",
                "gclid": "",
                "optimizelyID": "default",
                "prd_link": "",
                "referingURL": referingURL,
                "v1stCookie": ""
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

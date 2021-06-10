const authenticate = require("./authentication");
const {CSSselect, JSONBuild, JSONPath, clearSession, dataset, getHttpClient} = require("./up9lib");

describe.each(dataset("data/dataset_73.json"))("test_73_get_param_v1_applications_environment", (param) => {
    it("test_73_get_param_v1_applications_environment", () => {
        clearSession();

        // GET https://onboarding.usbank.com/{param}/v1/applications/environment (endp 73)
        const onboarding_usbank_com = getHttpClient("https://onboarding.usbank.com", authenticate);
        return onboarding_usbank_com.fetch("/" + param + "/v1/applications/environment", {
            headers: {
                "x-requested-with": "XMLHttpRequest"
            }
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
                path: "$.name",
                json: data
            })).toContain("onboarding-application-intake");
        });
    });
});

describe.each(dataset("data/dataset_71.json"))("test_71_post_KrNCmr_param1_param2_param3_param4_param5_param6_param7", (param, param1, param2, param3, param4, param5, param6, sensor_data) => {
    it("test_71_post_KrNCmr_param1_param2_param3_param4_param5_param6_param7", () => {
        clearSession();

        // POST https://onboarding.usbank.com/KrNCmr/{param1}/{param2}/{param3}/{param4}/{param5}/{param6}/{param7} (endp 71)
        const onboarding_usbank_com = getHttpClient("https://onboarding.usbank.com", authenticate);
        return onboarding_usbank_com.fetch("/KrNCmr/" + param + "/" + param1 + "/" + param2 + "/" + param3 + "/" + param4 + "/" + param5 + "/" + param6, {
            method: "POST",
            headers: {
                "content-type": "application/json"
            },
            body: JSONBuild("data/payload_for_endp_71.json", {
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

describe.each(dataset("data/dataset_74.json"))("test_74_get_cards_cardId_param1_param2_intro", (cardId, param, param1) => {
    it("test_74_get_cards_cardId_param1_param2_intro", () => {
        clearSession();

        // GET https://onboarding.usbank.com/cards/{cardId}/{param1}/{param2}/intro (endp 74)
        const onboarding_usbank_com = getHttpClient("https://onboarding.usbank.com", authenticate);
        return onboarding_usbank_com.fetch("/cards/" + cardId + "/" + param + "/" + param1 + "/intro")
        .then((response) => {
            expect(response.status).toEqual(200);
            return response.text();
        })
        .then((text) => {
            expect(CSSselect("html head title", text)).toContain("U.S. Bank Credit Card");
        })
        .then((data) => {
        });
    });
});

describe.each(dataset("data/dataset_39.json"))("test_39_get_deposits_depositId_PI_start", (depositId) => {
    it("test_39_get_deposits_depositId_PI_start", () => {
        clearSession();

        // GET https://onboarding.usbank.com/deposits/{depositId}/PI/start (endp 39)
        const onboarding_usbank_com = getHttpClient("https://onboarding.usbank.com", authenticate);
        return onboarding_usbank_com.fetch("/deposits/" + depositId + "/PI/start")
        .then((response) => {
            expect(response.status).toEqual(200);
            return response.text();
        })
        .then((text) => {
            expect(CSSselect("html head title", text)).toContain("U.S. Bank Checking");
        })
        .then((data) => {
        });
    });
});

describe.each(dataset("data/dataset_75.json"))("test_75_post_proxies_v1_param", (icsLocationCode, offerID, param, sourceCode) => {
    it("test_75_post_proxies_v1_param", () => {
        clearSession();

        // POST https://onboarding.usbank.com/proxies/v1/{param} (endp 75)
        const onboarding_usbank_com = getHttpClient("https://onboarding.usbank.com", authenticate);
        return onboarding_usbank_com.fetch("/proxies/v1/" + param, {
            method: "POST",
            headers: {
                "content-type": "application/json",
                "x-requested-with": "XMLHttpRequest"
            },
            body: JSONBuild("data/payload_for_endp_75.json", {
                "$.icsLocationCode": icsLocationCode,
                "$.offerID": offerID,
                "$.sourceCode": sourceCode
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
                path: "$.ratesAndFeesResponse.productDetailData.productVariableData[*].productName",
                json: data
            })).toContain("U.S. Bank Altitude\u00c2\u00ae Connect Visa Signature\u00c2\u00ae Card");
        });
    });
});

describe.each(dataset("data/dataset_76.json"))("test_76_post_proxies_v1_oad_terms_and_conditions", (locationCode, offerId, sourceCode) => {
    it("test_76_post_proxies_v1_oad_terms_and_conditions", () => {
        clearSession();

        // POST https://onboarding.usbank.com/proxies/v1/oad/terms-and-conditions (endp 76)
        const onboarding_usbank_com = getHttpClient("https://onboarding.usbank.com", authenticate);
        return onboarding_usbank_com.fetch("/proxies/v1/oad/terms-and-conditions", {
            method: "POST",
            headers: {
                "content-type": "application/json",
                "x-requested-with": "XMLHttpRequest"
            },
            body: JSONBuild("data/payload_for_endp_76.json", {
                "$.locationCode": locationCode,
                "$.offerId": offerId,
                "$.sourceCode": sourceCode
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

describe.each(dataset("data/dataset_40.json"))("test_40_post_proxies_v1_validateAddress", (securitytoken, usAddress, zipCode) => {
    it("test_40_post_proxies_v1_validateAddress", () => {
        clearSession();

        // POST https://onboarding.usbank.com/proxies/v1/validateAddress (endp 40)
        const onboarding_usbank_com = getHttpClient("https://onboarding.usbank.com", authenticate);
        return onboarding_usbank_com.fetch("/proxies/v1/validateAddress", {
            method: "POST",
            headers: {
                "content-type": "application/json",
                "securitytoken": securitytoken,
                "x-requested-with": "X-Requested-With"
            },
            body: JSONBuild("data/payload_for_endp_40.json", {
                "$.customerAddress.usAddress.*": usAddress,
                "$.customerAddress.usAddress.zipCode": zipCode
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

const authenticate = require("./authentication");
const {CSSselect, JSONBuild, JSONPath, clearSession, dataset, getHttpClient} = require("./up9lib");

describe.each(dataset("data/dataset_38.json"))("test_38_post_param_v1_applications_applicationId_patch", (countriesOfCitizenship, email, lastName, monthlyHousingPayment, number, op, param, path, productCode) => {
    it("test_38_post_param_v1_applications_applicationId_patch", () => {
        clearSession();

        // POST https://onboarding.usbank.com/{param}/v1/applications (endp 37)
        const onboarding_usbank_com = getHttpClient("https://onboarding.usbank.com", authenticate);
        return onboarding_usbank_com.fetch("/" + param + "/v1/applications", {
            method: "POST",
            headers: {
                "content-type": "application/json",
                "x-requested-with": "X-Requested-With"
            },
            body: JSONBuild("data/payload_for_endp_37.json", {
                "$.products[*].productCode": productCode
            })
        })
        .then((response) => {
            expect(response.status).toEqual(201);
            const securitytoken = response.headers.get("securitytoken");
            return response.text();
        })
        .then((text) => {
            return JSON.parse(text);
        })
        .then((data) => {
            expect(JSONPath({
                path: "$.applicants[*].addresses.primary.country",
                json: data
            })).toContain("US");
            const applicationId = JSONPath({
                path: "$.applicationId",
                json: data
            })[0];

            // POST https://onboarding.usbank.com/{param}/v1/applications/{applicationId}/patch (endp 38)
            return onboarding_usbank_com.fetch("/" + param + "/v1/applications/" + applicationId + "/patch", {
                method: "POST",
                headers: {
                    "content-type": "application/json",
                    "securitytoken": securitytoken,
                    "x-requested-with": "X-Requested-With"
                },
                body: JSONBuild("data/payload_for_endp_38.json", {
                    "$[*].op": op,
                    "$[*].path": path,
                    "$[*].value.countriesOfCitizenship[*]": countriesOfCitizenship,
                    "$[*].value.lastName": lastName,
                    "$[*].value.monthlyHousingPayment": monthlyHousingPayment,
                    "$[*].value.number": number,
                    "$[*].value.personal.email": email
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
                    path: "$.applicants[*].addresses.primary.country",
                    json: data
                })).toContain("US");
            });
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

describe.each(dataset("data/dataset_40.json"))("test_40_post_proxies_v1_validateAddress", (param, productCode, usAddress, zipCode) => {
    it("test_40_post_proxies_v1_validateAddress", () => {
        clearSession();

        // POST https://onboarding.usbank.com/{param}/v1/applications (endp 37)
        const onboarding_usbank_com = getHttpClient("https://onboarding.usbank.com", authenticate);
        return onboarding_usbank_com.fetch("/" + param + "/v1/applications", {
            method: "POST",
            headers: {
                "content-type": "application/json",
                "x-requested-with": "X-Requested-With"
            },
            body: JSONBuild("data/payload_for_endp_37.json", {
                "$.products[*].productCode": productCode
            })
        })
        .then((response) => {
            expect(response.status).toEqual(201);
            const securitytoken = response.headers.get("securitytoken");
            return response.text();
        })
        .then((text) => {
            return JSON.parse(text);
        })
        .then((data) => {
            expect(JSONPath({
                path: "$.applicants[*].addresses.primary.country",
                json: data
            })).toContain("US");

            // POST https://onboarding.usbank.com/proxies/v1/validateAddress (endp 40)
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
                return JSON.parse(text);
            })
            .then((data) => {
                expect(JSONPath({
                    path: "$.address.city",
                    json: data
                })).toContain("NEWARK");
            });
        });
    });
});
